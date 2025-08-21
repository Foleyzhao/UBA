package com.huanniankj.sys.modular.group.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.sys.modular.group.entity.SysGroup;
import com.huanniankj.sys.modular.group.param.SysGroupAddParam;
import com.huanniankj.sys.modular.group.param.SysGroupEditParam;
import com.huanniankj.sys.modular.group.param.SysGroupGrantUserParam;
import com.huanniankj.sys.modular.group.param.SysGroupIdParam;
import com.huanniankj.sys.modular.group.param.SysGroupPageParam;
import com.huanniankj.sys.modular.group.param.SysGroupSelectorUserParam;
import com.huanniankj.sys.modular.group.service.SysGroupService;
import com.huanniankj.sys.modular.user.entity.SysUser;
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
public class SysGroupController {

    @Resource
    private SysGroupService sysGroupService;

    /**
     * 获取用户组分页
     */
    @Operation(summary = "获取用户组分页")
    @GetMapping("/sys/group/page")
    public CommonResult<Page<SysGroup>> page(SysGroupPageParam sysGroupPageParam) {
        return CommonResult.data(sysGroupService.page(sysGroupPageParam));
    }

    /**
     * 添加用户组
     */
    @Operation(summary = "添加用户组")
    @CommonLog("添加用户组")
    @PostMapping("/sys/group/add")
    public CommonResult<String> add(@RequestBody @Valid SysGroupAddParam sysGroupAddParam) {
        sysGroupService.add(sysGroupAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑用户组
     */
    @Operation(summary = "编辑用户组")
    @CommonLog("编辑用户组")
    @PostMapping("/sys/group/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysGroupEditParam sysGroupEditParam) {
        sysGroupService.edit(sysGroupEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除用户组
     */
    @Operation(summary = "删除用户组")
    @CommonLog("删除用户组")
    @PostMapping("/sys/group/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<SysGroupIdParam> sysGroupIdParamList) {
        sysGroupService.delete(sysGroupIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取用户组详情
     */
    @Operation(summary = "获取用户组详情")
    @GetMapping("/sys/group/detail")
    public CommonResult<SysGroup> detail(@Valid SysGroupIdParam sysGroupIdParam) {
        return CommonResult.data(sysGroupService.detail(sysGroupIdParam));
    }

    /**
     * 获取用户组下的用户
     */
    @Operation(summary = "获取用户组下的用户")
    @GetMapping("/sys/group/ownUser")
    public CommonResult<List<String>> ownUser(@Valid SysGroupIdParam sysGroupIdParam) {
        return CommonResult.data(sysGroupService.ownUser(sysGroupIdParam));
    }

    /**
     * 获取组织树选择器
     */
    @Operation(summary = "获取组织树选择器")
    @GetMapping("/sys/group/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(sysGroupService.orgTreeSelector());
    }

    /**
     * 获取用户选择器
     */
    @Operation(summary = "获取用户选择器")
    @GetMapping("/sys/group/userSelector")
    public CommonResult<Page<SysUser>> userSelector(SysGroupSelectorUserParam sysGroupSelectorUserParam) {
        return CommonResult.data(sysGroupService.userSelector(sysGroupSelectorUserParam));
    }

    /**
     * 给用户组授权用户
     */
    @Operation(summary = "给用户组授权用户")
    @CommonLog("给用户组授权用户")
    @PostMapping("/sys/group/grantUser")
    public CommonResult<String> grantUser(@RequestBody @Valid SysGroupGrantUserParam sysGroupGrantUserParam) {
        sysGroupService.grantUser(sysGroupGrantUserParam);
        return CommonResult.ok();
    }

}
