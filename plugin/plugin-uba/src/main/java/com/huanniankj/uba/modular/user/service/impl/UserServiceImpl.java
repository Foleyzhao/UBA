package com.huanniankj.uba.modular.user.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.uba.modular.user.entity.User;
import com.huanniankj.uba.modular.user.entity.UserTag;
import com.huanniankj.uba.modular.user.enums.UserStatusEnum;
import com.huanniankj.uba.modular.user.mapper.UserMapper;
import com.huanniankj.uba.modular.user.param.UserAddParam;
import com.huanniankj.uba.modular.user.param.UserEditParam;
import com.huanniankj.uba.modular.user.param.UserIdParam;
import com.huanniankj.uba.modular.user.param.UserPageParam;
import com.huanniankj.uba.modular.user.service.UserService;
import com.huanniankj.uba.modular.user.service.UserTagService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * 运营用户服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private UserTagService userTagService;

    @Override
    public Page<User> page(UserPageParam sysUserPageParam) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(sysUserPageParam.getSearchKey())) {
            queryWrapper.lambda().and(
                    q -> q.like(User::getAccount, sysUserPageParam.getSearchKey()));
        }
        if (ObjectUtil.isNotEmpty(sysUserPageParam.getUserStatus())) {
            queryWrapper.lambda().eq(User::getUserStatus, sysUserPageParam.getUserStatus());
        }
        if (ObjectUtil.isAllNotEmpty(sysUserPageParam.getSortField(), sysUserPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sysUserPageParam.getSortOrder());
            queryWrapper.orderBy(true, sysUserPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sysUserPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(User::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(UserAddParam userAddParam, String source) {
        checkParam(userAddParam);
        User user = BeanUtil.toBean(userAddParam, User.class);
        // 设置状态
        user.setUserStatus(UserStatusEnum.ENABLE.getValue());
        // 设置来源类型
        user.setSource(source);
        // 保存用户
        this.save(user);
    }

    private void checkParam(UserAddParam userAddParam) {
        if (this.count(new LambdaQueryWrapper<User>()
                .eq(User::getAccount, userAddParam.getAccount())) > 0) {
            throw new CommonException("存在重复的运营用户的关联账号，关联账号为：{}", userAddParam.getAccount());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(UserEditParam userEditParam) {
        User user = this.queryEntity(userEditParam.getId());
        checkParam(userEditParam);
        BeanUtil.copyProperties(userEditParam, user);
        // 更新用户
        this.updateById(user);
    }

    private void checkParam(UserEditParam userEditParam) {
        if (this.count(new LambdaQueryWrapper<User>()
                .eq(User::getAccount, userEditParam.getAccount())
                .ne(User::getId, userEditParam.getId())) > 0) {
            throw new CommonException("存在重复的运营用户的关联账号，关联账号为：{}", userEditParam.getAccount());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<UserIdParam> userIdParamList) {
        List<String> userIdList = CollStreamUtil.toList(userIdParamList, UserIdParam::getId);
        if (ObjectUtil.isNotEmpty(userIdList)) {
            // 执行删除
            this.removeByIds(userIdList);
            // 删除标签信息
            userTagService.remove(new LambdaQueryWrapper<UserTag>().in(UserTag::getUserId, userIdList));
        }
    }

    @Override
    public User detail(UserIdParam userIdParam) {
        return this.queryEntity(userIdParam.getId());
    }

    @Override
    public User queryEntity(String id) {
        User user = this.getById(id);
        if (ObjectUtil.isEmpty(user)) {
            throw new CommonException("运营用户不存在，id值为：{}", id);
        }
        return user;
    }

    @Override
    public void disableUser(UserIdParam userIdParam) {
        this.update(new LambdaUpdateWrapper<User>().eq(User::getId,
                userIdParam.getId()).set(User::getUserStatus, UserStatusEnum.DISABLED.getValue()));
    }

    @Override
    public void enableUser(UserIdParam userIdParam) {
        this.update(new LambdaUpdateWrapper<User>().eq(User::getId,
                userIdParam.getId()).set(User::getUserStatus, UserStatusEnum.ENABLE.getValue()));
    }

    @Override
    public void updateUserLastOperateTime(String userId, Date lastOperateTime) {
        User user = this.queryEntity(userId);
        user.setLastOperateTime(lastOperateTime);
        this.updateById(user);
    }

    @Override
    public void updateUserNickname(String userId, String nickname) {
        User user = this.queryEntity(userId);
        user.setNickname(nickname);
        // 更新指定字段
        this.updateById(user);
    }

}
