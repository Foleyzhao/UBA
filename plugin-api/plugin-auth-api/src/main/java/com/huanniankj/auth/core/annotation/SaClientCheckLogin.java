package com.huanniankj.auth.core.annotation;

import cn.dev33.satoken.annotation.SaCheckLogin;
import com.huanniankj.auth.core.util.StpClientUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 登录认证(前台User版)：只有登录之后才能进入该方法
 * 可标注在函数、类上（效果等同于标注在此类的所有方法上）
 *
 * @author happynewyear
 */
@SaCheckLogin(type = StpClientUtil.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SaClientCheckLogin {

}
