package com.huanniankj.dev.modular.email.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.email.entity.DevEmail;
import com.huanniankj.dev.modular.email.param.DevEmailIdParam;
import com.huanniankj.dev.modular.email.param.DevEmailPageParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendAliyunHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendAliyunTmpParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendAliyunTxtParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendDynamicHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendDynamicTxtParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendLocalHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendLocalTxtParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendTencentHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendTencentTmpParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendTencentTxtParam;

import java.util.List;

/**
 * 邮件服务接口
 *
 * @author happynewyear
 */
public interface DevEmailService extends IService<DevEmail> {

    /**
     * 动态发送TXT邮件
     *
     * @param engine          发送引擎
     * @param receiveAccounts 收件人邮箱，逗号拼接
     * @param subject         邮件主题
     * @param content         邮件内容
     */
    void sendDynamicTxt(String engine, String receiveAccounts, String subject, String content);

    /**
     * 动态发送HTML邮件
     *
     * @param engine          发送引擎
     * @param receiveAccounts 收件人邮箱，逗号拼接
     * @param subject         邮件主题
     * @param content         邮件内容
     */
    void sendDynamicHtml(String engine, String receiveAccounts, String subject, String content);

    /**
     * 动态发送TXT邮件
     */
    void sendDynamicTxt(DevEmailSendDynamicTxtParam devEmailSendDynamicTxtParam);

    /**
     * 动态发送HTML邮件
     */
    void sendDynamicHtml(DevEmailSendDynamicHtmlParam devEmailSendDynamicHtmlParam);

    /**
     * 发送邮件——本地TXT
     */
    void sendLocal(DevEmailSendLocalTxtParam devEmailSendLocalTxtParam);

    /**
     * 发送邮件——本地HTML
     */
    void sendLocal(DevEmailSendLocalHtmlParam devEmailSendLocalHtmlParam);

    /**
     * 发送邮件——阿里云TXT
     */
    void sendAliyun(DevEmailSendAliyunTxtParam devEmailSendAliyunTxtParam);

    /**
     * 发送邮件——阿里云HTML
     */
    void sendAliyun(DevEmailSendAliyunHtmlParam devEmailSendAliyunHtmlParam);

    /**
     * 发送邮件——阿里云TMP
     */
    void sendAliyun(DevEmailSendAliyunTmpParam devEmailSendAliyunTmpParam);

    /**
     * 发送邮件——腾讯云TXT
     */
    void sendTencent(DevEmailSendTencentTxtParam devEmailSendTencentTxtParam);

    /**
     * 发送邮件——腾讯云HTML
     */
    void sendTencent(DevEmailSendTencentHtmlParam devEmailSendTencentHtmlParam);

    /**
     * 发送邮件——腾讯云TMP
     */
    void sendTencent(DevEmailSendTencentTmpParam devEmailSendTencentTmpParam);

    /**
     * 获取邮件分页
     */
    Page<DevEmail> page(DevEmailPageParam devEmailPageParam);

    /**
     * 删除邮件
     */
    void delete(List<DevEmailIdParam> devEmailIdParamList);

    /**
     * 获取邮件详情
     */
    DevEmail detail(DevEmailIdParam devEmailIdParam);

    /**
     * 获取邮件详情
     */
    DevEmail queryEntity(String id);

}
