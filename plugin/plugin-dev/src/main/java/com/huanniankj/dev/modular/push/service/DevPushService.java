package com.huanniankj.dev.modular.push.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.push.entity.DevPush;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkLinkParam;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkMarkdownParam;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkTextParam;
import com.huanniankj.dev.modular.push.param.DevPushDynamicTextParam;
import com.huanniankj.dev.modular.push.param.DevPushFeiShuTextParam;
import com.huanniankj.dev.modular.push.param.DevPushIdParam;
import com.huanniankj.dev.modular.push.param.DevPushPageParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatMarkdownParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatNewsParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatTextParam;

import java.util.List;

/**
 * 消息推送服务接口
 *
 * @author happynewyear
 */
public interface DevPushService extends IService<DevPush> {

    /**
     * 动态推送消息
     *
     * @param engine    推送引擎
     * @param content   文本内容
     * @param noticeAll 是否@所有人
     */
    void pushDynamicText(String engine, String content, boolean noticeAll);

    /**
     * 动态推送消息
     */
    void pushDynamicText(DevPushDynamicTextParam devPushDynamicTextParam);

    /**
     * 推送消息——飞书TXT
     */
    void pushFeiShuText(DevPushFeiShuTextParam devPushFeiShuTextParam);

    /**
     * 推送消息——钉钉TXT
     */
    void pushDingTalkText(DevPushDingTalkTextParam devPushDingTalkTextParam);

    /**
     * 推送消息——钉钉MARKDOWN
     */
    void pushDingTalkMarkdown(DevPushDingTalkMarkdownParam devPushDingTalkMarkdownParam);

    /**
     * 推送消息——钉钉LINK
     */
    void pushDingTalkLink(DevPushDingTalkLinkParam devPushDingTalkLinkParam);

    /**
     * 推送消息——企业微信TXT
     */
    void pushWorkWechatText(DevPushWorkWechatTextParam devPushWorkWechatTextParam);

    /**
     * 推送消息——企业微信MARKDOWN
     */
    void pushWorkWechatMarkdown(DevPushWorkWechatMarkdownParam devPushWorkWechatMarkdownParam);

    /**
     * 推送消息——企业微信NEWS
     */
    void pushWorkWechatNews(DevPushWorkWechatNewsParam devPushWorkWechatNewsParam);

    /**
     * 获取推送消息分页
     */
    Page<DevPush> page(DevPushPageParam devPushPageParam);

    /**
     * 删除推送消息
     */
    void delete(List<DevPushIdParam> devPushIdParamList);

    /**
     * 获取推送消息详情
     */
    DevPush detail(DevPushIdParam devPushIdParam);

    /**
     * 获取推送消息详情
     */
    DevPush queryEntity(String id);
}
