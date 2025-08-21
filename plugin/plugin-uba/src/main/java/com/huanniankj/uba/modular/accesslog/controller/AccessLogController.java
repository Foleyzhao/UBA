package com.huanniankj.uba.modular.accesslog.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.uba.modular.accesslog.entity.AccessLogCh;
import com.huanniankj.uba.modular.accesslog.param.AccessLogUuidParam;
import com.huanniankj.uba.modular.accesslog.param.AccessLogPageParam;
import com.huanniankj.uba.modular.accesslog.service.AccessLogService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 访问日志控制器
 *
 * @author happynewyear
 */
@Tag(name = "访问日志控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 2)
@RestController
@Validated
public class AccessLogController {

    @Resource
    private AccessLogService accessLogService;

    /**
     * 获取访问日志分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取访问日志分页")
    @GetMapping("/uba/accesslog/page")
    public CommonResult<Page<AccessLogCh>> page(AccessLogPageParam accessLogPageParam) {
        return CommonResult.data(accessLogService.page(accessLogPageParam));
    }

    /**
     * 删除访问日志
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除访问日志")
    @CommonLog("删除访问日志")
    @PostMapping("/uba/accesslog/delete")
    public CommonResult<String> delete(
            @RequestBody @Valid @NotEmpty(message = "集合不能为空") List<AccessLogUuidParam> accessLogUuidParams) {
        accessLogService.delete(accessLogUuidParams);
        return CommonResult.ok();
    }

    /**
     * 获取访问日志详情
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "获取访问日志详情")
    @GetMapping("/uba/accesslog/detail")
    public CommonResult<AccessLogCh> detail(@Valid AccessLogUuidParam accessLogUuidParam) {
        return CommonResult.data(accessLogService.detail(accessLogUuidParam));
    }

}
