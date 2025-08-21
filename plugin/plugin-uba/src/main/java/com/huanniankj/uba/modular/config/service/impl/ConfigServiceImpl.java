package com.huanniankj.uba.modular.config.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.cache.CommonCacheOperator;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.uba.modular.config.entity.Config;
import com.huanniankj.uba.modular.config.enums.ConfigCategoryEnum;
import com.huanniankj.uba.modular.config.mapper.ConfigMapper;
import com.huanniankj.uba.modular.config.param.ConfigAddParam;
import com.huanniankj.uba.modular.config.param.ConfigBatchParam;
import com.huanniankj.uba.modular.config.param.ConfigEditParam;
import com.huanniankj.uba.modular.config.param.ConfigIdParam;
import com.huanniankj.uba.modular.config.param.ConfigListParam;
import com.huanniankj.uba.modular.config.param.ConfigPageParam;
import com.huanniankj.uba.modular.config.service.ConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 配置服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {

    /**
     * 缓存前缀
     */
    private static final String CONFIG_CACHE_KEY = "dev-config:";

    /**
     * 是否开启请求来源过滤
     */
    private static final String UBA_DEFAULT_ENABLE_REFERER_FILTER = "UBA_DEFAULT_ENABLE_REFERER_FILTER";

    /**
     * 是否开启请求路径过滤
     */
    private static final String UBA_DEFAULT_ENABLE_URI_FILTER = "UBA_DEFAULT_ENABLE_URI_FILTER";

    /**
     * 是否开启客户端浏览器标识过滤
     */
    private static final String UBA_DEFAULT_ENABLE_UA_FILTER = "UBA_DEFAULT_ENABLE_UA_FILTER";

    /**
     * 是否开启请求客户端IP过滤
     */
    private static final String UBA_DEFAULT_ENABLE_IP_FILTER = "UBA_DEFAULT_ENABLE_IP_FILTER";

    /**
     * 请求来源过滤列表
     */
    private static final String UBA_DEFAULT_REFERER_FILTER_LIST = "UBA_DEFAULT_REFERER_FILTER_LIST";

    /**
     * 请求路径过滤列表
     */
    private static final String UBA_DEFAULT_URI_FILTER_LIST = "UBA_DEFAULT_URI_FILTER_LIST";

    /**
     * 客户端浏览器标识过滤列表
     */
    private static final String UBA_DEFAULT_UA_FILTER_LIST = "UBA_DEFAULT_UA_FILTER_LIST";

    /**
     * 请求客户端IP过滤列表
     */
    private static final String UBA_DEFAULT_IP_FILTER_LIST = "UBA_DEFAULT_IP_FILTER_LIST";

    @Resource
    private CommonCacheOperator commonCacheOperator;

    @Override
    public String getValueByKey(String key) {
        // 从缓存中取
        Object cacheValue = commonCacheOperator.get(CONFIG_CACHE_KEY + key);
        if (ObjectUtil.isNotEmpty(cacheValue)) {
            return Convert.toStr(cacheValue);
        }
        Config config = this.getOne(new LambdaQueryWrapper<Config>().eq(Config::getConfigKey, key));
        if (ObjectUtil.isNotEmpty(config)) {
            // 更新到缓存
            commonCacheOperator.put(CONFIG_CACHE_KEY + key, config.getConfigValue());
            return config.getConfigValue();
        }
        return null;
    }

    @Override
    public Page<Config> page(ConfigPageParam configPageParam) {
        QueryWrapper<Config> queryWrapper = new QueryWrapper<Config>().checkSqlInjection();
        // 查询部分字段
        queryWrapper.lambda().select(Config::getId, Config::getConfigKey, Config::getConfigValue,
                Config::getCategory, Config::getRemark, Config::getSortCode);
        if (ObjectUtil.isNotEmpty(configPageParam.getSearchKey())) {
            queryWrapper.lambda().like(Config::getConfigKey, configPageParam.getSearchKey());
        }
        if (ObjectUtil.isAllNotEmpty(configPageParam.getSortField(), configPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(configPageParam.getSortOrder());
            queryWrapper.orderBy(true, configPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(configPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(Config::getSortCode);
        }
        queryWrapper.lambda().eq(Config::getCategory, ConfigCategoryEnum.UBA_DEFINE.getValue());
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Override
    public List<Config> ubaDefineList() {
        LambdaQueryWrapper<Config> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 查询部分字段
        lambdaQueryWrapper.select(Config::getId, Config::getConfigKey, Config::getConfigValue,
                Config::getCategory, Config::getSortCode, Config::getRemark);
        // 类型为UBA业务定义的
        lambdaQueryWrapper.eq(Config::getCategory, ConfigCategoryEnum.UBA_DEFINE.getValue());
        // 或key为特殊的几个
        lambdaQueryWrapper.or().in(Config::getConfigKey,
                CollectionUtil.newArrayList(UBA_DEFAULT_ENABLE_REFERER_FILTER,
                        UBA_DEFAULT_ENABLE_URI_FILTER,
                        UBA_DEFAULT_ENABLE_UA_FILTER,
                        UBA_DEFAULT_ENABLE_IP_FILTER,
                        UBA_DEFAULT_REFERER_FILTER_LIST,
                        UBA_DEFAULT_URI_FILTER_LIST,
                        UBA_DEFAULT_UA_FILTER_LIST,
                        UBA_DEFAULT_IP_FILTER_LIST));
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public List<Config> list(ConfigListParam configListParam) {
        LambdaQueryWrapper<Config> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 查询部分字段
        lambdaQueryWrapper.select(Config::getId, Config::getConfigKey, Config::getConfigValue,
                Config::getCategory, Config::getSortCode, Config::getRemark);
        if (ObjectUtil.isNotEmpty(configListParam.getCategory())) {
            lambdaQueryWrapper.eq(Config::getCategory, configListParam.getCategory());
        }
        return this.list(lambdaQueryWrapper);
    }

    @Override
    public void add(ConfigAddParam configAddParam) {
        checkParam(configAddParam);
        Config config = BeanUtil.toBean(configAddParam, Config.class);
        config.setCategory(ConfigCategoryEnum.UBA_DEFINE.getValue());
        this.save(config);
    }

    private void checkParam(ConfigAddParam devConfigAddParam) {
        boolean hasSameConfig = this.count(new LambdaQueryWrapper<Config>()
                .eq(Config::getConfigKey, devConfigAddParam.getConfigKey())) > 0;
        if (hasSameConfig) {
            throw new CommonException("存在重复的配置，配置键为：{}", devConfigAddParam.getConfigKey());
        }
    }

    @Override
    public void edit(ConfigEditParam configEditParam) {
        Config config = this.queryEntity(configEditParam.getId());
        checkParam(configEditParam);
        BeanUtil.copyProperties(configEditParam, config);
        config.setCategory(ConfigCategoryEnum.UBA_DEFINE.getValue());
        this.updateById(config);
        // 移除对应的缓存
        commonCacheOperator.remove(CONFIG_CACHE_KEY + config.getConfigKey());
    }

    private void checkParam(ConfigEditParam configEditParam) {
        boolean hasSameConfig = this.count(new LambdaQueryWrapper<Config>()
                .eq(Config::getConfigKey, configEditParam.getConfigKey())
                .ne(Config::getId, configEditParam.getId())) > 0;
        if (hasSameConfig) {
            throw new CommonException("存在重复的配置，配置键为：{}", configEditParam.getConfigKey());
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<ConfigIdParam> configIdParams) {
        List<String> configIdList = CollStreamUtil.toList(configIdParams, ConfigIdParam::getId);
        if (ObjectUtil.isNotEmpty(configIdList)) {
            List<Config> configList = this.listByIds(configIdList);
            if (ObjectUtil.isNotEmpty(configList)) {
                // 执行删除
                this.removeByIds(configList);
                configList.forEach(devConfig -> {
                    // 移除对应的缓存
                    commonCacheOperator.remove(CONFIG_CACHE_KEY + devConfig.getConfigKey());
                });
            }
        }
    }

    @Override
    public Config detail(ConfigIdParam configIdParam) {
        return this.queryEntity(configIdParam.getId());
    }

    @Override
    public Config queryEntity(String id) {
        Config devConfig = this.getById(id);
        if (ObjectUtil.isEmpty(devConfig)) {
            throw new CommonException("配置不存在，id值为：{}", id);
        }
        return devConfig;
    }

    @Override
    public void editBatch(List<ConfigBatchParam> configBatchParams) {
        configBatchParams.forEach(devConfigBatchParam -> {
            this.update(new LambdaUpdateWrapper<Config>()
                    .eq(Config::getConfigKey, devConfigBatchParam.getConfigKey())
                    .set(Config::getConfigValue, devConfigBatchParam.getConfigValue()));
            // 移除对应的缓存
            commonCacheOperator.remove(CONFIG_CACHE_KEY + devConfigBatchParam.getConfigKey());
        });
    }

}
