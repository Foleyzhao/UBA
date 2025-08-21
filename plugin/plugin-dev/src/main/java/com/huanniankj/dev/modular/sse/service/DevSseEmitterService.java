package com.huanniankj.dev.modular.sse.service;

import com.huanniankj.common.sse.CommonSseParam;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.function.Consumer;

/**
 * SSE通信服务接口
 *
 * @author happynewyear
 */
public interface DevSseEmitterService {

    /**
     * 创建连接
     */
    SseEmitter createSseConnect(String clientId, Boolean setHeartBeat, Boolean defaultHeartbeat,
                                Consumer<CommonSseParam> consumer);

    /**
     * 关闭连接
     */
    void closeSseConnect(String clientId);

    /**
     * 推送消息到所有客户端
     */
    void sendMessageToAllClient(String msg);

    /**
     * 根据clientId发送消息给某一客户端
     */
    void sendMessageToOneClient(String clientId, String msg);
}
