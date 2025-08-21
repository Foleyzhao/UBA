package com.huanniankj.auth.modular.login.service;

import com.huanniankj.auth.core.pojo.SaBaseClientLoginUser;
import com.huanniankj.auth.core.pojo.SaBaseLoginUser;
import com.huanniankj.auth.modular.login.param.AuthAccountPasswordLoginParam;
import com.huanniankj.auth.modular.login.param.AuthEmailValidCodeLoginParam;
import com.huanniankj.auth.modular.login.param.AuthGetEmailValidCodeParam;
import com.huanniankj.auth.modular.login.param.AuthGetPhoneValidCodeParam;
import com.huanniankj.auth.modular.login.param.AuthPhoneValidCodeLoginParam;
import com.huanniankj.auth.modular.login.param.AuthRegisterParam;
import com.huanniankj.auth.modular.login.result.AuthPicValidCodeResult;

/**
 * 登录服务接口
 *
 * @author happynewyear
 */
public interface AuthService {

    /**
     * 获取图片验证码
     */
    AuthPicValidCodeResult getPicCaptcha(String type);

    /**
     * 获取手机登录验证码
     */
    String getPhoneValidCode(AuthGetPhoneValidCodeParam authGetPhoneValidCodeParam, String type);

    /**
     * 获取邮箱登录验证码
     */
    String getEmailValidCode(AuthGetEmailValidCodeParam authGetEmailValidCodeParam, String type);

    /**
     * 账号密码登录
     */
    String doLogin(AuthAccountPasswordLoginParam authAccountPasswordLoginParam, String type);

    /**
     * 手机验证码登录
     */
    String doLoginByPhone(AuthPhoneValidCodeLoginParam authPhoneValidCodeLoginParam, String type);

    /**
     * 邮箱验证码登录
     */
    String doLoginByEmail(AuthEmailValidCodeLoginParam authEmailValidCodeLoginParam, String type);

    /**
     * 获取B端登录用户信息
     */
    SaBaseLoginUser getLoginUser();

    /**
     * 获取C端登录用户信息
     */
    SaBaseClientLoginUser getClientLoginUser();

    /**
     * 根据用户id和客户端类型登录，用于第三方登录
     */
    String doLoginById(String userId, String device, String type);

    /**
     * C端注册
     */
    void register(AuthRegisterParam authRegisterParam, String type);
}
