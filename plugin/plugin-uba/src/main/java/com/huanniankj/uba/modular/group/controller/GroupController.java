package com.huanniankj.uba.modular.group.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.uba.modular.group.entity.Group;
import com.huanniankj.uba.modular.group.param.GroupAddParam;
import com.huanniankj.uba.modular.group.param.GroupEditParam;
import com.huanniankj.uba.modular.group.param.GroupIdParam;
import com.huanniankj.uba.modular.group.param.GroupPageParam;
import com.huanniankj.uba.modular.group.service.GroupService;
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
 * 运营用户组控制器
 *
 * @author happynewyear
 */
@Tag(name = "运营用户组控制器")
@RestController
@Validated
public class GroupController {

    @Resource
    private GroupService groupService;

    /**
     * 获取运营用户组分页
     */
    @Operation(summary = "获取运营用户组分页")
    @GetMapping("/uba/group/page")
    public CommonResult<Page<Group>> page(GroupPageParam groupPageParam) {
        return CommonResult.data(groupService.page(groupPageParam));
    }

    /**
     * 添加运营用户组
     */
    @Operation(summary = "添加运营用户组")
    @CommonLog("添加运营用户组")
    @PostMapping("/uba/group/add")
    public CommonResult<String> add(@RequestBody @Valid GroupAddParam groupAddParam) {
        groupService.add(groupAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑运营用户组
     */
    @Operation(summary = "编辑运营用户组")
    @CommonLog("编辑运营用户组")
    @PostMapping("/uba/group/edit")
    public CommonResult<String> edit(@RequestBody @Valid GroupEditParam groupEditParam) {
        groupService.edit(groupEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除运营用户组
     */
    @Operation(summary = "删除运营用户组")
    @CommonLog("删除运营用户组")
    @PostMapping("/uba/group/delete")
    public CommonResult<String> delete(
            @RequestBody @Valid @NotEmpty(message = "集合不能为空") List<GroupIdParam> groupIdParams) {
        groupService.delete(groupIdParams);
        return CommonResult.ok();
    }

    /**
     * 获取运营用户组详情
     */
    @Operation(summary = "获取运营用户组详情")
    @GetMapping("/uba/group/detail")
    public CommonResult<Group> detail(@Valid GroupIdParam groupIdParam) {
        return CommonResult.data(groupService.detail(groupIdParam));
    }

}
