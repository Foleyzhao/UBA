package com.huanniankj.uba.modular.tag.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.uba.modular.tag.entity.Tag;
import com.huanniankj.uba.modular.tag.param.TagAddParam;
import com.huanniankj.uba.modular.tag.param.TagEditParam;
import com.huanniankj.uba.modular.tag.param.TagIdParam;
import com.huanniankj.uba.modular.tag.param.TagListParam;
import com.huanniankj.uba.modular.tag.param.TagPageParam;
import com.huanniankj.uba.modular.tag.param.TagTreeParam;

import java.util.List;

/**
 * 标签服务接口
 *
 * @author happynewyear
 */
public interface TagService extends IService<Tag> {

    /**
     * 获取标签分页
     */
    Page<Tag> page(TagPageParam tagPageParam);

    /**
     * 获取标签列表
     */
    List<Tag> list(TagListParam tagListParam);

    /**
     * 获取标签树
     */
    List<Tree<String>> tree(TagTreeParam tagTreeParam);

    /**
     * 添加标签
     */
    void add(TagAddParam tagAddParam);

    /**
     * 编辑标签
     */
    void edit(TagEditParam tagEditParam);

    /**
     * 删除标签
     */
    void delete(List<TagIdParam> tagIdParamList);

    /**
     * 获取标签详情
     */
    Tag detail(TagIdParam tagIdParam);

    /**
     * 获取标签详情
     */
    Tag queryEntity(String id);

}
