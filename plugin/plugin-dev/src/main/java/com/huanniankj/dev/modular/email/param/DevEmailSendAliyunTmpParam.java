package com.huanniankj.dev.modular.email.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 邮件发送——阿里云TMP参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevEmailSendAliyunTmpParam {

    /**
     * 发件人邮箱
     */
    @Schema(description = "管理控制台中配置的发信地址")
    @NotBlank(message = "sendAccount不能为空")
    private String sendAccount;

    /**
     * 接收人
     */
    @Schema(description = "预先创建且上传了收件人的收件人列表名称")
    @NotBlank(message = "receiveAccounts不能为空")
    private String receiveAccounts;

    /**
     * 模板名
     */
    @Schema(description = "预先创建且通过审核的模板名称")
    @NotBlank(message = "templateName不能为空")
    private String templateName;

    /**
     * 标签名
     */
    @Schema(description = "标签名")
    private String tagName;
}
