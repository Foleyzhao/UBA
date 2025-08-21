package com.huanniankj.sys.modular.resource.provider;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.huanniankj.sys.api.SysButtonApi;
import com.huanniankj.sys.modular.resource.service.SysButtonService;

/**
 * 按钮API接口实现类
 *
 * @author happynewyear
 */
@Service
public class SysButtonApiProvider implements SysButtonApi {

    @Resource
    private SysButtonService sysButtonService;

    @Override
    public void addForGenButton(String menuId, String className, String functionName) {
        sysButtonService.addForGenButton(menuId, className, functionName);
    }
}
