package com.huanniankj.dev.modular.sms.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.dev.api.DevConfigApi;
import lombok.extern.slf4j.Slf4j;
import org.dromara.sms4j.aliyun.config.AlibabaConfig;
import org.dromara.sms4j.api.SmsBlend;
import org.dromara.sms4j.api.entity.SmsResponse;
import org.dromara.sms4j.core.factory.SmsFactory;
import org.dromara.sms4j.javase.config.SEInitializer;
import org.dromara.sms4j.provider.config.SmsConfig;

import java.util.LinkedHashMap;

/**
 * 阿里云短信工具类
 *
 * @author happynewyear
 */
@Slf4j
public class DevSmsAliyunUtil {

    private static SmsBlend smsBlend;

    private static final String SYSTEM_SMS_ALIYUN_ACCESS_KEY_ID_KEY = "SYSTEM_SMS_ALIYUN_ACCESS_KEY_ID";

    private static final String SYSTEM_SMS_ALIYUN_ACCESS_KEY_SECRET_KEY = "SYSTEM_SMS_ALIYUN_ACCESS_KEY_SECRET";

    private static final String SYSTEM_SMS_ALIYUN_DEFAULT_SIGN_NAME_KEY = "SYSTEM_SMS_ALIYUN_DEFAULT_SIGN_NAME";

    /**
     * 初始化操作的客户端
     */
    private static void initClient(String signName) {

        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);

        /* accessKeyId */
        String accessKeyId = devConfigApi.getValueByKey(SYSTEM_SMS_ALIYUN_ACCESS_KEY_ID_KEY);

        if (ObjectUtil.isEmpty(accessKeyId)) {
            throw new CommonException("阿里云短信操作客户端未正确配置：accessKeyId为空");
        }

        /* accessKeySecret */
        String accessKeySecret = devConfigApi.getValueByKey(SYSTEM_SMS_ALIYUN_ACCESS_KEY_SECRET_KEY);

        if (ObjectUtil.isEmpty(accessKeySecret)) {
            throw new CommonException("阿里云短信操作客户端未正确配置：accessKeySecret为空");
        }

        AlibabaConfig alibabaConfig = new AlibabaConfig();
        alibabaConfig.setConfigId(accessKeyId);
        alibabaConfig.setAccessKeyId(accessKeyId);
        alibabaConfig.setAccessKeySecret(accessKeySecret);
        alibabaConfig.setSignature(signName);
        SEInitializer.initializer().fromConfig(new SmsConfig(), CollectionUtil.newArrayList(alibabaConfig));
        smsBlend = SmsFactory.getSmsBlend(alibabaConfig.getConfigId());
    }

    /**
     * 发送模板短信
     *
     * @param phoneNumbers  手机号码，支持对多个手机号码发送短信，手机号码之间以半角逗号（,）分隔。
     * @param signName      短信签名，为空则使用默认签名
     * @param templateId    模板id
     * @param templateParam 短信模板变量对应的实际值，JSON格式。支持传入多个参数，示例：{"name":"张三","number":"15038****76"}
     * @return 发送的结果信息
     */
    public static String sendSms(String phoneNumbers, String signName, String templateId, String templateParam) {
        LinkedHashMap<String, String> paramMap = new LinkedHashMap<>();
        JSONUtil.parseObj(templateParam).forEach((k, v) -> paramMap.put(k, Convert.toStr(v)));
        return sendSms(phoneNumbers, signName, templateId, paramMap);
    }

    /**
     * 发送模板短信
     *
     * @param phoneNumbers 手机号码，支持对多个手机号码发送短信，手机号码之间以半角逗号（,）分隔。
     * @param signName     短信签名，为空则使用默认签名
     * @param templateId   模板id
     * @param paramMap     短信参数，HashMap
     * @return 发送的结果信息
     */
    public static String sendSms(String phoneNumbers, String signName, String templateId,
                                 LinkedHashMap<String, String> paramMap) {
        try {
            if (ObjectUtil.isEmpty(signName)) {
                signName = getDefaultSignName();
            }
            // 初始化客户端
            initClient(signName);
            // 发送短信
            SmsResponse smsResponse = smsBlend.massTexting(StrUtil.split(phoneNumbers, StrUtil.COMMA), templateId,
                    paramMap);
            if (smsResponse.isSuccess()) {
                return JSONUtil.toJsonStr(smsResponse.getData());
            } else {
                String data = Convert.toStr(smsResponse.getData());
                if (JSONUtil.isTypeJSON(data)) {
                    JSONObject responseData = JSONUtil.parseObj(smsResponse.getData());
                    throw new CommonException(responseData.getStr("resInfo"));
                } else {
                    throw new CommonException(data);
                }
            }
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 获取默认签名
     */
    public static String getDefaultSignName() {
        // 签名为空，则获取默认签名
        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);
        String signName = devConfigApi.getValueByKey(SYSTEM_SMS_ALIYUN_DEFAULT_SIGN_NAME_KEY);
        if (ObjectUtil.isEmpty(signName)) {
            throw new CommonException("阿里云短信操作客户端未正确配置：signName为空");
        }
        return signName;
    }

}
