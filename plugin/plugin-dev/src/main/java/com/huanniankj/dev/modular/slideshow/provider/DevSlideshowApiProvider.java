package com.huanniankj.dev.modular.slideshow.provider;

import cn.hutool.json.JSONObject;
import com.huanniankj.dev.api.DevSlideshowApi;
import com.huanniankj.dev.modular.slideshow.service.DevSlideshowService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 轮播图API接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevSlideshowApiProvider implements DevSlideshowApi {

    @Resource
    private DevSlideshowService devSlideshowService;

    @Override
    public List<JSONObject> getListByPlace(String place) {
        return devSlideshowService.getListByPlace(place);
    }
}
