package com.huanniankj.biz.modular.notice.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.biz.modular.notice.entity.BizNotice;
import com.huanniankj.biz.modular.notice.enums.BizNoticeStatusEnum;
import com.huanniankj.biz.modular.notice.mapper.BizNoticeMapper;
import com.huanniankj.biz.modular.notice.param.BizNoticeAddParam;
import com.huanniankj.biz.modular.notice.param.BizNoticeEditParam;
import com.huanniankj.biz.modular.notice.param.BizNoticeIdParam;
import com.huanniankj.biz.modular.notice.param.BizNoticePageParam;
import com.huanniankj.biz.modular.notice.service.BizNoticeService;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 通知公告服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class BizNoticeServiceImpl extends ServiceImpl<BizNoticeMapper, BizNotice> implements BizNoticeService {


    @Override
    public Page<BizNotice> page(BizNoticePageParam bizNoticePageParam) {
        QueryWrapper<BizNotice> queryWrapper = new QueryWrapper<BizNotice>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(bizNoticePageParam.getTitle())) {
            queryWrapper.lambda().like(BizNotice::getTitle, bizNoticePageParam.getTitle());
        }
        if (ObjectUtil.isNotEmpty(bizNoticePageParam.getType())) {
            queryWrapper.lambda().eq(BizNotice::getType, bizNoticePageParam.getType());
        }
        if (ObjectUtil.isNotEmpty(bizNoticePageParam.getPlace())) {
            queryWrapper.lambda().like(BizNotice::getPlace, bizNoticePageParam.getPlace());
        }
        if (ObjectUtil.isNotEmpty(bizNoticePageParam.getStatus())) {
            queryWrapper.lambda().eq(BizNotice::getStatus, bizNoticePageParam.getStatus());
        }
        if (ObjectUtil.isNotEmpty(bizNoticePageParam.getStartCreateTime())
                && ObjectUtil.isNotEmpty(bizNoticePageParam.getEndCreateTime())) {
            queryWrapper.lambda().between(BizNotice::getCreateTime, bizNoticePageParam.getStartCreateTime(),
                    bizNoticePageParam.getEndCreateTime());
        }
        if (ObjectUtil.isAllNotEmpty(bizNoticePageParam.getSortField(), bizNoticePageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(bizNoticePageParam.getSortOrder());
            queryWrapper.orderBy(true, bizNoticePageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(bizNoticePageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(BizNotice::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(BizNoticeAddParam bizNoticeAddParam) {
        BizNotice bizNotice = BeanUtil.toBean(bizNoticeAddParam, BizNotice.class);
        // 默认为禁用的
        bizNotice.setStatus(BizNoticeStatusEnum.DISABLE.getValue());
        this.save(bizNotice);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(BizNoticeEditParam bizNoticeEditParam) {
        BizNotice bizNotice = this.queryEntity(bizNoticeEditParam.getId());
        BeanUtil.copyProperties(bizNoticeEditParam, bizNotice);
        this.updateById(bizNotice);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<BizNoticeIdParam> bizNoticeIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(bizNoticeIdParamList, BizNoticeIdParam::getId));
    }

    @Override
    public BizNotice detail(BizNoticeIdParam bizNoticeIdParam) {
        return this.queryEntity(bizNoticeIdParam.getId());
    }

    @Override
    public BizNotice queryEntity(String id) {
        BizNotice bizNotice = this.getById(id);
        if (ObjectUtil.isEmpty(bizNotice)) {
            throw new CommonException("通知公告不存在，id值为：{}", id);
        }
        return bizNotice;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void disableStatus(BizNoticeIdParam bizNoticeIdParam) {
        this.update(new LambdaUpdateWrapper<BizNotice>().eq(BizNotice::getId,
                bizNoticeIdParam.getId()).set(BizNotice::getStatus, BizNoticeStatusEnum.DISABLE.getValue()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enableStatus(BizNoticeIdParam bizNoticeIdParam) {
        this.update(new LambdaUpdateWrapper<BizNotice>().eq(BizNotice::getId,
                bizNoticeIdParam.getId()).set(BizNotice::getStatus, BizNoticeStatusEnum.ENABLE.getValue()));
    }
}
