package com.huanniankj.dev.modular.log.util;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.util.CommonCryptogramUtil;
import com.huanniankj.common.util.CommonIpAddressUtil;
import com.huanniankj.common.util.CommonJoinPointUtil;
import com.huanniankj.common.util.CommonServletUtil;
import com.huanniankj.common.util.CommonUaUtil;
import com.huanniankj.dev.modular.log.entity.DevLog;
import com.huanniankj.dev.modular.log.enums.DevLogCategoryEnum;
import com.huanniankj.dev.modular.log.enums.DevLogExeStatusEnum;
import com.huanniankj.dev.modular.log.service.DevLogService;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;

/**
 * 日志工具类
 *
 * @author happynewyears
 */
public class DevLogUtil {

    private static final DevLogService devLogService = SpringUtil.getBean(DevLogService.class);

    /**
     * 记录操作日志
     */
    public static void executeOperationLog(CommonLog commonLog, String userName,
                                           JoinPoint joinPoint, String resultJson) {
        HttpServletRequest request = CommonServletUtil.getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getRequestURI();
        DevLog devLog = genBasOpLog();
        ThreadUtil.execute(() -> {
            devLog.setCategory(DevLogCategoryEnum.OPERATE.getValue());
            devLog.setName(commonLog.value());
            devLog.setExeStatus(DevLogExeStatusEnum.SUCCESS.getValue());
            devLog.setClassName(joinPoint.getTarget().getClass().getName());
            devLog.setMethodName(joinPoint.getSignature().getName());
            devLog.setReqMethod(method);
            devLog.setReqUrl(requestURI);
            devLog.setParamJson(CommonJoinPointUtil.getArgsJsonString(joinPoint));
            devLog.setResultJson(resultJson);
            devLog.setOpTime(DateTime.now());
            devLog.setOpUser(userName);
            creatLogSignValue(devLog);
            devLogService.save(devLog);
        });
    }

    /**
     * 记录异常日志
     */
    public static void executeExceptionLog(CommonLog commonLog, String userName, JoinPoint joinPoint,
                                           Exception exception) {
        HttpServletRequest request = CommonServletUtil.getRequest();
        String requestURI = request.getRequestURI();
        String method = request.getRequestURI();
        DevLog devLog = genBasOpLog();
        ThreadUtil.execute(() -> {
            devLog.setCategory(DevLogCategoryEnum.EXCEPTION.getValue());
            devLog.setName(commonLog.value());
            devLog.setExeStatus(DevLogExeStatusEnum.FAIL.getValue());
            devLog.setExeMessage(ExceptionUtil.stacktraceToString(exception, Integer.MAX_VALUE));
            devLog.setClassName(joinPoint.getTarget().getClass().getName());
            devLog.setMethodName(joinPoint.getSignature().getName());
            devLog.setReqMethod(method);
            devLog.setReqUrl(requestURI);
            devLog.setParamJson(CommonJoinPointUtil.getArgsJsonString(joinPoint));
            devLog.setOpTime(DateTime.now());
            devLog.setOpUser(userName);
            creatLogSignValue(devLog);
            devLogService.save(devLog);
        });
    }

    /**
     * 记录登录日志
     */
    public static void executeLoginLog(String userName) {
        DevLog devLog = genBasOpLog();
        ThreadUtil.execute(() -> {
            devLog.setCategory(DevLogCategoryEnum.LOGIN.getValue());
            devLog.setName("用户登录");
            devLog.setExeStatus(DevLogExeStatusEnum.SUCCESS.getValue());
            devLog.setOpTime(DateTime.now());
            devLog.setOpUser(userName);
            creatLogSignValue(devLog);
            devLogService.save(devLog);
        });
    }

    /**
     * 记录登出日志
     */
    public static void executeLogoutLog(String userName) {
        DevLog devLog = genBasOpLog();
        ThreadUtil.execute(() -> {
            devLog.setCategory(DevLogCategoryEnum.LOGOUT.getValue());
            devLog.setName("用户登出");
            devLog.setExeStatus(DevLogExeStatusEnum.SUCCESS.getValue());
            devLog.setOpTime(DateTime.now());
            devLog.setOpUser(userName);
            creatLogSignValue(devLog);
            devLogService.save(devLog);
        });
    }

    /**
     * 构建基础操作日志
     */
    private static DevLog genBasOpLog() {
        HttpServletRequest request = CommonServletUtil.getRequest();
        String ip = CommonIpAddressUtil.getIp(request);
        DevLog devLog = new DevLog();
        devLog.setOpIp(CommonIpAddressUtil.getIp(request));
        devLog.setOpAddress(CommonIpAddressUtil.getCityInfo(ip));
        devLog.setOpBrowser(CommonUaUtil.getBrowser(request));
        devLog.setOpOs(CommonUaUtil.getOs(request));
        return devLog;
    }

    /**
     * 构建日志完整性保护签名数据
     */
    private static void creatLogSignValue(DevLog devLog) {
        String logStr = devLog.toString().replaceAll(" +", "");
        devLog.setSignData(CommonCryptogramUtil.doSignature(logStr));
    }
}
