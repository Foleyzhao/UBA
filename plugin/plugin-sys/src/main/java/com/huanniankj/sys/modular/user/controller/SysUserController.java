package com.huanniankj.sys.modular.user.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.sys.modular.org.entity.SysOrg;
import com.huanniankj.sys.modular.position.entity.SysPosition;
import com.huanniankj.sys.modular.role.entity.SysRole;
import com.huanniankj.sys.modular.user.entity.SysUser;
import com.huanniankj.sys.modular.user.enums.SysUserSourceFromTypeEnum;
import com.huanniankj.sys.modular.user.param.SysUserAddParam;
import com.huanniankj.sys.modular.user.param.SysUserEditParam;
import com.huanniankj.sys.modular.user.param.SysUserExportParam;
import com.huanniankj.sys.modular.user.param.SysUserGrantPermissionParam;
import com.huanniankj.sys.modular.user.param.SysUserGrantResourceParam;
import com.huanniankj.sys.modular.user.param.SysUserGrantRoleParam;
import com.huanniankj.sys.modular.user.param.SysUserIdParam;
import com.huanniankj.sys.modular.user.param.SysUserPageParam;
import com.huanniankj.sys.modular.user.param.SysUserSelectorOrgListParam;
import com.huanniankj.sys.modular.user.param.SysUserSelectorPositionParam;
import com.huanniankj.sys.modular.user.param.SysUserSelectorRoleParam;
import com.huanniankj.sys.modular.user.param.SysUserSelectorUserParam;
import com.huanniankj.sys.modular.user.result.SysUserOwnPermissionResult;
import com.huanniankj.sys.modular.user.result.SysUserOwnResourceResult;
import com.huanniankj.sys.modular.user.service.SysUserService;
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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * B端用户控制器
 *
 * @author happynewyear
 */
@Tag(name = "B端用户控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 9)
@RestController
@Validated
public class SysUserController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 获取用户分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取用户分页")
    @GetMapping("/sys/user/page")
    public CommonResult<Page<SysUser>> page(SysUserPageParam sysUserPageParam) {
        return CommonResult.data(sysUserService.page(sysUserPageParam));
    }

    /**
     * 添加用户
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加用户")
    @CommonLog("添加用户")
    @PostMapping("/sys/user/add")
    public CommonResult<String> add(@RequestBody @Valid SysUserAddParam sysUserAddParam) {
        sysUserService.add(sysUserAddParam, SysUserSourceFromTypeEnum.SYSTEM_ADD.getValue());
        return CommonResult.ok();
    }

    /**
     * 编辑用户
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑用户")
    @CommonLog("编辑用户")
    @PostMapping("/sys/user/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysUserEditParam sysUserEditParam) {
        sysUserService.edit(sysUserEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除用户
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除用户")
    @CommonLog("删除用户")
    @PostMapping("/sys/user/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<SysUserIdParam> sysUserIdParamList) {
        sysUserService.delete(sysUserIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取用户详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取用户详情")
    @GetMapping("/sys/user/detail")
    public CommonResult<SysUser> detail(@Valid SysUserIdParam sysUserIdParam) {
        return CommonResult.data(sysUserService.detail(sysUserIdParam));
    }

    /**
     * 禁用用户
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "禁用用户")
    @CommonLog("禁用用户")
    @PostMapping("/sys/user/disableUser")
    public CommonResult<String> disableUser(@RequestBody SysUserIdParam sysUserIdParam) {
        sysUserService.disableUser(sysUserIdParam);
        return CommonResult.ok();
    }

    /**
     * 启用用户
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "启用用户")
    @CommonLog("启用用户")
    @PostMapping("/sys/user/enableUser")
    public CommonResult<String> enableUser(@RequestBody @Valid SysUserIdParam sysUserIdParam) {
        sysUserService.enableUser(sysUserIdParam);
        return CommonResult.ok();
    }

    /**
     * 重置用户密码
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "重置用户密码")
    @CommonLog("重置用户密码")
    @PostMapping("/sys/user/resetPassword")
    public CommonResult<String> resetPassword(@RequestBody @Valid SysUserIdParam sysUserIdParam) {
        sysUserService.resetPassword(sysUserIdParam);
        return CommonResult.ok();
    }

    /**
     * 用户拥有角色
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "获取用户拥有角色")
    @GetMapping("/sys/user/ownRole")
    public CommonResult<List<String>> ownRole(@Valid SysUserIdParam sysUserIdParam) {
        return CommonResult.data(sysUserService.ownRole(sysUserIdParam));
    }

    /**
     * 给用户授权角色
     */
    @ApiOperationSupport(order = 10)
    @Operation(summary = "给用户授权角色")
    @CommonLog("给用户授权角色")
    @PostMapping("/sys/user/grantRole")
    public CommonResult<String> grantRole(@RequestBody @Valid SysUserGrantRoleParam sysUserGrantRoleParam) {
        sysUserService.grantRole(sysUserGrantRoleParam);
        return CommonResult.ok();
    }

    /**
     * 获取用户拥有资源
     */
    @ApiOperationSupport(order = 11)
    @Operation(summary = "获取用户拥有资源")
    @GetMapping("/sys/user/ownResource")
    public CommonResult<SysUserOwnResourceResult> ownResource(@Valid SysUserIdParam sysUserIdParam) {
        return CommonResult.data(sysUserService.ownResource(sysUserIdParam));
    }

    /**
     * 给用户授权资源
     */
    @ApiOperationSupport(order = 12)
    @Operation(summary = "给用户授权资源")
    @CommonLog("给用户授权资源")
    @PostMapping("/sys/user/grantResource")
    public CommonResult<String> grantResource(@RequestBody @Valid SysUserGrantResourceParam sysUserGrantResourceParam) {
        sysUserService.grantResource(sysUserGrantResourceParam);
        return CommonResult.ok();
    }

    /**
     * 获取用户拥有权限
     */
    @ApiOperationSupport(order = 13)
    @Operation(summary = "获取用户拥有权限")
    @GetMapping("/sys/user/ownPermission")
    public CommonResult<SysUserOwnPermissionResult> ownPermission(@Valid SysUserIdParam sysUserIdParam) {
        return CommonResult.data(sysUserService.ownPermission(sysUserIdParam));
    }

    /**
     * 给用户授权权限
     */
    @ApiOperationSupport(order = 14)
    @Operation(summary = "给用户授权权限")
    @CommonLog("给用户授权权限")
    @PostMapping("/sys/user/grantPermission")
    public CommonResult<String> grantPermission(
            @RequestBody @Valid SysUserGrantPermissionParam sysUserGrantPermissionParam) {
        sysUserService.grantPermission(sysUserGrantPermissionParam);
        return CommonResult.ok();
    }

    /**
     * 下载用户导入模板
     */
    @ApiOperationSupport(order = 15)
    @Operation(summary = "下载用户导入模板")
    @CommonLog("下载用户导入模板")
    @GetMapping(value = "/sys/user/downloadImportUserTemplate", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadImportUserTemplate(HttpServletResponse response) throws IOException {
        sysUserService.downloadImportUserTemplate(response);
    }

    /**
     * 用户导入
     */
    @ApiOperationSupport(order = 16)
    @Operation(summary = "用户导入")
    @CommonLog("用户导入")
    @PostMapping("/sys/user/import")
    public CommonResult<JSONObject> importUser(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(sysUserService.importUser(file));
    }

    /**
     * 用户导出
     */
    @ApiOperationSupport(order = 17)
    @Operation(summary = "用户导出")
    @CommonLog("用户导出")
    @GetMapping(value = "/sys/user/export", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void exportUser(SysUserExportParam sysUserExportParam, HttpServletResponse response) throws IOException {
        sysUserService.exportUser(sysUserExportParam, response);
    }

    /**
     * 按模板导出用户个人信息
     */
    @ApiOperationSupport(order = 18)
    @Operation(summary = "导出用户个人信息")
    @CommonLog("导出用户个人信息")
    @GetMapping(value = "/sys/user/exportUserInfo", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void exportUserInfo(SysUserIdParam sysUserIdParam, HttpServletResponse response) throws IOException {
        sysUserService.exportUserInfo(sysUserIdParam, response);
    }

    /* ====用户部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     */
    @ApiOperationSupport(order = 19)
    @Operation(summary = "获取组织树选择器")
    @GetMapping("/sys/user/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(sysUserService.orgTreeSelector());
    }

    /**
     * 获取组织列表选择器
     */
    @ApiOperationSupport(order = 20)
    @Operation(summary = "获取组织列表选择器")
    @GetMapping("/sys/user/orgListSelector")
    public CommonResult<Page<SysOrg>> orgListSelector(SysUserSelectorOrgListParam sysUserSelectorOrgListParam) {
        return CommonResult.data(sysUserService.orgListSelector(sysUserSelectorOrgListParam));
    }

    /**
     * 获取职位选择器
     */
    @ApiOperationSupport(order = 21)
    @Operation(summary = "获取职位选择器")
    @GetMapping("/sys/user/positionSelector")
    public CommonResult<Page<SysPosition>> positionSelector(SysUserSelectorPositionParam sysUserSelectorPositionParam) {
        return CommonResult.data(sysUserService.positionSelector(sysUserSelectorPositionParam));
    }

    /**
     * 获取角色选择器
     */
    @ApiOperationSupport(order = 22)
    @Operation(summary = "获取角色选择器")
    @GetMapping("/sys/user/roleSelector")
    public CommonResult<Page<SysRole>> roleSelector(SysUserSelectorRoleParam sysUserSelectorRoleParam) {
        return CommonResult.data(sysUserService.roleSelector(sysUserSelectorRoleParam));
    }

    /**
     * 获取用户选择器
     */
    @ApiOperationSupport(order = 23)
    @Operation(summary = "获取用户选择器")
    @GetMapping("/sys/user/userSelector")
    public CommonResult<Page<SysUser>> userSelector(SysUserSelectorUserParam sysUserSelectorUserParam) {
        return CommonResult.data(sysUserService.userSelector(sysUserSelectorUserParam));
    }

}
