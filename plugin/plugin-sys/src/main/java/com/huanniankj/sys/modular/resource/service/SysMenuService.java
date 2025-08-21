package com.huanniankj.sys.modular.resource.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.sys.modular.resource.entity.SysMenu;
import com.huanniankj.sys.modular.resource.entity.SysModule;
import com.huanniankj.sys.modular.resource.param.menu.SysMenuAddParam;
import com.huanniankj.sys.modular.resource.param.menu.SysMenuChangeModuleParam;
import com.huanniankj.sys.modular.resource.param.menu.SysMenuEditParam;
import com.huanniankj.sys.modular.resource.param.menu.SysMenuIdParam;
import com.huanniankj.sys.modular.resource.param.menu.SysMenuPageParam;
import com.huanniankj.sys.modular.resource.param.menu.SysMenuSelectorMenuParam;
import com.huanniankj.sys.modular.resource.param.menu.SysMenuSelectorModuleParam;
import com.huanniankj.sys.modular.resource.param.menu.SysMenuTreeParam;

import java.util.List;

/**
 * 菜单服务接口
 *
 * @author happynewyear
 */
public interface SysMenuService extends IService<SysMenu> {


    /**
     * 获取菜单分页
     */
    Page<SysMenu> page(SysMenuPageParam sysMenuPageParam);

    /**
     * 获取菜单树
     */
    List<Tree<String>> tree(SysMenuTreeParam sysMenuTreeParam);

    /**
     * 添加菜单
     */
    void add(SysMenuAddParam sysMenuAddParam);

    /**
     * 代码生成菜单插入
     */
    String addForGenMenu(String parentId, String busName, String title, String module, String path);

    /**
     * 编辑菜单
     */
    void edit(SysMenuEditParam sysMenuEditParam);

    /**
     * 更改菜单所属模块
     */
    void changeModule(SysMenuChangeModuleParam sysMenuChangeModuleParam);

    /**
     * 删除菜单
     */
    void delete(List<SysMenuIdParam> sysMenuIdParamList);

    /**
     * 获取菜单详情
     */
    SysMenu detail(SysMenuIdParam sysMenuIdParam);

    /**
     * 获取菜单详情
     */
    SysMenu queryEntity(String id);

    /* ====以下为各种递归方法==== */

    /**
     * 根据id获取所有的子数据列表
     */
    List<SysMenu> getChildListById(List<SysMenu> originDataList, String id, boolean includeSelf);

    /**
     * 根据id获取所有的父数据列表
     */
    List<SysMenu> getParentListById(List<SysMenu> originDataList, String id, boolean includeSelf);

    /* ====菜单部分所需要用到的选择器==== */

    /**
     * 获取模块选择器
     */
    List<SysModule> moduleSelector(SysMenuSelectorModuleParam sysMenuSelectorModuleParam);

    /**
     * 获取菜单树选择器
     */
    List<Tree<String>> menuTreeSelector(SysMenuSelectorMenuParam sysMenuSelectorMenuParam);
}
