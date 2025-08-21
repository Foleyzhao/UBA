package com.huanniankj.uba.modular.accesslog.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.huanniankj.uba.core.config.CustomInstantDeserializer;
import lombok.Data;

import java.time.Instant;
import java.util.Date;

/**
 * 访问日志实体
 *
 * @author happynewyear
 */
@Data
@TableName("access_log")
public class AccessLogCh {

    /**
     * 请求ID
     */
    private String requestId;

    /**
     * 服务名称
     */
    private String serverName;

    /**
     * 连接ID
     */
    private String connection;

    /**
     * 请求时间
     */
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    private Instant timestamp;

    /**
     * 客户端IP地址
     */
    private String remoteAddr;

    /**
     * http请求
     */
    private String request;

    /**
     * http状态码
     */
    private Integer status;

    /**
     * 发送给客户端字节数
     */
    private Long bodyBytesSent;

    /**
     * 请求来源
     */
    private String httpReferer;

    /**
     * 客户端浏览器标识
     */
    private String httpUserAgent;

    /**
     * 请求处理时间
     */
    private String requestTime;

    /**
     * 后端服务器响应时间
     */
    private String upstreamResponseTime;

    /**
     * 请求协议
     */
    private String scheme;

    /**
     * http协议版本
     */
    private String serverProtocol;

    /**
     * http方法
     */
    private String requestMethod;

    /**
     * 请求uri
     */
    private String requestUri;

    /**
     * 查询参数
     */
    private String queryString;

    /**
     * 请求host头
     */
    private String httpHost;

    /**
     * 客户端语言偏好
     */
    private String httpAcceptLanguage;

    /**
     * 代理服务器的原始客户端IP
     */
    private String httpXForwardedFor;

    /**
     * 请求头中的请求ID
     */
    private String httpXRequestId;

    /**
     * 请求的Cookie
     */
    private String httpCookie;

    /**
     * Cookie标识
     */
    private String cookieSession;

    /**
     * 请求日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;

}
