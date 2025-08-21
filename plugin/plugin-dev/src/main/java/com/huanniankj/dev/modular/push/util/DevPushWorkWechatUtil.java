package com.huanniankj.dev.modular.push.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.dev.api.DevConfigApi;
import lombok.extern.slf4j.Slf4j;
import org.dromara.oa.api.OaSender;
import org.dromara.oa.comm.entity.Request;
import org.dromara.oa.comm.entity.Response;
import org.dromara.oa.comm.entity.WeTalkRequestArticle;
import org.dromara.oa.comm.enums.MessageType;
import org.dromara.oa.core.provider.factory.OaFactory;
import org.dromara.oa.core.provider.factory.ProviderFactoryHolder;
import org.dromara.oa.core.weTalk.config.WeTalkConfig;
import org.dromara.oa.core.weTalk.config.WeTalkFactory;

/**
 * 企业微信消息推送工具类
 *
 * @author happynewyear
 */
@Slf4j
public class DevPushWorkWechatUtil {

    private static OaSender oaSender;

    private static final String SYSTEM_PUSH_WORKWECHAT_TOKEN_ID_KEY = "SYSTEM_PUSH_WORKWECHAT_TOKEN_ID";

    static {
        ProviderFactoryHolder.registerFactory(WeTalkFactory.instance());
    }

    /**
     * 初始化操作的客户端
     */
    private static void initClient() {

        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);

        /* tokenId */
        String tokenId = devConfigApi.getValueByKey(SYSTEM_PUSH_WORKWECHAT_TOKEN_ID_KEY);

        if (ObjectUtil.isEmpty(tokenId)) {
            throw new CommonException("企业微信推送操作客户端未正确配置：tokenId为空");
        }

        WeTalkConfig weTalkConfig = new WeTalkConfig();
        weTalkConfig.setConfigId(tokenId);
        weTalkConfig.setTokenId(tokenId);
        OaFactory.createAndRegisterOaSender(weTalkConfig);
        oaSender = OaFactory.getSmsOaBlend(tokenId);
    }

    /**
     * 发送文本消息
     *
     * @param content   内容
     * @param noticeAll 是否通知所有人
     * @param phones    通知的用户手机号，英文逗号分割
     * @return 发送成功的回执信息
     */
    public static String pushWorkWechatText(String content, boolean noticeAll, String phones) {
        try {
            initClient();
            Request request = new Request();
            request.setContent(content);
            if (noticeAll) {
                request.setIsNoticeAll(true);
            } else {
                if (ObjectUtil.isNotEmpty(phones)) {
                    request.setPhoneList((StrUtil.split(phones, StrUtil.COMMA)));
                }
            }
            Response response = oaSender.sender(request, MessageType.WE_TALK_TEXT);
            if (!response.isSuccess()) {
                throw new CommonException("消息推送错误，返回内容：{}", JSONUtil.toJsonStr(response.getData()));
            }
            return JSONUtil.toJsonStr(response.getData());
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 发送MarkDown消息
     *
     * @param title   标题
     * @param content 内容
     * @return 发送成功的回执信息
     */
    public static String pushWorkWechatMarkdown(String title, String content) {
        try {
            initClient();
            Request request = new Request();
            request.setTitle(title);
            request.setContent(content);
            Response response = oaSender.sender(request, MessageType.WE_TALK_MARKDOWN);
            if (!response.isSuccess()) {
                throw new CommonException("消息推送错误，返回内容：{}", JSONUtil.toJsonStr(response.getData()));
            }
            return JSONUtil.toJsonStr(response.getData());
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 发送新闻消息
     *
     * @param title      标题
     * @param content    内容
     * @param picUrl     封面图片地址
     * @param messageUrl 消息链接地址
     * @return 发送成功的回执信息
     */
    public static String pushWorkWechatNews(String title, String content, String picUrl, String messageUrl) {
        try {
            initClient();
            Request request = new Request();
            WeTalkRequestArticle weTalkRequestArticle = new WeTalkRequestArticle();
            weTalkRequestArticle.setTitle(title);
            weTalkRequestArticle.setDescription(content);
            weTalkRequestArticle.setPicUrl(picUrl);
            weTalkRequestArticle.setUrl(messageUrl);
            request.setArticleList(CollectionUtil.newArrayList(weTalkRequestArticle));
            Response response = oaSender.sender(request, MessageType.WE_TALK_NEWS);
            if (!response.isSuccess()) {
                throw new CommonException("消息推送错误，返回内容：{}", JSONUtil.toJsonStr(response.getData()));
            }
            return JSONUtil.toJsonStr(response.getData());
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }
}
