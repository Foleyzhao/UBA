package com.huanniankj.auth.modular.sso.service.impl;

import com.huanniankj.auth.modular.login.service.AuthService;
import com.huanniankj.auth.modular.sso.param.AuthSsoTicketLoginParam;
import com.huanniankj.auth.modular.sso.service.AuthSsoService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 单点登录服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class AuthSsoServiceImpl implements AuthSsoService {

    @Resource
    private AuthService authService;

    @Override
    public String doLogin(AuthSsoTicketLoginParam authAccountPasswordLoginParam, String device) {
        return null;
    }
}
