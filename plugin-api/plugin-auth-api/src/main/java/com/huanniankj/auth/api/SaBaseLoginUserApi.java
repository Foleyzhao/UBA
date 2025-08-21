package com.huanniankj.auth.api;

import cn.hutool.json.JSONObject;
import com.huanniankj.auth.core.pojo.SaBaseClientLoginUser;
import com.huanniankj.auth.core.pojo.SaBaseLoginUser;

import java.util.List;

/**
 * 登录用户API，由其他模块实现
 *
 * @author happynewyear
 */
public interface SaBaseLoginUserApi {

    /**
     * 根据id获取B端用户信息，查不到则返回null
     */
    SaBaseLoginUser getUserById(String id);

    /**
     * 根据id获取C端用户信息，查不到则返回null
     */
    SaBaseClientLoginUser getClientUserById(String id);

    /**
     * 根据账号获取B端用户信息，查不到则返回null
     */
    SaBaseLoginUser getUserByAccount(String account);

    /**
     * 根据账号获取C端用户信息，查不到则返回null
     */
    SaBaseClientLoginUser getClientUserByAccount(String account);

    /**
     * 根据手机号获取B端用户信息，查不到则返回null
     */
    SaBaseLoginUser getUserByPhone(String phone);

    /**
     * 根据邮箱获取B端用户信息，查不到则返回null
     */
    SaBaseLoginUser getUserByEmail(String email);

    /**
     * 根据手机号获取C端用户信息，查不到则返回null
     */
    SaBaseClientLoginUser getClientUserByPhone(String phone);

    /**
     * 根据邮箱获取C端用户信息，查不到则返回null
     */
    SaBaseClientLoginUser getClientUserByEmail(String email);

    /**
     * 根据用户id获取用户集合
     */
    List<JSONObject> listUserByUserIdList(List<String> userIdList);

    /**
     * 根据用户id获取角色集合
     */
    List<JSONObject> getRoleListByUserId(String userId);

    /**
     * 根据角色id和用户id集合获取按钮码集合
     */
    List<String> getButtonCodeListListByUserAndRoleIdList(List<String> userAndRoleIdList);

    /**
     * 根据角色id和用户id集合获取移动端按钮码集合
     */
    List<String> getMobileButtonCodeListListByUserIdAndRoleIdList(List<String> userAndRoleIdList);

    /**
     * 根据角色id和用户id集合获取权限集合
     */
    List<JSONObject> getPermissionListByUserIdAndRoleIdList(List<String> userAndRoleIdList, String orgId);

    /**
     * 更新用户的登录时间和登录ip等信息
     */
    void updateUserLoginInfo(String userId, String device);

    /**
     * 使用手机号创建B端用户
     */
    SaBaseLoginUser createUserWithPhone(String phone);

    /**
     * 使用手机号创建C端用户
     */
    SaBaseClientLoginUser createClientUserWithPhone(String phone);

    /**
     * 使用邮箱创建B端用户
     */
    SaBaseLoginUser createUserWithEmail(String email);

    /**
     * 使用邮箱创建C端用户
     */
    SaBaseClientLoginUser createClientUserWithEmail(String email);

    /**
     * 执行注册
     */
    void doRegister(String account, String password);
}
