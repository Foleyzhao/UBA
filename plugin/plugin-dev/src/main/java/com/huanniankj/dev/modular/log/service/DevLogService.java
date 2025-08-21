package com.huanniankj.dev.modular.log.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.log.entity.DevLog;
import com.huanniankj.dev.modular.log.param.DevLogDeleteParam;
import com.huanniankj.dev.modular.log.param.DevLogIdParam;
import com.huanniankj.dev.modular.log.param.DevLogPageParam;
import com.huanniankj.dev.modular.log.result.DevLogOpBarChartDataResult;
import com.huanniankj.dev.modular.log.result.DevLogOpPieChartDataResult;
import com.huanniankj.dev.modular.log.result.DevLogVisLineChartDataResult;
import com.huanniankj.dev.modular.log.result.DevLogVisPieChartDataResult;

import java.util.List;

/**
 * 日志服务接口
 *
 * @author happynewyear
 */
public interface DevLogService extends IService<DevLog> {

    /**
     * 获取日志分页
     */
    Page<DevLog> page(DevLogPageParam devLogPageParam);

    /**
     * 获取单条日志详情
     */
    DevLog detail(DevLogIdParam devLogIdParam);

    /**
     * 清空日志
     */
    void delete(DevLogDeleteParam devLogDeleteParam);

    /**
     * 获取访问日志折线图数据
     */
    List<DevLogVisLineChartDataResult> visLogLineChartData();

    /**
     * 获取访问日志饼状图数据
     */
    List<DevLogVisPieChartDataResult> visLogPieChartData();

    /**
     * 获取操作日志柱状图数据
     */
    List<DevLogOpBarChartDataResult> opLogBarChartData();

    /**
     * 获取操作日志饼状图数据
     */
    List<DevLogOpPieChartDataResult> opLogPieChartData();
}
