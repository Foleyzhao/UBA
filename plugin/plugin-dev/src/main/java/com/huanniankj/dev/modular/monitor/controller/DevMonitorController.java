package com.huanniankj.dev.modular.monitor.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.dev.modular.monitor.result.DevMonitorServerResult;
import com.huanniankj.dev.modular.monitor.service.DevMonitorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 监控控制器
 *
 * @author happynewyear
 */
@Tag(name = "监控控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 9)
@RestController
@Validated
public class DevMonitorController {

    @Resource
    private DevMonitorService devMonitorService;

    /**
     * 获取服务器监控信息
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取服务器监控信息")
    @GetMapping("/dev/monitor/serverInfo")
    public CommonResult<DevMonitorServerResult> serverInfo() {
        return CommonResult.data(devMonitorService.serverInfo());
    }

    /**
     * 获取服务器网络情况
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取服务器网络情况")
    @GetMapping("/dev/monitor/networkInfo")
    public CommonResult<DevMonitorServerResult> networkInfo() {
        return CommonResult.data(devMonitorService.networkInfo());
    }
}
