package com.huanniankj.client.modular.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.client.modular.user.entity.ClientUserExt;

/**
 * C端用户扩展服务接口
 *
 * @author happynewyear
 */
public interface ClientUserExtService extends IService<ClientUserExt> {

    /**
     * 更新用户最新修改密码时间
     */
    void updatePasswordLastTime(String userId);

    /**
     * 插入扩展信息
     */
    void createExtInfo(String userId, String sourceFromType);
}
