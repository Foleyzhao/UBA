package com.huanniankj.client.modular.user.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.huanniankj.auth.api.SaBaseLoginUserApi;
import com.huanniankj.auth.core.pojo.SaBaseClientLoginUser;
import com.huanniankj.auth.core.pojo.SaBaseLoginUser;
import com.huanniankj.client.modular.user.entity.ClientUser;
import com.huanniankj.client.modular.user.result.ClientLoginUser;
import com.huanniankj.client.modular.user.service.ClientUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * C端登录用户API接口实现类
 *
 * @author happynewyear
 */
@Service("clientLoginUserApi")
public class ClientLoginUserApiProvider implements SaBaseLoginUserApi {

    @Resource
    private ClientUserService clientUserService;

    /**
     * 不实现B端用户信息
     */
    @Override
    public SaBaseLoginUser getUserById(String id) {
        return null;
    }

    /**
     * 根据id获取C端用户信息，查不到则返回null
     */
    @Override
    public SaBaseClientLoginUser getClientUserById(String id) {
        return clientUserService.getUserById(id);
    }

    /**
     * 不实现B端用户信息
     */
    @Override
    public SaBaseLoginUser getUserByAccount(String account) {
        return null;
    }

    /**
     * 根据账号获取C端用户信息，查不到则返回null
     */
    @Override
    public ClientLoginUser getClientUserByAccount(String account) {
        return clientUserService.getUserByAccount(account);
    }

    /**
     * 不实现B端用户信息
     */
    @Override
    public SaBaseLoginUser getUserByPhone(String phone) {
        return null;
    }

    @Override
    public SaBaseLoginUser getUserByEmail(String email) {
        return null;
    }

    /**
     * 根据手机号获取C端用户信息，查不到则返回null
     */
    @Override
    public SaBaseClientLoginUser getClientUserByPhone(String phone) {
        return clientUserService.getUserByPhone(phone);
    }

    @Override
    public SaBaseClientLoginUser getClientUserByEmail(String email) {
        return clientUserService.getUserByEmail(email);
    }

    /**
     * 根据用户id获取用户集合
     */
    @Override
    public List<JSONObject> listUserByUserIdList(List<String> userIdList) {
        return clientUserService.listByIds(userIdList).stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }

    /**
     * 根据用户id获取角色集合
     */
    @Override
    public List<JSONObject> getRoleListByUserId(String userId) {
        // TODO C端用户暂无角色
        return CollectionUtil.newArrayList();
    }

    /**
     * 根据角色id和用户id集合获取按钮码集合
     */
    @Override
    public List<String> getButtonCodeListListByUserAndRoleIdList(List<String> userAndRoleIdList) {
        // TODO C端用户暂无按钮码
        return CollectionUtil.newArrayList();
    }

    /**
     * 根据角色id和用户id集合获取移动端按钮码集合
     */
    @Override
    public List<String> getMobileButtonCodeListListByUserIdAndRoleIdList(List<String> userAndRoleIdList) {
        // TODO C端用户暂无移动端按钮码
        return CollectionUtil.newArrayList();
    }

    /**
     * 根据角色id和用户id集合获取权限集合
     */
    @Override
    public List<JSONObject> getPermissionListByUserIdAndRoleIdList(List<String> userAndRoleIdList, String orgId) {
        // TODO C端用户暂无权限码
        return CollectionUtil.newArrayList();
    }

    /**
     * 更新用户的登录时间和登录ip等信息
     */
    @Override
    public void updateUserLoginInfo(String userId, String device) {
        clientUserService.updateUserLoginInfo(userId, device);
    }

    @Override
    public SaBaseLoginUser createUserWithPhone(String phone) {
        return null;
    }

    @Override
    public SaBaseClientLoginUser createClientUserWithPhone(String phone) {
        ClientUser clientUser = clientUserService.createUserWithPhone(phone);
        return BeanUtil.copyProperties(clientUser, SaBaseClientLoginUser.class);
    }

    @Override
    public SaBaseLoginUser createUserWithEmail(String email) {
        return null;
    }

    @Override
    public SaBaseClientLoginUser createClientUserWithEmail(String email) {
        ClientUser clientUser = clientUserService.createUserWithEmail(email);
        return BeanUtil.copyProperties(clientUser, SaBaseClientLoginUser.class);
    }

    @Override
    public void doRegister(String account, String password) {
        clientUserService.doRegister(account, password);
    }
}
