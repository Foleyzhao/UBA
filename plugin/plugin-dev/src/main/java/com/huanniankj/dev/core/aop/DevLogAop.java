package com.huanniankj.dev.core.aop;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.huanniankj.auth.core.pojo.SaBaseClientLoginUser;
import com.huanniankj.auth.core.pojo.SaBaseLoginUser;
import com.huanniankj.auth.core.util.StpClientLoginUserUtil;
import com.huanniankj.auth.core.util.StpLoginUserUtil;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.dev.modular.log.util.DevLogUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * 业务日志aop切面
 *
 * @author happynewyear
 */
@Aspect
@Order
@Component
public class DevLogAop {

    /**
     * 日志切入点
     */
    @Pointcut("@annotation(com.huanniankj.common.annotation.CommonLog)")
    private void getLogPointCut() {
    }

    /**
     * 操作成功返回结果记录日志
     */
    @AfterReturning(pointcut = "getLogPointCut()", returning = "result")
    public void doAfterReturning(JoinPoint joinPoint, Object result) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CommonLog commonLog = method.getAnnotation(CommonLog.class);
        String userName = "未知";
        try {
            try {
                SaBaseLoginUser loginUser = StpLoginUserUtil.getLoginUser();
                if (ObjectUtil.isNotNull(loginUser)) {
                    userName = loginUser.getName();
                }
            } catch (Exception e) {
                SaBaseClientLoginUser clientLoginUser = StpClientLoginUserUtil.getClientLoginUser();
                if (ObjectUtil.isNotNull(clientLoginUser)) {
                    userName = clientLoginUser.getName();
                }
            }
        } catch (Exception ignored) {
        }
        // 异步记录日志
        DevLogUtil.executeOperationLog(commonLog, userName, joinPoint, JSONUtil.toJsonStr(result));
    }

    /**
     * 操作发生异常记录日志
     */
    @AfterThrowing(pointcut = "getLogPointCut()", throwing = "exception")
    public void doAfterThrowing(JoinPoint joinPoint, Exception exception) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        CommonLog commonLog = method.getAnnotation(CommonLog.class);
        String userName = "未知";
        try {
            SaBaseLoginUser loginUser = StpLoginUserUtil.getLoginUser();
            if (ObjectUtil.isNotNull(loginUser)) {
                userName = loginUser.getName();
            } else {
                SaBaseClientLoginUser clientLoginUser = StpClientLoginUserUtil.getClientLoginUser();
                if (ObjectUtil.isNotNull(clientLoginUser)) {
                    userName = clientLoginUser.getName();
                }
            }
        } catch (Exception ignored) {
        }
        //异步记录日志
        DevLogUtil.executeExceptionLog(commonLog, userName, joinPoint, exception);
    }
    
}
