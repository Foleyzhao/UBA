package com.huanniankj.common.listener;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;

import java.util.List;

/**
 * 通用数据变化侦听器，你可以实现该侦听器接口，在数据新增、更新、删除时进行一些AOP操作
 *
 * @author happynewyear
 */
public interface CommonDataChangeListener {

    /**
     * 执行添加，ID
     *
     * @param dataType 数据类型，如USER、ORG，自行定义
     * @param dataId   被添加的数据ID
     */
    void doAddWithDataId(String dataType, String dataId);

    /**
     * 执行添加，ID集合
     *
     * @param dataType   数据类型，如USER、ORG，自行定义
     * @param dataIdList 被添加的数据ID集合
     */
    void doAddWithDataIdList(String dataType, List<String> dataIdList);

    /**
     * 执行添加，数据
     *
     * @param dataType   数据类型，如USER、ORG，自行定义
     */
    void doAddWithData(String dataType, JSONObject jsonObject);

    /**
     * 执行添加，数据集合
     *
     * @param dataType  数据类型，如USER、ORG，自行定义
     * @param jsonArray 被添加的数据集合
     */
    void doAddWithDataList(String dataType, JSONArray jsonArray);

    /**
     * 执行更新，ID
     *
     * @param dataType 数据类型，如USER、ORG，自行定义
     * @param dataId   被更新的数据ID
     */
    void doUpdateWithDataId(String dataType, String dataId);

    /**
     * 执行更新，ID集合
     *
     * @param dataType   数据类型，如USER、ORG，自行定义
     * @param dataIdList 被更新的数据ID集合
     */
    void doUpdateWithDataIdList(String dataType, List<String> dataIdList);

    /**
     * 执行更新，数据
     *
     * @param dataType   数据类型，如USER、ORG，自行定义
     * @param jsonObject 被更新的数据
     */
    void doUpdateWithData(String dataType, JSONObject jsonObject);

    /**
     * 执行更新，数据集合
     *
     * @param dataType  数据类型，如USER、ORG，自行定义
     * @param jsonArray 被更新的数据集合
     */
    void doUpdateWithDataList(String dataType, JSONArray jsonArray);

    /**
     * 执行删除，ID
     *
     * @param dataType 数据类型，如USER、ORG，自行定义
     * @param dataId   被删除的数据ID
     */
    void doDeleteWithDataId(String dataType, String dataId);

    /**
     * 执行删除，ID集合
     *
     * @param dataType   数据类型，如USER、ORG，自行定义
     * @param dataIdList 被删除的数据ID集合
     */
    void doDeleteWithDataIdList(String dataType, List<String> dataIdList);
}
