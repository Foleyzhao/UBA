package com.huanniankj.core.handler;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Collections;
import java.util.Map;

/**
 * 全局异常页面处理器，覆盖默认的Whitelabel Error Page
 *
 * @author happynewyear
 */
@RestController
public class GlobalErrorViewHandler extends BasicErrorController {

    public GlobalErrorViewHandler(ServerProperties serverProperties) {
        super(new GlobalErrorAttributesHandler(), serverProperties.getError());
    }

    /**
     * 覆盖默认的Json响应
     */
    @Override
    public ResponseEntity<Map<String, Object>> error(HttpServletRequest request) {
        Map<String, Object> defaultErrorAttributes =
                super.getErrorAttributes(request, getErrorAttributeOptions(request, MediaType.ALL));
        Integer code = Convert.toInt(defaultErrorAttributes.get("code"));
        return new ResponseEntity<>(defaultErrorAttributes, HttpStatus.valueOf(ObjectUtil.isNotEmpty(code) ? code : 500));
    }

    /**
     * 覆盖默认的错误页面，响应JSON
     */
    @Override
    @RequestMapping(produces = {"text/html"})
    public ModelAndView errorHtml(HttpServletRequest request, HttpServletResponse response) {
        HttpStatus status = this.getStatus(request);
        Map<String, Object> model = Collections.unmodifiableMap(this.getErrorAttributes(request,
                this.getErrorAttributeOptions(request, MediaType.TEXT_HTML)));
        response.setStatus(status.value());
        ModelAndView modelAndView = this.resolveErrorView(request, response, status, model);
        request.setAttribute("model", model);
        return modelAndView != null ? modelAndView : new ModelAndView("errorView", model);
    }
}
