package com.huanniankj.dev.modular.job.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.job.entity.DevJob;
import com.huanniankj.dev.modular.job.param.DevJobAddParam;
import com.huanniankj.dev.modular.job.param.DevJobEditParam;
import com.huanniankj.dev.modular.job.param.DevJobIdParam;
import com.huanniankj.dev.modular.job.param.DevJobListParam;
import com.huanniankj.dev.modular.job.param.DevJobPageParam;

import java.util.List;

/**
 * 定时任务服务接口
 *
 * @author happynewyear
 */
public interface DevJobService extends IService<DevJob> {

    /**
     * 获取定时任务分页
     */
    Page<DevJob> page(DevJobPageParam devJobPageParam);

    /**
     * 获取定时任务列表
     */
    List<DevJob> list(DevJobListParam devJobListParam);

    /**
     * 添加定时任务
     */
    void add(DevJobAddParam devJobAddParam);

    /**
     * 编辑定时任务
     */
    void edit(DevJobEditParam devJobEditParam);

    /**
     * 删除定时任务
     */
    void delete(List<DevJobIdParam> devJobIdParamList);

    /**
     * 获取定时任务详情
     */
    DevJob detail(DevJobIdParam devJobIdParam);

    /**
     * 获取定时任务详情
     */
    DevJob queryEntity(String id);

    /**
     * 停止定时任务
     */
    void stopJob(DevJobIdParam devJobIdParam);

    /**
     * 运行定时任务
     */
    void runJob(DevJobIdParam devJobIdParam);

    /**
     * 立即运行定时任务
     */
    void runJobNow(DevJobIdParam devJobIdParam);

    /**
     * 获取定时任务类
     */
    List<String> getActionClass();
}
