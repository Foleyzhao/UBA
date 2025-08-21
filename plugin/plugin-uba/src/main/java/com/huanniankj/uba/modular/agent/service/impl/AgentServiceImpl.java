package com.huanniankj.uba.modular.agent.service.impl;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.uba.modular.agent.entity.Agent;
import com.huanniankj.uba.modular.agent.mapper.AgentMapper;
import com.huanniankj.uba.modular.agent.param.AgentIdParam;
import com.huanniankj.uba.modular.agent.param.AgentPageParam;
import com.huanniankj.uba.modular.agent.param.AgentReportParam;
import com.huanniankj.uba.modular.agent.service.AgentService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.huanniankj.uba.modular.agent.enums.AgentStatusEnum.OFF_LINE;
import static com.huanniankj.uba.modular.agent.enums.AgentStatusEnum.ON_LINE;

/**
 * 采集器（agent）服务接口实现类
 *
 * @author happynewyear
 */
@Slf4j
@Service
public class AgentServiceImpl extends ServiceImpl<AgentMapper, Agent> implements AgentService {

    /**
     * 采集器（agent）心跳缓存
     */
    private final Cache<String, Long> heartbeatCache = Caffeine.newBuilder()
            // 5分钟无心跳自动过期
            .expireAfterWrite(5, TimeUnit.MINUTES)
            .build();

    @Override
    public Page<Agent> page(AgentPageParam agentPageParam) {
        QueryWrapper<Agent> queryWrapper = new QueryWrapper<Agent>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(agentPageParam.getHostIp())) {
            queryWrapper.lambda().eq(Agent::getHostIp, agentPageParam.getHostIp());
        }
        if (ObjectUtil.isNotEmpty(agentPageParam.getPublicIp())) {
            queryWrapper.lambda().like(Agent::getPublicIp, agentPageParam.getPublicIp());
        }
        if (ObjectUtil.isNotEmpty(agentPageParam.getStatus())) {
            queryWrapper.lambda().like(Agent::getStatus, agentPageParam.getStatus());
        }
        if (ObjectUtil.isAllNotEmpty(agentPageParam.getSortField(), agentPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(agentPageParam.getSortOrder());
            queryWrapper.orderBy(true, agentPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(agentPageParam.getSortField()));
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<AgentIdParam> agentIdParamList) {
        this.removeByIds(CollStreamUtil.toList(agentIdParamList, AgentIdParam::getId));
    }

    @Override
    public Agent detail(AgentIdParam agentIdParam) {
        return this.queryEntity(agentIdParam.getId());
    }

    @Override
    public Agent queryEntity(String id) {
        Agent agent = this.getById(id);
        if (ObjectUtil.isEmpty(agent)) {
            throw new CommonException("采集器（agent）不存在，id值为：{}", id);
        }
        return agent;
    }

    @Override
    public Agent queryEntityByAgentId(String agentId) {
        QueryWrapper<Agent> queryWrapper = new QueryWrapper<Agent>().checkSqlInjection();
        queryWrapper.lambda().eq(Agent::getAgentId, agentId);
        return this.getOne(queryWrapper);
    }

    @Override
    public void reportAgent(AgentReportParam agentReportParam) {
        Agent agent = queryEntityByAgentId(agentReportParam.getAgentId());
        if (ObjectUtil.isEmpty(agent)) {
            // 新增
            agent = new Agent();
            agent.setAgentId(agentReportParam.getAgentId());
            switch (agentReportParam.getConfigName()) {
                case "os" -> agent.setOs(agentReportParam.getConfigValue());
                case "host_ip" ->
                        agent.setHostIp(StringUtils.substringBefore(agentReportParam.getConfigValue(),
                                " "));
                case "public_ip" -> agent.setPublicIp(agentReportParam.getConfigValue());
            }
            agent.setStatus(ON_LINE.getValue());
            this.save(agent);
        } else {
            // 上报信息（更新）
            switch (agentReportParam.getConfigName()) {
                case "os" -> agent.setOs(agentReportParam.getConfigValue());
                case "host_ip" ->
                        agent.setHostIp(StringUtils.substringBefore(agentReportParam.getConfigValue(),
                                " "));
                case "public_ip" -> agent.setPublicIp(agentReportParam.getConfigValue());
            }
            agent.setStatus(ON_LINE.getValue());
            this.updateById(agent);
        }
    }

    @Override
    public void updateHeartbeat(String agentId) {
        heartbeatCache.put(agentId, System.currentTimeMillis());
    }

    /**
     * 检查采集器（agent）是否在线
     */
    public boolean isOnline(String agentId) {
        Long lastHeartbeat = heartbeatCache.getIfPresent(agentId);
        return lastHeartbeat != null &&
                // 2分钟内有心跳
                (System.currentTimeMillis() - lastHeartbeat) < TimeUnit.MINUTES.toMillis(2);
    }

    /**
     * 获取所有采集器（agent）状态
     */
    public Map<String, Boolean> getAllStatus() {
        return heartbeatCache.asMap().entrySet().stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        e -> (System.currentTimeMillis() - e.getValue()) < TimeUnit.MINUTES.toMillis(2)
                ));
    }

    /**
     * 每分钟更新一次采集器状态
     */
    @Scheduled(fixedRate = 60_000)
    public void storageHeartbeat() {
        getAllStatus().forEach((agentId, online) -> {
            Agent agent = queryEntityByAgentId(agentId);
            if (!ObjectUtil.isEmpty(agent)) {
                if (!online) {
                    // 更新为离线
                    agent.setStatus(OFF_LINE.getValue());
                    this.updateById(agent);
                }
            } else {
                log.warn("Agent not exist, agentId:{}", agentId);
            }
        });
    }

}
