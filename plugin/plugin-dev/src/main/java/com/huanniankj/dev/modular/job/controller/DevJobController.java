package com.huanniankj.dev.modular.job.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.dev.modular.job.param.DevJobAddParam;
import com.huanniankj.dev.modular.job.param.DevJobEditParam;
import com.huanniankj.dev.modular.job.param.DevJobIdParam;
import com.huanniankj.dev.modular.job.param.DevJobListParam;
import com.huanniankj.dev.modular.job.param.DevJobPageParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.dev.modular.job.entity.DevJob;
import com.huanniankj.dev.modular.job.service.DevJobService;

import javax.validation.Valid;
import java.util.List;

/**
 * 定时任务控制器
 *
 * @author happynewyear
 */
@Tag(name = "定时任务控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 7)
@RestController
@Validated
public class DevJobController {

    @Resource
    private DevJobService devJobService;

    /**
     * 获取定时任务分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取定时任务分页")
    @GetMapping("/dev/job/page")
    public CommonResult<Page<DevJob>> page(DevJobPageParam devJobPageParam) {
        return CommonResult.data(devJobService.page(devJobPageParam));
    }

    /**
     * 获取定时任务列表
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取定时任务列表")
    @GetMapping("/dev/job/list")
    public CommonResult<List<DevJob>> list(DevJobListParam devJobListParam) {
        return CommonResult.data(devJobService.list(devJobListParam));
    }

    /**
     * 添加定时任务
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "添加定时任务")
    @CommonLog("添加定时任务")
    @PostMapping("/dev/job/add")
    public CommonResult<String> add(@RequestBody @Valid DevJobAddParam devJobAddParam) {
        devJobService.add(devJobAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑定时任务
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "编辑定时任务")
    @CommonLog("编辑定时任务")
    @PostMapping("/dev/job/edit")
    public CommonResult<String> edit(@RequestBody @Valid DevJobEditParam devJobEditParam) {
        devJobService.edit(devJobEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除定时任务
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "删除定时任务")
    @CommonLog("删除定时任务")
    @PostMapping("/dev/job/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                               List<DevJobIdParam> devJobIdParamList) {
        devJobService.delete(devJobIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取定时任务详情
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "获取定时任务详情")
    @GetMapping("/dev/job/detail")
    public CommonResult<DevJob> detail(@Valid DevJobIdParam devJobIdParam) {
        return CommonResult.data(devJobService.detail(devJobIdParam));
    }

    /**
     * 停止定时任务
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "停止定时任务")
    @CommonLog("停止定时任务")
    @PostMapping("/dev/job/stopJob")
    public CommonResult<String> stopJob(@RequestBody DevJobIdParam devJobIdParam) {
        devJobService.stopJob(devJobIdParam);
        return CommonResult.ok();
    }

    /**
     * 运行定时任务
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "运行定时任务")
    @CommonLog("运行定时任务")
    @PostMapping("/dev/job/runJob")
    public CommonResult<String> runJob(@RequestBody @Valid DevJobIdParam devJobIdParam) {
        devJobService.runJob(devJobIdParam);
        return CommonResult.ok();
    }

    /**
     * 立即运行定时任务
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "立即运行定时任务")
    @CommonLog("立即运行定时任务")
    @PostMapping("/dev/job/runJobNow")
    public CommonResult<String> runJobNow(@RequestBody @Valid DevJobIdParam devJobIdParam) {
        devJobService.runJobNow(devJobIdParam);
        return CommonResult.ok();
    }

    /**
     * 获取定时任务类
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "获取定时任务类")
    @GetMapping("/dev/job/getActionClass")
    public CommonResult<List<String>> getActionClass() {
        return CommonResult.data(devJobService.getActionClass());
    }
}
