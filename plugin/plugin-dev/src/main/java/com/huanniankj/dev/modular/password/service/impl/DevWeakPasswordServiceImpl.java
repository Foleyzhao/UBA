package com.huanniankj.dev.modular.password.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.dev.modular.password.entity.DevWeakPassword;
import com.huanniankj.dev.modular.password.mapper.DevWeakPasswordMapper;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordAddParam;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordEditParam;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordIdParam;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordPageParam;
import com.huanniankj.dev.modular.password.service.DevWeakPasswordService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 弱密码库服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevWeakPasswordServiceImpl extends ServiceImpl<DevWeakPasswordMapper, DevWeakPassword>
        implements DevWeakPasswordService {

    @Override
    public Page<DevWeakPassword> page(DevWeakPasswordPageParam devWeakPasswordPageParam) {
        QueryWrapper<DevWeakPassword> queryWrapper = new QueryWrapper<DevWeakPassword>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(devWeakPasswordPageParam.getSearchKey())) {
            queryWrapper.lambda().like(DevWeakPassword::getPassword, devWeakPasswordPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(devWeakPasswordPageParam.getSortField(), devWeakPasswordPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devWeakPasswordPageParam.getSortOrder());
            queryWrapper.orderBy(true, devWeakPasswordPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devWeakPasswordPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByDesc(DevWeakPassword::getCreateTime);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(DevWeakPasswordAddParam devWeakPasswordAddParam) {
        DevWeakPassword devWeakPassword = BeanUtil.toBean(devWeakPasswordAddParam, DevWeakPassword.class);
        boolean repeatPassword = this.count(new LambdaQueryWrapper<DevWeakPassword>()
                .eq(DevWeakPassword::getPassword, devWeakPassword.getPassword())) > 0;
        if (repeatPassword) {
            throw new CommonException("存在重复的密码，值为：{}", devWeakPassword.getPassword());
        }
        this.save(devWeakPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(DevWeakPasswordEditParam devWeakPasswordEditParam) {
        DevWeakPassword devWeakPassword = this.queryEntity(devWeakPasswordEditParam.getId());
        BeanUtil.copyProperties(devWeakPasswordEditParam, devWeakPassword);
        boolean repeatPassword = this.count(new LambdaQueryWrapper<DevWeakPassword>()
                .eq(DevWeakPassword::getPassword, devWeakPassword.getPassword())
                .ne(DevWeakPassword::getId, devWeakPassword.getId())) > 0;
        if (repeatPassword) {
            throw new CommonException("存在重复的密码，值为：{}", devWeakPassword.getPassword());
        }
        this.updateById(devWeakPassword);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<DevWeakPasswordIdParam> devWeakPasswordIdParamList) {
        List<String> weakPasswordIdList = CollStreamUtil.toList(devWeakPasswordIdParamList,
                DevWeakPasswordIdParam::getId);
        // 执行删除
        this.removeByIds(weakPasswordIdList);
    }

    @Override
    public DevWeakPassword detail(DevWeakPasswordIdParam devWeakPasswordIdParam) {
        return this.queryEntity(devWeakPasswordIdParam.getId());
    }

    @Override
    public DevWeakPassword queryEntity(String id) {
        DevWeakPassword devWeakPassword = this.getById(id);
        if (ObjectUtil.isEmpty(devWeakPassword)) {
            throw new CommonException("弱密码数据不存在，id值为：{}", id);
        }
        return devWeakPassword;
    }
}
