package com.huanniankj.dev.modular.push.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 推送消息——钉钉LINK参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevPushDingTalkLinkParam {

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
     * 图片url
     */
    @Schema(description = "图片url")
    @NotBlank(message = "picUrl不能为空")
    private String picUrl;

    /**
     * 跳转地址
     */
    @Schema(description = "跳转地址")
    @NotBlank(message = "messageUrl不能为空")
    private String messageUrl;

}
