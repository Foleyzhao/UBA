package com.huanniankj.dev.modular.email.param;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * 邮件发送——本地HTML参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevEmailSendLocalHtmlParam {

    /**
     * 接收人
     */
    @Schema(description = "接收人邮箱地址，多个逗号拼接")
    @NotBlank(message = "receiveAccounts不能为空")
    private String receiveAccounts;

    /**
     * 邮件主题
     */
    @Schema(description = "邮件主题")
    @NotBlank(message = "subject不能为空")
    private String subject;

    /**
     * 邮件正文
     */
    @Schema(description = "邮件正文")
    @NotBlank(message = "content不能为空")
    private String content;

    /**
     * 图片展位符
     */
    @Schema(description = "图片展位符", hidden = true)
    private Map<String, InputStream> imageMap = MapUtil.newHashMap();

    /**
     * 附件列表
     */
    @Schema(description = "附件列表", hidden = true)
    private List<File> files = CollectionUtil.newArrayList();
}
