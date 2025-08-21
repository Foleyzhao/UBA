package com.huanniankj.dev.modular.push.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 推送消息——钉钉MARKDOWN参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevPushDingTalkMarkdownParam {

    /**
     * 消息标题
     */
    @Schema(description = "消息标题")
    @NotBlank(message = "title不能为空")
    private String title;

    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    @NotBlank(message = "content不能为空")
    private String content;

    /**
     * 通知类型（无、全部）
     */
    @Schema(description = "通知类型")
    @NotBlank(message = "noticeType不能为空")
    private String noticeType;
}
