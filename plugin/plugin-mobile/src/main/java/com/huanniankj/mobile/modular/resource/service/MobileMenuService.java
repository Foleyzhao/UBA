package com.huanniankj.mobile.modular.resource.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.mobile.modular.resource.entity.MobileMenu;
import com.huanniankj.mobile.modular.resource.entity.MobileModule;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuAddParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuChangeModuleParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuEditParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuIdParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuSelectorMenuParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuSelectorModuleParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuTreeParam;

import java.util.List;

/**
 * 移动端菜单服务接口
 *
 * @author happynewyear
 */
public interface MobileMenuService extends IService<MobileMenu> {

    /**
     * 获取移动端菜单tree
     */
    List<Tree<String>> tree(MobileMenuTreeParam mobileMenuTreeParam);

    /**
     * 添加移动端菜单
     */
    void add(MobileMenuAddParam mobileMenuAddParam);

    /**
     * 编辑移动端菜单
     */
    void edit(MobileMenuEditParam mobileMenuEditParam);

    /**
     * 更改移动端菜单所属模块
     */
    void changeModule(MobileMenuChangeModuleParam mobileMenuChangeModuleParam);

    /**
     * 删除移动端菜单
     */
    void delete(List<MobileMenuIdParam> mobileMenuIdParamList);

    /**
     * 获取移动端菜单详情
     */
    MobileMenu detail(MobileMenuIdParam mobileMenuIdParam);

    /**
     * 获取移动端菜单详情
     */
    MobileMenu queryEntity(String id);

    /* ====以下为各种递归方法==== */

    /**
     * 根据id获取所有的子数据列表
     */
    List<MobileMenu> getChildListById(List<MobileMenu> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的父数据列表
     */
    List<MobileMenu> getParentListById(List<MobileMenu> originDataList, String id, boolean includeSelf);

    /**
     * 获取模块选择器
     */
    List<MobileModule> moduleSelector(MobileMenuSelectorModuleParam mobileMenuSelectorModuleParam);

    /**
     * 获取菜单树选择器
     */
    List<Tree<String>> menuTreeSelector(MobileMenuSelectorMenuParam mobileMenuSelectorMenuParam);

    /**
     * 获取移动端菜单授权树
     */
    List<JSONObject> mobileMenuTreeSelector();

    /**
     * 获取移动端菜单授权树
     */
    List<JSONObject> mobileMenuTreeSelector(List<MobileMenu> originDataList);

    /**
     * 获取移动端登录菜单树
     */
    List<Tree<String>> loginMobileMenuTree(List<String> menuIdList);
}
