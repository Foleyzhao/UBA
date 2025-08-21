package com.huanniankj.dev.modular.job.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.pattern.CronPattern;
import cn.hutool.extra.spring.SpringUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.dev.modular.job.param.DevJobAddParam;
import com.huanniankj.dev.modular.job.param.DevJobEditParam;
import com.huanniankj.dev.modular.job.param.DevJobIdParam;
import com.huanniankj.dev.modular.job.param.DevJobListParam;
import com.huanniankj.dev.modular.job.param.DevJobPageParam;
import org.springframework.aop.support.AopUtils;
import org.springframework.scheduling.support.CronExpression;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.common.timer.CommonTimerTaskRunner;
import com.huanniankj.dev.modular.job.entity.DevJob;
import com.huanniankj.dev.modular.job.enums.DevJobCategoryEnum;
import com.huanniankj.dev.modular.job.enums.DevJobStatusEnum;
import com.huanniankj.dev.modular.job.mapper.DevJobMapper;
import com.huanniankj.dev.modular.job.service.DevJobService;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 定时任务服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevJobServiceImpl extends ServiceImpl<DevJobMapper, DevJob> implements DevJobService {

    @Override
    public Page<DevJob> page(DevJobPageParam devJobPageParam) {
        QueryWrapper<DevJob> queryWrapper = new QueryWrapper<DevJob>().checkSqlInjection();
        // 查询部分字段
        queryWrapper.lambda().select(DevJob::getId, DevJob::getName, DevJob::getCategory,
                DevJob::getActionClass, DevJob::getCronExpression, DevJob::getJobStatus, DevJob::getSortCode);
        if(ObjectUtil.isNotEmpty(devJobPageParam.getCategory())) {
            queryWrapper.lambda().eq(DevJob::getCategory, devJobPageParam.getCategory());
        }
        if(ObjectUtil.isNotEmpty(devJobPageParam.getSearchKey())) {
            queryWrapper.lambda().like(DevJob::getName, devJobPageParam.getSearchKey());
        }
        if(ObjectUtil.isNotEmpty(devJobPageParam.getJobStatus())) {
            queryWrapper.lambda().like(DevJob::getJobStatus, devJobPageParam.getJobStatus());
        }
        if(ObjectUtil.isAllNotEmpty(devJobPageParam.getSortField(), devJobPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devJobPageParam.getSortOrder());
            queryWrapper.orderBy(true, devJobPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devJobPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(DevJob::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public List<DevJob> list(DevJobListParam devJobListParam) {
        QueryWrapper<DevJob> queryWrapper = new QueryWrapper<DevJob>().checkSqlInjection();
        // 查询部分字段
        queryWrapper.lambda().select(DevJob::getId, DevJob::getName, DevJob::getCategory,
                DevJob::getActionClass, DevJob::getCronExpression, DevJob::getJobStatus, DevJob::getSortCode);
        if(ObjectUtil.isNotEmpty(devJobListParam.getCategory())) {
            queryWrapper.lambda().eq(DevJob::getCategory, devJobListParam.getCategory());
        }
        if(ObjectUtil.isNotEmpty(devJobListParam.getSearchKey())) {
            queryWrapper.lambda().like(DevJob::getName, devJobListParam.getSearchKey());
        }
        if(ObjectUtil.isNotEmpty(devJobListParam.getJobStatus())) {
            queryWrapper.lambda().like(DevJob::getJobStatus, devJobListParam.getJobStatus());
        }
        return this.list(queryWrapper);
    }

    @Override
    public void add(DevJobAddParam devJobAddParam) {
        checkParam(devJobAddParam);
        DevJob devJob = BeanUtil.toBean(devJobAddParam, DevJob.class);
        devJob.setCode(RandomUtil.randomString(10));
        devJob.setJobStatus(DevJobStatusEnum.STOPPED.getValue());
        this.save(devJob);
    }

    private void checkParam(DevJobAddParam devJobAddParam) {
        DevJobCategoryEnum.validate(devJobAddParam.getCategory());
        try {
            CronPattern.of(devJobAddParam.getCronExpression());
        } catch (Exception e) {
            throw new CommonException("cron表达式：{}格式不正确", devJobAddParam.getCronExpression());
        }
        try {
            Class<?> actionClass = Class.forName(devJobAddParam.getActionClass());
            if(!CommonTimerTaskRunner.class.isAssignableFrom(actionClass)) {
                List<String> actionClassArr = StrUtil.split(devJobAddParam.getActionClass(), StrUtil.DOT);
                throw new CommonException("定时任务对应的类：{}不符合要求", actionClassArr.get(actionClassArr.size() - 1));
            }
        } catch (ClassNotFoundException e) {
            throw new CommonException("定时任务找不到对应的类，名称为：{}", devJobAddParam.getActionClass());
        }
        boolean hasSameJob = this.count(new LambdaQueryWrapper<DevJob>()
                .eq(DevJob::getActionClass, devJobAddParam.getActionClass())
                .eq(DevJob::getCronExpression, devJobAddParam.getCronExpression())) > 0;
        if (hasSameJob) {
            throw new CommonException("存在重复执行的定时任务，名称为：{}", devJobAddParam.getName());
        }
    }

    @Override
    public void edit(DevJobEditParam devJobEditParam) {
        DevJob devJob = this.queryEntity(devJobEditParam.getId());
        if(devJob.getJobStatus().equals(DevJobStatusEnum.RUNNING.getValue())) {
            throw new CommonException("运行中的定时任务不可编辑，id值为：{}", devJob.getId());
        }
        checkParam(devJobEditParam);
        BeanUtil.copyProperties(devJobEditParam, devJob);
        this.updateById(devJob);
    }

    private void checkParam(DevJobEditParam devJobEditParam) {
        DevJobCategoryEnum.validate(devJobEditParam.getCategory());
        if(!CronExpression.isValidExpression(devJobEditParam.getCronExpression())) {
            throw new CommonException("cron表达式：{}格式不正确", devJobEditParam.getCronExpression());
        }
        try {
            Class<?> actionClass = Class.forName(devJobEditParam.getActionClass());
            if(!CommonTimerTaskRunner.class.isAssignableFrom(actionClass)) {
                List<String> actionClassArr = StrUtil.split(devJobEditParam.getActionClass(), StrUtil.DOT);
                throw new CommonException("定时任务对应的类：{}不符合要求", actionClassArr.get(actionClassArr.size() - 1));
            }
        } catch (ClassNotFoundException e) {
            throw new CommonException("定时任务找不到对应的类，名称为：{}", devJobEditParam.getActionClass());
        }
        boolean hasSameJob = this.count(new LambdaQueryWrapper<DevJob>()
                .eq(DevJob::getActionClass, devJobEditParam.getActionClass())
                .eq(DevJob::getCronExpression, devJobEditParam.getCronExpression())
                .ne(DevJob::getId, devJobEditParam.getId())) > 0;
        if (hasSameJob) {
            throw new CommonException("存在重复的定时任务，名称为：{}", devJobEditParam.getName());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<DevJobIdParam> devJobIdParamList) {
        List<String> devJobIdList = CollStreamUtil.toList(devJobIdParamList, DevJobIdParam::getId);
        if(ObjectUtil.isNotEmpty(devJobIdList)) {
            // 将运行中的停止
            devJobIdList.forEach(CronUtil::remove);
            // 执行删除
            this.removeByIds(devJobIdList);
        }
    }

    @Override
    public DevJob detail(DevJobIdParam devJobIdParam) {
        return this.queryEntity(devJobIdParam.getId());
    }

    @Override
    public DevJob queryEntity(String id) {
        DevJob devJob = this.getById(id);
        if(ObjectUtil.isEmpty(devJob)) {
            throw new CommonException("定时任务不存在，id值为：{}", id);
        }
        return devJob;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void stopJob(DevJobIdParam devJobIdParam) {
        DevJob devJob = this.detail(devJobIdParam);
        if(devJob.getJobStatus().equals(DevJobStatusEnum.STOPPED.getValue())) {
            throw new CommonException("定时任务已经处于停止状态，id值为：{}", devJob.getId());
        }
        // 将运行中的定时任务停止
        CronUtil.remove(devJob.getId());
        this.update(new LambdaUpdateWrapper<DevJob>().eq(DevJob::getId, devJobIdParam.getId())
                .set(DevJob::getJobStatus, DevJobStatusEnum.STOPPED.getValue()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void runJob(DevJobIdParam devJobIdParam) {
        DevJob devJob = this.detail(devJobIdParam);
        if(devJob.getJobStatus().equals(DevJobStatusEnum.RUNNING.getValue())) {
            throw new CommonException("定时任务已经处于运行状态，id值为：{}", devJob.getId());
        }
        CronUtil.schedule(devJob.getId(), devJob.getCronExpression(), () -> {
            try {
                // 运行定时任务
                ((CommonTimerTaskRunner) SpringUtil.getBean(Class.forName(devJob.getActionClass())))
                        .action(devJob.getExtJson());
            } catch (ClassNotFoundException e) {
                throw new CommonException("定时任务找不到对应的类，名称为：{}", devJob.getActionClass());
            }
        });
        this.update(new LambdaUpdateWrapper<DevJob>().eq(DevJob::getId, devJobIdParam.getId())
                .set(DevJob::getJobStatus, DevJobStatusEnum.RUNNING.getValue()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void runJobNow(DevJobIdParam devJobIdParam) {
        DevJob devJob = this.detail(devJobIdParam);
        if(devJob.getJobStatus().equals(DevJobStatusEnum.STOPPED.getValue())) {
            // 如果是停止的，则先开启运行
            this.runJob(devJobIdParam);
        }
        try {
            // 直接运行一次
            ((CommonTimerTaskRunner) SpringUtil.getBean(Class.forName(devJob.getActionClass())))
                    .action(devJob.getExtJson());
        } catch (ClassNotFoundException e) {
            throw new CommonException("定时任务找不到对应的类，名称为：{}", devJob.getActionClass());
        }
    }

    @Override
    public List<String> getActionClass() {
        Map<String, CommonTimerTaskRunner> commonTimerTaskRunnerMap =
                SpringUtil.getBeansOfType(CommonTimerTaskRunner.class);
        if (ObjectUtil.isNotEmpty(commonTimerTaskRunnerMap)) {
            Collection<CommonTimerTaskRunner> values = commonTimerTaskRunnerMap.values();
            return values.stream().map(commonTimerTaskRunner -> {
                Class<?> clazz = null;
                if(AopUtils.isAopProxy(commonTimerTaskRunner)) {
                    clazz = AopUtils.getTargetClass(commonTimerTaskRunner);
                } else {
                    clazz = commonTimerTaskRunner.getClass();
                }
                return clazz.getName();
            }).collect(Collectors.toList());
        } else {
            return CollectionUtil.newArrayList();
        }
    }
}
