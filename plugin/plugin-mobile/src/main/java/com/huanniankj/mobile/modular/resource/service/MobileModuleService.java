package com.huanniankj.mobile.modular.resource.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.mobile.modular.resource.entity.MobileModule;
import com.huanniankj.mobile.modular.resource.param.module.MobileModuleAddParam;
import com.huanniankj.mobile.modular.resource.param.module.MobileModuleEditParam;
import com.huanniankj.mobile.modular.resource.param.module.MobileModuleIdParam;
import com.huanniankj.mobile.modular.resource.param.module.MobileModulePageParam;

import java.util.List;

/**
 * 移动端模块服务接口
 *
 * @author happynewyear
 */
public interface MobileModuleService extends IService<MobileModule> {

    /**
     * 获取移动端模块分页
     */
    Page<MobileModule> page(MobileModulePageParam mobileModulePageParam);

    /**
     * 添加移动端模块
     */
    void add(MobileModuleAddParam mobileModuleAddParam);

    /**
     * 编辑移动端模块
     */
    void edit(MobileModuleEditParam mobileModuleEditParam);

    /**
     * 删除移动端模块
     */
    void delete(List<MobileModuleIdParam> mobileModuleIdParamList);

    /**
     * 获取移动端模块详情
     */
    MobileModule detail(MobileModuleIdParam mobileModuleIdParam);

    /**
     * 获取移动端模块详情
     */
    MobileModule queryEntity(String id);

    /**
     * 获取移动端模块选择器
     */
    List<JSONObject> mobileModuleSelector();
}
