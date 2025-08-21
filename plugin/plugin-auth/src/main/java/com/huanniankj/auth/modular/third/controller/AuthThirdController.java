package com.huanniankj.auth.modular.third.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.auth.modular.third.entity.AuthThirdUser;
import com.huanniankj.auth.modular.third.param.AuthThirdCallbackParam;
import com.huanniankj.auth.modular.third.param.AuthThirdRenderParam;
import com.huanniankj.auth.modular.third.param.AuthThirdUserPageParam;
import com.huanniankj.auth.modular.third.result.AuthThirdRenderResult;
import com.huanniankj.auth.modular.third.service.AuthThirdService;
import com.huanniankj.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import me.zhyd.oauth.model.AuthCallback;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 第三方登录控制器
 *
 * @author happynewyear
 */
@Tag(name = "三方登录控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 5)
@RestController
@Validated
public class AuthThirdController {

    @Resource
    private AuthThirdService authThirdService;

    /**
     * 第三方登录页面渲染
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "第三方登录页面渲染")
    @GetMapping("/auth/third/render")
    public CommonResult<AuthThirdRenderResult> render(@Valid AuthThirdRenderParam authThirdRenderParam) {
        return CommonResult.data(authThirdService.render(authThirdRenderParam));
    }

    /**
     * 第三方登录授权回调
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "第三方登录授权回调")
    @GetMapping("/auth/third/callback")
    public CommonResult<String> callback(@Valid AuthThirdCallbackParam authThirdCallbackParam,
                                         AuthCallback authCallback) {
        return CommonResult.data(authThirdService.callback(authThirdCallbackParam, authCallback));
    }

    /**
     * 获取三方用户分页
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "获取三方用户分页")
    @GetMapping("/auth/third/page")
    public CommonResult<Page<AuthThirdUser>> page(AuthThirdUserPageParam authThirdUserPageParam) {
        return CommonResult.data(authThirdService.page(authThirdUserPageParam));
    }
}
