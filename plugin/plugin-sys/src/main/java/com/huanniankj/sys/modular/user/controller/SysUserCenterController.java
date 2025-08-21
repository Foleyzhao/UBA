package com.huanniankj.sys.modular.user.controller;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.sys.modular.group.entity.SysGroup;
import com.huanniankj.sys.modular.org.entity.SysOrg;
import com.huanniankj.sys.modular.position.entity.SysPosition;
import com.huanniankj.sys.modular.role.entity.SysRole;
import com.huanniankj.sys.modular.user.entity.SysUser;
import com.huanniankj.sys.modular.user.param.SysUserBindEmailParam;
import com.huanniankj.sys.modular.user.param.SysUserBindPhoneParam;
import com.huanniankj.sys.modular.user.param.SysUserFindPwdByEmailParam;
import com.huanniankj.sys.modular.user.param.SysUserFindPwdByPhoneParam;
import com.huanniankj.sys.modular.user.param.SysUserGetEmailValidCodeParam;
import com.huanniankj.sys.modular.user.param.SysUserGetPhoneValidCodeParam;
import com.huanniankj.sys.modular.user.param.SysUserGroupIdListParam;
import com.huanniankj.sys.modular.user.param.SysUserIdListParam;
import com.huanniankj.sys.modular.user.param.SysUserIdParam;
import com.huanniankj.sys.modular.user.param.SysUserMessageIdParam;
import com.huanniankj.sys.modular.user.param.SysUserMessagePageParam;
import com.huanniankj.sys.modular.user.param.SysUserSignatureParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdateInfoParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdatePwdByEmailParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdatePwdByOldParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdatePwdByPhoneParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdateWorkbenchParam;
import com.huanniankj.sys.modular.user.result.SysUserMessageDetailResult;
import com.huanniankj.sys.modular.user.result.SysUserMessageResult;
import com.huanniankj.sys.modular.user.result.SysUserPicValidCodeResult;
import com.huanniankj.sys.modular.user.result.SysUserPositionResult;
import com.huanniankj.sys.modular.user.service.SysUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.util.List;

/**
 * B端用户个人控制器
 *
 * @author happynewyear
 */
@Tag(name = "B端用户个人控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 10)
@RestController
@Validated
public class SysUserCenterController {

    @Resource
    private SysUserService sysUserService;

    /**
     * 获取图片验证码
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取图片验证码")
    @GetMapping("/sys/userCenter/getPicCaptcha")
    public CommonResult<SysUserPicValidCodeResult> getPicCaptcha() {
        return CommonResult.data(sysUserService.getPicCaptcha());
    }

    /**
     * 找回密码获取手机验证码
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "找回密码获取手机验证码")
    @GetMapping("/sys/userCenter/findPasswordGetPhoneValidCode")
    public CommonResult<String> findPasswordGetPhoneValidCode(
            @Valid SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam) {
        return CommonResult.data(sysUserService.findPasswordGetPhoneValidCode(sysUserGetPhoneValidCodeParam));
    }

    /**
     * 找回密码获取邮箱验证码
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "找回密码获取邮箱验证码")
    @GetMapping("/sys/userCenter/findPasswordGetEmailValidCode")
    public CommonResult<String> findPasswordGetEmailValidCode(
            @Valid SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam) {
        return CommonResult.data(sysUserService.findPasswordGetEmailValidCode(sysUserGetEmailValidCodeParam));
    }

    /**
     * 通过手机号找回用户密码
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "通过手机号找回用户密码")
    @CommonLog("通过手机号找回用户密码")
    @PostMapping("/sys/userCenter/findPasswordByPhone")
    public CommonResult<String> findPasswordByPhone(
            @RequestBody @Valid SysUserFindPwdByPhoneParam sysUserFindPwdByPhoneParam) {
        sysUserService.findPasswordByPhone(sysUserFindPwdByPhoneParam);
        return CommonResult.ok();
    }

    /**
     * 通过邮箱找回用户密码
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "通过邮箱找回用户密码")
    @CommonLog("通过邮箱找回用户密码")
    @PostMapping("/sys/userCenter/findPasswordByEmail")
    public CommonResult<String> findPasswordByEmail(
            @RequestBody @Valid SysUserFindPwdByEmailParam sysUserFindPwdByEmailParam) {
        sysUserService.findPasswordByEmail(sysUserFindPwdByEmailParam);
        return CommonResult.ok();
    }

    /**
     * 修改密码获取手机验证码
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "修改密码获取手机验证码")
    @GetMapping("/sys/userCenter/updatePasswordGetPhoneValidCode")
    public CommonResult<String> updatePasswordGetPhoneValidCode(
            @Valid SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam) {
        return CommonResult.data(sysUserService.updatePasswordGetPhoneValidCode(sysUserGetPhoneValidCodeParam));
    }

    /**
     * 修改密码获取邮箱验证码
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "修改密码获取邮箱验证码")
    @GetMapping("/sys/userCenter/updatePasswordGetEmailValidCode")
    public CommonResult<String> updatePasswordGetEmailValidCode(
            @Valid SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam) {
        return CommonResult.data(sysUserService.updatePasswordGetEmailValidCode(sysUserGetEmailValidCodeParam));
    }

    /**
     * 通过验证旧密码修改用户密码
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "通过验证旧密码修改用户密码")
    @CommonLog("通过验证旧密码修改用户密码")
    @PostMapping("/sys/userCenter/updatePasswordByOld")
    public CommonResult<String> updatePasswordByOld(
            @RequestBody @Valid SysUserUpdatePwdByOldParam sysUserUpdatePwdByOldParam) {
        sysUserService.updatePasswordByOld(sysUserUpdatePwdByOldParam);
        return CommonResult.ok();
    }

    /**
     * 通过验证手机号修改用户密码
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "通过验证手机号修改用户密码")
    @CommonLog("通过验证手机号修改用户密码")
    @PostMapping("/sys/userCenter/updatePasswordByPhone")
    public CommonResult<String> updatePasswordByPhone(
            @RequestBody @Valid SysUserUpdatePwdByPhoneParam sysUserUpdatePwdByPhoneParam) {
        sysUserService.updatePasswordByPhone(sysUserUpdatePwdByPhoneParam);
        return CommonResult.ok();
    }

    /**
     * 通过验证邮箱修改用户密码
     */
    @ApiOperationSupport(order = 10)
    @Operation(summary = "通过验证邮箱修改用户密码")
    @CommonLog("通过验证邮箱修改用户密码")
    @PostMapping("/sys/userCenter/updatePasswordByEmail")
    public CommonResult<String> updatePasswordByEmail(
            @RequestBody @Valid SysUserUpdatePwdByEmailParam sysUserUpdatePwdByEmailParam) {
        sysUserService.updatePasswordByEmail(sysUserUpdatePwdByEmailParam);
        return CommonResult.ok();
    }

    /**
     * 绑定手机号获取手机验证码
     */
    @ApiOperationSupport(order = 11)
    @Operation(summary = "绑定手机号获取手机验证码")
    @GetMapping("/sys/userCenter/bindPhoneGetPhoneValidCode")
    public CommonResult<String> bindPhoneGetPhoneValidCode(
            @Valid SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam) {
        return CommonResult.data(sysUserService.bindPhoneGetPhoneValidCode(sysUserGetPhoneValidCodeParam));
    }

    /**
     * 修改绑定手机号获取手机验证码
     */
    @ApiOperationSupport(order = 12)
    @Operation(summary = "修改绑定手机号获取手机验证码")
    @GetMapping("/sys/userCenter/updateBindPhoneGetPhoneValidCode")
    public CommonResult<String> updateBindPhoneGetPhoneValidCode(
            @Valid SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam) {
        return CommonResult.data(sysUserService.updateBindPhoneGetPhoneValidCode(sysUserGetPhoneValidCodeParam));
    }

    /**
     * 绑定手机号
     */
    @ApiOperationSupport(order = 13)
    @Operation(summary = "绑定手机号")
    @CommonLog("绑定手机号")
    @PostMapping("/sys/userCenter/bindPhone")
    public CommonResult<String> bindPhone(@RequestBody @Valid SysUserBindPhoneParam sysUserBindPhoneParam) {
        sysUserService.bindPhone(sysUserBindPhoneParam);
        return CommonResult.ok();
    }

    /**
     * 绑定邮箱获取邮箱验证码
     */
    @ApiOperationSupport(order = 14)
    @Operation(summary = "绑定邮箱获取邮箱验证码")
    @GetMapping("/sys/userCenter/bindEmailGetEmailValidCode")
    public CommonResult<String> bindEmailGetEmailValidCode
    (@Valid SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam) {
        return CommonResult.data(sysUserService.bindEmailGetEmailValidCode(sysUserGetEmailValidCodeParam));
    }

    /**
     * 修改绑定邮箱获取邮箱验证码
     */
    @ApiOperationSupport(order = 15)
    @Operation(summary = "修改绑定邮箱获取邮箱验证码")
    @GetMapping("/sys/userCenter/updateBindEmailGetEmailValidCode")
    public CommonResult<String> updateBindEmailGetEmailValidCode(
            @Valid SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam) {
        return CommonResult.data(sysUserService.updateBindEmailGetEmailValidCode(sysUserGetEmailValidCodeParam));
    }

    /**
     * 绑定邮箱
     */
    @ApiOperationSupport(order = 16)
    @Operation(summary = "绑定邮箱")
    @CommonLog("绑定邮箱")
    @PostMapping("/sys/userCenter/bindEmail")
    public CommonResult<String> bindEmail(@RequestBody @Valid SysUserBindEmailParam sysUserBindEmailParam) {
        sysUserService.bindEmail(sysUserBindEmailParam);
        return CommonResult.ok();
    }

    /**
     * 修改用户头像
     */
    @ApiOperationSupport(order = 17)
    @Operation(summary = "修改用户头像")
    @CommonLog("修改用户头像")
    @PostMapping("/sys/userCenter/updateAvatar")
    public CommonResult<String> updateAvatar(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(sysUserService.updateAvatar(file));
    }

    /**
     * 修改用户签名图片
     */
    @ApiOperationSupport(order = 18)
    @Operation(summary = "修改用户签名图片")
    @CommonLog("修改用户签名图片")
    @PostMapping("/sys/userCenter/updateSignature")
    public CommonResult<String> updateSignature(@RequestBody @Valid SysUserSignatureParam sysUserSignatureParam) {
        sysUserService.updateSignature(sysUserSignatureParam);
        return CommonResult.ok();
    }

    /**
     * 编辑个人信息
     */
    @ApiOperationSupport(order = 19)
    @Operation(summary = "编辑个人信息")
    @CommonLog("编辑个人信息")
    @PostMapping("/sys/userCenter/updateUserInfo")
    public CommonResult<String> updateUserInfo(@RequestBody @Valid SysUserUpdateInfoParam sysUserUpdateInfoParam) {
        sysUserService.updateUserInfo(sysUserUpdateInfoParam);
        return CommonResult.ok();
    }

    /**
     * 编辑个人工作台
     */
    @ApiOperationSupport(order = 20)
    @Operation(summary = "编辑个人工作台")
    @CommonLog("编辑个人工作台")
    @PostMapping("/sys/userCenter/updateUserWorkbench")
    public CommonResult<String> updateUserWorkbench(
            @RequestBody @Valid SysUserUpdateWorkbenchParam sysUserUpdateWorkbenchParam) {
        sysUserService.updateUserWorkbench(sysUserUpdateWorkbenchParam);
        return CommonResult.ok();
    }

    /**
     * 获取登录用户的菜单（B端、PC端菜单）
     */
    @ApiOperationSupport(order = 21)
    @Operation(summary = "获取登录用户PC端菜单")
    @GetMapping("/sys/userCenter/loginMenu")
    public CommonResult<List<Tree<String>>> loginMenu() {
        SysUserIdParam sysUserIdParam = new SysUserIdParam();
        sysUserIdParam.setId(StpUtil.getLoginIdAsString());
        return CommonResult.data(sysUserService.ownMenu(sysUserIdParam));
    }

    /**
     * 获取登录用户的菜单（B端、移动端菜单）
     */
    @ApiOperationSupport(order = 22)
    @Operation(summary = "获取登录用户移动端菜单")
    @GetMapping("/sys/userCenter/loginMobileMenu")
    public CommonResult<List<Tree<String>>> loginMobileMenu() {
        SysUserIdParam sysUserIdParam = new SysUserIdParam();
        sysUserIdParam.setId(StpUtil.getLoginIdAsString());
        return CommonResult.data(sysUserService.ownMobileMenu(sysUserIdParam));
    }

    /**
     * 获取登录用户组织树
     */
    @ApiOperationSupport(order = 23)
    @Operation(summary = "获取登录用户组织树")
    @GetMapping("/sys/userCenter/loginOrgTree")
    public CommonResult<List<Tree<String>>> loginOrgTree() {
        SysUserIdParam sysUserIdParam = new SysUserIdParam();
        sysUserIdParam.setId(StpUtil.getLoginIdAsString());
        return CommonResult.data(sysUserService.loginOrgTree(sysUserIdParam));
    }

    /**
     * 获取登录用户的职位信息
     */
    @ApiOperationSupport(order = 24)
    @Operation(summary = "获取登录用户的职位信息")
    @GetMapping("/sys/userCenter/loginPositionInfo")
    public CommonResult<List<SysUserPositionResult>> loginPositionInfo() {
        SysUserIdParam sysUserIdParam = new SysUserIdParam();
        sysUserIdParam.setId(StpUtil.getLoginIdAsString());
        return CommonResult.data(sysUserService.loginPositionInfo(sysUserIdParam));
    }

    /**
     * 获取登录用户的工作台
     */
    @ApiOperationSupport(order = 25)
    @Operation(summary = "获取登录用户的工作台")
    @GetMapping("/sys/userCenter/loginWorkbench")
    public CommonResult<String> loginWorkbench() {
        SysUserIdParam sysUserIdParam = new SysUserIdParam();
        sysUserIdParam.setId(StpUtil.getLoginIdAsString());
        return CommonResult.data(sysUserService.loginWorkbench(sysUserIdParam));
    }

    /**
     * 获取登录用户的站内信分页
     */
    @ApiOperationSupport(order = 26)
    @Operation(summary = "获取登录用户的站内信分页")
    @GetMapping("/sys/userCenter/loginUnreadMessagePage")
    public CommonResult<Page<SysUserMessageResult>> loginMessagePage(SysUserMessagePageParam sysUserMessagePageParam) {
        return CommonResult.data(sysUserService.loginMessagePage(sysUserMessagePageParam));
    }

    /**
     * 读取登录用户站内信详情
     */
    @ApiOperationSupport(order = 27)
    @Operation(summary = "读取登录用户站内信详情")
    @GetMapping("/sys/userCenter/loginUnreadMessageDetail")
    public CommonResult<SysUserMessageDetailResult> loginMessageDetail(
            @Valid SysUserMessageIdParam sysUserMessageIdParam) {
        return CommonResult.data(sysUserService.loginMessageDetail(sysUserMessageIdParam));
    }

    /**
     * 根据id集合获取组织集合
     */
    @ApiOperationSupport(order = 28)
    @Operation(summary = "根据id集合获取组织集合")
    @PostMapping("/sys/userCenter/getOrgListByIdList")
    public CommonResult<List<SysOrg>> getOrgListByIdList(@RequestBody @Valid SysUserIdListParam sysUserIdListParam) {
        return CommonResult.data(sysUserService.getOrgListByIdList(sysUserIdListParam));
    }

    /**
     * 根据id集合获取用户集合
     */
    @ApiOperationSupport(order = 29)
    @Operation(summary = "根据id集合获取用户集合")
    @PostMapping("/sys/userCenter/getUserListByIdList")
    public CommonResult<List<SysUser>> getUserListByIdList(@RequestBody @Valid SysUserIdListParam sysUserIdListParam) {
        return CommonResult.data(sysUserService.getUserListByIdList(sysUserIdListParam));
    }

    /**
     * 根据id集合获取职位集合
     */
    @ApiOperationSupport(order = 30)
    @Operation(summary = "根据id集合获取职位集合")
    @PostMapping("/sys/userCenter/getPositionListByIdList")
    public CommonResult<List<SysPosition>> getPositionListByIdList(
            @RequestBody @Valid SysUserIdListParam sysUserIdListParam) {
        return CommonResult.data(sysUserService.getPositionListByIdList(sysUserIdListParam));
    }

    /**
     * 根据id集合获取角色集合
     */
    @ApiOperationSupport(order = 31)
    @Operation(summary = "根据id集合获取角色集合")
    @PostMapping("/sys/userCenter/getRoleListByIdList")
    public CommonResult<List<SysRole>> getRoleListByIdList(@RequestBody @Valid SysUserIdListParam sysUserIdListParam) {
        return CommonResult.data(sysUserService.getRoleListByIdList(sysUserIdListParam));
    }

    /**
     * 根据id集合获取用户组集合
     */
    @ApiOperationSupport(order = 32)
    @Operation(summary = "根据id集合获取用户组集合")
    @PostMapping("/sys/userCenter/getGroupListByIdList")
    public CommonResult<List<SysGroup>> getGroupListByIdList(
            @RequestBody @Valid SysUserGroupIdListParam sysUserGroupIdListParam) {
        return CommonResult.data(sysUserService.getGroupListByIdList(sysUserGroupIdListParam));
    }

    /**
     * 根据id获取头像
     */
    @ApiOperationSupport(order = 33)
    @Operation(summary = "根据id获取头像")
    @GetMapping("/sys/userCenter/getAvatarById")
    public CommonResult<String> getAvatarById(@Valid SysUserIdParam sysUserIdParam) {
        return CommonResult.data(sysUserService.getAvatarById(sysUserIdParam));
    }

    /**
     * 判断当前用户是否需要绑定手机号
     */
    @ApiOperationSupport(order = 34)
    @Operation(summary = "判断当前用户是否需要绑定手机号")
    @GetMapping("/sys/userCenter/isUserNeedBindPhone")
    public CommonResult<Boolean> isUserNeedBindPhone() {
        return CommonResult.data(sysUserService.isUserNeedBindPhone());
    }

    /**
     * 判断当前用户是否需要绑定邮箱
     */
    @ApiOperationSupport(order = 35)
    @Operation(summary = "判断当前用户是否需要绑定邮箱")
    @GetMapping("/sys/userCenter/isUserNeedBindEmail")
    public CommonResult<Boolean> isUserNeedBindEmail() {
        return CommonResult.data(sysUserService.isUserNeedBindEmail());
    }

    /**
     * 判断当前用户密码是否过期
     */
    @ApiOperationSupport(order = 36)
    @Operation(summary = "判断当前用户密码是否过期")
    @GetMapping("/sys/userCenter/isUserPasswordExpired")
    public CommonResult<Boolean> isUserPasswordExpired() {
        return CommonResult.data(sysUserService.isUserPasswordExpired());
    }

    /**
     * 获取修改密码验证方式及配置
     */
    @ApiOperationSupport(order = 37)
    @Operation(summary = "获取修改密码验证方式及配置")
    @GetMapping("/sys/userCenter/getUpdatePasswordValidConfig")
    public CommonResult<JSONObject> getUpdatePasswordValidConfig() {
        return CommonResult.data(sysUserService.getUpdatePasswordValidConfig());
    }

}
