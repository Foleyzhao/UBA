package com.huanniankj.sys.api;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 角色API
 *
 * @author happynewyear
 */
public interface SysRoleApi {

    /**
     * 判断组织下是否存在角色
     */
    boolean orgHasRole(List<String> orgIdList);

    /**
     * 获取角色选择器
     */
    Page<JSONObject> roleSelector(String orgId, String category, String searchKey, List<String> dataScopeList,
                                  boolean excludeSuperAdmin);

    /**
     * 代码生成菜单按钮授权
     */
    void grantForGenMenuAndButton(String menuId);

    /**
     * 获取资源授权树
     */
    List<JSONObject> resourceTreeSelector();

    /**
     * 获取权限授权树
     */
    List<String> permissionTreeSelector();
}
