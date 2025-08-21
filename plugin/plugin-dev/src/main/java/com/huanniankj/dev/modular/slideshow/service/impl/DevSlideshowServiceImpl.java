package com.huanniankj.dev.modular.slideshow.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollStreamUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.dev.core.conts.DevConstants;
import com.huanniankj.dev.modular.slideshow.entity.DevSlideshow;
import com.huanniankj.dev.modular.slideshow.enums.DevSlideshowStatusEnum;
import com.huanniankj.dev.modular.slideshow.mapper.DevSlideshowMapper;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowAddParam;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowEditParam;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowIdParam;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowPageParam;
import com.huanniankj.dev.modular.slideshow.service.DevSlideshowService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 轮播图服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevSlideshowServiceImpl extends ServiceImpl<DevSlideshowMapper, DevSlideshow>
        implements DevSlideshowService {

    @Override
    public Page<DevSlideshow> page(DevSlideshowPageParam devSlideshowPageParam) {
        QueryWrapper<DevSlideshow> queryWrapper = new QueryWrapper<DevSlideshow>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(devSlideshowPageParam.getTitle())) {
            queryWrapper.lambda().like(DevSlideshow::getTitle, devSlideshowPageParam.getTitle());
        }
        if (ObjectUtil.isNotEmpty(devSlideshowPageParam.getPlace())) {
            queryWrapper.lambda().like(DevSlideshow::getPlace, devSlideshowPageParam.getPlace());
        }
        if (ObjectUtil.isNotEmpty(devSlideshowPageParam.getStatus())) {
            queryWrapper.lambda().eq(DevSlideshow::getStatus, devSlideshowPageParam.getStatus());
        }
        if (ObjectUtil.isAllNotEmpty(devSlideshowPageParam.getSortField(), devSlideshowPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(devSlideshowPageParam.getSortOrder());
            queryWrapper.orderBy(true, devSlideshowPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(devSlideshowPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByAsc(DevSlideshow::getSortCode);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void add(DevSlideshowAddParam devSlideshowAddParam) {
        DevSlideshow devSlideshow = BeanUtil.toBean(devSlideshowAddParam, DevSlideshow.class);
        // 默认禁用的
        devSlideshow.setStatus(DevSlideshowStatusEnum.DISABLE.getValue());
        this.save(devSlideshow);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void edit(DevSlideshowEditParam devSlideshowEditParam) {
        DevSlideshow devSlideshow = this.queryEntity(devSlideshowEditParam.getId());
        BeanUtil.copyProperties(devSlideshowEditParam, devSlideshow);
        this.updateById(devSlideshow);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(List<DevSlideshowIdParam> devSlideshowIdParamList) {
        // 执行删除
        this.removeByIds(CollStreamUtil.toList(devSlideshowIdParamList, DevSlideshowIdParam::getId));
    }

    @Override
    public DevSlideshow queryEntity(String id) {
        DevSlideshow devSlideshow = this.getById(id);
        if (ObjectUtil.isEmpty(devSlideshow)) {
            throw new CommonException("轮播图不存在，id值为：{}", id);
        }
        return devSlideshow;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void disableStatus(DevSlideshowIdParam devSlideshowIdParam) {
        this.update(new LambdaUpdateWrapper<DevSlideshow>().eq(DevSlideshow::getId,
                devSlideshowIdParam.getId()).set(DevSlideshow::getStatus, DevSlideshowStatusEnum.DISABLE.getValue()));
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void enableStatus(DevSlideshowIdParam devSlideshowIdParam) {
        this.update(new LambdaUpdateWrapper<DevSlideshow>().eq(DevSlideshow::getId,
                devSlideshowIdParam.getId()).set(DevSlideshow::getStatus, DevSlideshowStatusEnum.ENABLE.getValue()));
    }

    @Override
    public List<JSONObject> getListByPlace(String place) {
        List<JSONObject> resultList = CollectionUtil.newArrayList();
        List<DevSlideshow> slideshowList = this.list(new LambdaQueryWrapper<DevSlideshow>().eq(DevSlideshow::getStatus,
                DevSlideshowStatusEnum.ENABLE.getValue()).orderByDesc(DevSlideshow::getSortCode));
        slideshowList.forEach((item) -> {
            JSONArray slideshowPlaceArray = JSONUtil.parseArray(item.getPlace());
            slideshowPlaceArray.forEach((placeArray) -> {
                if (place.equals(placeArray)) {
                    JSONObject obj = new JSONObject();
                    obj.set("id", item.getId());
                    obj.set("title", item.getTitle());
                    // TODO 判断这个图片是否库里真是存在，不存在则返回base64国定的，不能让前端图片裂开
                    obj.set("image", item.getImage());
                    JSONArray array = JSONUtil.parseArray(item.getPathDetails());
                    array.forEach((detail) -> {
                        JSONObject detailObj = JSONUtil.parseObj(detail);
                        if (detailObj.get("key").equals(place)) {
                            obj.set("pathDetails", detailObj);
                        }
                    });
                    resultList.add(obj);
                }
            });
        });
        if (resultList.isEmpty()) {
            // 如果库里未配置，则补充一条静态的，避免图片为空
            JSONObject staticObj = new JSONObject();
            staticObj.set("id", IdWorker.getIdStr());
            staticObj.set("title", "静态文件");
            staticObj.set("image", DevConstants.STATIC_SLIDESHOW_IMAGE);
            staticObj.set("pathDetails", null);
            resultList.add(staticObj);
            return resultList;
        }
        return resultList;
    }
}
