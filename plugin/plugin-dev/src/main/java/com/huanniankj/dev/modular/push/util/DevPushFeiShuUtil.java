package com.huanniankj.dev.modular.push.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.dromara.oa.api.OaSender;
import org.dromara.oa.comm.entity.Request;
import org.dromara.oa.comm.entity.Response;
import org.dromara.oa.comm.enums.MessageType;
import org.dromara.oa.core.byteTalk.config.ByteTalkConfig;
import org.dromara.oa.core.byteTalk.config.ByteTalkFactory;
import org.dromara.oa.core.provider.factory.OaFactory;
import org.dromara.oa.core.provider.factory.ProviderFactoryHolder;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.dev.api.DevConfigApi;

/**
 * 飞书消息推送工具类
 *
 * @author happynewyear
 */
@Slf4j
public class DevPushFeiShuUtil {

    private static OaSender oaSender;

    private static final String SYSTEM_PUSH_FEISHU_TOKEN_ID_KEY = "SYSTEM_PUSH_FEISHU_TOKEN_ID";

    static {
        ProviderFactoryHolder.registerFactory(ByteTalkFactory.instance());
    }

    /**
     * 初始化操作的客户端
     */
    private static void initClient() {

        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);

        /* tokenId */
        String tokenId = devConfigApi.getValueByKey(SYSTEM_PUSH_FEISHU_TOKEN_ID_KEY);

        if(ObjectUtil.isEmpty(tokenId)) {
            throw new CommonException("飞书推送操作客户端未正确配置：tokenId为空");
        }

        ByteTalkConfig byteTalkConfig = new ByteTalkConfig();
        byteTalkConfig.setConfigId(tokenId);
        byteTalkConfig.setTokenId(tokenId);
        OaFactory.createAndRegisterOaSender(byteTalkConfig);
        oaSender = OaFactory.getSmsOaBlend(tokenId);
    }

    /**
     * 发送文本消息
     *
     * @param content 内容
     * @param noticeAll 是否通知所有人
     * @return 发送成功的回执信息
     */
    public static String pushFeiShuText(String content, boolean noticeAll) {
        try {
            initClient();
            Request request = new Request();
            request.setContent(content);
            if(noticeAll) {
                request.setIsNoticeAll(true);
            }
            Response response = oaSender.sender(request, MessageType.BYTE_TALK_TEXT);
            if(!response.isSuccess()) {
                throw new CommonException("消息推送错误，返回内容：{}", JSONUtil.toJsonStr(response.getData()));
            }
            return JSONUtil.toJsonStr(response.getData());
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }
}
