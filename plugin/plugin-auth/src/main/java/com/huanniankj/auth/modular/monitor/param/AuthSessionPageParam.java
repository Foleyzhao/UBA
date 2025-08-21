package com.huanniankj.auth.modular.monitor.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 会话查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AuthSessionPageParam {

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String userId;
}
