package com.huanniankj.client.core.util;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import com.huanniankj.dev.api.DevConfigApi;

/**
 * 系统相关邮件格式化工具类
 *
 * @author happynewyear
 */
public class ClientEmailFormatUtl {

    /**
     * 系统名称
     */
    private static final String SYSTEM_SYS_NAME_KEY = "SYSTEM_SYS_NAME";

    /**
     * 格式化邮件内容
     */
    public static String format(String content, JSONObject paramMap) {
        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);
        // 获取系统名称
        String sysName = devConfigApi.getValueByKey(SYSTEM_SYS_NAME_KEY);
        // 系统名称
        paramMap.set("sysName", sysName);
        // 当前时间
        paramMap.set("sysNowTime", DateUtil.now());
        return StrUtil.format(content, paramMap);
    }
}
