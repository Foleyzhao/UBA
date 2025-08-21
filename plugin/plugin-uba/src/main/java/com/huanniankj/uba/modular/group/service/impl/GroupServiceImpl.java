package com.huanniankj.uba.modular.group.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.uba.modular.group.entity.Group;
import com.huanniankj.uba.modular.group.mapper.GroupMapper;
import com.huanniankj.uba.modular.group.param.GroupAddParam;
import com.huanniankj.uba.modular.group.param.GroupEditParam;
import com.huanniankj.uba.modular.group.param.GroupIdParam;
import com.huanniankj.uba.modular.group.param.GroupPageParam;
import com.huanniankj.uba.modular.group.service.GroupService;
import com.huanniankj.uba.modular.user.service.UserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 运营用户组服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class GroupServiceImpl extends ServiceImpl<GroupMapper, Group> implements GroupService {

    @Resource
    private UserService sysUserService;

    @Override
    public Page<Group> page(GroupPageParam groupPageParam) {
        QueryWrapper<Group> queryWrapper = new QueryWrapper<Group>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(groupPageParam.getName())) {
            queryWrapper.lambda().like(Group::getName, groupPageParam.getName());
        }
        if (ObjectUtil.isAllNotEmpty(groupPageParam.getSortField(), groupPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(groupPageParam.getSortOrder());
            queryWrapper.orderBy(true, groupPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(groupPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Group::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(GroupAddParam groupAddParam) {
        Group group = BeanUtil.toBean(groupAddParam, Group.class);
        this.save(group);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(GroupEditParam groupEditParam) {
        Group group = this.queryEntity(groupEditParam.getId());
        BeanUtil.copyProperties(groupEditParam, group);
        this.updateById(group);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<GroupIdParam> groupIdParams) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(groupIdParams, GroupIdParam::getId));
    }

    @Override
    public Group detail(GroupIdParam groupIdParam) {
        return this.queryEntity(groupIdParam.getId());
    }

    @Override
    public Group queryEntity(String id) {
        Group sysGroup = this.getById(id);
        if (ObjectUtil.isEmpty(sysGroup)) {
            throw new CommonException("用户组不存在，id值为：{}", id);
        }
        return sysGroup;
    }

}
