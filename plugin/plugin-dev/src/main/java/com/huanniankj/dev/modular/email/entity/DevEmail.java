package com.huanniankj.dev.modular.email.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 邮件实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("DEV_EMAIL")
public class DevEmail extends CommonEntity {

    /**
     * id
     */
    @Schema(description = "主键")
    private String id;

    /**
     * 邮件引擎
     */
    @Schema(description = "邮件引擎")
    private String engine;

    /**
     * 发件人邮箱
     */
    @Schema(description = "发件人邮箱")
    private String sendAccount;

    /**
     * 发件人昵称
     */
    @Schema(description = "发件人昵称")
    private String sendUser;

    /**
     * 接收人
     */
    @Schema(description = "接收人")
    private String receiveAccounts;

    /**
     * 邮件主题
     */
    @Schema(description = "邮件主题")
    private String subject;

    /**
     * 邮件正文
     */
    @Schema(description = "邮件正文")
    private String content;

    /**
     * 标签名
     */
    @Schema(description = "标签名")
    private String tagName;

    /**
     * 模板名
     */
    @Schema(description = "模板名")
    private String templateName;

    /**
     * 发送参数
     */
    @Schema(description = "发送参数")
    private String templateParam;

    /**
     * 回执信息
     */
    @Schema(description = "回执信息")
    private String receiptInfo;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
