package com.huanniankj.uba.modular.dict.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.uba.modular.dict.entity.Dict;
import com.huanniankj.uba.modular.dict.param.DictAddParam;
import com.huanniankj.uba.modular.dict.param.DictEditParam;
import com.huanniankj.uba.modular.dict.param.DictIdParam;
import com.huanniankj.uba.modular.dict.param.DictListParam;
import com.huanniankj.uba.modular.dict.param.DictPageParam;
import com.huanniankj.uba.modular.dict.param.DictTreeParam;

import java.util.List;

/**
 * 数据处理字典服务接口
 *
 * @author happynewyear
 */
public interface DictService extends IService<Dict> {

    /**
     * 获取字典分页
     */
    Page<Dict> page(DictPageParam devDictPageParam);

    /**
     * 获取字典列表
     */
    List<Dict> list(DictListParam devDictListParam);

    /**
     * 获取字典树
     */
    List<Tree<String>> tree(DictTreeParam devDictTreeParam);

    /**
     * 添加字典
     */
    void add(DictAddParam devDictAddParam);

    /**
     * 编辑字典
     */
    void edit(DictEditParam devDictEditParam);

    /**
     * 删除字典
     */
    void delete(List<DictIdParam> devDictIdParamList);

    /**
     * 获取字典详情
     */
    Dict detail(DictIdParam devDictIdParam);

    /**
     * 获取字典详情
     */
    Dict queryEntity(String id);

    /**
     * 根据类型跟子类型获得翻译后的label
     */
    String getDictLabel(String typeCode, String value);
}
