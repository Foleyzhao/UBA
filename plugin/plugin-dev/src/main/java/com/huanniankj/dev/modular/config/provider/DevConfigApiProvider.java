package com.huanniankj.dev.modular.config.provider;

import com.huanniankj.dev.api.DevConfigApi;
import com.huanniankj.dev.modular.config.service.DevConfigService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 配置API接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevConfigApiProvider implements DevConfigApi {

    @Resource
    private DevConfigService devConfigService;

    @Override
    public String getValueByKey(String key) {
        return devConfigService.getValueByKey(key);
    }
}
