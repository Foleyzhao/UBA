package com.huanniankj.auth.modular.login.controller;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.stp.StpUtil;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.auth.core.enums.SaClientTypeEnum;
import com.huanniankj.auth.core.pojo.SaBaseLoginUser;
import com.huanniankj.auth.modular.login.param.AuthAccountPasswordLoginParam;
import com.huanniankj.auth.modular.login.param.AuthEmailValidCodeLoginParam;
import com.huanniankj.auth.modular.login.param.AuthGetEmailValidCodeParam;
import com.huanniankj.auth.modular.login.param.AuthGetPhoneValidCodeParam;
import com.huanniankj.auth.modular.login.param.AuthPhoneValidCodeLoginParam;
import com.huanniankj.auth.modular.login.param.AuthRegisterParam;
import com.huanniankj.auth.modular.login.result.AuthPicValidCodeResult;
import com.huanniankj.auth.modular.login.service.AuthService;
import com.huanniankj.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * B端登录控制器
 *
 * @author happynewyear
 */
@Tag(name = "B端登录控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 2)
@RestController
@Validated
public class AuthController {

    @Resource
    private AuthService authService;

    /**
     * B端获取图片验证码
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "B端获取图片验证码")
    @GetMapping("/auth/b/getPicCaptcha")
    public CommonResult<AuthPicValidCodeResult> getPicCaptcha() {
        return CommonResult.data(authService.getPicCaptcha(SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端获取手机登录验证码
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "B端获取手机登录验证码")
    @GetMapping("/auth/b/getPhoneValidCode")
    public CommonResult<String> getPhoneValidCode(@Valid AuthGetPhoneValidCodeParam authGetPhoneValidCodeParam) {
        return CommonResult.data(authService.getPhoneValidCode(authGetPhoneValidCodeParam, SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端获取邮箱登录验证码
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "B端获取邮箱登录验证码")
    @GetMapping("/auth/b/getEmailValidCode")
    public CommonResult<String> getEmailValidCode(@Valid AuthGetEmailValidCodeParam authGetEmailValidCodeParam) {
        return CommonResult.data(authService.getEmailValidCode(authGetEmailValidCodeParam,
                SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端账号密码登录
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "B端账号密码登录")
    @PostMapping("/auth/b/doLogin")
    public CommonResult<String> doLogin(
            @RequestBody @Valid AuthAccountPasswordLoginParam authAccountPasswordLoginParam) {
        return CommonResult.data(authService.doLogin(authAccountPasswordLoginParam, SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端手机验证码登录
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "B端手机验证码登录")
    @PostMapping("/auth/b/doLoginByPhone")
    public CommonResult<String> doLoginByPhone(
            @RequestBody @Valid AuthPhoneValidCodeLoginParam authPhoneValidCodeLoginParam) {
        return CommonResult.data(authService.doLoginByPhone(authPhoneValidCodeLoginParam,
                SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端邮箱验证码登录
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "B端邮箱验证码登录")
    @PostMapping("/auth/b/doLoginByEmail")
    public CommonResult<String> doLoginByEmail(
            @RequestBody @Valid AuthEmailValidCodeLoginParam authEmailValidCodeLoginParam) {
        return CommonResult.data(authService.doLoginByEmail(authEmailValidCodeLoginParam,
                SaClientTypeEnum.B.getValue()));
    }

    /**
     * B端退出
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "B端退出")
    @SaCheckLogin
    @GetMapping("/auth/b/doLogout")
    public CommonResult<String> doLogout() {
        StpUtil.logout();
        return CommonResult.ok();
    }

    /**
     * B端获取用户信息
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "B端获取用户信息")
    @SaCheckLogin
    @GetMapping("/auth/b/getLoginUser")
    public CommonResult<SaBaseLoginUser> getLoginUser() {
        return CommonResult.data(authService.getLoginUser());
    }

    /**
     * B端注册
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "B端注册")
    @PostMapping("/auth/b/register")
    public CommonResult<String> register(@RequestBody @Valid AuthRegisterParam authRegisterParam) {
        authService.register(authRegisterParam, SaClientTypeEnum.B.getValue());
        return CommonResult.ok();
    }
}
