package com.huanniankj.client.modular.user.result;

import com.huanniankj.auth.core.pojo.SaBaseClientLoginUser;
import com.huanniankj.client.modular.user.enums.ClientUserStatusEnum;

/**
 * C端登录用户对象
 *
 * @author happynewyear
 */
public class ClientLoginUser extends SaBaseClientLoginUser {

    /**
     * 实现是否可以登录
     */
    @Override
    public Boolean getEnabled() {
        // 仅判断状态是否正常，可自行扩展
        return this.getUserStatus().equals(ClientUserStatusEnum.ENABLE.getValue());
    }
}
