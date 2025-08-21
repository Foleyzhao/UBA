package com.huanniankj.uba.modular.dict.provider;

import com.huanniankj.uba.api.DictApi;
import com.huanniankj.uba.modular.dict.service.DictService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 数据处理字典API接口实现类
 *
 * @author happynewyear
 */
@Service
public class DictApiProvider implements DictApi {

    @Resource
    private DictService devDictService;

    /**
     * 根据类型跟子类型获得翻译后的label
     */
    public String getDictLabel(String typeCode, String value) {
        return devDictService.getDictLabel(typeCode, value);
    }

}
