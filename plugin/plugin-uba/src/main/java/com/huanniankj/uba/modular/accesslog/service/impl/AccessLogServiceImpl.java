package com.huanniankj.uba.modular.accesslog.service.impl;

import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.uba.modular.accesslog.entity.AccessLogCh;
import com.huanniankj.uba.modular.accesslog.mapper.AccessLogChMapper;
import com.huanniankj.uba.modular.accesslog.param.AccessLogPageParam;
import com.huanniankj.uba.modular.accesslog.param.AccessLogUuidParam;
import com.huanniankj.uba.modular.accesslog.service.AccessLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 访问日志服务接口实现类
 *
 * @author happynewyear
 */
@Slf4j
@Service
public class AccessLogServiceImpl extends ServiceImpl<AccessLogChMapper, AccessLogCh>
        implements AccessLogService {

    @Override
    public Page<AccessLogCh> page(AccessLogPageParam accessLogPageParam) {
        QueryWrapper<AccessLogCh> queryWrapper = new QueryWrapper<AccessLogCh>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(accessLogPageParam.getHttpUserAgent())) {
            queryWrapper.lambda().like(AccessLogCh::getHttpUserAgent, accessLogPageParam.getHttpUserAgent());
        }
        if (ObjectUtil.isNotEmpty(accessLogPageParam.getRequestUri())) {
            queryWrapper.lambda().like(AccessLogCh::getRequestUri, accessLogPageParam.getRequestUri());
        }
        if (ObjectUtil.isAllNotEmpty(accessLogPageParam.getSortField(), accessLogPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(accessLogPageParam.getSortOrder());
            queryWrapper.orderBy(true, accessLogPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(accessLogPageParam.getSortField()));
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<AccessLogUuidParam> accessLogUuidParams) {
        this.removeByIds(CollStreamUtil.toList(accessLogUuidParams, AccessLogUuidParam::getRequestId));
    }

    @Override
    public AccessLogCh detail(AccessLogUuidParam accessLogUuidParam) {
        return this.queryEntity(accessLogUuidParam.getRequestId());
    }

    @Override
    public AccessLogCh queryEntity(String id) {
        AccessLogCh accessLogCh = this.getOne(new QueryWrapper<AccessLogCh>().eq("request_id", id));
        // AccessLogCh accessLogCh = this.getById(id);
        if (ObjectUtil.isEmpty(accessLogCh)) {
            throw new CommonException("访问日志不存在，id值为：{}", id);
        }
        return accessLogCh;
    }

}
