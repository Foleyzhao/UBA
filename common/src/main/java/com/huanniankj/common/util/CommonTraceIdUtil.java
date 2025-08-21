package com.huanniankj.common.util;

import cn.hutool.core.util.StrUtil;
import jakarta.servlet.http.HttpServletRequest;

import java.util.UUID;

/**
 * TraceId工具类
 *
 * @author happynewyear
 */
public class CommonTraceIdUtil {

    public static final String TRACE_ID_STRING = "traceId";

    private static final InheritableThreadLocal<String> TRACE_ID = new InheritableThreadLocal<>();

    public static String generateTraceId(HttpServletRequest request) {
        String header = request.getHeader(TRACE_ID_STRING);
        if (header != null) {
            return header;
        }
        return UUID.randomUUID().toString().replace(StrUtil.DASHED, StrUtil.EMPTY);
    }

    public static String getTraceId() {
        return TRACE_ID.get();
    }

    public static String getTraceId(HttpServletRequest request) {
        String traceId = getTraceId();
        if (traceId == null) {
            traceId = generateTraceId(request);
            setTraceId(traceId);
        }
        return traceId;
    }

    public static void setTraceId(String traceId) {
        TRACE_ID.set(traceId);
    }

    public static void clear() {
        TRACE_ID.remove();
    }
}
