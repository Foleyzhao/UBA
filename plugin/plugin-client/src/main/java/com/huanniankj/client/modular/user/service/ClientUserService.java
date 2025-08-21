package com.huanniankj.client.modular.user.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.client.modular.user.entity.ClientUser;
import com.huanniankj.client.modular.user.param.ClientUserAddParam;
import com.huanniankj.client.modular.user.param.ClientUserBindEmailParam;
import com.huanniankj.client.modular.user.param.ClientUserBindPhoneParam;
import com.huanniankj.client.modular.user.param.ClientUserEditParam;
import com.huanniankj.client.modular.user.param.ClientUserFindPwdByEmailParam;
import com.huanniankj.client.modular.user.param.ClientUserFindPwdByPhoneParam;
import com.huanniankj.client.modular.user.param.ClientUserGetEmailValidCodeParam;
import com.huanniankj.client.modular.user.param.ClientUserGetPhoneValidCodeParam;
import com.huanniankj.client.modular.user.param.ClientUserIdParam;
import com.huanniankj.client.modular.user.param.ClientUserPageParam;
import com.huanniankj.client.modular.user.param.ClientUserSignatureParam;
import com.huanniankj.client.modular.user.param.ClientUserUpdateInfoParam;
import com.huanniankj.client.modular.user.param.ClientUserUpdatePwdByEmailParam;
import com.huanniankj.client.modular.user.param.ClientUserUpdatePwdByOldParam;
import com.huanniankj.client.modular.user.param.ClientUserUpdatePwdByPhoneParam;
import com.huanniankj.client.modular.user.result.ClientLoginUser;
import com.huanniankj.client.modular.user.result.ClientUserPicValidCodeResult;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 用户服务接口
 *
 * @author happynewyear
 */
public interface ClientUserService extends IService<ClientUser> {

    /**
     * 根据id获取用户信息，查不到则返回null
     */
    ClientLoginUser getUserById(String id);

    /**
     * 根据账户获取用户信息，查不到则返回null
     */
    ClientLoginUser getUserByAccount(String account);

    /**
     * 根据手机号获取用户信息，查不到则返回null
     */
    ClientLoginUser getUserByPhone(String phone);

    /**
     * 根据邮箱获取用户信息，查不到则返回null
     */
    ClientLoginUser getUserByEmail(String email);

    /**
     * 获取用户分页
     */
    Page<ClientUser> page(ClientUserPageParam clientUserPageParam);

    /**
     * 添加用户
     */
    void add(ClientUserAddParam clientUserAddParam, String sourceFromType);

    /**
     * 编辑用户
     */
    void edit(ClientUserEditParam clientUserEditParam);

    /**
     * 删除用户
     */
    void delete(List<ClientUserIdParam> clientUserIdParamList);

    /**
     * 获取用户详情
     */
    ClientUser detail(ClientUserIdParam clientUserIdParam);

    /**
     * 获取用户详情
     */
    ClientUser queryEntity(String id);

    /**
     * 获取图片验证码
     */
    ClientUserPicValidCodeResult getPicCaptcha();

    /**
     * 找回密码获取手机验证码
     */
    String findPasswordGetPhoneValidCode(ClientUserGetPhoneValidCodeParam clientUserGetPhoneValidCodeParam);

    /**
     * 找回密码获取邮箱验证码
     */
    String findPasswordGetEmailValidCode(ClientUserGetEmailValidCodeParam clientUserGetEmailValidCodeParam);

    /**
     * 通过手机号找回用户密码
     */
    void findPasswordByPhone(ClientUserFindPwdByPhoneParam clientUserFindPwdByPhoneParam);

    /**
     * 通过邮箱找回用户密码
     */
    void findPasswordByEmail(ClientUserFindPwdByEmailParam clientUserFindPwdByEmailParam);

    /**
     * 修改密码获取手机验证码
     */
    String updatePasswordGetPhoneValidCode(ClientUserGetPhoneValidCodeParam clientUserGetPhoneValidCodeParam);

    /**
     * 修改密码获取邮箱验证码
     */
    String updatePasswordGetEmailValidCode(ClientUserGetEmailValidCodeParam clientUserGetEmailValidCodeParam);

    /**
     * 通过验证旧密码修改用户密码
     */
    void updatePasswordByOld(ClientUserUpdatePwdByOldParam clientUserUpdatePwdByOldParam);

    /**
     * 通过验证手机号修改用户密码
     */
    void updatePasswordByPhone(ClientUserUpdatePwdByPhoneParam clientUserUpdatePwdByPhoneParam);

    /**
     * 通过验证邮箱修改用户密码
     */
    void updatePasswordByEmail(ClientUserUpdatePwdByEmailParam clientUserUpdatePwdByEmailParam);

    /**
     * 绑定手机号获取手机验证码
     */
    String bindPhoneGetPhoneValidCode(ClientUserGetPhoneValidCodeParam clientUserGetPhoneValidCodeParam);

    /**
     * 修改绑定手机号获取手机验证码
     */
    String updateBindPhoneGetPhoneValidCode(ClientUserGetPhoneValidCodeParam clientUserGetPhoneValidCodeParam);

    /**
     * 绑定手机号
     */
    void bindPhone(ClientUserBindPhoneParam clientUserBindPhoneParam);

    /**
     * 绑定邮箱获取邮箱验证码
     */
    String bindEmailGetEmailValidCode(ClientUserGetEmailValidCodeParam clientUserGetEmailValidCodeParam);

    /**
     * 修改绑定邮箱获取邮箱验证码
     */
    String updateBindEmailGetEmailValidCode(ClientUserGetEmailValidCodeParam clientUserGetEmailValidCodeParam);

    /**
     * 绑定邮箱
     */
    void bindEmail(ClientUserBindEmailParam clientUserBindEmailParam);

    /**
     * 修改用户头像返回base64
     */
    String updateAvatar(MultipartFile file);

    /**
     * 修改用户签名图片返回base64
     */
    void updateSignature(ClientUserSignatureParam clientUserSignatureParam);

    /**
     * 更新用户的登录时间和登录ip等信息
     */
    void updateUserLoginInfo(String userId, String device);

    /**
     * 编辑个人信息
     */
    void updateUserInfo(ClientUserUpdateInfoParam clientUserUpdateInfoParam);

    /**
     * 根据id获取头像
     */
    String getAvatarById(ClientUserIdParam clientUserIdParam);

    /**
     * 根据手机号创建用户
     */
    ClientUser createUserWithPhone(String phone);

    /**
     * 根据邮箱创建用户
     */
    ClientUser createUserWithEmail(String email);

    /**
     * 根据账号密码创建用户
     */
    ClientUser createUserWithAccount(String account, String password);

    /**
     * 判断当前用户密码是否过期
     */
    Boolean isUserPasswordExpired();

    /**
     * 判断当前用户是否需要绑定手机号
     */
    Boolean isUserNeedBindPhone();

    /**
     * 判断当前用户是否需要绑定邮箱
     */
    Boolean isUserNeedBindEmail();

    /**
     * 通知用户密码即将到期
     */
    void noticeUserPasswordAboutToExpired();

    /**
     * 执行注册
     */
    void doRegister(String account, String password);
}
