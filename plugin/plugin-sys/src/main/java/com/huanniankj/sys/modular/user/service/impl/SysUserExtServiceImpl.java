package com.huanniankj.sys.modular.user.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.sys.modular.user.entity.SysUserExt;
import com.huanniankj.sys.modular.user.enums.SysUserSourceFromTypeEnum;
import com.huanniankj.sys.modular.user.mapper.SysUserExtMapper;
import com.huanniankj.sys.modular.user.service.SysUserExtService;
import org.springframework.stereotype.Service;

/**
 * 用户扩展服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class SysUserExtServiceImpl extends ServiceImpl<SysUserExtMapper, SysUserExt> implements SysUserExtService {

    @Override
    public void updatePasswordLastTime(String userId) {
        SysUserExt sysUserExt = this.getOne(new LambdaQueryWrapper<SysUserExt>().eq(SysUserExt::getUserId, userId));
        if (ObjectUtil.isEmpty(sysUserExt)) {
            sysUserExt = new SysUserExt();
            sysUserExt.setUserId(userId);
            sysUserExt.setSourceFromType(SysUserSourceFromTypeEnum.SYSTEM_ADD.getValue());
            sysUserExt.setPasswordUpdateTime(DateTime.now());
            this.save(sysUserExt);
        } else {
            sysUserExt.setPasswordUpdateTime(DateTime.now());
            this.updateById(sysUserExt);
        }
    }

    @Override
    public void createExtInfo(String userId, String sourceFromType) {
        SysUserExt sysUserExt = new SysUserExt();
        sysUserExt.setUserId(userId);
        sysUserExt.setSourceFromType(sourceFromType);
        sysUserExt.setPasswordUpdateTime(DateTime.now());
        this.save(sysUserExt);
    }

}
