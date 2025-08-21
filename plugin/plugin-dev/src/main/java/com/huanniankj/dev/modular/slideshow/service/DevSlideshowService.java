package com.huanniankj.dev.modular.slideshow.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.slideshow.entity.DevSlideshow;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowAddParam;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowEditParam;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowIdParam;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowPageParam;

import java.util.List;

/**
 * 轮播图服务接口
 *
 * @author happynewyear
 */
public interface DevSlideshowService extends IService<DevSlideshow> {

    /**
     * 获取轮播图分页
     */
    Page<DevSlideshow> page(DevSlideshowPageParam devSlideshowPageParam);

    /**
     * 添加轮播图
     */
    void add(DevSlideshowAddParam devSlideshowAddParam);

    /**
     * 编辑轮播图
     */
    void edit(DevSlideshowEditParam devSlideshowEditParam);

    /**
     * 删除轮播图
     */
    void delete(List<DevSlideshowIdParam> devSlideshowIdParamList);

    /**
     * 获取轮播图详情
     */
    DevSlideshow queryEntity(String id);

    /**
     * 禁用轮播图
     */
    void disableStatus(DevSlideshowIdParam devSlideshowIdParam);

    /**
     * 启用轮播图
     */
    void enableStatus(DevSlideshowIdParam devSlideshowIdParam);

    /**
     * 通过位置获得轮播图列表
     */
    List<JSONObject> getListByPlace(String place);
}
