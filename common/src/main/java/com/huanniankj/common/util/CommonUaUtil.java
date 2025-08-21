package com.huanniankj.common.util;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.servlet.JakartaServletUtil;
import cn.hutool.http.useragent.Browser;
import cn.hutool.http.useragent.UserAgent;
import cn.hutool.http.useragent.UserAgentUtil;
import jakarta.servlet.http.HttpServletRequest;

/**
 * 用户代理工具类
 *
 * @author happynewyear
 */
public class CommonUaUtil {

    /**
     * 获取客户端浏览器
     */
    public static String getBrowser(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        if (ObjectUtil.isEmpty(userAgent)) {
            return StrUtil.DASHED;
        } else {
            String browser = userAgent.getBrowser().toString();
            if (StrUtil.isNotBlank(browser) && browser.length() > 250) {
                browser = browser.substring(0, 250);
            }
            return "Unknown".equals(browser) ? StrUtil.DASHED : browser;
        }
    }

    /**
     * 获取客户端操作系统
     */
    public static String getOs(HttpServletRequest request) {
        UserAgent userAgent = getUserAgent(request);
        if (ObjectUtil.isEmpty(userAgent)) {
            return StrUtil.DASHED;
        } else {
            String os = userAgent.getOs().toString();
            if (StrUtil.isNotBlank(os) && os.length() > 250) {
                os = os.substring(0, 250);
            }
            return "Unknown".equals(os) ? StrUtil.DASHED : os;
        }
    }

    /**
     * 获取请求代理头
     */
    private static UserAgent getUserAgent(HttpServletRequest request) {
        String userAgentStr = JakartaServletUtil.getHeaderIgnoreCase(request, "User-Agent");
        UserAgent userAgent = UserAgentUtil.parse(userAgentStr);
        if (ObjectUtil.isNotEmpty(userAgentStr)) {
            if ("Unknown".equals(userAgent.getBrowser().getName())) {
                userAgent.setBrowser(new Browser(userAgentStr, null, ""));
            }
        }
        return userAgent;
    }
}
