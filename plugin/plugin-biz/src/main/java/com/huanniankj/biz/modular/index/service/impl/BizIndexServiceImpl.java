package com.huanniankj.biz.modular.index.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huanniankj.biz.modular.index.param.BizIndexNoticeIdParam;
import com.huanniankj.biz.modular.index.param.BizIndexNoticeListParam;
import com.huanniankj.biz.modular.index.param.BizIndexSlideshowListParam;
import com.huanniankj.biz.modular.index.result.BizIndexNoticeListResult;
import com.huanniankj.biz.modular.index.result.BizIndexSlideshowDetailResult;
import com.huanniankj.biz.modular.index.result.BizIndexSlideshowListResult;
import com.huanniankj.biz.modular.index.service.BizIndexService;
import com.huanniankj.biz.modular.notice.entity.BizNotice;
import com.huanniankj.biz.modular.notice.enums.BizNoticeStatusEnum;
import com.huanniankj.biz.modular.notice.service.BizNoticeService;
import com.huanniankj.dev.api.DevSlideshowApi;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 业务首页服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class BizIndexServiceImpl implements BizIndexService {


    @Resource
    private DevSlideshowApi devSlideshowApi;

    @Resource
    private BizNoticeService bizNoticeService;

    @Override
    public List<BizIndexSlideshowListResult> slideshowListByPlace(
            BizIndexSlideshowListParam bizIndexSlideshowListParam) {
        return devSlideshowApi.getListByPlace(bizIndexSlideshowListParam.getPlace()).stream()
                .map(jsonObject -> JSONUtil.toBean(jsonObject, BizIndexSlideshowListResult.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<BizIndexNoticeListResult> noticeListByLimit(BizIndexNoticeListParam bizIndexNoticeListParam) {
        return bizNoticeService.list(new LambdaQueryWrapper<BizNotice>()
                        .eq(BizNotice::getStatus, BizNoticeStatusEnum.ENABLE.getValue())
                        .orderByAsc(BizNotice::getCreateTime)
                )
                .stream()
                .limit(ObjectUtil.isNotEmpty(bizIndexNoticeListParam.getLimit()) ?
                        bizIndexNoticeListParam.getLimit().longValue() : 10)
                .map(notice -> JSONUtil.toBean(JSONUtil.toJsonStr(notice), BizIndexNoticeListResult.class))
                .collect(Collectors.toList());
    }

    @Override
    public BizIndexSlideshowDetailResult noticeDetailById(BizIndexNoticeIdParam bizIndexNoticeIdParam) {
        BizIndexSlideshowDetailResult result = new BizIndexSlideshowDetailResult();
        BeanUtil.copyProperties(bizNoticeService.getById(bizIndexNoticeIdParam.getId()), result);
        return result;
    }
}
