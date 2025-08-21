package com.huanniankj.dev.modular.dict.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.dict.entity.DevDict;
import com.huanniankj.dev.modular.dict.param.DevDictAddParam;
import com.huanniankj.dev.modular.dict.param.DevDictEditParam;
import com.huanniankj.dev.modular.dict.param.DevDictIdParam;
import com.huanniankj.dev.modular.dict.param.DevDictListParam;
import com.huanniankj.dev.modular.dict.param.DevDictPageParam;
import com.huanniankj.dev.modular.dict.param.DevDictTreeParam;

import java.util.List;

/**
 * 字典服务接口
 *
 * @author happynewyear
 */
public interface DevDictService extends IService<DevDict> {

    /**
     * 获取字典分页
     */
    Page<DevDict> page(DevDictPageParam devDictPageParam);

    /**
     * 获取字典列表
     */
    List<DevDict> list(DevDictListParam devDictListParam);

    /**
     * 获取字典树
     */
    List<Tree<String>> tree(DevDictTreeParam devDictTreeParam);

    /**
     * 添加字典
     */
    void add(DevDictAddParam devDictAddParam);

    /**
     * 编辑字典
     */
    void edit(DevDictEditParam devDictEditParam);

    /**
     * 删除字典
     */
    void delete(List<DevDictIdParam> devDictIdParamList);

    /**
     * 获取字典详情
     */
    DevDict detail(DevDictIdParam devDictIdParam);

    /**
     * 获取字典详情
     */
    DevDict queryEntity(String id);

    /**
     * 根据类型跟子类型获得翻译后的label
     */
    String getDictLabel(String typeCode, String value);
}
