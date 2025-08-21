package com.huanniankj.sys.modular.group.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.sys.modular.group.entity.SysGroup;
import com.huanniankj.sys.modular.group.mapper.SysGroupMapper;
import com.huanniankj.sys.modular.group.param.SysGroupAddParam;
import com.huanniankj.sys.modular.group.param.SysGroupEditParam;
import com.huanniankj.sys.modular.group.param.SysGroupGrantUserParam;
import com.huanniankj.sys.modular.group.param.SysGroupIdParam;
import com.huanniankj.sys.modular.group.param.SysGroupPageParam;
import com.huanniankj.sys.modular.group.param.SysGroupSelectorParam;
import com.huanniankj.sys.modular.group.param.SysGroupSelectorUserParam;
import com.huanniankj.sys.modular.group.service.SysGroupService;
import com.huanniankj.sys.modular.org.entity.SysOrg;
import com.huanniankj.sys.modular.org.service.SysOrgService;
import com.huanniankj.sys.modular.relation.entity.SysRelation;
import com.huanniankj.sys.modular.relation.enums.SysRelationCategoryEnum;
import com.huanniankj.sys.modular.relation.service.SysRelationService;
import com.huanniankj.sys.modular.user.entity.SysUser;
import com.huanniankj.sys.modular.user.enums.SysUserStatusEnum;
import com.huanniankj.sys.modular.user.service.SysUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户组服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class SysGroupServiceImpl extends ServiceImpl<SysGroupMapper, SysGroup> implements SysGroupService {

    @Resource
    private SysRelationService sysRelationService;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysOrgService sysOrgService;

    @Override
    public Page<SysGroup> page(SysGroupPageParam sysGroupPageParam) {
        QueryWrapper<SysGroup> queryWrapper = new QueryWrapper<SysGroup>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(sysGroupPageParam.getName())) {
            queryWrapper.lambda().like(SysGroup::getName, sysGroupPageParam.getName());
        }
        if (ObjectUtil.isAllNotEmpty(sysGroupPageParam.getSortField(), sysGroupPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(sysGroupPageParam.getSortOrder());
            queryWrapper.orderBy(true, sysGroupPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(sysGroupPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(SysGroup::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(SysGroupAddParam sysGroupAddParam) {
        SysGroup sysGroup = BeanUtil.toBean(sysGroupAddParam, SysGroup.class);
        this.save(sysGroup);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(SysGroupEditParam sysGroupEditParam) {
        SysGroup sysGroup = this.queryEntity(sysGroupEditParam.getId());
        BeanUtil.copyProperties(sysGroupEditParam, sysGroup);
        this.updateById(sysGroup);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<SysGroupIdParam> sysGroupIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(sysGroupIdParamList, SysGroupIdParam::getId));
    }

    @Override
    public SysGroup detail(SysGroupIdParam sysGroupIdParam) {
        return this.queryEntity(sysGroupIdParam.getId());
    }

    @Override
    public SysGroup queryEntity(String id) {
        SysGroup sysGroup = this.getById(id);
        if (ObjectUtil.isEmpty(sysGroup)) {
            throw new CommonException("用户组不存在，id值为：{}", id);
        }
        return sysGroup;
    }

    @Override
    public List<String> ownUser(SysGroupIdParam sysGroupIdParam) {
        return sysRelationService.getRelationObjectIdListByTargetIdAndCategory(sysGroupIdParam.getId(),
                SysRelationCategoryEnum.SYS_USER_HAS_GROUP.getValue());
    }

    @Override
    public List<Tree<String>> orgTreeSelector() {
        List<SysOrg> sysOrgList = sysOrgService.getAllOrgList();
        List<TreeNode<String>> treeNodeList = sysOrgList.stream().map(sysOrg ->
                        new TreeNode<>(sysOrg.getId(), sysOrg.getParentId(), sysOrg.getName(), sysOrg.getSortCode()))
                .collect(Collectors.toList());
        return TreeUtil.build(treeNodeList, "0");
    }

    @Override
    public Page<SysUser> userSelector(SysGroupSelectorUserParam sysGroupSelectorUserParam) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<SysUser>().checkSqlInjection();
        // 只查询状态为正常的
        queryWrapper.lambda().eq(SysUser::getUserStatus, SysUserStatusEnum.ENABLE.getValue());
        // 只查询部分字段
        queryWrapper.lambda().select(SysUser::getId, SysUser::getAvatar, SysUser::getOrgId, SysUser::getPositionId,
                SysUser::getAccount, SysUser::getName, SysUser::getSortCode, SysUser::getGender, SysUser::getEntryDate);
        // 如果查询条件为空，则直接查询
        if (ObjectUtil.isAllEmpty(sysGroupSelectorUserParam.getOrgId(), sysGroupSelectorUserParam.getSearchKey())) {
            return sysUserService.getAllUserSelectorList();
        } else {
            if (ObjectUtil.isNotEmpty(sysGroupSelectorUserParam.getOrgId())) {
                // 如果组织id不为空，则查询该组织及其子极其子下的所有人
                List<String> childOrgIdList = CollStreamUtil.toList(sysOrgService.getChildListById(sysOrgService
                        .getAllOrgList(), sysGroupSelectorUserParam.getOrgId(), true), SysOrg::getId);
                if (ObjectUtil.isNotEmpty(childOrgIdList)) {
                    queryWrapper.lambda().in(SysUser::getOrgId, childOrgIdList);
                } else {
                    return new Page<>();
                }
            }
            if (ObjectUtil.isNotEmpty(sysGroupSelectorUserParam.getSearchKey())) {
                queryWrapper.lambda().like(SysUser::getName, sysGroupSelectorUserParam.getSearchKey());
            }
            queryWrapper.lambda().orderByAsc(SysUser::getSortCode);
            return sysUserService.page(CommonPageRequest.defaultPage(), queryWrapper.lambda());
        }
    }

    @Override
    public void grantUser(SysGroupGrantUserParam sysGroupGrantUserParam) {
        String id = sysGroupGrantUserParam.getId();
        List<String> grantInfoList = sysGroupGrantUserParam.getGrantInfoList();
        sysRelationService.remove(new LambdaQueryWrapper<SysRelation>().eq(SysRelation::getTargetId, id)
                .eq(SysRelation::getCategory, SysRelationCategoryEnum.SYS_USER_HAS_GROUP.getValue()));
        sysRelationService.saveBatch(grantInfoList.stream().map(userId -> {
            SysRelation sysRelation = new SysRelation();
            sysRelation.setObjectId(userId);
            sysRelation.setTargetId(id);
            sysRelation.setCategory(SysRelationCategoryEnum.SYS_USER_HAS_GROUP.getValue());
            return sysRelation;
        }).collect(Collectors.toList()));
    }

    @Override
    public Page<SysGroup> groupSelector(SysGroupSelectorParam sysGroupSelectorParam) {
        QueryWrapper<SysGroup> queryWrapper = new QueryWrapper<SysGroup>().checkSqlInjection();
        // 只查询部分字段，排掉extJson
        queryWrapper.lambda().select(SysGroup::getId, SysGroup::getName, SysGroup::getSortCode, SysGroup::getRemark);
        queryWrapper.lambda().orderByAsc(SysGroup::getSortCode);
        // 如果查询条件为空，则直接查询
        if (!ObjectUtil.isAllEmpty(sysGroupSelectorParam.getSearchKey())) {
            if (ObjectUtil.isNotEmpty(sysGroupSelectorParam.getSearchKey())) {
                queryWrapper.lambda().like(SysGroup::getName, sysGroupSelectorParam.getSearchKey());
            }
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper.lambda());
    }

}
