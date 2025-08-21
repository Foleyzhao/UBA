package com.huanniankj.biz.api;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 机构API
 *
 * @author happynewyear
 */
public interface BizOrgApi {

    /**
     * 获取组织树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     */
    Page<JSONObject> orgListSelector(String parentId);
}
