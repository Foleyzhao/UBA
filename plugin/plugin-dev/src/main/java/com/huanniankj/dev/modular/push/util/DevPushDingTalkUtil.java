package com.huanniankj.dev.modular.push.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.dromara.oa.api.OaSender;
import org.dromara.oa.comm.entity.Request;
import org.dromara.oa.comm.entity.Response;
import org.dromara.oa.comm.enums.MessageType;
import org.dromara.oa.core.dingTalk.config.DingTalkConfig;
import org.dromara.oa.core.dingTalk.config.DingTalkFactory;
import org.dromara.oa.core.provider.factory.OaFactory;
import org.dromara.oa.core.provider.factory.ProviderFactoryHolder;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.dev.api.DevConfigApi;

/**
 * 钉钉消息推送工具类
 *
 * @author happynewyear
 */
@Slf4j
public class DevPushDingTalkUtil {

    private static OaSender oaSender;

    private static final String SYSTEM_PUSH_DINGTALK_SIGN_KEY = "SYSTEM_PUSH_DINGTALK_SIGN";

    private static final String SYSTEM_PUSH_DINGTALK_TOKEN_ID_KEY = "SYSTEM_PUSH_DINGTALK_TOKEN_ID";

    static {
        ProviderFactoryHolder.registerFactory(DingTalkFactory.instance());
    }

    /**
     * 初始化操作的客户端
     */
    private static void initClient() {

        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);

        /* sign */
        String sign = devConfigApi.getValueByKey(SYSTEM_PUSH_DINGTALK_SIGN_KEY);

        if(ObjectUtil.isEmpty(sign)) {
            throw new CommonException("钉钉推送操作客户端未正确配置：sign为空");
        }

        /* tokenId */
        String tokenId = devConfigApi.getValueByKey(SYSTEM_PUSH_DINGTALK_TOKEN_ID_KEY);

        if(ObjectUtil.isEmpty(tokenId)) {
            throw new CommonException("钉钉推送操作客户端未正确配置：tokenId为空");
        }

        DingTalkConfig dingTalkConfig = new DingTalkConfig();
        dingTalkConfig.setConfigId(tokenId);
        dingTalkConfig.setSign(sign);
        dingTalkConfig.setTokenId(tokenId);
        OaFactory.createAndRegisterOaSender(dingTalkConfig);
        oaSender = OaFactory.getSmsOaBlend(tokenId);
    }

    /**
     * 发送文本消息
     *
     * @param content 内容
     * @param noticeAll 是否通知所有人
     * @param phones 通知的用户手机号，英文逗号分割
     * @return 发送成功的回执信息
     */
    public static String pushDingTalkText(String content, boolean noticeAll, String phones) {
        try {
            initClient();
            Request request = new Request();
            request.setContent(content);
            if(noticeAll) {
                request.setIsNoticeAll(true);
            } else {
                if(ObjectUtil.isNotEmpty(phones)) {
                    request.setPhoneList((StrUtil.split(phones, StrUtil.COMMA)));
                }
            }
            Response response = oaSender.sender(request, MessageType.DING_TALK_TEXT);
            if(!response.isSuccess()) {
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
     * @param title 标题
     * @param content 内容
     * @param noticeAll 是否通知所有人
     * @return 发送成功的回执信息
     */
    public static String pushDingTalkMarkdown(String title, String content, boolean noticeAll) {
        try {
            initClient();
            Request request = new Request();
            request.setTitle(title);
            request.setContent(content);
            if(noticeAll) {
                request.setIsNoticeAll(true);
            }
            Response response = oaSender.sender(request, MessageType.DING_TALK_MARKDOWN);
            if(!response.isSuccess()) {
                throw new CommonException("消息推送错误，返回内容：{}", JSONUtil.toJsonStr(response.getData()));
            }
            return JSONUtil.toJsonStr(response.getData());
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 发送链接消息
     *
     * @param title 标题
     * @param content 内容
     * @param picUrl 封面图片地址
     * @param messageUrl 消息链接地址
     * @return 发送成功的回执信息
     */
    public static String pushDingTalkLink(String title, String content, String picUrl,String messageUrl) {
        try {
            initClient();
            Request request = new Request();
            request.setTitle(title);
            request.setContent(content);
            request.setPicUrl(picUrl);
            request.setMessageUrl(messageUrl);
            Response response = oaSender.sender(request, MessageType.DING_TALK_LINK);
            if(!response.isSuccess()) {
                throw new CommonException("消息推送错误，返回内容：{}", JSONUtil.toJsonStr(response.getData()));
            }
            return JSONUtil.toJsonStr(response.getData());
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }
}
