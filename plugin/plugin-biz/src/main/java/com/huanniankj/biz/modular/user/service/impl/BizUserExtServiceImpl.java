package com.huanniankj.biz.modular.user.service.impl;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.biz.modular.user.entity.BizUserExt;
import com.huanniankj.biz.modular.user.mapper.BizUserExtMapper;
import com.huanniankj.biz.modular.user.service.BizUserExtService;
import org.springframework.stereotype.Service;

/**
 * 用户扩展服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class BizUserExtServiceImpl extends ServiceImpl<BizUserExtMapper, BizUserExt> implements BizUserExtService {

    @Override
    public void updatePasswordLastTime(String userId) {
        BizUserExt bizUserExt = this.getOne(new LambdaQueryWrapper<BizUserExt>().eq(BizUserExt::getUserId, userId));
        if (ObjectUtil.isEmpty(bizUserExt)) {
            bizUserExt = new BizUserExt();
            bizUserExt.setUserId(userId);
            bizUserExt.setPasswordUpdateTime(DateTime.now());
            this.save(bizUserExt);
        } else {
            bizUserExt.setPasswordUpdateTime(DateTime.now());
            this.updateById(bizUserExt);
        }
    }

    @Override
    public void createExtInfo(String userId, String sourceFromType) {
        BizUserExt bizUserExt = new BizUserExt();
        bizUserExt.setUserId(userId);
        bizUserExt.setSourceFromType(sourceFromType);
        bizUserExt.setPasswordUpdateTime(DateTime.now());
        this.save(bizUserExt);
    }
}
