package com.huanniankj.uba.modular.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.uba.modular.user.entity.User;
import com.huanniankj.uba.modular.user.enums.UserSourceEnum;
import com.huanniankj.uba.modular.user.param.UserAddParam;
import com.huanniankj.uba.modular.user.param.UserEditParam;
import com.huanniankj.uba.modular.user.param.UserIdParam;
import com.huanniankj.uba.modular.user.param.UserPageParam;
import com.huanniankj.uba.modular.user.service.UserService;
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
 * 运营用户控制器
 *
 * @author happynewyear
 */
@Tag(name = "运营用户控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 1)
@RestController
@Validated
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 获取运营用户分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取运营用户分页")
    @GetMapping("/uba/user/page")
    public CommonResult<Page<User>> page(UserPageParam userPageParam) {
        return CommonResult.data(userService.page(userPageParam));
    }

    /**
     * 添加运营用户
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加运营用户")
    @CommonLog("添加运营用户")
    @PostMapping("/uba/user/add")
    public CommonResult<String> add(@RequestBody @Valid UserAddParam userAddParam) {
        userService.add(userAddParam, UserSourceEnum.API_SYNC.getValue());
        return CommonResult.ok();
    }

    /**
     * 编辑运营用户
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑运营用户")
    @CommonLog("编辑运营用户")
    @PostMapping("/uba/user/edit")
    public CommonResult<String> edit(@RequestBody @Valid UserEditParam userEditParam) {
        userService.edit(userEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除运营用户
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除运营用户")
    @CommonLog("删除运营用户")
    @PostMapping("/uba/user/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<UserIdParam> userIdParamList) {
        userService.delete(userIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取运营用户详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取运营用户详情")
    @GetMapping("/uba/user/detail")
    public CommonResult<User> detail(@Valid UserIdParam userIdParam) {
        return CommonResult.data(userService.detail(userIdParam));
    }

    /**
     * 禁用运营用户
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "禁用运营用户")
    @CommonLog("禁用运营用户")
    @PostMapping("/uba/user/disableUser")
    public CommonResult<String> disableUser(@RequestBody UserIdParam userIdParam) {
        userService.disableUser(userIdParam);
        return CommonResult.ok();
    }

    /**
     * 启用运营用户
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "启用运营用户")
    @CommonLog("启用运营用户")
    @PostMapping("/uba/user/enableUser")
    public CommonResult<String> enableUser(@RequestBody @Valid UserIdParam userIdParam) {
        userService.enableUser(userIdParam);
        return CommonResult.ok();
    }

}
