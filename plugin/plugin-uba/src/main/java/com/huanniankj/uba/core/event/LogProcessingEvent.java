package com.huanniankj.uba.core.event;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.huanniankj.uba.core.config.CustomInstantDeserializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Date;

/**
 * 访问日志基础事件
 *
 * @author happynewyear
 */
@NoArgsConstructor
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = RawLogEvent.class, name = "raw"),
        @JsonSubTypes.Type(value = PreprocessingFinishEvent.class, name = "preprocessing"),
        @JsonSubTypes.Type(value = EnrichmentFinishEvent.class, name = "enrichment"),
        @JsonSubTypes.Type(value = CleansingFinishEvent.class, name = "cleansing"),
        @JsonSubTypes.Type(value = StructuringFinishEvent.class, name = "structuring")
})
@Data
public abstract class LogProcessingEvent {

    /**
     * 事件类型
     */
    public String type;

    /**
     * 处理时间
     */
    public Instant dealTime = Instant.now();

    /**
     * 请求ID
     */
    @JsonProperty("request_id")
    public String requestId;

    /**
     * 服务名称
     */
    @JsonProperty("server_name")
    public String serverName;

    /**
     * 连接ID
     */
    public String connection;

    /**
     * 请求时间
     */
    @JsonDeserialize(using = CustomInstantDeserializer.class)
    public Instant timestamp;

    /**
     * 客户端IP地址
     */
    @JsonProperty("remote_addr")
    public String remoteAddr;

    /**
     * http请求
     */
    public String request;

    /**
     * http状态码
     */
    public Integer status;

    /**
     * 发送给客户端字节数
     */
    @JsonProperty("body_bytes_sent")
    public Long bodyBytesSent;

    /**
     * 请求来源
     */
    @JsonProperty("http_referer")
    public String httpReferer;

    /**
     * 客户端浏览器标识
     */
    @JsonProperty("http_user_agent")
    public String httpUserAgent;

    /**
     * 请求处理时间
     */
    @JsonProperty("request_time")
    public String requestTime;

    /**
     * 后端服务器响应时间
     */
    @JsonProperty("upstream_response_time")
    public String upstreamResponseTime;

    /**
     * 请求协议
     */
    public String scheme;

    /**
     * http协议版本
     */
    @JsonProperty("server_protocol")
    public String serverProtocol;

    /**
     * http方法
     */
    @JsonProperty("request_method")
    public String requestMethod;

    /**
     * 请求uri
     */
    @JsonProperty("request_uri")
    public String requestUri;

    /**
     * 查询参数
     */
    @JsonProperty("query_string")
    public String queryString;

    /**
     * 请求host头
     */
    @JsonProperty("http_host")
    public String httpHost;

    /**
     * 客户端语言偏好
     */
    @JsonProperty("http_accept_language")
    public String httpAcceptLanguage;

    /**
     * 代理服务器的原始客户端IP
     */
    @JsonProperty("http_x_forwarded_For")
    public String httpXForwardedFor;

    /**
     * 请求头中的请求ID
     */
    @JsonProperty("http_x_request_id")
    public String httpXRequestId;

    /**
     * 请求的Cookie
     */
    @JsonProperty("http_cookie")
    public String httpCookie;

    /**
     * Cookie标识
     */
    @JsonProperty("cookie_session")
    public String cookieSession;

    /**
     * 请求日期
     */
    @JsonFormat(pattern = "yyyy-mm-dd")
    public Date date;

}
