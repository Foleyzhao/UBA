package com.huanniankj.sys.modular.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.util.CommonCryptogramUtil;
import com.huanniankj.sys.modular.user.entity.SysUserPassword;
import com.huanniankj.sys.modular.user.mapper.SysUserPasswordMapper;
import com.huanniankj.sys.modular.user.service.SysUserPasswordService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户密码服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class SysUserPasswordServiceImpl extends ServiceImpl<SysUserPasswordMapper, SysUserPassword>
        implements SysUserPasswordService {

    @Override
    public void insertUserPasswordHistory(String userId, String newPassword) {
        SysUserPassword sysUserPassword = new SysUserPassword();
        sysUserPassword.setUserId(userId);
        sysUserPassword.setPassword(CommonCryptogramUtil.doHashValue(newPassword));
        this.save(sysUserPassword);
    }

    @Override
    public List<String> getUserPasswordHistoryLimit(String userId, int limitValue) {
        return this.page(new Page<>(1, limitValue), new LambdaQueryWrapper<SysUserPassword>()
                        .eq(SysUserPassword::getUserId, userId).orderByDesc(SysUserPassword::getCreateTime))
                .getRecords().stream().map(SysUserPassword::getPassword).collect(Collectors.toList());
    }

}
