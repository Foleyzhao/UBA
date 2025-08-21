package com.huanniankj.biz.modular.org.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.biz.modular.org.entity.BizOrg;
import com.huanniankj.biz.modular.org.param.BizOrgAddParam;
import com.huanniankj.biz.modular.org.param.BizOrgEditParam;
import com.huanniankj.biz.modular.org.param.BizOrgIdParam;
import com.huanniankj.biz.modular.org.param.BizOrgPageParam;
import com.huanniankj.biz.modular.org.param.BizOrgSelectorOrgListParam;
import com.huanniankj.biz.modular.org.param.BizOrgSelectorUserParam;
import com.huanniankj.biz.modular.user.entity.BizUser;

import java.util.List;

/**
 * 机构服务接口
 *
 * @author happynewyear
 */
public interface BizOrgService extends IService<BizOrg> {

    /**
     * 获取机构分页
     */
    Page<BizOrg> page(BizOrgPageParam bizOrgPageParam);

    /**
     * 获取机构树
     */
    List<Tree<String>> tree();

    /**
     * 添加机构
     */
    void add(BizOrgAddParam bizOrgAddParam, String sourceFromType);

    /**
     * 编辑机构
     */
    void edit(BizOrgEditParam bizOrgEditParam);

    /**
     * 删除机构
     */
    void delete(List<BizOrgIdParam> bizOrgIdParamList);

    /**
     * 获取机构详情
     */
    BizOrg detail(BizOrgIdParam bizOrgIdParam);

    /**
     * 获取机构详情
     */
    BizOrg queryEntity(String id);

    /**
     * 获取所有机构
     */
    List<BizOrg> getAllOrgList();

    /**
     * 根据组织全名称获取组织id，有则返回，无则创建
     */
    String getOrgIdByOrgFullNameWithCreate(String orgFullName);

    /**
     * 根据id获取父子数据列表
     */
    List<BizOrg> getParentAndChildListById(List<BizOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的子数据列表
     */
    List<BizOrg> getChildListById(List<BizOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的父数据列表
     */
    List<BizOrg> getParentListById(List<BizOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取数据
     */
    BizOrg getById(List<BizOrg> originDataList, String id);

    /**
     * 根据id获取父数据
     */
    BizOrg getParentById(List<BizOrg> originDataList, String id);

    /**
     * 根据id获取子数据
     */
    BizOrg getChildById(List<BizOrg> originDataList, String id);

    /**
     * 获取机构树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取机构列表选择器
     */
    List<BizOrg> orgListSelector(BizOrgSelectorOrgListParam bizOrgSelectorOrgListParam);

    /**
     * 获取人员选择器
     */
    Page<BizUser> userSelector(BizOrgSelectorUserParam bizOrgSelectorUserParam);
}
