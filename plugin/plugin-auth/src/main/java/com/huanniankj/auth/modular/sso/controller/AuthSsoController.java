package com.huanniankj.auth.modular.sso.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.auth.core.enums.SaClientTypeEnum;
import com.huanniankj.auth.modular.sso.param.AuthSsoTicketLoginParam;
import com.huanniankj.auth.modular.sso.service.AuthSsoService;
import com.huanniankj.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 单点登录控制器
 *
 * @author happynewyear
 */
@Tag(name = "单点登录控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 4)
@RestController
@Validated
public class AuthSsoController {

    @Resource
    private AuthSsoService authSsoService;

    /**
     * 根据ticket执行单点登录
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "根据ticket执行单点登录")
    @PostMapping("/auth/sso/doLogin")
    public CommonResult<String> doLogin(@RequestBody @Valid AuthSsoTicketLoginParam authAccountPasswordLoginParam) {
        return CommonResult.data(authSsoService.doLogin(authAccountPasswordLoginParam, SaClientTypeEnum.B.getValue()));
    }
}
