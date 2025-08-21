package com.huanniankj.sys.modular.index.service;

import com.huanniankj.sys.modular.index.param.SysIndexMessageIdParam;
import com.huanniankj.sys.modular.index.param.SysIndexMessageListParam;
import com.huanniankj.sys.modular.index.param.SysIndexScheduleAddParam;
import com.huanniankj.sys.modular.index.param.SysIndexScheduleIdParam;
import com.huanniankj.sys.modular.index.param.SysIndexScheduleListParam;
import com.huanniankj.sys.modular.index.result.SysBizDataCountResult;
import com.huanniankj.sys.modular.index.result.SysIndexMessageDetailResult;
import com.huanniankj.sys.modular.index.result.SysIndexMessageListResult;
import com.huanniankj.sys.modular.index.result.SysIndexOpLogListResult;
import com.huanniankj.sys.modular.index.result.SysIndexScheduleListResult;
import com.huanniankj.sys.modular.index.result.SysIndexVisLogListResult;
import com.huanniankj.sys.modular.index.result.SysOpDataCountResult;
import com.huanniankj.sys.modular.index.result.SysToolDataCountResult;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.List;

/**
 * 系统首页服务接口
 *
 * @author happynewyear
 */
public interface SysIndexService {

    /**
     * 添加当前用户日程
     */
    void addSchedule(SysIndexScheduleAddParam sysIndexScheduleAddParam);

    /**
     * 删除日程
     */
    void deleteSchedule(List<SysIndexScheduleIdParam> sysIndexScheduleIdParamList);

    /**
     * 获取当前用户日程列表
     */
    List<SysIndexScheduleListResult> scheduleList(SysIndexScheduleListParam sysIndexScheduleListParam);

    /**
     * 获取当前用户站内信列表
     */
    List<SysIndexMessageListResult> messageList(SysIndexMessageListParam sysIndexMessageListParam);

    /**
     * 获取站内信详情
     */
    SysIndexMessageDetailResult messageDetail(SysIndexMessageIdParam sysIndexMessageIdParam);

    /**
     * 站内信全部标记已读
     */
    void allMessageMarkRead();

    /**
     * 获取当前用户访问日志列表
     */
    List<SysIndexVisLogListResult> visLogList();

    /**
     * 获取当前用户操作日志列表
     */
    List<SysIndexOpLogListResult> opLogList();

    /**
     * 创建连接
     */
    SseEmitter createSseConnect(String clientId);

    /**
     * 获取基础系统业务数据
     */
    SysBizDataCountResult getBizDataCount();

    /**
     * 获取基础系统业务数据
     */
    SysOpDataCountResult getOpDataCount();

    /**
     * 获取基础系统业务数据
     */
    SysToolDataCountResult getToolDataCount();
}
