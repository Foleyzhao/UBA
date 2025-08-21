package com.huanniankj.biz.modular.group.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.auth.core.util.StpLoginUserUtil;
import com.huanniankj.biz.modular.group.entity.BizGroup;
import com.huanniankj.biz.modular.group.mapper.BizGroupMapper;
import com.huanniankj.biz.modular.group.param.BizGroupAddParam;
import com.huanniankj.biz.modular.group.param.BizGroupEditParam;
import com.huanniankj.biz.modular.group.param.BizGroupGrantUserParam;
import com.huanniankj.biz.modular.group.param.BizGroupIdParam;
import com.huanniankj.biz.modular.group.param.BizGroupPageParam;
import com.huanniankj.biz.modular.group.param.BizGroupSelectorUserParam;
import com.huanniankj.biz.modular.group.service.BizGroupService;
import com.huanniankj.biz.modular.org.entity.BizOrg;
import com.huanniankj.biz.modular.org.service.BizOrgService;
import com.huanniankj.biz.modular.user.entity.BizUser;
import com.huanniankj.biz.modular.user.enums.BizUserStatusEnum;
import com.huanniankj.biz.modular.user.service.BizUserService;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.sys.api.SysGroupApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户组服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class BizGroupServiceImpl extends ServiceImpl<BizGroupMapper, BizGroup> implements BizGroupService {

    @Resource
    private SysGroupApi sysGroupApi;

    @Resource
    private BizUserService bizUserService;

    @Resource
    private BizOrgService bizOrgService;

    @Override
    public Page<BizGroup> page(BizGroupPageParam bizGroupPageParam) {
        QueryWrapper<BizGroup> queryWrapper = new QueryWrapper<BizGroup>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(bizGroupPageParam.getName())) {
            queryWrapper.lambda().like(BizGroup::getName, bizGroupPageParam.getName());
        }
        if (ObjectUtil.isAllNotEmpty(bizGroupPageParam.getSortField(), bizGroupPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(bizGroupPageParam.getSortOrder());
            queryWrapper.orderBy(true, bizGroupPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(bizGroupPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BizGroup::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizGroupAddParam bizGroupAddParam) {
        BizGroup bizGroup = BeanUtil.toBean(bizGroupAddParam, BizGroup.class);
        this.save(bizGroup);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizGroupEditParam bizGroupEditParam) {
        BizGroup bizGroup = this.queryEntity(bizGroupEditParam.getId());
        BeanUtil.copyProperties(bizGroupEditParam, bizGroup);
        this.updateById(bizGroup);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizGroupIdParam> bizGroupIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(bizGroupIdParamList, BizGroupIdParam::getId));
    }

    @Override
    public BizGroup detail(BizGroupIdParam bizGroupIdParam) {
        return this.queryEntity(bizGroupIdParam.getId());
    }

    @Override
    public BizGroup queryEntity(String id) {
        BizGroup bizGroup = this.getById(id);
        if (ObjectUtil.isEmpty(bizGroup)) {
            throw new CommonException("用户组不存在，id值为：{}", id);
        }
        return bizGroup;
    }

    @Override
    public List<String> ownUser(BizGroupIdParam sysGroupIdParam) {
        return sysGroupApi.ownUser(sysGroupIdParam.getId());
    }

    @Override
    public List<Tree<String>> orgTreeSelector() {
        // 获取所有机构
        List<BizOrg> allOrgList = bizOrgService.list();
        // 定义机构集合
        Set<BizOrg> bizOrgSet = CollectionUtil.newHashSet();
        // 校验数据范围
        List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
        if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
            loginUserDataScope.forEach(orgId ->
                    bizOrgSet.addAll(bizOrgService.getParentListById(allOrgList, orgId, true)));
        } else {
            return CollectionUtil.newArrayList();
        }
        List<TreeNode<String>> treeNodeList = bizOrgSet.stream().map(bizOrg ->
                        new TreeNode<>(bizOrg.getId(), bizOrg.getParentId(),
                                bizOrg.getName(), bizOrg.getSortCode()).setExtra(JSONUtil.parseObj(bizOrg)))
                .collect(Collectors.toList());
        return TreeUtil.build(treeNodeList, "0");
    }

    @Override
    public Page<BizUser> userSelector(BizGroupSelectorUserParam bizGroupSelectorUserParam) {
        QueryWrapper<BizUser> queryWrapper = new QueryWrapper<BizUser>().checkSqlInjection();
        // 只查询状态为正常的
        queryWrapper.lambda().eq(BizUser::getUserStatus, BizUserStatusEnum.ENABLE.getValue());
        // 校验数据范围
        List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
        if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
            queryWrapper.lambda().in(BizUser::getOrgId, loginUserDataScope);
        } else {
            return new Page<>();
        }
        // 只查询部分字段
        queryWrapper.lambda().select(BizUser::getId, BizUser::getAvatar, BizUser::getOrgId, BizUser::getPositionId,
                BizUser::getAccount, BizUser::getName, BizUser::getSortCode, BizUser::getGender, BizUser::getEntryDate);
        if (ObjectUtil.isNotEmpty(bizGroupSelectorUserParam.getOrgId())) {
            // 如果机构id不为空，则查询该机构及其子机构下的所有人
            List<String> childOrgIdList = CollStreamUtil.toList(bizOrgService.getChildListById(bizOrgService
                    .getAllOrgList(), bizGroupSelectorUserParam.getOrgId(), true), BizOrg::getId);
            if (ObjectUtil.isNotEmpty(childOrgIdList)) {
                queryWrapper.lambda().in(BizUser::getOrgId, childOrgIdList);
            } else {
                return new Page<>();
            }
        }
        if (ObjectUtil.isNotEmpty(bizGroupSelectorUserParam.getSearchKey())) {
            queryWrapper.lambda().like(BizUser::getName, bizGroupSelectorUserParam.getSearchKey());
        }
        queryWrapper.lambda().orderByAsc(BizUser::getSortCode);
        return bizUserService.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public void grantUser(BizGroupGrantUserParam bizGroupGrantUserParam) {
        sysGroupApi.grantUser(bizGroupGrantUserParam.getId(), bizGroupGrantUserParam.getGrantInfoList());
    }
}
