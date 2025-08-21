package com.huanniankj.common.interceptor;

import com.huanniankj.common.util.CommonTraceIdUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 公共链路追踪拦截器
 *
 * @author happynewyear
 */
public class CommonTraceInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        try {
            CommonTraceIdUtil.getTraceId(request);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        CommonTraceIdUtil.clear();
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
