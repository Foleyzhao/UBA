package com.huanniankj.dev.modular.sms.provider;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.huanniankj.dev.api.DevSmsApi;
import com.huanniankj.dev.modular.sms.param.DevSmsSendAliyunParam;
import com.huanniankj.dev.modular.sms.param.DevSmsSendDynamicParam;
import com.huanniankj.dev.modular.sms.param.DevSmsSendTencentParam;
import com.huanniankj.dev.modular.sms.service.DevSmsService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 短信API接口提供者
 *
 * @author happynewyear
 */
@Service
public class DevSmsApiProvider implements DevSmsApi {

    @Resource
    private DevSmsService devSmsService;

    @Override
    public void sendDynamicSms(String phoneNumbers, String templateCodeOrId, JSONObject paramMap) {
        DevSmsSendDynamicParam dynamicParam = new DevSmsSendDynamicParam();
        dynamicParam.setPhoneNumbers(phoneNumbers);
        dynamicParam.setTemplateCodeOrId(templateCodeOrId);
        dynamicParam.setTemplateParam(JSONUtil.toJsonStr(paramMap));
        devSmsService.sendDynamic(dynamicParam);
    }

    @Override
    public void sendSmsAliyun(String phoneNumbers, String signName, String templateCode, String templateParam) {
        DevSmsSendAliyunParam devSmsSendAliyunParam = new DevSmsSendAliyunParam();
        devSmsSendAliyunParam.setPhoneNumbers(phoneNumbers);
        devSmsSendAliyunParam.setSignName(signName);
        devSmsSendAliyunParam.setTemplateCode(templateCode);
        devSmsSendAliyunParam.setTemplateParam(templateParam);
        devSmsService.sendAliyun(devSmsSendAliyunParam);
    }

    @Override
    public void sendSmsTencent(String phoneNumbers, String signName, String templateCode, String templateParam) {
        DevSmsSendTencentParam devSmsSendTencentParam = new DevSmsSendTencentParam();
        devSmsSendTencentParam.setPhoneNumbers(phoneNumbers);
        devSmsSendTencentParam.setSignName(signName);
        devSmsSendTencentParam.setTemplateCode(templateCode);
        devSmsSendTencentParam.setTemplateParam(templateParam);
        devSmsService.sendTencent(devSmsSendTencentParam);
    }

}
