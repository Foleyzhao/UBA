package com.huanniankj.mobile.modular.resource.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.mobile.modular.resource.entity.MobileButton;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonAddParam;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonEditParam;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonIdParam;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonPageParam;
import com.huanniankj.mobile.modular.resource.service.MobileButtonService;
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
 * 移动端按钮控制器
 *
 * @author happynewyear
 */
@Tag(name = "移动端按钮控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 3)
@RestController
@Validated
public class MobileButtonController {

    @Resource
    private MobileButtonService mobileButtonService;

    /**
     * 获取移动端按钮分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取移动端按钮分页")
    @GetMapping("/mobile/button/page")
    public CommonResult<Page<MobileButton>> page(MobileButtonPageParam mobileButtonPageParam) {
        return CommonResult.data(mobileButtonService.page(mobileButtonPageParam));
    }

    /**
     * 添加移动端按钮
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加移动端按钮")
    @CommonLog("添加移动端按钮")
    @PostMapping("/mobile/button/add")
    public CommonResult<String> add(@RequestBody @Valid MobileButtonAddParam mobileButtonAddParam) {
        mobileButtonService.add(mobileButtonAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑移动端按钮
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑移动端按钮")
    @CommonLog("编辑移动端按钮")
    @PostMapping("/mobile/button/edit")
    public CommonResult<String> edit(@RequestBody @Valid MobileButtonEditParam mobileButtonEditParam) {
        mobileButtonService.edit(mobileButtonEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除移动端按钮
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除移动端按钮")
    @CommonLog("删除移动端按钮")
    @PostMapping("/mobile/button/delete")
    public CommonResult<String> delete(
            @RequestBody @Valid @NotEmpty(message = "集合不能为空") List<MobileButtonIdParam> mobileButtonIdParamList) {
        mobileButtonService.delete(mobileButtonIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取移动端按钮详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取移动端按钮详情")
    @GetMapping("/mobile/button/detail")
    public CommonResult<MobileButton> detail(@Valid MobileButtonIdParam mobileButtonIdParam) {
        return CommonResult.data(mobileButtonService.detail(mobileButtonIdParam));
    }
}
