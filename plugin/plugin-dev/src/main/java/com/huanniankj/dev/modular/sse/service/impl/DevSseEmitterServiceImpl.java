package com.huanniankj.dev.modular.sse.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.IdUtil;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.common.sse.CommonSseParam;
import com.huanniankj.dev.modular.sse.service.DevSseEmitterService;
import com.huanniankj.dev.modular.sse.util.DevSseCacheUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * SSE通信服务接口实现类
 *
 * @author happynewyear
 */
@Slf4j
@Service
public class DevSseEmitterServiceImpl implements DevSseEmitterService {

    /**
     * 心跳线程池
     */
    private static final ScheduledExecutorService heartbeatExecutors = Executors.newScheduledThreadPool(10);

    /**
     * 创建连接
     */
    @Override
    public SseEmitter createSseConnect(String clientId, Boolean setHeartBeat, Boolean defaultHeartbeat,
                                       Consumer<CommonSseParam> consumer) {
        // 设置超时时间，0表示不过期。默认30秒，超过时间未完成会抛出异常：AsyncRequestTimeoutException
        SseEmitter sseEmitter = new SseEmitter(0L);
        String loginId = StpUtil.getLoginIdAsString();
        // 判断连接是否有效
        if (DevSseCacheUtil.connectionValidity(clientId, loginId)) {
            return DevSseCacheUtil.getSseEmitterByClientId(clientId);
        } else {
            DevSseCacheUtil.removeConnection(clientId);
        }
        clientId = IdUtil.simpleUUID();
        String finalClientId = clientId;
        // 增加心跳
        final ScheduledFuture<?> future;
        // 是否自定义心跳任务
        if (setHeartBeat != null && setHeartBeat) {
            //是否使用默认心跳任务
            if (defaultHeartbeat != null && defaultHeartbeat) {
                //默认心跳任务
                future = heartbeatExecutors.scheduleAtFixedRate(() ->
                                DevSseCacheUtil.sendMessageToOneClient(finalClientId, finalClientId + "-" + loginId),
                        2, 10, TimeUnit.SECONDS);
            } else {
                //自定义心跳任务
                CommonSseParam commonSseParam = new CommonSseParam();
                commonSseParam.setClientId(clientId);
                commonSseParam.setLoginId(loginId);
                future = heartbeatExecutors.scheduleAtFixedRate(() -> consumer.accept(commonSseParam),
                        2, 10, TimeUnit.SECONDS);
            }
            // 增加连接
            DevSseCacheUtil.addConnection(clientId, loginId, sseEmitter, future);
        } else {
            // 增加连接
            DevSseCacheUtil.addConnection(clientId, loginId, sseEmitter, null);
        }
        // 长链接完成后回调(即关闭连接时调用)
        sseEmitter.onCompletion(DevSseCacheUtil.completionCallBack(clientId));
        // 连接超时回调
        sseEmitter.onTimeout(DevSseCacheUtil.timeoutCallBack(clientId));
        // 推送消息异常回调
        sseEmitter.onError(DevSseCacheUtil.errorCallBack(clientId));
        // 初次建立连接,推送客户端id
        CommonResult<String> message = new CommonResult<>(0, "", clientId);
        DevSseCacheUtil.sendMessageToClientByClientId(clientId, message);
        return sseEmitter;
    }

    /**
     * 关闭连接
     */
    @Override
    public void closeSseConnect(String clientId) {
        DevSseCacheUtil.removeConnection(clientId);
    }

    /**
     * 推送消息到所有客户端
     */
    @Override
    public void sendMessageToAllClient(String msg) {
        DevSseCacheUtil.sendMessageToAllClient(msg);
    }

    /**
     * 根据clientId发送消息给某一客户端
     */
    @Override
    public void sendMessageToOneClient(String clientId, String msg) {
        DevSseCacheUtil.sendMessageToOneClient(clientId, msg);
    }

}
