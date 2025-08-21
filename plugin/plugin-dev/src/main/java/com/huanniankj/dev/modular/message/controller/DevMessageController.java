package com.huanniankj.dev.modular.message.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.dev.modular.message.entity.DevMessage;
import com.huanniankj.dev.modular.message.param.DevMessageIdParam;
import com.huanniankj.dev.modular.message.param.DevMessagePageParam;
import com.huanniankj.dev.modular.message.param.DevMessageSendParam;
import com.huanniankj.dev.modular.message.result.DevMessageResult;
import com.huanniankj.dev.modular.message.service.DevMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 站内信控制器
 *
 * @author happynewyear
 */
@Tag(name = "站内信控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 6)
@RestController
@Validated
public class DevMessageController {

    @Resource
    private DevMessageService devMessageService;

    /**
     * 发送站内信
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "发送站内信")
    @CommonLog("发送站内信")
    @PostMapping("/dev/message/send")
    public CommonResult<String> send(@RequestBody @Valid DevMessageSendParam devMessageSendParam) {
        devMessageService.send(devMessageSendParam);
        return CommonResult.ok();
    }

    /**
     * 获取站内信分页
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取站内信分页")
    @GetMapping("/dev/message/page")
    public CommonResult<Page<DevMessage>> page(DevMessagePageParam devMessagePageParam) {
        return CommonResult.data(devMessageService.page(devMessagePageParam));
    }

    /**
     * 删除站内信
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "删除站内信")
    @CommonLog("删除站内信")
    @PostMapping("/dev/message/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<DevMessageIdParam> devMessageIdParamList) {
        devMessageService.delete(devMessageIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取站内信详情
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "获取站内信详情")
    @GetMapping("/dev/message/detail")
    public CommonResult<DevMessageResult> detail(@Valid DevMessageIdParam devMessageIdParam) {
        return CommonResult.data(devMessageService.detail(devMessageIdParam));
    }
}
