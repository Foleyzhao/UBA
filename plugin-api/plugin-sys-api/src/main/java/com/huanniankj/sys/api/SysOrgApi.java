package com.huanniankj.sys.api;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 组织API
 *
 * @author happynewyear
 */
public interface SysOrgApi {

    /**
     * 根据id获取名称
     */
    String getNameById(String orgId);

    /**
     * 根据组织id获取部门主管id
     */
    String getSupervisorIdByOrgId(String orgId);

    /**
     * 获取组织树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取组织列表选择器
     */
    Page<JSONObject> orgListSelector(String parentId);

    /**
     * 获取某组织的所有父级id集合
     */
    List<String> getParentIdListByOrgId(String orgId);

    /**
     * 根据组织id获取组织列表
     */
    List<JSONObject> getOrgListByIdListWithoutException(List<String> orgIdList);
}
