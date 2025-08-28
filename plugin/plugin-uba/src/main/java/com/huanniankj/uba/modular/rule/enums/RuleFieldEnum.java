package com.huanniankj.uba.modular.rule.enums;

import lombok.Getter;

/**
 * 数据清洗字段枚举
 *
 * @author happynewyear
 */
@Getter
public enum RuleFieldEnum {

    /**
     * 请求ID
     */
    REQUEST_ID("requestId"),

    /**
     * 服务名称
     */
    SERVER_NAME("serverName"),

    /**
     * 连接ID
     */
    CONNECTION("connection"),

    /**
     * 请求时间
     */
    TIMESTAMP("timestamp"),

    /**
     * 客户端IP地址
     */
    REMOTE_ADDR("remoteAddr"),

    /**
     * http请求
     */
    REQUEST("request"),

    /**
     * http状态码
     */
    STATUS("status"),

    /**
     * 发送给客户端字节数
     */
    BODY_BYTES_SENT("bodyBytesSent"),

    /**
     * 请求来源
     */
    HTTP_REFERER("httpReferer"),

    /**
     * 客户端浏览器标识
     */
    HTTP_USER_AGENT("httpUserAgent"),

    /**
     * 请求处理时间
     */
    REQUEST_TIME("requestTime"),

    /**
     * 后端服务器响应时间
     */
    UPSTREAM_RESPONSE_TIME("upstreamResponseTime"),

    /**
     * 请求协议
     */
    SCHEME("scheme"),

    /**
     * http协议版本
     */
    SERVER_PROTOCOL("serverProtocol"),

    /**
     * http方法
     */
    REQUEST_METHOD("requestMethod"),

    /**
     * 请求uri
     */
    REQUEST_URI("requestUri"),

    /**
     * 查询参数
     */
    QUERY_STRING("queryString"),

    /**
     * 请求host头
     */
    HTTP_HOST("httpHost"),

    /**
     * 客户端语言偏好
     */
    HTTP_ACCEPT_LANGUAGE("httpAcceptLanguage"),

    /**
     * 代理服务器的原始客户端IP
     */
    HTTP_X_FORWARDED_FOR("httpXForwardedFor"),

    /**
     * 请求头中的请求ID
     */
    HTTP_X_REQUEST_ID("httpXRequestId"),

    /**
     * 请求的Cookie
     */
    HTTP_COOKIE("httpCookie"),

    /**
     * cookieSession
     */
    COOKIE_SESSION("cookieSession");

    private final String value;

    RuleFieldEnum(String value) {
        this.value = value;
    }

}
