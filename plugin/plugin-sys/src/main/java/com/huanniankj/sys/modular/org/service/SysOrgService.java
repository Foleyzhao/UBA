package com.huanniankj.sys.modular.org.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.sys.modular.org.entity.SysOrg;
import com.huanniankj.sys.modular.org.param.SysOrgAddParam;
import com.huanniankj.sys.modular.org.param.SysOrgEditParam;
import com.huanniankj.sys.modular.org.param.SysOrgIdParam;
import com.huanniankj.sys.modular.org.param.SysOrgPageParam;
import com.huanniankj.sys.modular.org.param.SysOrgSelectorOrgListParam;
import com.huanniankj.sys.modular.org.param.SysOrgSelectorUserParam;
import com.huanniankj.sys.modular.user.entity.SysUser;

import java.util.List;

/**
 * 组织服务接口
 *
 * @author happynewyear
 */
public interface SysOrgService extends IService<SysOrg> {

    /**
     * 获取组织分页
     */
    Page<SysOrg> page(SysOrgPageParam sysOrgPageParam);

    /**
     * 获取组织树
     */
    List<Tree<String>> tree();

    /**
     * 添加组织
     */
    void add(SysOrgAddParam sysOrgAddParam, String sourceFromType);

    /**
     * 编辑组织
     */
    void edit(SysOrgEditParam sysOrgEditParam);

    /**
     * 删除组织
     */
    void delete(List<SysOrgIdParam> sysOrgIdParamList);

    /**
     * 获取组织详情
     */
    SysOrg detail(SysOrgIdParam sysOrgIdParam);

    /**
     * 获取组织详情
     */
    SysOrg queryEntity(String id);

    /**
     * 获取所有组织
     */
    List<SysOrg> getAllOrgList();

    /**
     * 根据组织全名称获取组织id，有则返回，无则创建
     */
    String getOrgIdByOrgFullNameWithCreate(String orgFullName);

    /**
     * 根据id获取父子数据列表
     */
    List<SysOrg> getParentAndChildListById(List<SysOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的子数据列表
     */
    List<SysOrg> getChildListById(List<SysOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的父数据列表
     */
    List<SysOrg> getParentListById(List<SysOrg> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取数据
     */
    SysOrg getById(List<SysOrg> originDataList, String id);

    /**
     * 根据id获取父数据
     */
    SysOrg getParentById(List<SysOrg> originDataList, String id);

    /**
     * 根据id获取子数据
     */
    SysOrg getChildById(List<SysOrg> originDataList, String id);

    /**
     * 获取组织树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     */
    Page<SysOrg> orgListSelector(SysOrgSelectorOrgListParam sysOrgSelectorOrgListParam);

    /**
     * 获取用户选择器
     */
    Page<SysUser> userSelector(SysOrgSelectorUserParam sysOrgSelectorUserParam);

    /**
     * 获取某组织的所有父级id集合
     */
    List<String> getParentIdListByOrgId(String orgId);

}
