package com.huanniankj.sys.modular.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.sys.modular.user.entity.SysUserPassword;

import java.util.List;

/**
 * 用户密码服务接口
 *
 * @author happynewyear
 */
public interface SysUserPasswordService extends IService<SysUserPassword> {

    /**
     * 追加用户历史密码信息
     */
    void insertUserPasswordHistory(String userId, String newPassword);

    /**
     * 获取用户前N个历史密码
     */
    List<String> getUserPasswordHistoryLimit(String userId, int limitValue);

}
