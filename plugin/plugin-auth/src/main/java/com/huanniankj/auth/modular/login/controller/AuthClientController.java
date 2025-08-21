package com.huanniankj.auth.modular.login.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.auth.core.annotation.SaClientCheckLogin;
import com.huanniankj.auth.core.enums.SaClientTypeEnum;
import com.huanniankj.auth.core.pojo.SaBaseClientLoginUser;
import com.huanniankj.auth.core.util.StpClientUtil;
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
 * C端登录控制器
 *
 * @author happynewyear
 */
@Tag(name = "C端登录控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 1)
@RestController
@Validated
public class AuthClientController {

    @Resource
    private AuthService authService;

    /**
     * C端获取图片验证码
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "C端获取图片验证码")
    @GetMapping("/auth/c/getPicCaptcha")
    public CommonResult<AuthPicValidCodeResult> getPicCaptcha() {
        return CommonResult.data(authService.getPicCaptcha(SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端获取手机登录验证码
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "C端获取手机登录验证码")
    @GetMapping("/auth/c/getPhoneValidCode")
    public CommonResult<String> getPhoneValidCode(@Valid AuthGetPhoneValidCodeParam authGetPhoneValidCodeParam) {
        return CommonResult.data(authService.getPhoneValidCode(authGetPhoneValidCodeParam,
                SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端获取邮箱登录验证码
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "C端获取邮箱登录验证码")
    @GetMapping("/auth/c/getEmailValidCode")
    public CommonResult<String> getEmailValidCode(@Valid AuthGetEmailValidCodeParam authGetEmailValidCodeParam) {
        return CommonResult.data(authService.getEmailValidCode(authGetEmailValidCodeParam,
                SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端账号密码登录
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "C端账号密码登录")
    @PostMapping("/auth/c/doLogin")
    public CommonResult<String> doLogin(
            @RequestBody @Valid AuthAccountPasswordLoginParam authAccountPasswordLoginParam) {
        return CommonResult.data(authService.doLogin(authAccountPasswordLoginParam, SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端手机验证码登录
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "C端手机验证码登录")
    @PostMapping("/auth/c/doLoginByPhone")
    public CommonResult<String> doLoginByPhone(
            @RequestBody @Valid AuthPhoneValidCodeLoginParam authPhoneValidCodeLoginParam) {
        return CommonResult.data(authService.doLoginByPhone(authPhoneValidCodeLoginParam,
                SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端邮箱验证码登录
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "C端邮箱验证码登录")
    @PostMapping("/auth/c/doLoginByEmail")
    public CommonResult<String> doLoginByEmail(
            @RequestBody @Valid AuthEmailValidCodeLoginParam authEmailValidCodeLoginParam) {
        return CommonResult.data(authService.doLoginByEmail(authEmailValidCodeLoginParam,
                SaClientTypeEnum.C.getValue()));
    }

    /**
     * C端退出
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "C端退出")
    @SaClientCheckLogin
    @GetMapping("/auth/c/doLogout")
    public CommonResult<String> doLogout() {
        StpClientUtil.logout();
        return CommonResult.ok();
    }

    /**
     * C端获取用户信息
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "C端获取用户信息")
    @SaClientCheckLogin
    @GetMapping("/auth/c/getLoginUser")
    public CommonResult<SaBaseClientLoginUser> getLoginUser() {
        return CommonResult.data(authService.getClientLoginUser());
    }

    /**
     * C端注册
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "C端注册")
    @PostMapping("/auth/c/register")
    public CommonResult<String> register(@RequestBody @Valid AuthRegisterParam authRegisterParam) {
        authService.register(authRegisterParam, SaClientTypeEnum.C.getValue());
        return CommonResult.ok();
    }
}
