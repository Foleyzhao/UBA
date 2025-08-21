package com.huanniankj.auth.modular.third.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 第三方登录授权结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AuthThirdRenderResult {

    /**
     * 授权地址
     */
    @Schema(description = "授权地址")
    private String authorizeUrl;

    /**
     * 状态码
     */
    @Schema(description = "状态码")
    private String state;
}
