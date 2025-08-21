package com.huanniankj.mobile.modular.resource.provider;

import cn.hutool.json.JSONObject;
import com.huanniankj.mobile.api.MobileModuleApi;
import com.huanniankj.mobile.modular.resource.service.MobileModuleService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 移动端模块API接口提供者
 *
 * @author happynewyear
 */
@Service
public class MobileModuleApiProvider implements MobileModuleApi {

    @Resource
    private MobileModuleService mobileModuleService;

    @Override
    public List<JSONObject> mobileModuleSelector() {
        return mobileModuleService.mobileModuleSelector();
    }
}
