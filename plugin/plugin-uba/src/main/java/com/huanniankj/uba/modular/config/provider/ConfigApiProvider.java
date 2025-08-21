package com.huanniankj.uba.modular.config.provider;

import com.huanniankj.uba.api.ConfigApi;
import com.huanniankj.uba.modular.config.service.ConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 配置API接口实现类
 *
 * @author happynewyear
 */
@Service
public class ConfigApiProvider implements ConfigApi {

    @Resource
    private ConfigService configService;

    @Override
    public String getValueByKey(String key) {
        return configService.getValueByKey(key);
    }
}
