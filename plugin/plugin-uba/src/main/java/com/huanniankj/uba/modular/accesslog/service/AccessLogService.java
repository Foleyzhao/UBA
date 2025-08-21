package com.huanniankj.uba.modular.accesslog.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.uba.modular.accesslog.entity.AccessLogCh;
import com.huanniankj.uba.modular.accesslog.param.AccessLogPageParam;
import com.huanniankj.uba.modular.accesslog.param.AccessLogUuidParam;

import java.util.List;

/**
 * 访问日志服务接口
 *
 * @author happynewyear
 */
public interface AccessLogService {

    /**
     * 获取访问日志分页
     */
    Page<AccessLogCh> page(AccessLogPageParam accessLogPageParam);

    /**
     * 删除访问日志
     */
    void delete(List<AccessLogUuidParam> accessLogUuidParams);

    /**
     * 获取访问日志详情
     */
    AccessLogCh detail(AccessLogUuidParam accessLogUuidParam);

    /**
     * 获取访问日志详情
     */
    AccessLogCh queryEntity(String id);

}
