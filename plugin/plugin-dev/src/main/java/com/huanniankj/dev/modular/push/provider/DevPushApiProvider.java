package com.huanniankj.dev.modular.push.provider;

import cn.hutool.core.util.ObjectUtil;
import com.huanniankj.dev.api.DevPushApi;
import com.huanniankj.dev.modular.push.enums.DevPushNoticeTypeEnum;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkLinkParam;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkMarkdownParam;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkTextParam;
import com.huanniankj.dev.modular.push.param.DevPushDynamicTextParam;
import com.huanniankj.dev.modular.push.param.DevPushFeiShuTextParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatMarkdownParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatNewsParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatTextParam;
import com.huanniankj.dev.modular.push.service.DevPushService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 消息推送API接口提供者
 *
 * @author happynewyear
 */
@Service
public class DevPushApiProvider implements DevPushApi {

    @Resource
    private DevPushService devPushService;

    @Override
    public void pushDynamicText(String content, boolean noticeAll) {
        DevPushDynamicTextParam dynamicTextParam = new DevPushDynamicTextParam();
        dynamicTextParam.setContent(content);
        dynamicTextParam.setNoticeAll(noticeAll);
        devPushService.pushDynamicText(dynamicTextParam);
    }

    @Override
    public void pushFeiShuText(String content, boolean noticeAll) {
        DevPushFeiShuTextParam pushFeiShuTextParam = new DevPushFeiShuTextParam();
        pushFeiShuTextParam.setContent(content);
        if (noticeAll) {
            pushFeiShuTextParam.setNoticeType(DevPushNoticeTypeEnum.ALL.getValue());
        } else {
            pushFeiShuTextParam.setNoticeType(DevPushNoticeTypeEnum.NONE.getValue());
        }
        devPushService.pushFeiShuText(pushFeiShuTextParam);
    }

    @Override
    public void pushDingTalkText(String content, boolean noticeAll, String phones) {
        DevPushDingTalkTextParam pushDingTalkTextParam = new DevPushDingTalkTextParam();
        pushDingTalkTextParam.setContent(content);
        if (noticeAll) {
            pushDingTalkTextParam.setNoticeType(DevPushNoticeTypeEnum.ALL.getValue());
        } else {
            if (ObjectUtil.isNotEmpty(phones)) {
                pushDingTalkTextParam.setNoticeType(DevPushNoticeTypeEnum.PHONE.getValue());
            } else {
                pushDingTalkTextParam.setNoticeType(DevPushNoticeTypeEnum.NONE.getValue());
            }
        }
        devPushService.pushDingTalkText(pushDingTalkTextParam);
    }

    @Override
    public void pushDingTalkMarkdown(String title, String content, boolean noticeAll) {
        DevPushDingTalkMarkdownParam devPushDingTalkMarkdownParam = new DevPushDingTalkMarkdownParam();
        devPushDingTalkMarkdownParam.setTitle(title);
        devPushDingTalkMarkdownParam.setContent(content);
        if (noticeAll) {
            devPushDingTalkMarkdownParam.setNoticeType(DevPushNoticeTypeEnum.ALL.getValue());
        } else {
            devPushDingTalkMarkdownParam.setNoticeType(DevPushNoticeTypeEnum.NONE.getValue());
        }
        devPushService.pushDingTalkMarkdown(devPushDingTalkMarkdownParam);
    }

    @Override
    public void pushDingTalkLink(String title, String content, String picUrl, String messageUrl) {
        DevPushDingTalkLinkParam devPushDingTalkLinkParam = new DevPushDingTalkLinkParam();
        devPushDingTalkLinkParam.setTitle(title);
        devPushDingTalkLinkParam.setContent(content);
        devPushDingTalkLinkParam.setPicUrl(picUrl);
        devPushDingTalkLinkParam.setMessageUrl(messageUrl);
        devPushService.pushDingTalkLink(devPushDingTalkLinkParam);
    }

    @Override
    public void pushWorkWechatText(String content, boolean noticeAll, String phones) {
        DevPushWorkWechatTextParam devPushWorkWechatTextParam = new DevPushWorkWechatTextParam();
        devPushWorkWechatTextParam.setContent(content);
        if (noticeAll) {
            devPushWorkWechatTextParam.setNoticeType(DevPushNoticeTypeEnum.ALL.getValue());
        } else {
            if (ObjectUtil.isNotEmpty(phones)) {
                devPushWorkWechatTextParam.setNoticeType(DevPushNoticeTypeEnum.PHONE.getValue());
            } else {
                devPushWorkWechatTextParam.setNoticeType(DevPushNoticeTypeEnum.NONE.getValue());
            }
        }
        devPushService.pushWorkWechatText(devPushWorkWechatTextParam);
    }

    @Override
    public void pushWorkWechatMarkdown(String title, String content) {
        DevPushWorkWechatMarkdownParam devPushWorkWechatMarkdownParam = new DevPushWorkWechatMarkdownParam();
        devPushWorkWechatMarkdownParam.setTitle(title);
        devPushWorkWechatMarkdownParam.setContent(content);
        devPushService.pushWorkWechatMarkdown(devPushWorkWechatMarkdownParam);
    }

    @Override
    public void pushWorkWechatNews(String title, String content, String picUrl, String messageUrl) {
        DevPushWorkWechatNewsParam devPushWorkWechatNewsParam = new DevPushWorkWechatNewsParam();
        devPushWorkWechatNewsParam.setTitle(title);
        devPushWorkWechatNewsParam.setContent(content);
        devPushWorkWechatNewsParam.setPicUrl(picUrl);
        devPushWorkWechatNewsParam.setMessageUrl(messageUrl);
        devPushService.pushWorkWechatNews(devPushWorkWechatNewsParam);
    }
}
