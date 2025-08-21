package com.huanniankj.biz.modular.org.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.lang.tree.TreeNode;
import cn.hutool.core.lang.tree.TreeUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.biz.modular.org.param.BizOrgAddParam;
import com.huanniankj.biz.modular.org.param.BizOrgEditParam;
import com.huanniankj.biz.modular.org.param.BizOrgIdParam;
import com.huanniankj.biz.modular.org.param.BizOrgPageParam;
import com.huanniankj.biz.modular.org.param.BizOrgSelectorOrgListParam;
import com.huanniankj.biz.modular.org.param.BizOrgSelectorUserParam;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huanniankj.auth.core.util.StpLoginUserUtil;
import com.huanniankj.biz.core.enums.BizDataTypeEnum;
import com.huanniankj.biz.modular.org.entity.BizOrg;
import com.huanniankj.biz.modular.org.enums.BizOrgCategoryEnum;
import com.huanniankj.biz.modular.org.mapper.BizOrgMapper;
import com.huanniankj.biz.modular.org.service.BizOrgExtService;
import com.huanniankj.biz.modular.org.service.BizOrgService;
import com.huanniankj.biz.modular.position.entity.BizPosition;
import com.huanniankj.biz.modular.position.service.BizPositionService;
import com.huanniankj.biz.modular.user.entity.BizUser;
import com.huanniankj.biz.modular.user.service.BizUserService;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.listener.CommonDataChangeEventCenter;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.sys.api.SysRoleApi;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 机构服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class BizOrgServiceImpl extends ServiceImpl<BizOrgMapper, BizOrg> implements BizOrgService {

    @Resource
    private SysRoleApi sysRoleApi;

    @Resource
    private BizOrgExtService bizOrgExtService;

    @Resource
    private BizPositionService bizPositionService;

    @Resource
    private BizUserService bizUserService;

    @Override
    public Page<BizOrg> page(BizOrgPageParam bizOrgPageParam) {
        QueryWrapper<BizOrg> queryWrapper = new QueryWrapper<BizOrg>().checkSqlInjection();
        // 查询部分字段
        queryWrapper.lambda().select(BizOrg::getId, BizOrg::getParentId, BizOrg::getName,
                BizOrg::getCategory, BizOrg::getSortCode);
        if (ObjectUtil.isNotEmpty(bizOrgPageParam.getParentId())) {
            queryWrapper.lambda().eq(BizOrg::getParentId, bizOrgPageParam.getParentId());
        }
        if (ObjectUtil.isNotEmpty(bizOrgPageParam.getSearchKey())) {
            queryWrapper.lambda().like(BizOrg::getName, bizOrgPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(bizOrgPageParam.getSortField(), bizOrgPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(bizOrgPageParam.getSortOrder());
            queryWrapper.orderBy(true, bizOrgPageParam.getSortOrder().equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(bizOrgPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BizOrg::getSortCode);
        }
        // 校验数据范围
        List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
        if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
            queryWrapper.lambda().in(BizOrg::getId, loginUserDataScope);
        } else {
            return new Page<>();
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public List<Tree<String>> tree() {
        // 获取所有机构
        List<BizOrg> allOrgList = this.list();
        // 定义机构集合
        Set<BizOrg> bizOrgSet = CollectionUtil.newHashSet();
        // 校验数据范围
        List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
        if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
            loginUserDataScope.forEach(orgId -> bizOrgSet.addAll(this.getParentListById(allOrgList, orgId,
                    true)));
        } else {
            return CollectionUtil.newArrayList();
        }
        // 先根据排序码排序
        List<BizOrg> bizOrgArrayList = CollectionUtil.sort(bizOrgSet, Comparator.comparingInt(BizOrg::getSortCode));
        // 再重置排序码，解决每次相同排序码顺序不一致的问题
        for (int i = 0; i < bizOrgArrayList.size(); i++) {
            bizOrgArrayList.get(i).setSortCode(i);
        }
        List<TreeNode<String>> treeNodeList = bizOrgArrayList.stream().map(bizOrg ->
                        new TreeNode<>(bizOrg.getId(), bizOrg.getParentId(),
                                bizOrg.getName(), bizOrg.getSortCode()).setExtra(JSONUtil.parseObj(bizOrg)))
                .collect(Collectors.toList());
        return TreeUtil.build(treeNodeList, "0");
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizOrgAddParam bizOrgAddParam, String sourceFromType) {
        BizOrgCategoryEnum.validate(bizOrgAddParam.getCategory());
        // 校验数据范围
        List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
        if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
            if (!loginUserDataScope.contains(bizOrgAddParam.getParentId())) {
                throw new CommonException("您没有权限在该机构下增加机构，机构id：{}", bizOrgAddParam.getParentId());
            }
        } else {
            throw new CommonException("您没有权限增加机构");
        }
        BizOrg bizOrg = BeanUtil.toBean(bizOrgAddParam, BizOrg.class);

        // 重复名称
        boolean repeatName = this.count(new LambdaQueryWrapper<BizOrg>().eq(BizOrg::getParentId, bizOrg.getParentId())
                .eq(BizOrg::getName, bizOrg.getName())) > 0;
        if (repeatName) {
            throw new CommonException("存在重复的同级机构，名称为：{}", bizOrg.getName());
        }
        bizOrg.setCode(RandomUtil.randomString(10));
        // 保存机构
        this.save(bizOrg);
        // 插入扩展信息
        bizOrgExtService.createExtInfo(bizOrg.getId(), sourceFromType);
        // 发布增加事件
        CommonDataChangeEventCenter.doAddWithData(BizDataTypeEnum.ORG.getValue(), JSONUtil.createArray().put(bizOrg));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizOrgEditParam bizOrgEditParam) {
        BizOrgCategoryEnum.validate(bizOrgEditParam.getCategory());
        BizOrg bizOrg = this.queryEntity(bizOrgEditParam.getId());
        // 校验数据范围
        List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
        if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
            if (!loginUserDataScope.contains(bizOrg.getId())) {
                throw new CommonException("您没有权限编辑该机构，机构id：{}", bizOrg.getId());
            }
            if (!loginUserDataScope.contains(bizOrg.getParentId())) {
                throw new CommonException("您没有权限编辑该机构下的机构，机构id：{}", bizOrg.getParentId());
            }
        } else {
            throw new CommonException("您没有权限编辑该机构，机构id：{}", bizOrg.getId());
        }
        BeanUtil.copyProperties(bizOrgEditParam, bizOrg);
        boolean repeatName = this.count(new LambdaQueryWrapper<BizOrg>().eq(BizOrg::getParentId, bizOrg.getParentId())
                .eq(BizOrg::getName, bizOrg.getName()).ne(BizOrg::getId, bizOrg.getId())) > 0;
        if (repeatName) {
            throw new CommonException("存在重复的同级机构，名称为：{}", bizOrg.getName());
        }
        List<BizOrg> originDataList = this.list();
        boolean errorLevel = this.getChildListById(originDataList, bizOrg.getId(), true).stream()
                .map(BizOrg::getId).toList().contains(bizOrg.getParentId());
        if (errorLevel) {
            throw new CommonException("不可选择上级机构：{}", this.getById(originDataList, bizOrg.getParentId()).getName());
        }
        // 更新机构
        this.updateById(bizOrg);
        // 发布更新事件
        CommonDataChangeEventCenter.doUpdateWithData(BizDataTypeEnum.ORG.getValue(), JSONUtil.createArray().put(bizOrg));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizOrgIdParam> bizOrgIdParamList) {
        List<String> orgIdList = CollStreamUtil.toList(bizOrgIdParamList, BizOrgIdParam::getId);
        if (ObjectUtil.isNotEmpty(orgIdList)) {
            // 校验数据范围
            List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
            if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
                if (!new HashSet<>(loginUserDataScope).containsAll(orgIdList)) {
                    throw new CommonException("您没有权限删除这些机构，机构id：{}", orgIdList);
                }
            } else {
                throw new CommonException("您没有权限删除这些机构，机构id：{}", orgIdList);
            }
            List<BizOrg> allOrgList = this.list();
            // 获取所有子机构
            List<String> toDeleteOrgIdList = CollectionUtil.newArrayList();
            orgIdList.forEach(orgId -> toDeleteOrgIdList.addAll(
                    this.getChildListById(allOrgList, orgId, true).stream()
                            .map(BizOrg::getId).toList()));
            // 机构下有人不能删除（直属机构）
            boolean hasOrgUser = bizUserService.count(
                    new LambdaQueryWrapper<BizUser>().in(BizUser::getOrgId, toDeleteOrgIdList)) > 0;
            if (hasOrgUser) {
                throw new CommonException("请先删除机构下的人员");
            }
            // 机构下有人不能删除（兼任机构）
            List<String> positionJsonList = bizUserService.list(new LambdaQueryWrapper<BizUser>()
                    .isNotNull(BizUser::getPositionJson)).stream().map(BizUser::getPositionJson)
                    .collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(positionJsonList)) {
                List<String> positionOrgIdList = CollectionUtil.newArrayList();
                positionJsonList.forEach(positionJson -> JSONUtil.toList(JSONUtil.parseArray(positionJson),
                                JSONObject.class)
                        .forEach(jsonObject -> positionOrgIdList.add(jsonObject.getStr("orgId"))));
                boolean hasPositionUser = !CollectionUtil.intersectionDistinct(toDeleteOrgIdList,
                        CollectionUtil.removeNull(positionOrgIdList)).isEmpty();
                if (hasPositionUser) {
                    throw new CommonException("请先删除机构下的人员");
                }
            }
            // 机构下有角色不能删除
            boolean hasRole = sysRoleApi.orgHasRole(toDeleteOrgIdList);
            if (hasRole) {
                throw new CommonException("请先删除机构下的角色");
            }
            // 机构下有岗位不能删除
            boolean hasPosition = bizPositionService.count(new LambdaQueryWrapper<BizPosition>()
                    .in(BizPosition::getOrgId, toDeleteOrgIdList)) > 0;
            if (hasPosition) {
                throw new CommonException("请先删除机构下的岗位");
            }
            // 执行删除
            this.removeByIds(toDeleteOrgIdList);

            // 发布删除事件
            CommonDataChangeEventCenter.doDeleteWithDataIdList(BizDataTypeEnum.ORG.getValue(), toDeleteOrgIdList);
        }
    }

    @Override
    public BizOrg detail(BizOrgIdParam bizOrgIdParam) {
        return this.queryEntity(bizOrgIdParam.getId());
    }

    @Override
    public BizOrg queryEntity(String id) {
        BizOrg bizOrg = this.getById(id);
        if (ObjectUtil.isEmpty(bizOrg)) {
            throw new CommonException("机构不存在，id值为：{}", id);
        }
        return bizOrg;
    }

    @Override
    public List<BizOrg> getAllOrgList() {
        return this.list(new LambdaQueryWrapper<BizOrg>().orderByAsc(BizOrg::getSortCode));
    }

    @Override
    public String getOrgIdByOrgFullNameWithCreate(String orgFullName) {
        List<BizOrg> allOrgList = this.getAllOrgList();
        List<Tree<String>> treeList = TreeUtil.build(allOrgList.stream().map(bizOrg ->
                        new TreeNode<>(bizOrg.getId(), bizOrg.getParentId(), bizOrg.getName(), bizOrg.getSortCode()))
                .collect(Collectors.toList()), "0");
        return findOrgIdByOrgName("0", StrUtil.split(orgFullName, StrUtil.DASHED).iterator(), allOrgList,
                treeList);
    }

    public String findOrgIdByOrgName(String parentId, Iterator<String> iterator, List<BizOrg> allOrgList,
                                     List<Tree<String>> treeList) {
        String orgName = iterator.next();
        if (ObjectUtil.isNotEmpty(treeList)) {
            List<Tree<String>> findList = treeList.stream().filter(tree -> tree.getName().equals(orgName))
                    .collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(findList)) {
                if (iterator.hasNext()) {
                    return findOrgIdByOrgName(findList.get(0).getId(), iterator, allOrgList, findList.get(0)
                            .getChildren());
                } else {
                    return findList.get(0).getId();
                }
            }
        }
        String orgId = this.doCreateOrg(parentId, orgName, allOrgList);
        if (iterator.hasNext()) {
            return findOrgIdByOrgName(orgId, iterator, allOrgList, CollectionUtil.newArrayList());
        } else {
            return orgId;
        }
    }

    /**
     * 执行创建机构
     *
     * @author happynewyear
     */
    public String doCreateOrg(String parentId, String orgName, List<BizOrg> allOrgList) {
        //创建该机构
        BizOrg bizOrg = new BizOrg();
        bizOrg.setName(orgName);
        bizOrg.setCode(RandomUtil.randomString(10));
        bizOrg.setParentId(parentId);
        bizOrg.setCategory("0".equals(parentId) ?
                BizOrgCategoryEnum.COMPANY.getValue() : BizOrgCategoryEnum.DEPT.getValue());
        bizOrg.setSortCode(99);
        this.save(bizOrg);
        // 发布增加事件
        CommonDataChangeEventCenter.doAddWithData(BizDataTypeEnum.ORG.getValue(), JSONUtil.createArray().put(bizOrg));
        return bizOrg.getId();
    }

    /* ====机构部分所需要用到的选择器==== */

    @Override
    public List<Tree<String>> orgTreeSelector() {
        LambdaQueryWrapper<BizOrg> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 校验数据范围
        List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
        // 定义机构集合
        Set<BizOrg> bizOrgSet = CollectionUtil.newHashSet();
        if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
            // 获取所有机构
            List<BizOrg> allOrgList = this.list();
            loginUserDataScope.forEach(orgId -> bizOrgSet.addAll(this.getParentListById(allOrgList, orgId,
                    true)));
            List<String> loginUserDataScopeFullList = bizOrgSet.stream().map(BizOrg::getId)
                    .collect(Collectors.toList());
            lambdaQueryWrapper.in(BizOrg::getId, loginUserDataScopeFullList);
        } else {
            return CollectionUtil.newArrayList();
        }
        lambdaQueryWrapper.orderByAsc(BizOrg::getSortCode);
        List<BizOrg> bizOrgList = this.list(lambdaQueryWrapper);
        List<TreeNode<String>> treeNodeList = bizOrgList.stream().map(bizOrg ->
                        new TreeNode<>(bizOrg.getId(), bizOrg.getParentId(), bizOrg.getName(), bizOrg.getSortCode()))
                .collect(Collectors.toList());
        return TreeUtil.build(treeNodeList, "0");
    }

    @Override
    public List<BizOrg> orgListSelector(BizOrgSelectorOrgListParam bizOrgSelectorOrgListParam) {
        QueryWrapper<BizOrg> queryWrapper = new QueryWrapper<BizOrg>().checkSqlInjection();
        // 校验数据范围
        List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
        if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
            queryWrapper.lambda().in(BizOrg::getId, loginUserDataScope);
        } else {
            return CollectionUtil.newArrayList();
        }
        // 查询部分字段
        queryWrapper.lambda().select(BizOrg::getId, BizOrg::getParentId, BizOrg::getName,
                BizOrg::getCategory, BizOrg::getSortCode);
        if (ObjectUtil.isNotEmpty(bizOrgSelectorOrgListParam.getParentId())) {
            queryWrapper.lambda().eq(BizOrg::getParentId, bizOrgSelectorOrgListParam.getParentId());
        }
        if (ObjectUtil.isNotEmpty(bizOrgSelectorOrgListParam.getSearchKey())) {
            queryWrapper.lambda().like(BizOrg::getName, bizOrgSelectorOrgListParam.getSearchKey());
        }
        queryWrapper.lambda().orderByAsc(BizOrg::getSortCode);
        return this.list(queryWrapper.lambda());
    }

    @Override
    public Page<BizUser> userSelector(BizOrgSelectorUserParam bizOrgSelectorUserParam) {
        QueryWrapper<BizUser> queryWrapper = new QueryWrapper<BizUser>().checkSqlInjection();
        // 校验数据范围
        List<String> loginUserDataScope = StpLoginUserUtil.getLoginUserDataScope();
        if (ObjectUtil.isNotEmpty(loginUserDataScope)) {
            queryWrapper.lambda().in(BizUser::getOrgId, loginUserDataScope);
        } else {
            return new Page<>();
        }
        // 只查询部分字段
        queryWrapper.lambda().select(BizUser::getId, BizUser::getAvatar, BizUser::getOrgId, BizUser::getPositionId,
                BizUser::getAccount,
                BizUser::getName, BizUser::getSortCode, BizUser::getGender, BizUser::getEntryDate);
        if (ObjectUtil.isNotEmpty(bizOrgSelectorUserParam.getOrgId())) {
            // 如果机构id不为空，则查询该机构及其子机构下的所有人
            List<String> childOrgIdList = CollStreamUtil.toList(this.getChildListById(this
                    .getAllOrgList(), bizOrgSelectorUserParam.getOrgId(), true), BizOrg::getId);
            if (ObjectUtil.isNotEmpty(childOrgIdList)) {
                queryWrapper.lambda().in(BizUser::getOrgId, childOrgIdList);
            } else {
                return new Page<>();
            }
        }
        if (ObjectUtil.isNotEmpty(bizOrgSelectorUserParam.getSearchKey())) {
            queryWrapper.lambda().like(BizUser::getName, bizOrgSelectorUserParam.getSearchKey());
        }
        queryWrapper.lambda().orderByAsc(BizUser::getSortCode);
        return bizUserService.page(CommonPageRequest.defaultPage(), queryWrapper.lambda());
    }

    /* ====以下为各种递归方法==== */

    @Override
    public List<BizOrg> getParentAndChildListById(List<BizOrg> originDataList, String id, boolean includeSelf) {
        List<BizOrg> parentListById = this.getParentListById(originDataList, id, false);
        List<BizOrg> childListById = this.getChildListById(originDataList, id, true);
        parentListById.addAll(childListById);
        return parentListById;
    }

    @Override
    public List<BizOrg> getChildListById(List<BizOrg> originDataList, String id, boolean includeSelf) {
        List<BizOrg> resultList = CollectionUtil.newArrayList();
        execRecursionFindChild(originDataList, id, resultList);
        if (includeSelf) {
            BizOrg self = this.getById(originDataList, id);
            if (ObjectUtil.isNotEmpty(self)) {
                resultList.add(self);
            }
        }
        return resultList;
    }

    @Override
    public List<BizOrg> getParentListById(List<BizOrg> originDataList, String id, boolean includeSelf) {
        List<BizOrg> resultList = CollectionUtil.newArrayList();
        execRecursionFindParent(originDataList, id, resultList);
        if (includeSelf) {
            BizOrg self = this.getById(originDataList, id);
            if (ObjectUtil.isNotEmpty(self)) {
                resultList.add(self);
            }
        }
        return resultList;
    }


    public void execRecursionFindChild(List<BizOrg> originDataList, String id, List<BizOrg> resultList) {
        originDataList.forEach(item -> {
            if (item.getParentId().equals(id)) {
                resultList.add(item);
                execRecursionFindChild(originDataList, item.getId(), resultList);
            }
        });
    }

    public void execRecursionFindParent(List<BizOrg> originDataList, String id, List<BizOrg> resultList) {
        originDataList.forEach(item -> {
            if (item.getId().equals(id)) {
                BizOrg parent = this.getById(originDataList, item.getParentId());
                if (ObjectUtil.isNotEmpty(parent)) {
                    resultList.add(parent);
                }
                execRecursionFindParent(originDataList, item.getParentId(), resultList);
            }
        });
    }

    @Override
    public BizOrg getById(List<BizOrg> originDataList, String id) {
        int index = CollStreamUtil.toList(originDataList, BizOrg::getId).indexOf(id);
        return index == -1 ? null : originDataList.get(index);
    }

    @Override
    public BizOrg getParentById(List<BizOrg> originDataList, String id) {
        BizOrg self = this.getById(originDataList, id);
        return ObjectUtil.isNotEmpty(self) ? self : this.getById(originDataList, self.getParentId());
    }

    @Override
    public BizOrg getChildById(List<BizOrg> originDataList, String id) {
        int index = CollStreamUtil.toList(originDataList, BizOrg::getParentId).indexOf(id);
        return index == -1 ? null : originDataList.get(index);
    }
}
