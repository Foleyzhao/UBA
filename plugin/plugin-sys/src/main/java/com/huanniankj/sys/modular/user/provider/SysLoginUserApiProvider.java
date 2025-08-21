package com.huanniankj.sys.modular.user.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.huanniankj.auth.api.SaBaseLoginUserApi;
import com.huanniankj.auth.core.pojo.SaBaseClientLoginUser;
import com.huanniankj.auth.core.pojo.SaBaseLoginUser;
import com.huanniankj.sys.modular.user.entity.SysUser;
import com.huanniankj.sys.modular.user.result.SysLoginUser;
import com.huanniankj.sys.modular.user.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 登录用户API接口实现类
 *
 * @author happynewyear
 */
@Service("loginUserApi")
public class SysLoginUserApiProvider implements SaBaseLoginUserApi {

    @Resource
    private SysUserService sysUserService;

    /**
     * 根据id获取B端用户信息，查不到则返回null
     */
    @Override
    public SaBaseLoginUser getUserById(String id) {
        return sysUserService.getUserById(id);
    }

    /**
     * 不实现C端用户信息
     */
    @Override
    public SaBaseClientLoginUser getClientUserById(String id) {
        return null;
    }

    /**
     * 根据账号获取B端用户信息，查不到则返回null
     */
    @Override
    public SysLoginUser getUserByAccount(String account) {
        return sysUserService.getUserByAccount(account);
    }

    /**
     * 不实现C端用户信息
     */
    @Override
    public SaBaseClientLoginUser getClientUserByAccount(String account) {
        return null;
    }

    /**
     * 根据手机号获取B端用户信息，查不到则返回null
     */
    @Override
    public SaBaseLoginUser getUserByPhone(String phone) {
        return sysUserService.getUserByPhone(phone);
    }

    @Override
    public SaBaseLoginUser getUserByEmail(String email) {
        return sysUserService.getUserByEmail(email);
    }

    /**
     * 不实现C端用户信息
     */
    @Override
    public SaBaseClientLoginUser getClientUserByPhone(String phone) {
        return null;
    }

    @Override
    public SaBaseClientLoginUser getClientUserByEmail(String email) {
        return null;
    }

    /**
     * 根据用户id获取用户集合
     */
    @Override
    public List<JSONObject> listUserByUserIdList(List<String> userIdList) {
        return sysUserService.listByIds(userIdList).stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }

    /**
     * 根据用户id获取角色集合
     */
    @Override
    public List<JSONObject> getRoleListByUserId(String userId) {
        return sysUserService.getRoleList(userId);
    }

    /**
     * 根据角色id和用户id集合获取按钮码集合
     */
    @Override
    public List<String> getButtonCodeListListByUserAndRoleIdList(List<String> userAndRoleIdList) {
        return sysUserService.getButtonCodeList(userAndRoleIdList);
    }

    /**
     * 根据角色id和用户id集合获取移动端按钮码集合
     */
    @Override
    public List<String> getMobileButtonCodeListListByUserIdAndRoleIdList(List<String> userAndRoleIdList) {
        return sysUserService.getMobileButtonCodeList(userAndRoleIdList);
    }

    /**
     * 根据角色id和用户id集合获取权限集合
     */
    @Override
    public List<JSONObject> getPermissionListByUserIdAndRoleIdList(List<String> userAndRoleIdList, String orgId) {
        return sysUserService.getPermissionList(userAndRoleIdList, orgId);
    }

    /**
     * 更新用户的登录时间和登录ip等信息
     */
    @Override
    public void updateUserLoginInfo(String userId, String device) {
        sysUserService.updateUserLoginInfo(userId, device);
    }

    @Override
    public SaBaseLoginUser createUserWithPhone(String phone) {
        SysUser sysUser = sysUserService.createUserWithPhone(phone);
        return BeanUtil.copyProperties(sysUser, SysLoginUser.class);
    }

    @Override
    public SaBaseClientLoginUser createClientUserWithPhone(String phone) {
        return null;
    }

    @Override
    public SaBaseLoginUser createUserWithEmail(String email) {
        SysUser sysUser = sysUserService.createUserWithEmail(email);
        return BeanUtil.copyProperties(sysUser, SysLoginUser.class);
    }

    @Override
    public SaBaseClientLoginUser createClientUserWithEmail(String email) {
        return null;
    }

    @Override
    public void doRegister(String account, String password) {
        sysUserService.doRegister(account, password);
    }

}
