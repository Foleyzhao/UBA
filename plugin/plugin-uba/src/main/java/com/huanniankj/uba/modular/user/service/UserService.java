package com.huanniankj.uba.modular.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.uba.modular.user.entity.User;
import com.huanniankj.uba.modular.user.param.UserAddParam;
import com.huanniankj.uba.modular.user.param.UserEditParam;
import com.huanniankj.uba.modular.user.param.UserIdParam;
import com.huanniankj.uba.modular.user.param.UserPageParam;

import java.util.Date;
import java.util.List;

/**
 * 运营用户服务接口
 *
 * @author happynewyear
 */
public interface UserService extends IService<User> {

    /**
     * 获取运营用户分页
     */
    Page<User> page(UserPageParam sysUserPageParam);

    /**
     * 添加运营用户
     */
    void add(UserAddParam userAddParam, String source);

    /**
     * 编辑运营用户
     */
    void edit(UserEditParam userEditParam);

    /**
     * 删除运营用户
     */
    void delete(List<UserIdParam> userIdParamList);

    /**
     * 获取运营用户详情
     */
    User detail(UserIdParam userIdParam);

    /**
     * 获取运营用户详情
     */
    User queryEntity(String id);

    /**
     * 禁用运营用户
     */
    void disableUser(UserIdParam userIdParam);

    /**
     * 启用运营用户
     */
    void enableUser(UserIdParam userIdParam);

    /**
     * 更新运营用户的上次操作时间
     */
    void updateUserLastOperateTime(String userId, Date lastOperateTime);

    /**
     * 更新运营用户的昵称
     */
    void updateUserNickname(String userId, String nickname);

}
