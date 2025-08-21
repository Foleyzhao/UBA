package com.huanniankj.sys.modular.resource.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
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
import com.huanniankj.sys.modular.resource.service.SysMenuService;
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
 * 菜单控制器
 *
 * @author happynewyear
 */
@Tag(name = "菜单控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 5)
@RestController
@Validated
public class SysMenuController {

    @Resource
    private SysMenuService sysMenuService;

    /**
     * 获取菜单分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取菜单分页")
    @GetMapping("/sys/menu/page")
    public CommonResult<Page<SysMenu>> page(SysMenuPageParam sysMenuPageParam) {
        return CommonResult.data(sysMenuService.page(sysMenuPageParam));
    }

    /**
     * 获取菜单树
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取菜单树")
    @GetMapping("/sys/menu/tree")
    public CommonResult<List<Tree<String>>> tree(SysMenuTreeParam sysMenuTreeParam) {
        return CommonResult.data(sysMenuService.tree(sysMenuTreeParam));
    }

    /**
     * 添加菜单
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "添加菜单")
    @CommonLog("添加菜单")
    @PostMapping("/sys/menu/add")
    public CommonResult<String> add(@RequestBody @Valid SysMenuAddParam sysMenuAddParam) {
        sysMenuService.add(sysMenuAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑菜单
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "编辑菜单")
    @CommonLog("编辑菜单")
    @PostMapping("/sys/menu/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysMenuEditParam sysMenuEditParam) {
        sysMenuService.edit(sysMenuEditParam);
        return CommonResult.ok();
    }

    /**
     * 更改菜单所属模块
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "更改菜单所属模块")
    @CommonLog("更改菜单所属模块")
    @PostMapping("/sys/menu/changeModule")
    public CommonResult<String> changeModule(@RequestBody @Valid SysMenuChangeModuleParam sysMenuChangeModuleParam) {
        sysMenuService.changeModule(sysMenuChangeModuleParam);
        return CommonResult.ok();
    }

    /**
     * 删除菜单
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "删除菜单")
    @CommonLog("删除菜单")
    @PostMapping("/sys/menu/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<SysMenuIdParam> sysMenuIdParamList) {
        sysMenuService.delete(sysMenuIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取菜单详情
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取菜单详情")
    @GetMapping("/sys/menu/detail")
    public CommonResult<SysMenu> detail(@Valid SysMenuIdParam sysMenuIdParam) {
        return CommonResult.data(sysMenuService.detail(sysMenuIdParam));
    }

    /* ====菜单部分所需要用到的选择器==== */

    /**
     * 获取模块选择器
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "获取模块选择器")
    @GetMapping("/sys/menu/moduleSelector")
    public CommonResult<List<SysModule>> moduleSelector(SysMenuSelectorModuleParam sysMenuSelectorModuleParam) {
        return CommonResult.data(sysMenuService.moduleSelector(sysMenuSelectorModuleParam));
    }

    /**
     * 获取菜单树选择器
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "获取菜单树选择器")
    @GetMapping("/sys/menu/menuTreeSelector")
    public CommonResult<List<Tree<String>>> menuTreeSelector(SysMenuSelectorMenuParam sysMenuSelectorMenuParam) {
        return CommonResult.data(sysMenuService.menuTreeSelector(sysMenuSelectorMenuParam));
    }
}
