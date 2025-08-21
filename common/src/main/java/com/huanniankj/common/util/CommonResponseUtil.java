package com.huanniankj.common.util;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.ContentType;
import cn.hutool.json.JSONUtil;
import com.huanniankj.common.pojo.CommonResult;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * 通用响应工具类
 *
 * @author happynewyear
 */
public class CommonResponseUtil {

    /**
     * 以流的方式响应错误信息，默认错误消息
     */
    public static void renderError(HttpServletResponse response) throws IOException {
        renderError(response, null);
    }

    /**
     * 以流的方式响应错误信息，指定错误消息
     */
    public static void renderError(HttpServletResponse response, String msg) throws IOException {
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(ContentType.JSON.toString());
        response.getWriter().write(JSONUtil.toJsonStr(ObjectUtil.isNotEmpty(msg) ?
                CommonResult.error(msg) : CommonResult.error()));
    }

    /**
     * 以流的方式响应错误信息，指定错误码和错误消息
     */
    public static void renderError(HttpServletResponse response, Integer code, String msg) throws IOException {
        response.setCharacterEncoding(CharsetUtil.UTF_8);
        response.setContentType(ContentType.JSON.toString());
        response.getWriter().write(JSONUtil.toJsonStr(CommonResult.get(code, msg, null)));
    }
}
