package com.huanniankj.biz.modular.user.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.biz.modular.org.entity.BizOrg;
import com.huanniankj.biz.modular.position.entity.BizPosition;
import com.huanniankj.biz.modular.user.entity.BizUser;
import com.huanniankj.biz.modular.user.enums.BizUserSourceFromTypeEnum;
import com.huanniankj.biz.modular.user.param.BizUserAddParam;
import com.huanniankj.biz.modular.user.param.BizUserEditParam;
import com.huanniankj.biz.modular.user.param.BizUserExportParam;
import com.huanniankj.biz.modular.user.param.BizUserGrantRoleParam;
import com.huanniankj.biz.modular.user.param.BizUserIdParam;
import com.huanniankj.biz.modular.user.param.BizUserPageParam;
import com.huanniankj.biz.modular.user.param.BizUserSelectorOrgListParam;
import com.huanniankj.biz.modular.user.param.BizUserSelectorPositionParam;
import com.huanniankj.biz.modular.user.param.BizUserSelectorRoleParam;
import com.huanniankj.biz.modular.user.param.BizUserSelectorUserParam;
import com.huanniankj.biz.modular.user.result.BizUserRoleResult;
import com.huanniankj.biz.modular.user.service.BizUserService;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 人员控制器
 *
 * @author happynewyear
 */
@Tag(name = "人员控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 9)
@RestController
@Validated
public class BizUserController {

    @Resource
    private BizUserService bizUserService;

    /**
     * 获取人员分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取人员分页")
    @SaCheckPermission("/biz/user/page")
    @GetMapping("/biz/user/page")
    public CommonResult<Page<BizUser>> page(BizUserPageParam bizUserPageParam) {
        return CommonResult.data(bizUserService.page(bizUserPageParam));
    }

    /**
     * 添加人员
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加人员")
    @CommonLog("添加人员")
    @SaCheckPermission("/biz/user/add")
    @PostMapping("/biz/user/add")
    public CommonResult<String> add(@RequestBody @Valid BizUserAddParam bizUserAddParam) {
        bizUserService.add(bizUserAddParam, BizUserSourceFromTypeEnum.SYSTEM_ADD.getValue());
        return CommonResult.ok();
    }

    /**
     * 编辑人员
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑人员")
    @CommonLog("编辑人员")
    @SaCheckPermission("/biz/user/edit")
    @PostMapping("/biz/user/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizUserEditParam bizUserEditParam) {
        bizUserService.edit(bizUserEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除人员
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除人员")
    @CommonLog("删除人员")
    @SaCheckPermission("/biz/user/delete")
    @PostMapping("/biz/user/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<BizUserIdParam> bizUserIdParamList) {
        bizUserService.delete(bizUserIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取人员详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取人员详情")
    @SaCheckPermission("/biz/user/detail")
    @GetMapping("/biz/user/detail")
    public CommonResult<BizUser> detail(@Valid BizUserIdParam bizUserIdParam) {
        return CommonResult.data(bizUserService.detail(bizUserIdParam));
    }

    /**
     * 禁用人员
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "禁用人员")
    @CommonLog("禁用人员")
    @SaCheckPermission("/biz/user/disableUser")
    @PostMapping("/biz/user/disableUser")
    public CommonResult<String> disableUser(@RequestBody BizUserIdParam bizUserIdParam) {
        bizUserService.disableUser(bizUserIdParam);
        return CommonResult.ok();
    }

    /**
     * 启用人员
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "启用人员")
    @CommonLog("启用人员")
    @SaCheckPermission("/biz/user/enableUser")
    @PostMapping("/biz/user/enableUser")
    public CommonResult<String> enableUser(@RequestBody @Valid BizUserIdParam bizUserIdParam) {
        bizUserService.enableUser(bizUserIdParam);
        return CommonResult.ok();
    }

    /**
     * 重置人员密码
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "重置人员密码")
    @CommonLog("重置人员密码")
    @SaCheckPermission("/biz/user/resetPassword")
    @PostMapping("/biz/user/resetPassword")
    public CommonResult<String> resetPassword(@RequestBody @Valid BizUserIdParam bizUserIdParam) {
        bizUserService.resetPassword(bizUserIdParam);
        return CommonResult.ok();
    }

    /**
     * 人员拥有角色
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "获取人员拥有角色")
    @SaCheckPermission("/biz/user/ownRole")
    @GetMapping("/biz/user/ownRole")
    public CommonResult<List<String>> ownRole(@Valid BizUserIdParam bizUserIdParam) {
        return CommonResult.data(bizUserService.ownRole(bizUserIdParam));
    }

    /**
     * 给人员授权角色
     */
    @ApiOperationSupport(order = 10)
    @Operation(summary = "给人员授权角色")
    @CommonLog("给人员授权角色")
    @SaCheckPermission("/biz/user/grantRole")
    @PostMapping("/biz/user/grantRole")
    public CommonResult<String> grantRole(@RequestBody @Valid BizUserGrantRoleParam bizUserGrantRoleParam) {
        bizUserService.grantRole(bizUserGrantRoleParam);
        return CommonResult.ok();
    }

    /**
     * 人员导出
     */
    @ApiOperationSupport(order = 11)
    @Operation(summary = "人员导出")
    @CommonLog("人员导出")
    @SaCheckPermission("/biz/user/export")
    @GetMapping(value = "/biz/user/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void exportUser(BizUserExportParam bizUserExportParam, HttpServletResponse response) throws IOException {
        bizUserService.exportUser(bizUserExportParam, response);
    }

    /**
     * 按模板导出人员个人信息
     */
    @ApiOperationSupport(order = 12)
    @Operation(summary = "导出人员个人信息")
    @CommonLog("导出人员个人信息")
    @SaCheckPermission("/biz/user/exportUserInfo")
    @GetMapping(value = "/biz/user/exportUserInfo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void exportUserInfo(BizUserIdParam bizUserIdParam, HttpServletResponse response) throws IOException {
        bizUserService.exportUserInfo(bizUserIdParam, response);
    }

    /* ====人员部分所需要用到的选择器==== */

    /**
     * 获取机构树选择器
     */
    @ApiOperationSupport(order = 13)
    @Operation(summary = "获取机构树选择器")
    @SaCheckPermission("/biz/user/orgTreeSelector")
    @GetMapping("/biz/user/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(bizUserService.orgTreeSelector());
    }

    /**
     * 获取机构列表选择器
     */
    @ApiOperationSupport(order = 14)
    @Operation(summary = "获取机构列表选择器")
    @SaCheckPermission("/biz/user/orgListSelector")
    @GetMapping("/biz/user/orgListSelector")
    public CommonResult<Page<BizOrg>> orgListSelector(BizUserSelectorOrgListParam bizUserSelectorOrgListParam) {
        return CommonResult.data(bizUserService.orgListSelector(bizUserSelectorOrgListParam));
    }

    /**
     * 获取岗位选择器
     */
    @ApiOperationSupport(order = 15)
    @Operation(summary = "获取岗位选择器")
    @SaCheckPermission("/biz/user/positionSelector")
    @GetMapping("/biz/user/positionSelector")
    public CommonResult<Page<BizPosition>> positionSelector(BizUserSelectorPositionParam bizUserSelectorPositionParam) {
        return CommonResult.data(bizUserService.positionSelector(bizUserSelectorPositionParam));
    }

    /**
     * 获取角色选择器
     */
    @ApiOperationSupport(order = 16)
    @Operation(summary = "获取角色选择器")
    @SaCheckPermission("/biz/user/roleSelector")
    @GetMapping("/biz/user/roleSelector")
    public CommonResult<Page<BizUserRoleResult>> roleSelector(BizUserSelectorRoleParam bizUserSelectorRoleParam) {
        return CommonResult.data(bizUserService.roleSelector(bizUserSelectorRoleParam));
    }

    /**
     * 获取人员选择器
     */
    @ApiOperationSupport(order = 17)
    @Operation(summary = "获取人员选择器")
    @SaCheckPermission("/biz/user/userSelector")
    @GetMapping("/biz/user/userSelector")
    public CommonResult<Page<BizUser>> userSelector(BizUserSelectorUserParam bizUserSelectorUserParam) {
        return CommonResult.data(bizUserService.userSelector(bizUserSelectorUserParam));
    }
}
