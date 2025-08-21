package com.huanniankj.dev.api;

import cn.hutool.json.JSONObject;

/**
 * 短信API
 *
 * @author happynewyear
 */
public interface DevSmsApi {

    /**
     * 动态发送短信（使用系统配置的默认短信引擎）
     *
     * @param phoneNumbers 手机号
     * @param templateCodeOrId 模板id或编码
     * @param paramMap 发送参数
     */
    void sendDynamicSms(String phoneNumbers, String templateCodeOrId, JSONObject paramMap);

    /* =========阿里云短信========= */

    /**
     * 发送短信
     *
     * @param phoneNumbers 手机号码，支持对多个手机号码发送短信，手机号码之间以半角逗号（,）分隔。
     *                     上限为1000个手机号码。批量调用相对于单条调用及时性稍有延迟。
     * @param signName 短信服务控制台配置且审核通过的短信签名
     * @param templateCode 短信服务控制台配置且审核通过的模板编码
     * @param templateParam 短信模板变量对应的实际值，JSON格式。支持传入多个参数，示例：{"name":"张三","number":"15038****76"}
     */
    void sendSmsAliyun(String phoneNumbers, String signName, String templateCode, String templateParam);

    /* =========腾讯云短信========= */

    /**
     * 发送短信
     *
     * @param phoneNumbers 手机号码，支持对多个手机号码发送短信，手机号码之间以半角逗号（,）分隔。
     *                     上限为1000个手机号码。批量调用相对于单条调用及时性稍有延迟。
     * @param signName 短信服务控制台配置且审核通过的短信签名
     * @param templateCode 短信服务控制台配置且审核通过的模板编码
     * @param templateParam 短信模板变量对应的顺序。支持传入多个参数，逗号拼接，示例："张三,15038****76,进行中"}
     */
    void sendSmsTencent(String phoneNumbers, String signName, String templateCode, String templateParam);

}
