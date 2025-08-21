package com.huanniankj.biz.modular.dict.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.biz.modular.dict.entity.BizDict;
import com.huanniankj.biz.modular.dict.param.BizDictEditParam;
import com.huanniankj.biz.modular.dict.param.BizDictPageParam;

import java.util.List;

/**
 * 业务字典服务接口
 *
 * @author happynewyear
 */
public interface BizDictService extends IService<BizDict> {

    /**
     * 获取业务字典分页
     */
    Page<BizDict> page(BizDictPageParam bizDictPageParam);

    /**
     * 获取业务字典树
     */
    List<Tree<String>> tree();

    /**
     * 获取所有字典树
     */
    List<Tree<String>> treeAll();

    /**
     * 编辑业务字典
     */
    void edit(BizDictEditParam bizDictEditParam);

    /**
     * 获取业务字典详情
     */
    BizDict queryEntity(String id);
}
