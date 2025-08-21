package com.huanniankj.gen.modular.config.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.gen.modular.config.entity.GenConfig;
import com.huanniankj.gen.modular.config.param.GenConfigEditParam;
import com.huanniankj.gen.modular.config.param.GenConfigIdParam;
import com.huanniankj.gen.modular.config.param.GenConfigListParam;

import java.util.List;

/**
 * 代码生成详细配置配置服务接口
 *
 * @author happynewyear
 */
public interface GenConfigService extends IService<GenConfig> {

    /**
     * 查询代码生成详细配置列表
     */
    List<GenConfig> list(GenConfigListParam genConfigListParam);

    /**
     * 编辑代码生成详细配置
     */
    void edit(GenConfigEditParam genConfigEditParam);

    /**
     * 删除代码生成详细配置
     */
    void delete(List<GenConfigIdParam> genConfigIdParamList);

    /**
     * 获取代码生成详细配置详情
     */
    GenConfig detail(GenConfigIdParam genConfigIdParam);

    /**
     * 获取代码生成详细配置详情
     */
    GenConfig queryEntity(String id);

    /**
     * 批量编辑代码生成详细配置
     */
    void editBatch(List<GenConfigEditParam> genConfigEditParamList);
}
