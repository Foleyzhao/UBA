package com.huanniankj.sys.api;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

/**
 * 用户Api
 *
 * @author happynewyear
 */
public interface SysUserApi {

    /**
     * 根据用户id获取用户对象，没有则返回null
     */
    JSONObject getUserByIdWithoutException(String userId);

    /**
     * 根据用户id获取用户对象列表，没有的则为空，都没有则返回空集合
     */
    List<JSONObject> getUserListByIdListWithoutException(List<String> userIdList);

    /**
     * 根据用户id获取用户对象，没有则抛出异常
     */
    JSONObject getUserByIdWithException(String userId);

    /**
     * 根据用户id获取用户对象列表，只要有一个没有则抛出异常
     */
    List<JSONObject> getUserListByIdWithException(List<String> userIdList);

    /**
     * 获取用户拥有角色
     */
    List<String> ownRole(String userId);

    /**
     * 给用户授权角色
     */
    void grantRole(String userId, List<String> roleIdList);

    /**
     * 根据组织id集合获取组织下用户id集合（含兼职该机构的人）
     */
    List<String> getUserIdListByOrgIdList(List<String> orgIdList);

    /**
     * 根据职位id集合获取职位下用户id集合（含兼职该职位的人）
     */
    List<String> getUserIdListByPositionIdList(List<String> positionIdList);

    /**
     * 根据用户id和组织id和主管层级获取上级主管id
     */
    JSONObject getSupervisorIdBySupervisorLevel(List<String> userIdList, String userId, String orgId,
                                                String supervisorLevel);

    /**
     * 根据用户id和组织id和终点主管层级获取上级主管id集合
     */
    List<String> getMulSupervisorIdListByEndLevel(String userId, String orgId, String endLevel);

    /**
     * 获取用户选择器
     */
    Page<JSONObject> userSelector(String orgId, String searchKey);

    /**
     * 获取用户列表（排除当前用户）
     */
    List<JSONObject> listUserWithoutCurrent();

    /**
     * 获取用户的职位列表
     */
    List<JSONObject> getPositionListByUserId(String userId);
}
