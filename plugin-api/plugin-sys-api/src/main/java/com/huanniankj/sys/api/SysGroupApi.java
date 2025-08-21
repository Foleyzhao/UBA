package com.huanniankj.sys.api;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 用户组Api
 *
 * @author happynewyear
 */
public interface SysGroupApi {

    /**
     * 获取用户组拥有人员
     */
    List<String> ownUser(String groupId);

    /**
     * 给用户组授权用户
     */
    void grantUser(String groupId, List<String> userIdList);

    /**
     * 获取用户组选择器
     */
    Page<JSONObject> groupSelector(String searchKey, int current, int size);
}
