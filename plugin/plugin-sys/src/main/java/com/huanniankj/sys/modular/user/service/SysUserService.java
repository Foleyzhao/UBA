package com.huanniankj.sys.modular.user.service;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.sys.modular.group.entity.SysGroup;
import com.huanniankj.sys.modular.org.entity.SysOrg;
import com.huanniankj.sys.modular.position.entity.SysPosition;
import com.huanniankj.sys.modular.role.entity.SysRole;
import com.huanniankj.sys.modular.user.entity.SysUser;
import com.huanniankj.sys.modular.user.param.SysUserAddParam;
import com.huanniankj.sys.modular.user.param.SysUserBindEmailParam;
import com.huanniankj.sys.modular.user.param.SysUserBindPhoneParam;
import com.huanniankj.sys.modular.user.param.SysUserEditParam;
import com.huanniankj.sys.modular.user.param.SysUserExportParam;
import com.huanniankj.sys.modular.user.param.SysUserFindPwdByEmailParam;
import com.huanniankj.sys.modular.user.param.SysUserFindPwdByPhoneParam;
import com.huanniankj.sys.modular.user.param.SysUserGetEmailValidCodeParam;
import com.huanniankj.sys.modular.user.param.SysUserGetPhoneValidCodeParam;
import com.huanniankj.sys.modular.user.param.SysUserGrantPermissionParam;
import com.huanniankj.sys.modular.user.param.SysUserGrantResourceParam;
import com.huanniankj.sys.modular.user.param.SysUserGrantRoleParam;
import com.huanniankj.sys.modular.user.param.SysUserGroupIdListParam;
import com.huanniankj.sys.modular.user.param.SysUserIdListParam;
import com.huanniankj.sys.modular.user.param.SysUserIdParam;
import com.huanniankj.sys.modular.user.param.SysUserMessageIdParam;
import com.huanniankj.sys.modular.user.param.SysUserMessagePageParam;
import com.huanniankj.sys.modular.user.param.SysUserPageParam;
import com.huanniankj.sys.modular.user.param.SysUserSelectorOrgListParam;
import com.huanniankj.sys.modular.user.param.SysUserSelectorPositionParam;
import com.huanniankj.sys.modular.user.param.SysUserSelectorRoleParam;
import com.huanniankj.sys.modular.user.param.SysUserSelectorUserParam;
import com.huanniankj.sys.modular.user.param.SysUserSignatureParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdateInfoParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdatePwdByEmailParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdatePwdByOldParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdatePwdByPhoneParam;
import com.huanniankj.sys.modular.user.param.SysUserUpdateWorkbenchParam;
import com.huanniankj.sys.modular.user.result.SysLoginUser;
import com.huanniankj.sys.modular.user.result.SysUserMessageDetailResult;
import com.huanniankj.sys.modular.user.result.SysUserMessageResult;
import com.huanniankj.sys.modular.user.result.SysUserOwnPermissionResult;
import com.huanniankj.sys.modular.user.result.SysUserOwnResourceResult;
import com.huanniankj.sys.modular.user.result.SysUserPicValidCodeResult;
import com.huanniankj.sys.modular.user.result.SysUserPositionResult;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 用户服务接口
 *
 * @author happynewyear
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 根据id获取用户信息，查不到则返回null
     */
    SysLoginUser getUserById(String id);

    /**
     * 根据账户获取用户信息，查不到则返回null
     */
    SysLoginUser getUserByAccount(String account);

    /**
     * 根据手机号获取用户信息，查不到则返回null
     */
    SysLoginUser getUserByPhone(String phone);

    /**
     * 根据邮箱获取用户信息，查不到则返回null
     */
    SysLoginUser getUserByEmail(String email);

    /**
     * 获取用户分页
     */
    Page<SysUser> page(SysUserPageParam sysUserPageParam);

    /**
     * 添加用户
     */
    void add(SysUserAddParam sysUserAddParam, String sourceFromType);

    /**
     * 编辑用户
     */
    void edit(SysUserEditParam sysUserEditParam);

    /**
     * 删除用户
     */
    void delete(List<SysUserIdParam> sysUserIdParamList);

    /**
     * 获取用户详情
     */
    SysUser detail(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户详情
     */
    SysUser queryEntity(String id);

    /**
     * 禁用用户
     */
    void disableUser(SysUserIdParam sysUserIdParam);

    /**
     * 启用用户
     */
    void enableUser(SysUserIdParam sysUserIdParam);

    /**
     * 重置用户密码
     */
    void resetPassword(SysUserIdParam sysUserIdParam);

    /**
     * 获取图片验证码
     */
    SysUserPicValidCodeResult getPicCaptcha();

    /**
     * 找回密码获取手机验证码
     */
    String findPasswordGetPhoneValidCode(SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam);

    /**
     * 找回密码获取邮箱验证码
     */
    String findPasswordGetEmailValidCode(SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam);

    /**
     * 通过手机号找回用户密码
     */
    void findPasswordByPhone(SysUserFindPwdByPhoneParam sysUserFindPwdByPhoneParam);

    /**
     * 通过邮箱找回用户密码
     */
    void findPasswordByEmail(SysUserFindPwdByEmailParam sysUserFindPwdByEmailParam);

    /**
     * 修改密码获取手机验证码
     */
    String updatePasswordGetPhoneValidCode(SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam);

    /**
     * 修改密码获取邮箱验证码
     */
    String updatePasswordGetEmailValidCode(SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam);

    /**
     * 通过验证旧密码修改用户密码
     */
    void updatePasswordByOld(SysUserUpdatePwdByOldParam sysUserUpdatePwdByOldParam);

    /**
     * 通过验证手机号修改用户密码
     */
    void updatePasswordByPhone(SysUserUpdatePwdByPhoneParam sysUserUpdatePwdByPhoneParam);

    /**
     * 通过验证邮箱修改用户密码
     */
    void updatePasswordByEmail(SysUserUpdatePwdByEmailParam sysUserUpdatePwdByEmailParam);

    /**
     * 绑定手机号获取手机验证码
     */
    String bindPhoneGetPhoneValidCode(SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam);

    /**
     * 修改绑定手机号获取手机验证码
     */
    String updateBindPhoneGetPhoneValidCode(SysUserGetPhoneValidCodeParam sysUserGetPhoneValidCodeParam);

    /**
     * 绑定手机号
     */
    void bindPhone(SysUserBindPhoneParam sysUserBindPhoneParam);

    /**
     * 绑定邮箱获取邮箱验证码
     */
    String bindEmailGetEmailValidCode(SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam);

    /**
     * 修改绑定邮箱获取邮箱验证码
     */
    String updateBindEmailGetEmailValidCode(SysUserGetEmailValidCodeParam sysUserGetEmailValidCodeParam);

    /**
     * 绑定邮箱
     */
    void bindEmail(SysUserBindEmailParam sysUserBindEmailParam);

    /**
     * 修改用户头像返回base64
     */
    String updateAvatar(MultipartFile file);

    /**
     * 修改用户签名图片返回base64
     */
    void updateSignature(SysUserSignatureParam sysUserSignatureParam);

    /**
     * 更新用户的登录时间和登录ip等信息
     */
    void updateUserLoginInfo(String userId, String device);

    /**
     * 获取用户拥有菜单
     */
    List<Tree<String>> ownMenu(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户拥有移动端菜单
     */
    List<Tree<String>> ownMobileMenu(SysUserIdParam sysUserIdParam);

    /**
     * 获取用户拥有角色
     */
    List<String> ownRole(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权角色
     */
    void grantRole(SysUserGrantRoleParam sysUserGrantRoleParam);

    /**
     * 获取用户拥有资源
     */
    SysUserOwnResourceResult ownResource(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权资源
     */
    void grantResource(SysUserGrantResourceParam sysUserGrantResourceParam);

    /**
     * 获取用户拥有权限
     */
    SysUserOwnPermissionResult ownPermission(SysUserIdParam sysUserIdParam);

    /**
     * 给用户授权权限
     */
    void grantPermission(SysUserGrantPermissionParam sysUserGrantPermissionParam);

    /**
     * 获取用户组织树
     */
    List<Tree<String>> loginOrgTree(SysUserIdParam sysUserIdParam);

    /**
     * 编辑个人信息
     */
    void updateUserInfo(SysUserUpdateInfoParam sysUserUpdateInfoParam);

    /**
     * 编辑个人工作台
     */
    void updateUserWorkbench(SysUserUpdateWorkbenchParam sysUserUpdateWorkbenchParam);

    /**
     * 获取用户工作台数据
     */
    String loginWorkbench(SysUserIdParam sysUserIdParam);

    /**
     * 获取角色集合
     */
    List<JSONObject> getRoleList(String userId);

    /**
     * 获取按钮编码集合
     */
    List<String> getButtonCodeList(List<String> userAndRoleIdList);

    /**
     * 获取移动端按钮编码集合
     */
    List<String> getMobileButtonCodeList(List<String> userAndRoleIdList);

    /**
     * 获取权限集合
     */
    List<JSONObject> getPermissionList(List<String> userAndRoleIdList, String orgId);

    /**
     * 下载用户导入模板
     */
    void downloadImportUserTemplate(HttpServletResponse response) throws IOException;

    /**
     * 用户导入
     */
    JSONObject importUser(MultipartFile file);

    /**
     * 用户导出
     */
    void exportUser(SysUserExportParam sysUserExportParam, HttpServletResponse response) throws IOException;

    /**
     * 导出用户个人信息
     */
    void exportUserInfo(SysUserIdParam sysUserIdParam, HttpServletResponse response) throws IOException;

    /**
     * 获取登录用户的职位信息
     */
    List<SysUserPositionResult> loginPositionInfo(SysUserIdParam sysUserIdParam);

    /**
     * 获取所有用户选择器
     */
    Page<SysUser> getAllUserSelectorList();

    /* ====用户部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     */
    Page<SysOrg> orgListSelector(SysUserSelectorOrgListParam sysUserSelectorOrgListParam);

    /**
     * 获取职位选择器
     */
    Page<SysPosition> positionSelector(SysUserSelectorPositionParam sysUserSelectorPositionParam);

    /**
     * 获取角色选择器
     */
    Page<SysRole> roleSelector(SysUserSelectorRoleParam sysUserSelectorRoleParam);

    /**
     * 获取用户选择器
     */
    Page<SysUser> userSelector(SysUserSelectorUserParam sysUserSelectorUserParam);

    /**
     * 获取登录用户的站内信分页
     */
    Page<SysUserMessageResult> loginMessagePage(SysUserMessagePageParam sysUserMessagePageParam);

    /**
     * 读取登录用户站内信详情
     */
    SysUserMessageDetailResult loginMessageDetail(SysUserMessageIdParam sysUserMessageIdParam);

    /**
     * 根据id集合获取组织集合
     */
    List<SysOrg> getOrgListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取用户集合
     */
    List<SysUser> getUserListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取职位集合
     */
    List<SysPosition> getPositionListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取角色集合
     */
    List<SysRole> getRoleListByIdList(SysUserIdListParam sysUserIdListParam);

    /**
     * 根据id集合获取用户组集合
     */
    List<SysGroup> getGroupListByIdList(SysUserGroupIdListParam sysUserGroupIdListParam);

    /**
     * 根据id获取头像
     */
    String getAvatarById(SysUserIdParam sysUserIdParam);

    /**
     * 根据手机号创建用户
     */
    SysUser createUserWithPhone(String phone);

    /**
     * 根据邮箱创建用户
     */
    SysUser createUserWithEmail(String email);

    /**
     * 根据账号密码创建用户
     */
    SysUser createUserWithAccount(String account, String password);

    /**
     * 判断当前用户是否需要绑定手机号
     */
    Boolean isUserNeedBindPhone();

    /**
     * 判断当前用户是否需要绑定邮箱
     */
    Boolean isUserNeedBindEmail();

    /**
     * 判断当前用户密码是否过期
     */
    Boolean isUserPasswordExpired();

    /**
     * 通知用户密码即将到期
     */
    void noticeUserPasswordAboutToExpired();

    /**
     * 执行注册
     */
    void doRegister(String account, String password);

    /**
     * 获取修改密码验证方式及配置
     */
    JSONObject getUpdatePasswordValidConfig();
}
