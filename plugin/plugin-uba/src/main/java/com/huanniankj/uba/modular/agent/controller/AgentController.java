package com.huanniankj.uba.modular.agent.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.uba.modular.agent.entity.Agent;
import com.huanniankj.uba.modular.agent.param.AgentIdParam;
import com.huanniankj.uba.modular.agent.param.AgentPageParam;
import com.huanniankj.uba.modular.agent.param.AgentReportParam;
import com.huanniankj.uba.modular.agent.service.AgentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

/**
 * 采集器（agent）控制器
 *
 * @author happynewyear
 */
@Slf4j
@Tag(name = "采集器控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 1)
@RestController
@Validated
public class AgentController {

    @Resource
    private AgentService agentService;

    /**
     * 获取采集器（agent）分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取采集器分页")
    @GetMapping("/uba/agent/page")
    public CommonResult<Page<Agent>> page(AgentPageParam agentPageParam) {
        return CommonResult.data(agentService.page(agentPageParam));
    }

    /**
     * 删除采集器（agent）
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除采集器（agent）")
    @CommonLog("删除采集器")
    @PostMapping("/uba/agent/delete")
    public CommonResult<String> delete(
            @RequestBody @Valid @NotEmpty(message = "集合不能为空") List<AgentIdParam> agentIdParamList) {
        agentService.delete(agentIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取采集器（agent）详情
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "获取采集器（agent）详情")
    @GetMapping("/uba/agent/detail")
    public CommonResult<Agent> detail(@Valid AgentIdParam agentIdParam) {
        return CommonResult.data(agentService.detail(agentIdParam));
    }

    /**
     * 采集器（agent）上报信息
     */
    @SuppressWarnings("unchecked")
    @ApiOperationSupport(order = 4)
    @Operation(summary = "采集器（agent）上报信息")
    @PostMapping("/uba/agent/report")
    public CommonResult<String> report(@RequestBody List<Map<String, Object>> agentLogs) {
        agentLogs.forEach(agentLog -> {
            if (agentLog.get("command") != null) {
                log.info("Receive agent report: {}", agentLog);
                AgentReportParam agentReportParam = new AgentReportParam();
                Map<String, Object> tags = (Map<String, Object>) agentLog.get("tags");
                agentReportParam.setAgentId(tags.get("agent_id").toString());
                agentReportParam.setConfigName(tags.get("config_name").toString());
                agentReportParam.setConfigValue(agentLog.get("message").toString());
                agentService.reportAgent(agentReportParam);
            }
        });
        return CommonResult.ok();
    }

    /**
     * 采集器（agent）心跳
     */
    @SuppressWarnings("unchecked")
    @ApiOperationSupport(order = 5)
    @Operation(summary = "采集器（agent）心跳")
    @PostMapping("/uba/agent/heartbeat")
    public CommonResult<String> heartbeat(@RequestBody List<Map<String, Object>> agentLogs) {
        agentLogs.forEach(agentLog -> {
            if ("heartbeat".equals(agentLog.get("name"))) {
                Map<String, Object> tags = (Map<String, Object>) agentLog.get("tags");
                String agentId = tags.get("agent_id").toString();
                agentService.updateHeartbeat(agentId);
            }
        });
        return CommonResult.ok();
    }

}
