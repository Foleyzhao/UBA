package com.huanniankj.mobile.modular.resource.controller;

import cn.hutool.core.lang.tree.Tree;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.mobile.modular.resource.entity.MobileMenu;
import com.huanniankj.mobile.modular.resource.entity.MobileModule;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuAddParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuChangeModuleParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuEditParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuIdParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuSelectorMenuParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuSelectorModuleParam;
import com.huanniankj.mobile.modular.resource.param.menu.MobileMenuTreeParam;
import com.huanniankj.mobile.modular.resource.service.MobileMenuService;
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
 * 移动端菜单控制器
 *
 * @author happynewyear
 */
@Tag(name = "移动端菜单控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 1)
@RestController
@Validated
public class MobileMenuController {

    @Resource
    private MobileMenuService mobileMenuService;

    /**
     * 获取移动端菜单tree
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取移动端菜单tree")
    @GetMapping("/mobile/menu/tree")
    public CommonResult<List<Tree<String>>> tree(MobileMenuTreeParam mobileMenuTreeParam) {
        return CommonResult.data(mobileMenuService.tree(mobileMenuTreeParam));
    }

    /**
     * 添加移动端菜单
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加移动端菜单")
    @CommonLog("添加移动端菜单")
    @PostMapping("/mobile/menu/add")
    public CommonResult<String> add(@RequestBody @Valid MobileMenuAddParam mobileMenuAddParam) {
        mobileMenuService.add(mobileMenuAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑移动端菜单
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑移动端菜单")
    @CommonLog("编辑移动端菜单")
    @PostMapping("/mobile/menu/edit")
    public CommonResult<String> edit(@RequestBody @Valid MobileMenuEditParam mobileMenuEditParam) {
        mobileMenuService.edit(mobileMenuEditParam);
        return CommonResult.ok();
    }

    /**
     * 更改移动端菜单所属模块
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "更改移动端菜单所属模块")
    @CommonLog("更改移动端菜单所属模块")
    @PostMapping("/mobile/menu/changeModule")
    public CommonResult<String> changeModule(@RequestBody @Valid MobileMenuChangeModuleParam mobileMenuChangeModuleParam) {
        mobileMenuService.changeModule(mobileMenuChangeModuleParam);
        return CommonResult.ok();
    }

    /**
     * 删除移动端菜单
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除移动端菜单")
    @CommonLog("删除移动端菜单")
    @PostMapping("/mobile/menu/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<MobileMenuIdParam> mobileMenuIdParamList) {
        mobileMenuService.delete(mobileMenuIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取移动端菜单详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取移动端菜单详情")
    @GetMapping("/mobile/menu/detail")
    public CommonResult<MobileMenu> detail(@Valid MobileMenuIdParam mobileMenuIdParam) {
        return CommonResult.data(mobileMenuService.detail(mobileMenuIdParam));
    }

    /* ====菜单部分所需要用到的选择器==== */

    /**
     * 获取模块选择器
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "获取模块选择器")
    @GetMapping("/mobile/menu/moduleSelector")
    public CommonResult<List<MobileModule>> moduleSelector(MobileMenuSelectorModuleParam mobileMenuSelectorModuleParam) {
        return CommonResult.data(mobileMenuService.moduleSelector(mobileMenuSelectorModuleParam));
    }

    /**
     * 获取菜单树选择器
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取菜单树选择器")
    @GetMapping("/mobile/menu/menuTreeSelector")
    public CommonResult<List<Tree<String>>> menuTreeSelector(MobileMenuSelectorMenuParam mobileMenuSelectorMenuParam) {
        return CommonResult.data(mobileMenuService.menuTreeSelector(mobileMenuSelectorMenuParam));
    }
}
