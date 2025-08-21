package com.huanniankj.sys.modular.resource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.sys.modular.resource.entity.SysButton;
import com.huanniankj.sys.modular.resource.param.button.SysButtonAddParam;
import com.huanniankj.sys.modular.resource.param.button.SysButtonEditParam;
import com.huanniankj.sys.modular.resource.param.button.SysButtonIdParam;
import com.huanniankj.sys.modular.resource.param.button.SysButtonPageParam;
import com.huanniankj.sys.modular.resource.service.SysButtonService;
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
 * 按钮控制器
 *
 * @author happynewyear
 */
@Tag(name = "按钮控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 3)
@RestController
@Validated
public class SysButtonController {

    @Resource
    private SysButtonService sysButtonService;

    /**
     * 获取按钮分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取按钮分页")
    @GetMapping("/sys/button/page")
    public CommonResult<Page<SysButton>> page(SysButtonPageParam sysButtonPageParam) {
        return CommonResult.data(sysButtonService.page(sysButtonPageParam));
    }

    /**
     * 添加按钮
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加按钮")
    @CommonLog("添加按钮")
    @PostMapping("/sys/button/add")
    public CommonResult<String> add(@RequestBody @Valid SysButtonAddParam sysButtonAddParam) {
        sysButtonService.add(sysButtonAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑按钮
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑按钮")
    @CommonLog("编辑按钮")
    @PostMapping("/sys/button/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysButtonEditParam sysButtonEditParam) {
        sysButtonService.edit(sysButtonEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除按钮
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除按钮")
    @CommonLog("删除按钮")
    @PostMapping("/sys/button/delete")
    public CommonResult<String> delete(
            @RequestBody @Valid @NotEmpty(message = "集合不能为空") List<SysButtonIdParam> sysButtonIdParamList) {
        sysButtonService.delete(sysButtonIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取按钮详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取按钮详情")
    @GetMapping("/sys/button/detail")
    public CommonResult<SysButton> detail(@Valid SysButtonIdParam sysButtonIdParam) {
        return CommonResult.data(sysButtonService.detail(sysButtonIdParam));
    }

}
