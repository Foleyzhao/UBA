package com.huanniankj.dev.api;

/**
 * 邮件API接口
 *
 * @author happynewyear
 */
public interface DevPushApi {

    /**
     * 动态推送消息（使用系统配置的默认消息推送引擎）
     *
     * @param content 内容
     * @param noticeAll 是否通知所有人
     */
    void pushDynamicText(String content, boolean noticeAll);

    /**
     * 推送消息——飞书TXT
     *
     * @param content 内容
     * @param noticeAll 是否通知所有人
     */
    void pushFeiShuText(String content, boolean noticeAll);

    /**
     * 推送消息——钉钉TXT
     *
     * @param content 内容
     * @param noticeAll 是否通知所有人
     * @param phones 通知的用户手机号，英文逗号分割
     */
    void pushDingTalkText(String content, boolean noticeAll, String phones);

    /**
     * 推送消息——钉钉MARKDOWN
     *
     * @param title 标题
     * @param content 内容
     * @param noticeAll 是否通知所有人
     */
    void pushDingTalkMarkdown(String title, String content, boolean noticeAll);

    /**
     * 推送消息——钉钉LINK
     *
     * @param title 标题
     * @param content 内容
     * @param picUrl 封面图片地址
     * @param messageUrl 消息链接地址
     */
    void pushDingTalkLink(String title, String content, String picUrl,String messageUrl);

    /**
     * 推送消息——企业微信TXT
     *
     * @param content 内容
     * @param noticeAll 是否通知所有人
     * @param phones 通知的用户手机号，英文逗号分割
     */
    void pushWorkWechatText(String content, boolean noticeAll, String phones);

    /**
     * 推送消息——企业微信MARKDOWN
     *
     * @param title 标题
     * @param content 内容
     */
    void pushWorkWechatMarkdown(String title, String content);

    /**
     * 推送消息——企业微信NEWS
     *
     * @param title 标题
     * @param content 内容
     * @param picUrl 封面图片地址
     * @param messageUrl 消息链接地址
     */
    void pushWorkWechatNews(String title, String content, String picUrl,String messageUrl);
}
