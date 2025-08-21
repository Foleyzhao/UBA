package com.huanniankj.dev.modular.dict.provider;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.huanniankj.dev.api.DevDictApi;
import com.huanniankj.dev.modular.dict.service.DevDictService;

/**
 * 字典API接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevDictApiProvider implements DevDictApi {

    @Resource
    private DevDictService devDictService;

    /**
     * 根据类型跟子类型获得翻译后的label
     */
    public String getDictLabel(String typeCode, String value) {
        return devDictService.getDictLabel(typeCode, value);
    }
}
