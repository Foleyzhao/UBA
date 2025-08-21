package com.huanniankj.sys.api;

import cn.hutool.core.lang.tree.Tree;

import java.util.List;

/**
 * 菜单API
 *
 * @author happynewyear
 */
public interface SysMenuApi {

    /**
     * 代码生成菜单插入
     */
    String addForGenMenu(String parentId, String busName, String module, String title, String path);

    /**
     * 获取所有菜单树包括未授权的
     */
    List<Tree<String>> menuTreeSelector(String module);
}
