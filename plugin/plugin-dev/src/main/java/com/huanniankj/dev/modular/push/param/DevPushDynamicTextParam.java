package com.huanniankj.dev.modular.push.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 动态推送消息参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevPushDynamicTextParam {

    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    @NotBlank(message = "content不能为空")
    private String content;

    /**
     * 是否@所有人
     */
    @Schema(description = "是否@所有人")
    @NotNull(message = "noticeAll不能为空")
    private Boolean noticeAll = false;
}
