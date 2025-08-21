package com.huanniankj.dev.modular.monitor.service;

import com.huanniankj.dev.modular.monitor.result.DevMonitorServerResult;

/**
 * 监控服务接口
 *
 * @author happynewyear
 */
public interface DevMonitorService {

    /**
     * 获取服务器监控信息
     */
    DevMonitorServerResult serverInfo();

    /**
     * 获取服务器网络情况
     */
    DevMonitorServerResult networkInfo();
}
