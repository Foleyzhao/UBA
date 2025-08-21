package com.huanniankj.dev.modular.sms.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.sms.entity.DevSms;
import com.huanniankj.dev.modular.sms.param.DevSmsIdParam;
import com.huanniankj.dev.modular.sms.param.DevSmsPageParam;
import com.huanniankj.dev.modular.sms.param.DevSmsSendAliyunParam;
import com.huanniankj.dev.modular.sms.param.DevSmsSendDynamicParam;
import com.huanniankj.dev.modular.sms.param.DevSmsSendTencentParam;

import java.util.List;

/**
 * 短信服务接口
 *
 * @author happynewyear
 */
public interface DevSmsService extends IService<DevSms> {

    /**
     * 动态发送短信
     *
     * @param engine           发送引擎
     * @param phoneNumbers     手机号
     * @param templateCodeOrId 模板id或编码
     * @param templateParam    发送参数
     */
    void sendDynamic(String engine, String phoneNumbers, String templateCodeOrId, JSONObject templateParam);

    /**
     * 动态发送短信
     */
    void sendDynamic(DevSmsSendDynamicParam devSmsSendDynamicParam);

    /**
     * 发送短信——阿里云
     */
    void sendAliyun(DevSmsSendAliyunParam devSmsSendAliyunParam);

    /**
     * 发送短信——腾讯云
     */
    void sendTencent(DevSmsSendTencentParam devSmsSendTencentParam);

    /**
     * 获取短信分页
     */
    Page<DevSms> page(DevSmsPageParam devSmsPageParam);

    /**
     * 删除短信
     */
    void delete(List<DevSmsIdParam> devSmsIdParamList);

    /**
     * 获取短信详情
     */
    DevSms detail(DevSmsIdParam devSmsIdParam);

    /**
     * 获取短信详情
     */
    DevSms queryEntity(String id);
}
