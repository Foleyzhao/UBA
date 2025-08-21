package com.huanniankj.uba.modular.config.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.uba.modular.config.entity.Config;
import com.huanniankj.uba.modular.config.param.ConfigAddParam;
import com.huanniankj.uba.modular.config.param.ConfigBatchParam;
import com.huanniankj.uba.modular.config.param.ConfigEditParam;
import com.huanniankj.uba.modular.config.param.ConfigIdParam;
import com.huanniankj.uba.modular.config.param.ConfigListParam;
import com.huanniankj.uba.modular.config.param.ConfigPageParam;

import java.util.List;

/**
 * 配置服务接口
 *
 * @author happynewyear
 */
public interface ConfigService extends IService<Config> {

    /**
     * 根据键获取值
     */
    String getValueByKey(String key);

    /**
     * 获取配置分页
     */
    Page<Config> page(ConfigPageParam configPageParam);

    /**
     * 获取基础配置列表
     */
    List<Config> ubaDefineList();

    /**
     * 获取配置列表
     */
    List<Config> list(ConfigListParam configListParam);

    /**
     * 添加配置
     */
    void add(ConfigAddParam configAddParam);

    /**
     * 编辑配置
     */
    void edit(ConfigEditParam configEditParam);

    /**
     * 删除配置
     */
    void delete(List<ConfigIdParam> configIdParams);

    /**
     * 获取配置详情
     */
    Config detail(ConfigIdParam configIdParam);

    /**
     * 获取配置详情
     */
    Config queryEntity(String id);

    /**
     * 配置批量更新
     */
    void editBatch(List<ConfigBatchParam> configBatchParams);

}
