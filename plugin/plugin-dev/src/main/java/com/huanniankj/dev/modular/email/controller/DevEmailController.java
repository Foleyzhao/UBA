package com.huanniankj.dev.modular.email.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.dev.modular.email.entity.DevEmail;
import com.huanniankj.dev.modular.email.param.DevEmailIdParam;
import com.huanniankj.dev.modular.email.param.DevEmailPageParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendAliyunHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendAliyunTmpParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendAliyunTxtParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendDynamicHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendDynamicTxtParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendLocalHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendLocalTxtParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendTencentHtmlParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendTencentTmpParam;
import com.huanniankj.dev.modular.email.param.DevEmailSendTencentTxtParam;
import com.huanniankj.dev.modular.email.service.DevEmailService;
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
 * 邮件控制器
 *
 * @author happynewyear
 */
@Tag(name = "邮件控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 3)
@RestController
@Validated
public class DevEmailController {

    @Resource
    private DevEmailService devEmailService;

    /**
     * 动态发送TXT邮件
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "动态发送TXT邮件")
    @CommonLog("动态发送TXT邮件")
    @PostMapping("/dev/email/sendDynamicTxt")
    public CommonResult<String> sendDynamic(@RequestBody @Valid DevEmailSendDynamicTxtParam devEmailSendDynamicTxtParam) {
        devEmailService.sendDynamicTxt(devEmailSendDynamicTxtParam);
        return CommonResult.ok();
    }

    /**
     * 动态发送HTML邮件
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "动态发送HTML邮件")
    @CommonLog("动态发送HTML邮件")
    @PostMapping("/dev/email/sendDynamicHtml")
    public CommonResult<String> sendDynamic(@RequestBody @Valid DevEmailSendDynamicHtmlParam devEmailSendDynamicHtmlParam) {
        devEmailService.sendDynamicHtml(devEmailSendDynamicHtmlParam);
        return CommonResult.ok();
    }

    /**
     * 发送邮件——本地TXT
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "发送本地文本邮件")
    @CommonLog("发送本地文本邮件")
    @PostMapping("/dev/email/sendLocalTxt")
    public CommonResult<String> sendLocal(@RequestBody @Valid DevEmailSendLocalTxtParam devEmailSendLocalTxtParam) {
        devEmailService.sendLocal(devEmailSendLocalTxtParam);
        return CommonResult.ok();
    }

    /**
     * 发送邮件——本地HTML
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "发送本地HTML邮件")
    @CommonLog("发送本地HTML邮件")
    @PostMapping("/dev/email/sendLocalHtml")
    public CommonResult<String> sendLocal(@RequestBody @Valid DevEmailSendLocalHtmlParam devEmailSendLocalHtmlParam) {
        devEmailService.sendLocal(devEmailSendLocalHtmlParam);
        return CommonResult.ok();
    }

    /**
     * 发送邮件——阿里云TXT
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "发送阿里云文本邮件")
    @CommonLog("发送阿里云文本邮件")
    @PostMapping("/dev/email/sendAliyunTxt")
    public CommonResult<String> sendAliyun(@RequestBody @Valid DevEmailSendAliyunTxtParam devEmailSendAliyunTxtParam) {
        devEmailService.sendAliyun(devEmailSendAliyunTxtParam);
        return CommonResult.ok();
    }

    /**
     * 发送邮件——阿里云HTML
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "发送阿里云HTML邮件")
    @CommonLog("发送阿里云HTML邮件")
    @PostMapping("/dev/email/sendAliyunHtml")
    public CommonResult<String> sendAliyun(@RequestBody @Valid DevEmailSendAliyunHtmlParam devEmailSendAliyunHtmlParam) {
        devEmailService.sendAliyun(devEmailSendAliyunHtmlParam);
        return CommonResult.ok();
    }

    /**
     * 发送邮件——阿里云TMP
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "发送阿里云模板邮件")
    @CommonLog("发送阿里云模板邮件")
    @PostMapping("/dev/email/sendAliyunTmp")
    public CommonResult<String> sendAliyun(@RequestBody @Valid DevEmailSendAliyunTmpParam devEmailSendAliyunTmpParam) {
        devEmailService.sendAliyun(devEmailSendAliyunTmpParam);
        return CommonResult.ok();
    }

    /**
     * 发送邮件——腾讯云TXT
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "发送腾讯云文本邮件")
    @CommonLog("发送腾讯云文本邮件")
    @PostMapping("/dev/email/sendTencentTxt")
    public CommonResult<String> sendTencent(@RequestBody @Valid DevEmailSendTencentTxtParam devEmailSendTencentTxtParam) {
        devEmailService.sendTencent(devEmailSendTencentTxtParam);
        return CommonResult.ok();
    }

    /**
     * 发送邮件——腾讯云HTML
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "发送腾讯云HTML邮件")
    @CommonLog("发送腾讯云HTML邮件")
    @PostMapping("/dev/email/sentTencentHtml")
    public CommonResult<String> sendTencent(@RequestBody @Valid DevEmailSendTencentHtmlParam devEmailSendTencentHtmlParam) {
        devEmailService.sendTencent(devEmailSendTencentHtmlParam);
        return CommonResult.ok();
    }

    /**
     * 发送邮件——腾讯云TMP
     */
    @ApiOperationSupport(order = 10)
    @Operation(summary = "发送腾讯云模板邮件")
    @CommonLog("发送腾讯云模板邮件")
    @PostMapping("/dev/email/sentTencentTmp")
    public CommonResult<String> sendTencent(@RequestBody @Valid DevEmailSendTencentTmpParam devEmailSendTencentTmpParam) {
        devEmailService.sendTencent(devEmailSendTencentTmpParam);
        return CommonResult.ok();
    }

    /**
     * 获取邮件分页
     */
    @ApiOperationSupport(order = 11)
    @Operation(summary = "获取邮件分页")
    @GetMapping("/dev/email/page")
    public CommonResult<Page<DevEmail>> page(DevEmailPageParam devEmailPageParam) {
        return CommonResult.data(devEmailService.page(devEmailPageParam));
    }

    /**
     * 删除邮件
     */
    @ApiOperationSupport(order = 12)
    @Operation(summary = "删除邮件")
    @CommonLog("删除邮件")
    @PostMapping("/dev/email/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<DevEmailIdParam> devEmailIdParamList) {
        devEmailService.delete(devEmailIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取邮件详情
     */
    @ApiOperationSupport(order = 13)
    @Operation(summary = "获取邮件详情")
    @GetMapping("/dev/email/detail")
    public CommonResult<DevEmail> detail(@Valid DevEmailIdParam devEmailIdParam) {
        return CommonResult.data(devEmailService.detail(devEmailIdParam));
    }
}
