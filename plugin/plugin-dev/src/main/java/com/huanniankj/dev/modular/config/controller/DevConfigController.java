package com.huanniankj.dev.modular.config.controller;

import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.dev.modular.config.entity.DevConfig;
import com.huanniankj.dev.modular.config.param.DevConfigAddParam;
import com.huanniankj.dev.modular.config.param.DevConfigBatchParam;
import com.huanniankj.dev.modular.config.param.DevConfigEditParam;
import com.huanniankj.dev.modular.config.param.DevConfigIdParam;
import com.huanniankj.dev.modular.config.param.DevConfigListParam;
import com.huanniankj.dev.modular.config.param.DevConfigPageParam;
import com.huanniankj.dev.modular.config.param.DevConfigSelectorOrgListParam;
import com.huanniankj.dev.modular.config.param.DevConfigSelectorPositionParam;
import com.huanniankj.dev.modular.config.param.DevConfigSelectorRoleParam;
import com.huanniankj.dev.modular.config.service.DevConfigService;
import com.huanniankj.sys.api.SysOrgApi;
import com.huanniankj.sys.api.SysPositionApi;
import com.huanniankj.sys.api.SysRoleApi;
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
public class DevConfigController {

    @Resource
    private DevConfigService devConfigService;

    @Resource
    private SysPositionApi sysPositionApi;

    @Resource
    private SysOrgApi sysOrgApi;

    @Resource
    private SysRoleApi sysRoleApi;

    /**
     * 获取配置分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取配置分页")
    @GetMapping("/dev/config/page")
    public CommonResult<Page<DevConfig>> page(DevConfigPageParam devConfigPageParam) {
        return CommonResult.data(devConfigService.page(devConfigPageParam));
    }

    /**
     * 获取系统基础配置
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取系统基础配置")
    @GetMapping("/dev/config/sysBaseList")
    public CommonResult<List<DevConfig>> sysBaseList() {
        return CommonResult.data(devConfigService.sysBaseList());
    }

    /**
     * 获取配置列表
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取配置列表")
    @GetMapping("/dev/config/list")
    public CommonResult<List<DevConfig>> list(DevConfigListParam devConfigListParam) {
        return CommonResult.data(devConfigService.list(devConfigListParam));
    }

    /**
     * 添加配置
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "添加配置")
    @CommonLog("添加配置")
    @PostMapping("/dev/config/add")
    public CommonResult<String> add(@RequestBody @Valid DevConfigAddParam devConfigAddParam) {
        devConfigService.add(devConfigAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑配置
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "编辑配置")
    @CommonLog("编辑配置")
    @PostMapping("/dev/config/edit")
    public CommonResult<String> edit(@RequestBody @Valid DevConfigEditParam devConfigEditParam) {
        devConfigService.edit(devConfigEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除配置
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "删除配置")
    @CommonLog("删除配置")
    @PostMapping("/dev/config/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<DevConfigIdParam> devConfigIdParamList) {
        devConfigService.delete(devConfigIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取配置详情
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "获取配置详情")
    @GetMapping("/dev/config/detail")
    public CommonResult<DevConfig> detail(@Valid DevConfigIdParam devConfigIdParam) {
        return CommonResult.data(devConfigService.detail(devConfigIdParam));
    }

    /**
     * 配置批量更新
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "配置批量更新")
    @CommonLog("配置批量更新")
    @PostMapping("/dev/config/editBatch")
    public CommonResult<String> editBatch(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                          List<DevConfigBatchParam> devConfigBatchParamList) {
        devConfigService.editBatch(devConfigBatchParamList);
        return CommonResult.ok();
    }

    /**
     * 获取机构选树
     */
    @Operation(summary = "获取机构选树")
    @GetMapping("/dev/config/orgTree")
    public CommonResult<List<Tree<String>>> orgTree() {
        return CommonResult.data(sysOrgApi.orgTreeSelector());
    }

    /**
     * 获取角色选择器
     */
    @Operation(summary = "获取角色选择器")
    @GetMapping("/dev/config/roleSelector")
    public CommonResult<Page<JSONObject>> roleSelector(DevConfigSelectorRoleParam devConfigSelectorRoleParam) {
        return CommonResult.data(sysRoleApi.roleSelector(devConfigSelectorRoleParam.getOrgId(),
                devConfigSelectorRoleParam.getCategory(),
                devConfigSelectorRoleParam.getSearchKey(), null, false));
    }

    /**
     * 获取机构选择器
     */
    @Operation(summary = "获取机构选择器")
    @GetMapping("/dev/config/orgSelector")
    public CommonResult<Page<JSONObject>> orgSelector(DevConfigSelectorOrgListParam devConfigSelectorOrgListParam) {
        return CommonResult.data(sysOrgApi.orgListSelector(devConfigSelectorOrgListParam.getParentId()));
    }

    /**
     * 获取职位选择器
     */
    @Operation(summary = "获取职位选择器")
    @GetMapping("/dev/config/positionSelector")
    public CommonResult<Page<JSONObject>> positionSelector(
            @Valid DevConfigSelectorPositionParam devConfigSelectorPositionParam) {
        return CommonResult.data(sysPositionApi.positionSelector(devConfigSelectorPositionParam.getOrgId(),
                devConfigSelectorPositionParam.getSearchKey(),
                devConfigSelectorPositionParam.getCurrent(), devConfigSelectorPositionParam.getSize()));
    }

}
