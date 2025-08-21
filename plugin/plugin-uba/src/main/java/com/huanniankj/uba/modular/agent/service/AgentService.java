package com.huanniankj.uba.modular.agent.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.uba.modular.agent.entity.Agent;
import com.huanniankj.uba.modular.agent.param.AgentIdParam;
import com.huanniankj.uba.modular.agent.param.AgentPageParam;
import com.huanniankj.uba.modular.agent.param.AgentReportParam;

import java.util.List;

/**
 * 采集器（agent）服务接口
 *
 * @author happynewyear
 */
public interface AgentService extends IService<Agent> {

    /**
     * 获取采集器（agent）分页
     */
    Page<Agent> page(AgentPageParam agentPageParam);

    /**
     * 删除采集器（agent）
     */
    void delete(List<AgentIdParam> agentIdParamList);

    /**
     * 获取采集器（agent）详情
     */
    Agent detail(AgentIdParam agentIdParam);

    /**
     * 获取采集器（agent）详情
     */
    Agent queryEntity(String id);

    /**
     * 根据agentId获取采集器（agent）详情
     */
    Agent queryEntityByAgentId(String agentId);

    /**
     * 上报 agent 信息
     */
    void reportAgent(AgentReportParam agentReportParam);

    /**
     * 更新采集器（agent）心跳状态
     */
    void updateHeartbeat(String agentId);

}
