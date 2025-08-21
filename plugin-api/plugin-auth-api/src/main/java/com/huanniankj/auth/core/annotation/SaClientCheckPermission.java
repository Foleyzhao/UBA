package com.huanniankj.auth.core.annotation;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import org.springframework.core.annotation.AliasFor;
import com.huanniankj.auth.core.util.StpClientUtil;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限认证(前台User版)：必须具有指定权限才能进入该方法
 * 可标注在函数、类上（效果等同于标注在此类的所有方法上）
 *
 * @author happynewyear
 */
@SaCheckPermission(type = StpClientUtil.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface SaClientCheckPermission {

    /**
     * 需要校验的权限码
     */
    @AliasFor(annotation = SaCheckPermission.class)
    String[] value() default {};

    /**
     * 验证模式：AND | OR，默认AND
     */
    @AliasFor(annotation = SaCheckPermission.class)
    SaMode mode() default SaMode.AND;

}
