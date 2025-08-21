package com.huanniankj.client.modular.user.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.client.modular.user.entity.ClientUserExt;
import com.huanniankj.client.modular.user.enums.ClientUserSourceFromTypeEnum;
import com.huanniankj.client.modular.user.mapper.ClientUserExtMapper;
import com.huanniankj.client.modular.user.service.ClientUserExtService;
import org.springframework.stereotype.Service;

/**
 * C端用户扩展服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class ClientUserExtServiceImpl extends ServiceImpl<ClientUserExtMapper, ClientUserExt>
        implements ClientUserExtService {

    @Override
    public void updatePasswordLastTime(String userId) {
        ClientUserExt clientUserExt = this.getOne(new LambdaQueryWrapper<ClientUserExt>().
                eq(ClientUserExt::getUserId, userId));
        if (ObjectUtil.isEmpty(clientUserExt)) {
            clientUserExt = new ClientUserExt();
            clientUserExt.setUserId(userId);
            clientUserExt.setSourceFromType(ClientUserSourceFromTypeEnum.SYSTEM_ADD.getValue());
            clientUserExt.setPasswordUpdateTime(DateTime.now());
            this.save(clientUserExt);
        } else {
            clientUserExt.setPasswordUpdateTime(DateTime.now());
            this.updateById(clientUserExt);
        }
    }

    @Override
    public void createExtInfo(String userId, String sourceFromType) {
        ClientUserExt clientUserExt = new ClientUserExt();
        clientUserExt.setUserId(userId);
        clientUserExt.setSourceFromType(sourceFromType);
        clientUserExt.setPasswordUpdateTime(DateTime.now());
        this.save(clientUserExt);
    }
}
