package com.huanniankj.sys.modular.role.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
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
import com.huanniankj.sys.modular.role.service.SysRoleService;
import com.huanniankj.sys.modular.user.entity.SysUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 角色控制器
 *
 * @author happynewyear
 */
@Tag(name = "角色控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 8)
@RestController
@Validated
public class SysRoleController {

    @Resource
    private SysRoleService sysRoleService;

    /**
     * 获取角色分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取角色分页")
    @GetMapping("/sys/role/page")
    public CommonResult<Page<SysRole>> page(SysRolePageParam sysRolePageParam) {
        return CommonResult.data(sysRoleService.page(sysRolePageParam));
    }

    /**
     * 添加角色
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加角色")
    @CommonLog("添加角色")
    @PostMapping("/sys/role/add")
    public CommonResult<String> add(@RequestBody @Valid SysRoleAddParam sysRoleAddParam) {
        sysRoleService.add(sysRoleAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑角色
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑角色")
    @CommonLog("编辑角色")
    @PostMapping("/sys/role/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysRoleEditParam sysRoleEditParam) {
        sysRoleService.edit(sysRoleEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除角色
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除角色")
    @CommonLog("删除角色")
    @PostMapping("/sys/role/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<SysRoleIdParam> sysRoleIdParamList) {
        sysRoleService.delete(sysRoleIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取角色详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取角色详情")
    @GetMapping("/sys/role/detail")
    public CommonResult<SysRole> detail(@Valid SysRoleIdParam sysRoleIdParam) {
        return CommonResult.data(sysRoleService.detail(sysRoleIdParam));
    }

    /**
     * 获取角色拥有资源
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "获取角色拥有资源")
    @GetMapping("/sys/role/ownResource")
    public CommonResult<SysRoleOwnResourceResult> ownResource(@Valid SysRoleIdParam sysRoleIdParam) {
        return CommonResult.data(sysRoleService.ownResource(sysRoleIdParam));
    }

    /**
     * 给角色授权资源
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "给角色授权资源")
    @CommonLog("给角色授权资源")
    @PostMapping("/sys/role/grantResource")
    public CommonResult<String> grantResource(@RequestBody @Valid SysRoleGrantResourceParam sysRoleGrantResourceParam) {
        sysRoleService.grantResource(sysRoleGrantResourceParam);
        return CommonResult.ok();
    }

    /**
     * 获取角色拥有移动端菜单
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "获取角色拥有移动端菜单")
    @GetMapping("/sys/role/ownMobileMenu")
    public CommonResult<SysRoleOwnMobileMenuResult> ownMobileMenu(@Valid SysRoleIdParam sysRoleIdParam) {
        return CommonResult.data(sysRoleService.ownMobileMenu(sysRoleIdParam));
    }

    /**
     * 给角色授权移动端菜单
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "给角色授权移动端菜单")
    @CommonLog("给角色授权移动端菜单")
    @PostMapping("/sys/role/grantMobileMenu")
    public CommonResult<String> grantMobileMenu(
            @RequestBody @Valid SysRoleGrantMobileMenuParam sysRoleGrantMobileMenuParam) {
        sysRoleService.grantMobileMenu(sysRoleGrantMobileMenuParam);
        return CommonResult.ok();
    }

    /**
     * 获取角色拥有权限
     */
    @ApiOperationSupport(order = 10)
    @Operation(summary = "获取角色拥有权限")
    @GetMapping("/sys/role/ownPermission")
    public CommonResult<SysRoleOwnPermissionResult> ownPermission(@Valid SysRoleIdParam sysRoleIdParam) {
        return CommonResult.data(sysRoleService.ownPermission(sysRoleIdParam));
    }

    /**
     * 给角色授权权限
     */
    @ApiOperationSupport(order = 11)
    @Operation(summary = "给角色授权权限")
    @CommonLog("给角色授权权限")
    @PostMapping("/sys/role/grantPermission")
    public CommonResult<String> grantPermission(
            @RequestBody @Valid SysRoleGrantPermissionParam sysRoleGrantPermissionParam) {
        sysRoleService.grantPermission(sysRoleGrantPermissionParam);
        return CommonResult.ok();
    }

    /**
     * 获取角色下的用户
     */
    @ApiOperationSupport(order = 12)
    @Operation(summary = "获取角色下的用户")
    @GetMapping("/sys/role/ownUser")
    public CommonResult<List<String>> ownUser(@Valid SysRoleIdParam sysRoleIdParam) {
        return CommonResult.data(sysRoleService.ownUser(sysRoleIdParam));
    }

    /**
     * 给角色授权用户
     */
    @ApiOperationSupport(order = 13)
    @Operation(summary = "给角色授权用户")
    @CommonLog("给角色授权用户")
    @PostMapping("/sys/role/grantUser")
    public CommonResult<String> grantUser(@RequestBody @Valid SysRoleGrantUserParam sysRoleGrantUserParam) {
        sysRoleGrantUserParam.setRemoveFirst(true);
        sysRoleService.grantUser(sysRoleGrantUserParam);
        return CommonResult.ok();
    }

    /* ====角色部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     */
    @ApiOperationSupport(order = 14)
    @Operation(summary = "获取组织树选择器")
    @GetMapping("/sys/role/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(sysRoleService.orgTreeSelector());
    }

    /**
     * 获取资源授权树
     */
    @ApiOperationSupport(order = 15)
    @Operation(summary = "获取资源授权树")
    @GetMapping("/sys/role/resourceTreeSelector")
    public CommonResult<List<SysRoleGrantResourceTreeResult>> resourceTreeSelector() {
        return CommonResult.data(sysRoleService.resourceTreeSelector(true));
    }

    /**
     * 获取移动端菜单授权树
     */
    @ApiOperationSupport(order = 16)
    @Operation(summary = "获取移动端菜单授权树")
    @GetMapping("/sys/role/mobileMenuTreeSelector")
    public CommonResult<List<SysRoleGrantMobileMenuTreeResult>> mobileMenuTreeSelector() {
        return CommonResult.data(sysRoleService.mobileMenuTreeSelector());
    }

    /**
     * 获取权限授权树
     */
    @ApiOperationSupport(order = 17)
    @Operation(summary = "获取权限授权树")
    @GetMapping("/sys/role/permissionTreeSelector")
    public CommonResult<List<String>> permissionTreeSelector() {
        return CommonResult.data(sysRoleService.permissionTreeSelector());
    }

    /**
     * 获取角色选择器
     */
    @ApiOperationSupport(order = 18)
    @Operation(summary = "获取角色选择器")
    @GetMapping("/sys/role/roleSelector")
    public CommonResult<Page<SysRole>> roleSelector(SysRoleSelectorRoleParam sysRoleSelectorRoleParam) {
        return CommonResult.data(sysRoleService.roleSelector(sysRoleSelectorRoleParam));
    }

    /**
     * 获取用户选择器
     */
    @ApiOperationSupport(order = 19)
    @Operation(summary = "获取用户选择器")
    @GetMapping("/sys/role/userSelector")
    public CommonResult<Page<SysUser>> userSelector(SysRoleSelectorUserParam sysRoleSelectorUserParam) {
        return CommonResult.data(sysRoleService.userSelector(sysRoleSelectorUserParam));
    }

}
