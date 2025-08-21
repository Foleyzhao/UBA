package com.huanniankj.common.listener;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.huanniankj.common.exception.CommonException;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 通用数据变化事件中心 事件发布器
 *
 * @author happynewyear
 */
public class CommonDataChangeEventCenter {

    // --------- 注册侦听器

    /**
     * 获取已注册的所有侦听器
     */
    @Getter
    private static List<CommonDataChangeListener> listenerList = new ArrayList<>();

    /**
     * 重置侦听器集合
     *
     * @param listenerList /
     */
    public static void setListenerList(List<CommonDataChangeListener> listenerList) {
        if (listenerList == null) {
            throw new CommonException("重置的侦听器集合不可以为空");
        }
        CommonDataChangeEventCenter.listenerList = listenerList;
    }

    /**
     * 注册一个侦听器
     *
     * @param listener /
     */
    public static void registerListener(CommonDataChangeListener listener) {
        if (listener == null) {
            throw new CommonException("注册的侦听器不可以为空");
        }
        listenerList.add(listener);
    }

    /**
     * 注册一组侦听器
     *
     * @param listenerList /
     */
    public static void registerListenerList(List<CommonDataChangeListener> listenerList) {
        if (listenerList == null) {
            throw new CommonException("注册的侦听器不可以为空");
        }
        for (CommonDataChangeListener listener : listenerList) {
            if (listener == null) {
                throw new CommonException("注册的侦听器不可以为空");
            }
        }
        CommonDataChangeEventCenter.listenerList.addAll(listenerList);
    }

    /**
     * 移除一个侦听器
     *
     * @param listener /
     */
    public static void removeListener(CommonDataChangeListener listener) {
        listenerList.remove(listener);
    }

    /**
     * 移除指定类型的所有侦听器
     *
     * @param cls /
     */
    public static void removeListener(Class<? extends CommonDataChangeListener> cls) {
        ArrayList<CommonDataChangeListener> listenerListCopy = new ArrayList<>(listenerList);
        for (CommonDataChangeListener listener : listenerListCopy) {
            if (cls.isAssignableFrom(listener.getClass())) {
                listenerList.remove(listener);
            }
        }
    }

    /**
     * 清空所有已注册的侦听器
     */
    public static void clearListener() {
        listenerList.clear();
    }

    /**
     * 判断是否已经注册了指定侦听器
     *
     * @param listener /
     * @return /
     */
    public static boolean hasListener(CommonDataChangeListener listener) {
        return listenerList.contains(listener);
    }

    /**
     * 判断是否已经注册了指定类型的侦听器
     *
     * @param cls /
     * @return /
     */
    public static boolean hasListener(Class<? extends CommonDataChangeListener> cls) {
        for (CommonDataChangeListener listener : listenerList) {
            if (cls.isAssignableFrom(listener.getClass())) {
                return true;
            }
        }
        return false;
    }

    // --------- 事件发布-添加 --------- //

    /**
     * 执行添加事件发布，数据集合
     */
    public static void doAddWithData(String dataType, JSONArray jsonArray) {
        for (CommonDataChangeListener listener : listenerList) {
            listener.doAddWithDataIdList(dataType, jsonArray.stream().map(o -> JSONUtil.parseObj(o).getStr("id")).collect(Collectors.toList()));
            listener.doAddWithDataList(dataType, jsonArray);
        }
    }

    /**
     * 执行添加事件发布，数据
     */
    public static void doAddWithData(String dataType, JSONObject jsonObject) {
        for (CommonDataChangeListener listener : listenerList) {
            listener.doAddWithDataId(dataType, JSONUtil.parseObj(jsonObject).getStr("id"));
            listener.doAddWithData(dataType, jsonObject);
        }
    }

    // --------- 事件发布-更新 --------- //

    /**
     * 执行更新事件发布，数据集合
     */
    public static void doUpdateWithData(String dataType, JSONArray jsonArray) {
        for (CommonDataChangeListener listener : listenerList) {
            listener.doUpdateWithDataIdList(dataType, jsonArray.stream().map(o -> JSONUtil.parseObj(o).getStr("id")).collect(Collectors.toList()));
            listener.doUpdateWithDataList(dataType, jsonArray);
        }
    }

    /**
     * 执行更新事件发布，数据
     */
    public static void doUpdateWithData(String dataType, JSONObject jsonObject) {
        for (CommonDataChangeListener listener : listenerList) {
            listener.doUpdateWithDataId(dataType, JSONUtil.parseObj(jsonObject).getStr("id"));
            listener.doUpdateWithData(dataType, jsonObject);
        }
    }

    // --------- 事件发布-删除 --------- //

    /**
     * 执行删除事件发布，ID集合
     */
    public static void doDeleteWithDataIdList(String dataType, List<String> dataIdList) {
        for (CommonDataChangeListener listener : listenerList) {
            listener.doDeleteWithDataIdList(dataType, dataIdList);
        }
    }

    /**
     * 执行删除事件发布，ID
     */
    public static void doDeleteWithDataId(String dataType, String dataId) {
        for (CommonDataChangeListener listener : listenerList) {
            listener.doDeleteWithDataId(dataType, dataId);
        }
    }

}
