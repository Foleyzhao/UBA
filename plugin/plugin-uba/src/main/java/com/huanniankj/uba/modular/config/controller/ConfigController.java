package com.huanniankj.uba.modular.config.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.uba.modular.config.entity.Config;
import com.huanniankj.uba.modular.config.param.ConfigAddParam;
import com.huanniankj.uba.modular.config.param.ConfigBatchParam;
import com.huanniankj.uba.modular.config.param.ConfigEditParam;
import com.huanniankj.uba.modular.config.param.ConfigIdParam;
import com.huanniankj.uba.modular.config.param.ConfigListParam;
import com.huanniankj.uba.modular.config.param.ConfigPageParam;
import com.huanniankj.uba.modular.config.service.ConfigService;
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
 * 配置控制器
 *
 * @author happynewyear
 */
@Tag(name = "配置控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 1)
@RestController
@Validated
public class ConfigController {

    @Resource
    private ConfigService configService;

    /**
     * 获取配置分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取配置分页")
    @GetMapping("/uba/config/page")
    public CommonResult<Page<Config>> page(ConfigPageParam configPageParam) {
        return CommonResult.data(configService.page(configPageParam));
    }

    /**
     * 获取UBA业务定义配置
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取UBA业务定义配置")
    @GetMapping("/uba/config/ubaDefineList")
    public CommonResult<List<Config>> ubaDefineList() {
        return CommonResult.data(configService.ubaDefineList());
    }

    /**
     * 获取配置列表
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "获取配置列表")
    @GetMapping("/uba/config/list")
    public CommonResult<List<Config>> list(ConfigListParam configListParam) {
        return CommonResult.data(configService.list(configListParam));
    }

    /**
     * 添加配置
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "添加配置")
    @CommonLog("添加配置")
    @PostMapping("/uba/config/add")
    public CommonResult<String> add(@RequestBody @Valid ConfigAddParam configAddParam) {
        configService.add(configAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑配置
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "编辑配置")
    @CommonLog("编辑配置")
    @PostMapping("/uba/config/edit")
    public CommonResult<String> edit(@RequestBody @Valid ConfigEditParam configEditParam) {
        configService.edit(configEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除配置
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "删除配置")
    @CommonLog("删除配置")
    @PostMapping("/uba/config/delete")
    public CommonResult<String> delete(
            @RequestBody @Valid @NotEmpty(message = "集合不能为空") List<ConfigIdParam> configIdParams) {
        configService.delete(configIdParams);
        return CommonResult.ok();
    }

    /**
     * 获取配置详情
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取配置详情")
    @GetMapping("/uba/config/detail")
    public CommonResult<Config> detail(@Valid ConfigIdParam configIdParam) {
        return CommonResult.data(configService.detail(configIdParam));
    }

    /**
     * 配置批量更新
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "配置批量更新")
    @CommonLog("配置批量更新")
    @PostMapping("/uba/config/editBatch")
    public CommonResult<String> editBatch(
            @RequestBody @Valid @NotEmpty(message = "集合不能为空") List<ConfigBatchParam> configBatchParams) {
        configService.editBatch(configBatchParams);
        return CommonResult.ok();
    }

}
