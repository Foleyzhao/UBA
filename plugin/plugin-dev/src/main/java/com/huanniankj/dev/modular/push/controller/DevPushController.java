package com.huanniankj.dev.modular.push.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkLinkParam;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkMarkdownParam;
import com.huanniankj.dev.modular.push.param.DevPushDingTalkTextParam;
import com.huanniankj.dev.modular.push.param.DevPushDynamicTextParam;
import com.huanniankj.dev.modular.push.param.DevPushFeiShuTextParam;
import com.huanniankj.dev.modular.push.param.DevPushIdParam;
import com.huanniankj.dev.modular.push.param.DevPushPageParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatMarkdownParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatNewsParam;
import com.huanniankj.dev.modular.push.param.DevPushWorkWechatTextParam;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.dev.modular.push.entity.DevPush;
import com.huanniankj.dev.modular.push.service.DevPushService;

import javax.validation.Valid;
import java.util.List;

/**
 * 消息推送控制器
 *
 * @author happynewyear
 */
@Tag(name = "消息推送控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 11)
@RestController
@Validated
public class DevPushController {

    @Resource
    private DevPushService devPushService;

    /**
     * 动态推送消息
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "动态推送TEXT消息")
    @CommonLog("动态推送TEXT消息")
    @PostMapping("/dev/push/pushDynamicText")
    public CommonResult<String> pushDynamicText(@RequestBody @Valid DevPushDynamicTextParam devPushDynamicTextParam) {
        devPushService.pushDynamicText(devPushDynamicTextParam);
        return CommonResult.ok();
    }

    /**
     * 推送消息——飞书TXT
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "推送飞书TEXT消息")
    @CommonLog("推送飞书TEXT消息")
    @PostMapping("/dev/push/pushFeiShuText")
    public CommonResult<String> pushFeiShuText(@RequestBody @Valid DevPushFeiShuTextParam devPushFeiShuTextParam) {
        devPushService.pushFeiShuText(devPushFeiShuTextParam);
        return CommonResult.ok();
    }

    /**
     * 推送消息——钉钉TXT
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "推送钉钉TEXT消息")
    @CommonLog("推送钉钉TEXT消息")
    @PostMapping("/dev/push/pushDingTalkText")
    public CommonResult<String> pushDingTalkText(
            @RequestBody @Valid DevPushDingTalkTextParam devPushDingTalkTextParam) {
        devPushService.pushDingTalkText(devPushDingTalkTextParam);
        return CommonResult.ok();
    }

    /**
     * 推送消息——钉钉MARKDOWN
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "推送钉钉MARKDOWN消息")
    @CommonLog("推送钉钉MARKDOWN消息")
    @PostMapping("/dev/push/pushDingTalkMarkdown")
    public CommonResult<String> pushDingTalkMarkdown(
            @RequestBody @Valid DevPushDingTalkMarkdownParam devPushDingTalkMarkdownParam) {
        devPushService.pushDingTalkMarkdown(devPushDingTalkMarkdownParam);
        return CommonResult.ok();
    }

    /**
     * 推送消息——钉钉LINK
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "推送钉钉LINK消息")
    @CommonLog("推送钉钉LINK消息")
    @PostMapping("/dev/push/pushDingTalkLink")
    public CommonResult<String> pushDingTalkLink(@RequestBody @Valid DevPushDingTalkLinkParam devPushDingTalkLinkParam) {
        devPushService.pushDingTalkLink(devPushDingTalkLinkParam);
        return CommonResult.ok();
    }

    /**
     * 推送消息——企业微信TXT
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "推送企业微信TEXT消息")
    @CommonLog("推送企业微信TEXT消息")
    @PostMapping("/dev/push/pushWorkWechatText")
    public CommonResult<String> pushWorkWechatText(
            @RequestBody @Valid DevPushWorkWechatTextParam devPushWorkWechatTextParam) {
        devPushService.pushWorkWechatText(devPushWorkWechatTextParam);
        return CommonResult.ok();
    }

    /**
     * 推送消息——企业微信MARKDOWN
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "推送企业微信MARKDOWN消息")
    @CommonLog("推送企业微信MARKDOWN消息")
    @PostMapping("/dev/push/pushWorkWechatMarkdown")
    public CommonResult<String> pushWorkWechatMarkdown(
            @RequestBody @Valid DevPushWorkWechatMarkdownParam devPushWorkWechatMarkdownParam) {
        devPushService.pushWorkWechatMarkdown(devPushWorkWechatMarkdownParam);
        return CommonResult.ok();
    }

    /**
     * 推送消息——企业微信NEWS
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "推送企业微信NEWS消息")
    @CommonLog("推送企业微信NEWS消息")
    @PostMapping("/dev/push/pushWorkWechatNews")
    public CommonResult<String> pushWorkWechatNews(
            @RequestBody @Valid DevPushWorkWechatNewsParam devPushWorkWechatNewsParam) {
        devPushService.pushWorkWechatNews(devPushWorkWechatNewsParam);
        return CommonResult.ok();
    }

    /**
     * 获取推送消息分页
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "获取推送消息分页")
    @GetMapping("/dev/push/page")
    public CommonResult<Page<DevPush>> page(DevPushPageParam devPushPageParam) {
        return CommonResult.data(devPushService.page(devPushPageParam));
    }

    /**
     * 删除推送消息
     */
    @ApiOperationSupport(order = 10)
    @Operation(summary = "删除推送消息")
    @CommonLog("删除推送消息")
    @PostMapping("/dev/push/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<DevPushIdParam> devPushIdParamList) {
        devPushService.delete(devPushIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取推送消息详情
     */
    @ApiOperationSupport(order = 11)
    @Operation(summary = "获取推送消息详情")
    @GetMapping("/dev/push/detail")
    public CommonResult<DevPush> detail(@Valid DevPushIdParam devPushIdParam) {
        return CommonResult.data(devPushService.detail(devPushIdParam));
    }

}
