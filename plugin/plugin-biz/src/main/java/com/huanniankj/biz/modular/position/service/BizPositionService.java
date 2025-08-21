package com.huanniankj.biz.modular.position.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.biz.modular.position.entity.BizPosition;
import com.huanniankj.biz.modular.position.param.BizPositionAddParam;
import com.huanniankj.biz.modular.position.param.BizPositionEditParam;
import com.huanniankj.biz.modular.position.param.BizPositionIdParam;
import com.huanniankj.biz.modular.position.param.BizPositionPageParam;
import com.huanniankj.biz.modular.position.param.BizPositionSelectorPositionParam;

import java.util.List;

/**
 * 岗位服务接口
 *
 * @author happynewyear
 */
public interface BizPositionService extends IService<BizPosition> {

    /**
     * 获取岗位分页
     */
    Page<BizPosition> page(BizPositionPageParam bizPositionPageParam);

    /**
     * 添加岗位
     */
    void add(BizPositionAddParam bizPositionAddParam);

    /**
     * 编辑岗位
     */
    void edit(BizPositionEditParam bizPositionEditParam);

    /**
     * 删除岗位
     */
    void delete(List<BizPositionIdParam> bizPositionIdParamList);

    /**
     * 获取岗位详情
     */
    BizPosition detail(BizPositionIdParam bizPositionIdParam);

    /**
     * 获取岗位详情
     */
    BizPosition queryEntity(String id);

    /**
     * 根据机构id和岗位名称获取岗位id，有则返回，无则创建
     */
    String getPositionIdByPositionNameWithCreate(String orgId, String positionName);

    /* ====岗位部分所需要用到的选择器==== */

    /**
     * 获取机构树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取岗位选择器
     */
    Page<BizPosition> positionSelector(BizPositionSelectorPositionParam bizPositionSelectorPositionParam);
}
