package com.huanniankj.core.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpStatus;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.common.util.CommonServletUtil;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

/**
 * 将未知错误异常，输出格式重写为我们熟悉的响应格式
 *
 * @author happynewyear
 */
@Component
public class GlobalErrorAttributesHandler extends DefaultErrorAttributes {
    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions attributeOptions) {
        // 获取spring默认的返回内容
        Map<String, Object> defaultErrorAttributes = super.getErrorAttributes(webRequest, attributeOptions);

        // 获取其状态码
        Object status = defaultErrorAttributes.get("status");
        Object path = defaultErrorAttributes.get("path");
        if (ObjectUtil.isNotEmpty(status)) {
            // 如果其为404，则处理
            if (HttpStatus.HTTP_NOT_FOUND == Convert.toInt(status)) {
                if (ObjectUtil.isNotEmpty(path)) {
                    return BeanUtil.beanToMap(CommonResult.get(HttpStatus.HTTP_NOT_FOUND, "路径不存在，请求地址："
                            + Convert.toStr(path), null));
                } else {
                    return BeanUtil.beanToMap(CommonResult.get(HttpStatus.HTTP_NOT_FOUND, "路径不存在", null));
                }
            } else {
                return BeanUtil.beanToMap(CommonResult.get(HttpStatus.HTTP_INTERNAL_ERROR, "服务器异常，请求地址："
                        + Convert.toStr(path), null));
            }
        }

        // 如果返回的异常是CommonException，则按CommonException响应的内容进行返回
        Throwable throwable = this.getError(webRequest);
        if (ObjectUtil.isNotEmpty(throwable)) {
            if (throwable instanceof CommonException commonException) {
                return BeanUtil.beanToMap(CommonResult.error(commonException.getMsg()));
            } else {
                return BeanUtil.beanToMap(CommonResult.get(HttpStatus.HTTP_INTERNAL_ERROR, "服务器异常，请求地址："
                        + CommonServletUtil.getRequest().getRequestURL(), null));
            }
        } else {
            // throwable为空，则直接返回默认异常
            return BeanUtil.beanToMap(CommonResult.error());
        }
    }
}
