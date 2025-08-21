package com.huanniankj.dev.modular.message.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 站内信发送参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevMessageSendParam {

    /**
     * 主题
     */
    @Schema(description = "主题")
    @NotBlank(message = "subject不能为空")
    private String subject;

    /**
     * 接收人id集合
     */
    @Schema(description = "接收人id集合")
    @NotEmpty(message = "receiverIdList不能为空")
    private List<String> receiverIdList;

    /**
     * 正文
     */
    @Schema(description = "正文")
    private String content;

    /**
     * 分类
     */
    @Schema(description = "分类", hidden = true)
    @NotBlank(message = "category不能为空")
    private String category;
}
