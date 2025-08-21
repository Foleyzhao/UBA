package com.huanniankj.biz.modular.notice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.biz.modular.notice.entity.BizNotice;
import com.huanniankj.biz.modular.notice.param.BizNoticeAddParam;
import com.huanniankj.biz.modular.notice.param.BizNoticeEditParam;
import com.huanniankj.biz.modular.notice.param.BizNoticeIdParam;
import com.huanniankj.biz.modular.notice.param.BizNoticePageParam;

import java.util.List;

/**
 * 通知公告服务接口
 *
 * @author happynewyear
 */
public interface BizNoticeService extends IService<BizNotice> {

    /**
     * 获取通知公告分页
     */
    Page<BizNotice> page(BizNoticePageParam bizNoticePageParam);

    /**
     * 添加通知公告
     */
    void add(BizNoticeAddParam bizNoticeAddParam);

    /**
     * 编辑通知公告
     */
    void edit(BizNoticeEditParam bizNoticeEditParam);

    /**
     * 删除通知公告
     */
    void delete(List<BizNoticeIdParam> bizNoticeIdParamList);

    /**
     * 获取通知公告详情
     */
    BizNotice detail(BizNoticeIdParam bizNoticeIdParam);

    /**
     * 获取通知公告详情
     */
    BizNotice queryEntity(String id);

    /**
     * 禁用通知公告
     */
    void disableStatus(BizNoticeIdParam bizNoticeIdParam);

    /**
     * 启用通知公告
     */
    void enableStatus(BizNoticeIdParam bizNoticeIdParam);
}
