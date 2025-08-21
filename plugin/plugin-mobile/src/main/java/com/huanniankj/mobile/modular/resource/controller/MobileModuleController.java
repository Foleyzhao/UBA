package com.huanniankj.mobile.modular.resource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.mobile.modular.resource.entity.MobileModule;
import com.huanniankj.mobile.modular.resource.param.module.MobileModuleAddParam;
import com.huanniankj.mobile.modular.resource.param.module.MobileModuleEditParam;
import com.huanniankj.mobile.modular.resource.param.module.MobileModuleIdParam;
import com.huanniankj.mobile.modular.resource.param.module.MobileModulePageParam;
import com.huanniankj.mobile.modular.resource.service.MobileModuleService;
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
 * 移动端模块控制器
 *
 * @author happynewyear
 */
@Tag(name = "移动端模块控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 6)
@RestController
@Validated
public class MobileModuleController {

    @Resource
    private MobileModuleService mobileModuleService;

    /**
     * 获取移动端模块分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取移动端模块分页")
    @GetMapping("/mobile/module/page")
    public CommonResult<Page<MobileModule>> page(MobileModulePageParam mobileModulePageParam) {
        return CommonResult.data(mobileModuleService.page(mobileModulePageParam));
    }

    /**
     * 添加移动端模块
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加移动端模块")
    @CommonLog("添加移动端模块")
    @PostMapping("/mobile/module/add")
    public CommonResult<String> add(@RequestBody @Valid MobileModuleAddParam mobileModuleAddParam) {
        mobileModuleService.add(mobileModuleAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑移动端模块
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑移动端模块")
    @CommonLog("编辑移动端模块")
    @PostMapping("/mobile/module/edit")
    public CommonResult<String> edit(@RequestBody @Valid MobileModuleEditParam mobileModuleEditParam) {
        mobileModuleService.edit(mobileModuleEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除移动端模块
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除移动端模块")
    @CommonLog("删除移动端模块")
    @PostMapping("/mobile/module/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<MobileModuleIdParam> mobileModuleIdParamList) {
        mobileModuleService.delete(mobileModuleIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取移动端模块详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取移动端模块详情")
    @GetMapping("/mobile/module/detail")
    public CommonResult<MobileModule> detail(@Valid MobileModuleIdParam mobileModuleIdParam) {
        return CommonResult.data(mobileModuleService.detail(mobileModuleIdParam));
    }
}
