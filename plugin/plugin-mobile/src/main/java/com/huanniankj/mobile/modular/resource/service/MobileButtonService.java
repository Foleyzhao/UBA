package com.huanniankj.mobile.modular.resource.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.mobile.modular.resource.entity.MobileButton;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonAddParam;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonEditParam;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonIdParam;
import com.huanniankj.mobile.modular.resource.param.button.MobileButtonPageParam;

import java.util.List;

/**
 * 移动端按钮服务接口
 *
 * @author happynewyear
 */
public interface MobileButtonService extends IService<MobileButton> {

    /**
     * 获取移动端按钮分页
     */
    Page<MobileButton> page(MobileButtonPageParam mobileButtonPageParam);

    /**
     * 添加移动端按钮
     */
    void add(MobileButtonAddParam mobileButtonAddParam);

    /**
     * 编辑移动端按钮
     */
    void edit(MobileButtonEditParam mobileButtonEditParam);

    /**
     * 删除移动端按钮
     */
    void delete(List<MobileButtonIdParam> mobileButtonIdParamList);

    /**
     * 获取移动端按钮详情
     */
    MobileButton detail(MobileButtonIdParam mobileButtonIdParam);

    /**
     * 获取移动端按钮详情
     */
    MobileButton queryEntity(String id);
}
