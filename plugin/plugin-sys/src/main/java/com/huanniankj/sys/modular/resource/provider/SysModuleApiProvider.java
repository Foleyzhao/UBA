package com.huanniankj.sys.modular.resource.provider;

import cn.hutool.json.JSONObject;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.huanniankj.sys.api.SysModuleApi;
import com.huanniankj.sys.modular.resource.service.SysModuleService;

import java.util.List;

/**
 * 模块API接口实现类
 *
 * @author happynewyear
 */
@Service
public class SysModuleApiProvider implements SysModuleApi {

    @Resource
    private SysModuleService sysModuleService;

    @Override
    public List<JSONObject> moduleSelector() {
        return sysModuleService.moduleSelector();
    }
}
