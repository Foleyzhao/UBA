package com.huanniankj.dev.modular.password.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.dev.modular.password.entity.DevWeakPassword;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordAddParam;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordEditParam;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordIdParam;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordPageParam;
import com.huanniankj.dev.modular.password.service.DevWeakPasswordService;
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
 * 弱密码库控制器
 *
 * @author happynewyear
 */
@Tag(name = "弱密码库控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 2)
@RestController
@Validated
public class DevWeakPasswordController {

    @Resource
    private DevWeakPasswordService devWeakPasswordService;

    /**
     * 获取弱密码库分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取弱密码库分页")
    @GetMapping("/dev/weakPassword/page")
    public CommonResult<Page<DevWeakPassword>> page(DevWeakPasswordPageParam devWeakPasswordPageParam) {
        return CommonResult.data(devWeakPasswordService.page(devWeakPasswordPageParam));
    }

    /**
     * 添加弱密码库
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加弱密码库")
    @CommonLog("添加弱密码库")
    @PostMapping("/dev/weakPassword/add")
    public CommonResult<String> add(@RequestBody @Valid DevWeakPasswordAddParam devWeakPasswordAddParam) {
        devWeakPasswordService.add(devWeakPasswordAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑弱密码库
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑弱密码库")
    @CommonLog("编辑弱密码库")
    @PostMapping("/dev/weakPassword/edit")
    public CommonResult<String> edit(@RequestBody @Valid DevWeakPasswordEditParam devWeakPasswordEditParam) {
        devWeakPasswordService.edit(devWeakPasswordEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除弱密码库
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除弱密码库")
    @CommonLog("删除弱密码库")
    @PostMapping("/dev/weakPassword/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<DevWeakPasswordIdParam> devWeakPasswordIdParamList) {
        devWeakPasswordService.delete(devWeakPasswordIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取弱密码库详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取弱密码库详情")
    @GetMapping("/dev/weakPassword/detail")
    public CommonResult<DevWeakPassword> detail(@Valid DevWeakPasswordIdParam devWeakPasswordIdParam) {
        return CommonResult.data(devWeakPasswordService.detail(devWeakPasswordIdParam));
    }
}
