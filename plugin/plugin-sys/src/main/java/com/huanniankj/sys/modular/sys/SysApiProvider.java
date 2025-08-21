package com.huanniankj.sys.modular.sys;

import com.huanniankj.sys.api.SysApi;
import com.huanniankj.sys.core.util.SysPasswordUtl;
import org.springframework.stereotype.Service;

/**
 * 系统模块综合API接口实现类
 *
 * @author happynewyear
 */
@Service
public class SysApiProvider implements SysApi {

    @Override
    public String getDefaultPassword() {
        return SysPasswordUtl.getDefaultPassword();
    }

}
