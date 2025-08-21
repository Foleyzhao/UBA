package com.huanniankj.biz.modular.group.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.biz.modular.group.entity.BizGroup;
import com.huanniankj.biz.modular.group.param.BizGroupAddParam;
import com.huanniankj.biz.modular.group.param.BizGroupEditParam;
import com.huanniankj.biz.modular.group.param.BizGroupGrantUserParam;
import com.huanniankj.biz.modular.group.param.BizGroupIdParam;
import com.huanniankj.biz.modular.group.param.BizGroupPageParam;
import com.huanniankj.biz.modular.group.param.BizGroupSelectorUserParam;
import com.huanniankj.biz.modular.group.service.BizGroupService;
import com.huanniankj.biz.modular.user.entity.BizUser;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 用户组控制器
 *
 * @author happynewyear
 */
@Tag(name = "用户组控制器")
@RestController
@Validated
public class BizGroupController {

    @Resource
    private BizGroupService bizGroupService;

    /**
     * 获取用户组分页
     */
    @Operation(summary = "获取用户组分页")
    @SaCheckPermission("/biz/group/page")
    @GetMapping("/biz/group/page")
    public CommonResult<Page<BizGroup>> page(BizGroupPageParam bizGroupPageParam) {
        return CommonResult.data(bizGroupService.page(bizGroupPageParam));
    }

    /**
     * 添加用户组
     */
    @Operation(summary = "添加用户组")
    @CommonLog("添加用户组")
    @SaCheckPermission("/biz/group/add")
    @PostMapping("/biz/group/add")
    public CommonResult<String> add(@RequestBody @Valid BizGroupAddParam bizGroupAddParam) {
        bizGroupService.add(bizGroupAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑用户组
     */
    @Operation(summary = "编辑用户组")
    @CommonLog("编辑用户组")
    @SaCheckPermission("/biz/group/edit")
    @PostMapping("/biz/group/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizGroupEditParam bizGroupEditParam) {
        bizGroupService.edit(bizGroupEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除用户组
     */
    @Operation(summary = "删除用户组")
    @CommonLog("删除用户组")
    @SaCheckPermission("/biz/group/delete")
    @PostMapping("/biz/group/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<BizGroupIdParam> bizGroupIdParamList) {
        bizGroupService.delete(bizGroupIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取用户组详情
     */
    @Operation(summary = "获取用户组详情")
    @SaCheckPermission("/biz/group/detail")
    @GetMapping("/biz/group/detail")
    public CommonResult<BizGroup> detail(@Valid BizGroupIdParam bizGroupIdParam) {
        return CommonResult.data(bizGroupService.detail(bizGroupIdParam));
    }

    /**
     * 获取用户组下的用户
     */
    @Operation(summary = "获取用户组下的用户")
    @SaCheckPermission("/biz/group/ownUser")
    @GetMapping("/biz/group/ownUser")
    public CommonResult<List<String>> ownUser(@Valid BizGroupIdParam bizGroupIdParam) {
        return CommonResult.data(bizGroupService.ownUser(bizGroupIdParam));
    }

    /**
     * 获取组织树选择器
     */
    @Operation(summary = "获取组织树选择器")
    @SaCheckPermission("/biz/group/orgTreeSelector")
    @GetMapping("/biz/group/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(bizGroupService.orgTreeSelector());
    }

    /**
     * 获取用户选择器
     */
    @Operation(summary = "获取用户选择器")
    @SaCheckPermission("/biz/group/userSelector")
    @GetMapping("/biz/group/userSelector")
    public CommonResult<Page<BizUser>> userSelector(BizGroupSelectorUserParam bizGroupSelectorUserParam) {
        return CommonResult.data(bizGroupService.userSelector(bizGroupSelectorUserParam));
    }

    /**
     * 给用户组授权用户
     */
    @Operation(summary = "给用户组授权用户")
    @CommonLog("给用户组授权用户")
    @SaCheckPermission("/biz/group/grantUser")
    @PostMapping("/biz/group/grantUser")
    public CommonResult<String> grantUser(@RequestBody @Valid BizGroupGrantUserParam bizGroupGrantUserParam) {
        bizGroupService.grantUser(bizGroupGrantUserParam);
        return CommonResult.ok();
    }
}
