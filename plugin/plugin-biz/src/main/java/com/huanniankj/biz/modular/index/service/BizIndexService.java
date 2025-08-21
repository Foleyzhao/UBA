package com.huanniankj.biz.modular.index.service;

import com.huanniankj.biz.modular.index.param.BizIndexNoticeIdParam;
import com.huanniankj.biz.modular.index.param.BizIndexNoticeListParam;
import com.huanniankj.biz.modular.index.param.BizIndexSlideshowListParam;
import com.huanniankj.biz.modular.index.result.BizIndexNoticeListResult;
import com.huanniankj.biz.modular.index.result.BizIndexSlideshowDetailResult;
import com.huanniankj.biz.modular.index.result.BizIndexSlideshowListResult;
import jakarta.validation.Valid;

import java.util.List;

/**
 * 业务首页服务接口
 *
 * @author happynewyear
 */
public interface BizIndexService {

    /**
     * 获取轮播图列表
     */
    List<BizIndexSlideshowListResult> slideshowListByPlace(BizIndexSlideshowListParam bizIndexSlideshowListParam);

    /**
     * 获取通知公告列表
     */
    List<BizIndexNoticeListResult> noticeListByLimit(@Valid BizIndexNoticeListParam bizIndexNoticeListParam);

    /**
     * 获取通知公告详情
     */
    BizIndexSlideshowDetailResult noticeDetailById(BizIndexNoticeIdParam bizIndexNoticeIdParam);
}
