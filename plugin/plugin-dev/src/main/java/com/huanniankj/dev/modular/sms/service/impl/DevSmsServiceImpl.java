package com.huanniankj.dev.modular.sms.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.PhoneUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.dev.api.DevConfigApi;
import com.huanniankj.dev.modular.sms.entity.DevSms;
import com.huanniankj.dev.modular.sms.enums.DevSmsEngineTypeEnum;
import com.huanniankj.dev.modular.sms.mapper.DevSmsMapper;
import com.huanniankj.dev.modular.sms.param.DevSmsIdParam;
import com.huanniankj.dev.modular.sms.param.DevSmsPageParam;
import com.huanniankj.dev.modular.sms.param.DevSmsSendAliyunParam;
import com.huanniankj.dev.modular.sms.param.DevSmsSendDynamicParam;
import com.huanniankj.dev.modular.sms.param.DevSmsSendTencentParam;
import com.huanniankj.dev.modular.sms.service.DevSmsService;
import com.huanniankj.dev.modular.sms.util.DevSmsAliyunUtil;
import com.huanniankj.dev.modular.sms.util.DevSmsTencentUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 短信服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevSmsServiceImpl extends ServiceImpl<DevSmsMapper, DevSms> implements DevSmsService {

    /**
     * 默认短信引擎
     */
    private static final String SYSTEM_SYS_DEFAULT_SMS_ENGINE_KEY = "SYSTEM_SYS_DEFAULT_SMS_ENGINE";

    @Resource
    private DevConfigApi devConfigApi;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendDynamic(String engine, String phoneNumbers, String templateCodeOrId, JSONObject templateParam) {
        if (engine.equals(DevSmsEngineTypeEnum.ALIYUN.getValue())) {
            DevSmsSendAliyunParam devSmsSendAliyunParam = new DevSmsSendAliyunParam();
            devSmsSendAliyunParam.setPhoneNumbers(phoneNumbers);
            devSmsSendAliyunParam.setTemplateCode(templateCodeOrId);
            devSmsSendAliyunParam.setTemplateParam(JSONUtil.toJsonStr(templateParam));
            this.sendAliyun(devSmsSendAliyunParam);
        } else if (engine.equals(DevSmsEngineTypeEnum.TENCENT.getValue())) {
            DevSmsSendTencentParam devSmsSendTencentParam = new DevSmsSendTencentParam();
            devSmsSendTencentParam.setPhoneNumbers(phoneNumbers);
            devSmsSendTencentParam.setTemplateCode(templateCodeOrId);
            devSmsSendTencentParam.setTemplateParam(JSONUtil.toJsonStr(templateParam));
            this.sendTencent(devSmsSendTencentParam);
        } else {
            throw new CommonException("不支持的短信引擎：{}", engine);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendDynamic(DevSmsSendDynamicParam devSmsSendDynamicParam) {
        String defaultSmsEngine = devConfigApi.getValueByKey(SYSTEM_SYS_DEFAULT_SMS_ENGINE_KEY);
        if (ObjectUtil.isEmpty(defaultSmsEngine)) {
            throw new CommonException("请联系管理员配置默认短信发送引擎");
        }
        String phoneNumbers = devSmsSendDynamicParam.getPhoneNumbers();
        String templateCodeOrId = devSmsSendDynamicParam.getTemplateCodeOrId();
        String templateParamStr = devSmsSendDynamicParam.getTemplateParam();
        JSONObject templateParam = JSONUtil.parseObj(templateParamStr);
        this.sendDynamic(defaultSmsEngine, phoneNumbers, templateCodeOrId, templateParam);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendAliyun(DevSmsSendAliyunParam devSmsSendAliyunParam) {
        validPhone(devSmsSendAliyunParam.getPhoneNumbers());
        String receiptInfo = DevSmsAliyunUtil.sendSms(devSmsSendAliyunParam.getPhoneNumbers(),
                devSmsSendAliyunParam.getSignName(), devSmsSendAliyunParam.getTemplateCode(),
                devSmsSendAliyunParam.getTemplateParam());
        DevSms devSms = new DevSms();
        BeanUtil.copyProperties(devSmsSendAliyunParam, devSms);
        devSms.setSignName(ObjectUtil.isNotEmpty(devSms.getSignName()) ? devSms.getSignName() :
                DevSmsAliyunUtil.getDefaultSignName());
        devSms.setEngine(DevSmsEngineTypeEnum.ALIYUN.getValue());
        devSms.setReceiptInfo(receiptInfo);
        this.save(devSms);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void sendTencent(DevSmsSendTencentParam smsSendTencentParam) {
        validPhone(smsSendTencentParam.getPhoneNumbers());
        String receiptInfo = DevSmsTencentUtil.sendSms(smsSendTencentParam.getPhoneNumbers(),
                smsSendTencentParam.getSignName(), smsSendTencentParam.getTemplateCode(),
                smsSendTencentParam.getTemplateParam());
        DevSms devSms = new DevSms();
        BeanUtil.copyProperties(smsSendTencentParam, devSms);
        devSms.setSignName(ObjectUtil.isNotEmpty(devSms.getSignName()) ? devSms.getSignName() :
                DevSmsTencentUtil.getDefaultSignName());
        devSms.setEngine(DevSmsEngineTypeEnum.TENCENT.getValue());
        devSms.setReceiptInfo(receiptInfo);
        this.save(devSms);
    }

    @Override
    public Page<DevSms> page(DevSmsPageParam devSmsPageParam) {
        QueryWrapper<DevSms> queryWrapper = new QueryWrapper<DevSms>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(devSmsPageParam.getEngine())) {
            queryWrapper.lambda().eq(DevSms::getEngine, devSmsPageParam.getEngine());
        }
        if (ObjectUtil.isNotEmpty(devSmsPageParam.getSearchKey())) {
            queryWrapper.lambda().like(DevSms::getPhoneNumbers, devSmsPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(devSmsPageParam.getSortField(), devSmsPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devSmsPageParam.getSortOrder());
            queryWrapper.orderBy(true, devSmsPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devSmsPageParam.getSortField()));
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<DevSmsIdParam> devSmsIdParamList) {
        this.removeByIds(CollStreamUtil.toList(devSmsIdParamList, DevSmsIdParam::getId));
    }

    @Override
    public DevSms detail(DevSmsIdParam devSmsIdParam) {
        return this.queryEntity(devSmsIdParam.getId());
    }

    @Override
    public DevSms queryEntity(String id) {
        DevSms devSms = this.getById(id);
        if (ObjectUtil.isEmpty(devSms)) {
            throw new CommonException("短信不存在，id值为：{}", id);
        }
        return devSms;
    }

    /**
     * 校验手机格式
     *
     * @author happynewyear
     */
    private void validPhone(String phones) {
        StrUtil.split(phones, StrUtil.COMMA).forEach(phone -> {
            if (!PhoneUtil.isMobile(phone)) {
                throw new CommonException("手机号码：{}格式错误", phone);
            }
        });
    }
}
