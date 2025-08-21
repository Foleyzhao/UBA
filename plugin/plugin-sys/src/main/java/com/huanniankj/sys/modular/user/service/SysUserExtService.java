package com.huanniankj.sys.modular.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.sys.modular.user.entity.SysUserExt;

/**
 * 用户扩展服务接口
 *
 * @author happynewyear
 */
public interface SysUserExtService extends IService<SysUserExt> {

    /**
     * 更新用户最新修改密码时间
     */
    void updatePasswordLastTime(String userId);

    /**
     * 插入扩展信息
     */
    void createExtInfo(String userId, String sourceFromType);

}
