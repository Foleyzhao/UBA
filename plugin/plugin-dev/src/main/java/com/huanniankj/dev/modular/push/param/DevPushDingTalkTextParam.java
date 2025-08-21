package com.huanniankj.dev.modular.push.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 推送消息——钉钉TXT参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevPushDingTalkTextParam {

    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    @NotBlank(message = "content不能为空")
    private String content;

    /**
     * 通知类型（无、指定手机、全部）
     */
    @Schema(description = "通知类型")
    @NotBlank(message = "noticeType不能为空")
    private String noticeType;

    /**
     * 手机号
     */
    @Schema(description = "手机号")
    private String phones;
}
