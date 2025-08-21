package com.huanniankj.auth.modular.third.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.auth.modular.third.entity.AuthThirdUser;
import com.huanniankj.auth.modular.third.param.AuthThirdCallbackParam;
import com.huanniankj.auth.modular.third.param.AuthThirdRenderParam;
import com.huanniankj.auth.modular.third.param.AuthThirdUserPageParam;
import com.huanniankj.auth.modular.third.result.AuthThirdRenderResult;
import me.zhyd.oauth.model.AuthCallback;

/**
 * 第三方登录服务接口
 *
 * @author happynewyear
 */
public interface AuthThirdService extends IService<AuthThirdUser> {

    /**
     * 第三方登录页面渲染，返回授权结果
     */
    AuthThirdRenderResult render(AuthThirdRenderParam authThirdRenderParam);

    /**
     * 第三方登录授权回调，返回登录token
     */
    String callback(AuthThirdCallbackParam authThirdCallbackParam, AuthCallback authCallback);

    /**
     * 获取三方用户分页
     */
    Page<AuthThirdUser> page(AuthThirdUserPageParam authThirdUserPageParam);
}
