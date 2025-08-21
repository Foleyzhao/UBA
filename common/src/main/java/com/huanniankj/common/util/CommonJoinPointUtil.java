package com.huanniankj.common.util;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * Spring切面工具类
 *
 * @author happynewyear
 */
public class CommonJoinPointUtil {

    /**
     * 获取切面的参数JSON
     */
    public static String getArgsJsonString(JoinPoint joinPoint) {
        Signature signature = joinPoint.getSignature();
        // 参数名数组
        String[] parameterNames = ((MethodSignature) signature).getParameterNames();
        // 构造参数组集合
        Map<String, Object> map = MapUtil.newHashMap();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            if (ObjectUtil.isNotEmpty(args[i]) && isUsefulParam(args[i])) {
                if (JSONUtil.isTypeJSON(StrUtil.toString(args[i]))) {
                    try {
                        JSONObject jsonObject = JSONUtil.parseObj(args[i]);
                        if (ObjectUtil.isNotEmpty(jsonObject)) {
                            map.put(parameterNames[i], jsonObject);
                        } else {
                            map.put(parameterNames[i], JSONUtil.parseArray(args[i]));
                        }
                    } catch (Exception e) {
                        map.put(parameterNames[i], null);
                    }
                } else {
                    map.put(parameterNames[i], JSONUtil.toJsonStr(args[i]));
                }
            }
        }
        return JSONUtil.toJsonStr(map);
    }

    /**
     * 判断是否需要拼接的参数，过滤掉HttpServletRequest,MultipartFile,HttpServletResponse等类型参数
     */
    private static boolean isUsefulParam(Object arg) {
        return !(arg instanceof MultipartFile) &&
                !(arg instanceof HttpServletRequest) &&
                !(arg instanceof HttpServletResponse);
    }
}
