package com.huanniankj.sys.core.listener;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import org.springframework.stereotype.Component;
import com.huanniankj.auth.core.pojo.SaBaseLoginUser;
import com.huanniankj.auth.core.util.StpLoginUserUtil;
import com.huanniankj.common.listener.CommonDataChangeListener;
import com.huanniankj.sys.core.enums.SysDataTypeEnum;

import java.util.List;

/**
 * 系统模块数据变化侦听器
 *
 * @author happynewyear
 */
@Component
public class SysDataChangeListener implements CommonDataChangeListener {

    @Override
    public void doAddWithDataId(String dataType, String dataId) {
        // 此处可做额外处理
    }

    @Override
    public void doAddWithDataIdList(String dataType, List<String> dataIdList) {
        // 如果检测到机构增加，则将该机构加入到当前登录用户的数据范围缓存
        if (dataType.equals(SysDataTypeEnum.ORG.getValue())) {
            SaBaseLoginUser saBaseLoginUser = StpLoginUserUtil.getLoginUser();
            saBaseLoginUser.getDataScopeList().forEach(dataScope -> dataScope.getDataScope()
                    .addAll(dataIdList));
            saBaseLoginUser.setDataScopeList(saBaseLoginUser.getDataScopeList());
            // 重新缓存当前登录用户信息
            StpUtil.getTokenSession().set("loginUser", saBaseLoginUser);
        }
    }

    @Override
    public void doAddWithData(String dataType, JSONObject jsonObject) {
        // 此处可做额外处理
    }

    @Override
    public void doAddWithDataList(String dataType, JSONArray jsonArray) {
        // 此处可做额外处理
    }

    @Override
    public void doUpdateWithDataId(String dataType, String dataId) {
        // 此处可做额外处理
    }

    @Override
    public void doUpdateWithDataIdList(String dataType, List<String> dataIdList) {
        // 此处可做额外处理
    }

    @Override
    public void doUpdateWithData(String dataType, JSONObject jsonObject) {
        // 此处可做额外处理
    }

    @Override
    public void doUpdateWithDataList(String dataType, JSONArray jsonArray) {
        // 此处可做额外处理
    }

    @Override
    public void doDeleteWithDataId(String dataType, String dataId) {
        // 此处可做额外处理
    }

    @Override
    public void doDeleteWithDataIdList(String dataType, List<String> dataIdList) {
        // 此处可做额外处理
    }
}
