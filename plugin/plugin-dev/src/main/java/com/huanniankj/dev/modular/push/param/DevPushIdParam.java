package com.huanniankj.dev.modular.push.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 消息推送Id参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevPushIdParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;
}
