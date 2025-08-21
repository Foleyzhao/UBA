package com.huanniankj.dev.modular.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.config.entity.DevConfig;
import com.huanniankj.dev.modular.config.param.DevConfigAddParam;
import com.huanniankj.dev.modular.config.param.DevConfigBatchParam;
import com.huanniankj.dev.modular.config.param.DevConfigEditParam;
import com.huanniankj.dev.modular.config.param.DevConfigIdParam;
import com.huanniankj.dev.modular.config.param.DevConfigListParam;
import com.huanniankj.dev.modular.config.param.DevConfigPageParam;

import java.util.List;

/**
 * 配置服务接口
 *
 * @author happynewyear
 */
public interface DevConfigService extends IService<DevConfig> {

    /**
     * 根据键获取值
     */
    String getValueByKey(String key);

    /**
     * 获取配置分页
     */
    Page<DevConfig> page(DevConfigPageParam devConfigPageParam);

    /**
     * 获取基础配置列表
     */
    List<DevConfig> sysBaseList();

    /**
     * 获取配置列表
     */
    List<DevConfig> list(DevConfigListParam devConfigListParam);

    /**
     * 添加配置
     */
    void add(DevConfigAddParam devConfigAddParam);

    /**
     * 编辑配置
     */
    void edit(DevConfigEditParam devConfigEditParam);

    /**
     * 删除配置
     */
    void delete(List<DevConfigIdParam> devConfigIdParamList);

    /**
     * 获取配置详情
     */
    DevConfig detail(DevConfigIdParam devConfigIdParam);

    /**
     * 获取配置详情
     */
    DevConfig queryEntity(String id);

    /**
     * 配置批量更新
     */
    void editBatch(List<DevConfigBatchParam> devConfigBatchParamList);
}
