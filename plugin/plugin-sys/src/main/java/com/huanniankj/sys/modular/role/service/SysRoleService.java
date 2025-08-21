package com.huanniankj.sys.modular.role.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.sys.modular.resource.entity.SysMenu;
import com.huanniankj.sys.modular.role.entity.SysRole;
import com.huanniankj.sys.modular.role.param.SysRoleAddParam;
import com.huanniankj.sys.modular.role.param.SysRoleEditParam;
import com.huanniankj.sys.modular.role.param.SysRoleGrantMobileMenuParam;
import com.huanniankj.sys.modular.role.param.SysRoleGrantPermissionParam;
import com.huanniankj.sys.modular.role.param.SysRoleGrantResourceParam;
import com.huanniankj.sys.modular.role.param.SysRoleGrantUserParam;
import com.huanniankj.sys.modular.role.param.SysRoleIdParam;
import com.huanniankj.sys.modular.role.param.SysRolePageParam;
import com.huanniankj.sys.modular.role.param.SysRoleSelectorRoleParam;
import com.huanniankj.sys.modular.role.param.SysRoleSelectorUserParam;
import com.huanniankj.sys.modular.role.result.SysRoleGrantMobileMenuTreeResult;
import com.huanniankj.sys.modular.role.result.SysRoleGrantResourceTreeResult;
import com.huanniankj.sys.modular.role.result.SysRoleOwnMobileMenuResult;
import com.huanniankj.sys.modular.role.result.SysRoleOwnPermissionResult;
import com.huanniankj.sys.modular.role.result.SysRoleOwnResourceResult;
import com.huanniankj.sys.modular.user.entity.SysUser;

import java.util.List;

/**
 * 角色服务接口
 *
 * @author happynewyear
 */
public interface SysRoleService extends IService<SysRole> {

    /**
     * 获取角色分页
     */
    Page<SysRole> page(SysRolePageParam sysRolePageParam);

    /**
     * 添加角色
     */
    void add(SysRoleAddParam sysRoleAddParam);

    /**
     * 编辑角色
     */
    void edit(SysRoleEditParam sysRoleEditParam);

    /**
     * 删除角色
     */
    void delete(List<SysRoleIdParam> sysRoleIdParamList);

    /**
     * 获取角色详情
     */
    SysRole detail(SysRoleIdParam sysRoleIdParam);

    /**
     * 获取角色详情
     */
    SysRole queryEntity(String id);

    /**
     * 获取角色拥有资源
     */
    SysRoleOwnResourceResult ownResource(SysRoleIdParam sysRoleIdParam);

    /**
     * 给角色授权资源
     */
    void grantResource(SysRoleGrantResourceParam sysRoleGrantResourceParam);

    /**
     * 获取角色拥有移动端菜单
     */
    SysRoleOwnMobileMenuResult ownMobileMenu(SysRoleIdParam sysRoleIdParam);

    /**
     * 给角色授权移动端菜单
     */
    void grantMobileMenu(SysRoleGrantMobileMenuParam sysRoleGrantMobileMenuParam);

    /**
     * 获取角色拥有权限
     */
    SysRoleOwnPermissionResult ownPermission(SysRoleIdParam sysRoleIdParam);

    /**
     * 给角色授权权限
     */
    void grantPermission(SysRoleGrantPermissionParam sysRoleGrantPermissionParam);

    /**
     * 获取角色下的用户
     */
    List<String> ownUser(SysRoleIdParam sysRoleIdParam);

    /**
     * 给角色授权用户
     */
    void grantUser(SysRoleGrantUserParam sysRoleGrantUserParam);

    /* ====角色部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取资源授权树
     */
    List<SysRoleGrantResourceTreeResult> resourceTreeSelector(boolean containsTen);

    /**
     * 获取资源授权树
     */
    List<SysRoleGrantResourceTreeResult> resourceTreeSelector(List<SysMenu> originDataList);

    /**
     * 获取移动端菜单授权树
     */
    List<SysRoleGrantMobileMenuTreeResult> mobileMenuTreeSelector();

    /**
     * 获取移动端菜单授权树
     */
    List<SysRoleGrantMobileMenuTreeResult> mobileMenuTreeSelector(List<JSONObject> originDataList);

    /**
     * 获取权限授权树
     */
    List<String> permissionTreeSelector();

    /**
     * 获取角色选择器
     */
    Page<SysRole> roleSelector(SysRoleSelectorRoleParam sysRoleSelectorRoleParam);

    /**
     * 获取用户选择器
     */
    Page<SysUser> userSelector(SysRoleSelectorUserParam sysRoleSelectorUserParam);

}
