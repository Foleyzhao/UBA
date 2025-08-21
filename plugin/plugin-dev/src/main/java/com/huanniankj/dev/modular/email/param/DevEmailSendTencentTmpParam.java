package com.huanniankj.dev.modular.email.param;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 邮件发送——腾讯云TMP参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevEmailSendTencentTmpParam {

    /**
     * 发件人邮箱
     */
    @Schema(description = "管理控制台中配置的发信地址")
    @NotBlank(message = "sendAccount不能为空")
    private String sendAccount;

    /**
     * 接收人
     */
    @Schema(description = "预先创建且上传了收件人的收件人列表id")
    @NotBlank(message = "receiveAccounts不能为空")
    private String receiveAccounts;

    /**
     * 模板名
     */
    @Schema(description = "预先创建且通过审核的模板Id")
    @NotBlank(message = "templateName不能为空")
    private String templateName;

    /**
     * 邮件主题
     */
    @Schema(description = "邮件主题")
    @NotBlank(message = "subject不能为空")
    private String subject;

    /**
     * 发件人昵称
     */
    @Schema(description = "发件人昵称")
    private String sendUser;

    /**
     * 发送参数
     */
    @Schema(description = "预先创建且通过审核的模板的参数json")
    private String templateParam;

    /**
     * 附件列表
     */
    @Schema(description = "附件列表", hidden = true)
    List<JSONObject> attachmentList = CollectionUtil.newArrayList();
}
