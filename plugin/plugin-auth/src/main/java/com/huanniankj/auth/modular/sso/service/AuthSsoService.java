package com.huanniankj.auth.modular.sso.service;

import com.huanniankj.auth.modular.sso.param.AuthSsoTicketLoginParam;

/**
 * 单点登录服务接口
 *
 * @author happynewyear
 */
public interface AuthSsoService {

    /**
     * 根据ticket执行单点登录
     */
    String doLogin(AuthSsoTicketLoginParam authAccountPasswordLoginParam, String value);
    
}
