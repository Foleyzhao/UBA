package com.huanniankj.sys.modular.index.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.sys.modular.index.service.SysIndexService;

import javax.validation.Valid;
import java.util.List;

/**
 * 系统首页控制器
 *
 * @author happynewyear
 */
@Tag(name = "系统首页控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 0)
@RestController
@Validated
public class SysIndexController {

    @Resource
    private SysIndexService sysIndexService;

    /**
     * 添加当前用户日程
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "添加日程")
    @CommonLog("添加日程")
    @PostMapping("/sys/index/schedule/add")
    public CommonResult<String> addSchedule(@RequestBody @Valid SysIndexScheduleAddParam sysIndexScheduleAddParam) {
        sysIndexService.addSchedule(sysIndexScheduleAddParam);
        return CommonResult.ok();
    }

    /**
     * 删除日程
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "删除日程")
    @CommonLog("删除日程")
    @PostMapping("/sys/index/schedule/deleteSchedule")
    public CommonResult<String> deleteSchedule(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                               List<SysIndexScheduleIdParam> sysIndexScheduleIdParamList) {
        sysIndexService.deleteSchedule(sysIndexScheduleIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取当前用户日程列表
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "获取日程列表")
    @GetMapping("/sys/index/schedule/list")
    public CommonResult<List<SysIndexScheduleListResult>> scheduleList(
            @Valid SysIndexScheduleListParam sysIndexScheduleListParam) {
        return CommonResult.data(sysIndexService.scheduleList(sysIndexScheduleListParam));
    }

    /**
     * 获取当前用户站内信列表
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "获取当前用户站内信列表")
    @GetMapping("/sys/index/message/list")
    public CommonResult<List<SysIndexMessageListResult>> messageList(
            SysIndexMessageListParam sysIndexMessageListParam) {
        return CommonResult.data(sysIndexService.messageList(sysIndexMessageListParam));
    }

    /**
     * 获取站内信详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取站内信详情")
    @GetMapping("/sys/index/message/detail")
    public CommonResult<SysIndexMessageDetailResult> messageDetail(
            @Valid SysIndexMessageIdParam sysIndexMessageIdParam) {
        return CommonResult.data(sysIndexService.messageDetail(sysIndexMessageIdParam));
    }

    /**
     * 站内信全部标记已读
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "站内信全部标记已读")
    @PostMapping("/sys/index/message/allMessageMarkRead")
    public CommonResult<String> allMessageMarkRead() {
        sysIndexService.allMessageMarkRead();
        return CommonResult.ok();
    }

    /**
     * 获取当前用户访问日志列表
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取当前用户访问日志列表")
    @GetMapping("/sys/index/visLog/list")
    public CommonResult<List<SysIndexVisLogListResult>> visLogList() {
        return CommonResult.data(sysIndexService.visLogList());
    }

    /**
     * 获取当前用户操作日志列表
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "获取当前用户操作日志列表")
    @GetMapping("/sys/index/opLog/list")
    public CommonResult<List<SysIndexOpLogListResult>> opLogList() {
        return CommonResult.data(sysIndexService.opLogList());
    }

    /**
     * 创建sse连接
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "创建sse连接")
    @GetMapping("/dev/message/createSseConnect")
    public SseEmitter createSseConnect(String clientId) {
        return sysIndexService.createSseConnect(clientId);
    }

    /**
     * 获取基础系统业务数据
     */
    @ApiOperationSupport(order = 10)
    @Operation(summary = "获取基础系统业务数据")
    @GetMapping("/sys/index/bizDataCount")
    public CommonResult<SysBizDataCountResult> getBizDataCount() {
        return CommonResult.data(sysIndexService.getBizDataCount());
    }

    /**
     * 获取运维一览数据
     */
    @ApiOperationSupport(order = 11)
    @Operation(summary = "获取运维一览数据")
    @GetMapping("/sys/index/opDataCount")
    public CommonResult<SysOpDataCountResult> getOpDataCount() {
        return CommonResult.data(sysIndexService.getOpDataCount());
    }

    /**
     * 获取基础工具数据
     */
    @ApiOperationSupport(order = 12)
    @Operation(summary = "获取基础工具数据")
    @GetMapping("/sys/index/toolDataCount")
    public CommonResult<SysToolDataCountResult> getToolDataCount() {
        return CommonResult.data(sysIndexService.getToolDataCount());
    }
}
