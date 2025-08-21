package com.huanniankj.sys.modular.position.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.sys.modular.position.entity.SysPosition;
import com.huanniankj.sys.modular.position.param.SysPositionAddParam;
import com.huanniankj.sys.modular.position.param.SysPositionEditParam;
import com.huanniankj.sys.modular.position.param.SysPositionIdParam;
import com.huanniankj.sys.modular.position.param.SysPositionPageParam;
import com.huanniankj.sys.modular.position.param.SysPositionSelectorPositionParam;
import com.huanniankj.sys.modular.position.service.SysPositionService;
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
 * 职位控制器
 *
 * @author happynewyear
 */
@Tag(name = "职位控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 2)
@RestController
@Validated
public class SysPositionController {

    @Resource
    private SysPositionService sysPositionService;

    /**
     * 获取职位分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取职位分页")
    @GetMapping("/sys/position/page")
    public CommonResult<Page<SysPosition>> page(SysPositionPageParam sysPositionPageParam) {
        return CommonResult.data(sysPositionService.page(sysPositionPageParam));
    }

    /**
     * 添加职位
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加职位")
    @CommonLog("添加职位")
    @PostMapping("/sys/position/add")
    public CommonResult<String> add(@RequestBody @Valid SysPositionAddParam sysPositionAddParam) {
        sysPositionService.add(sysPositionAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑职位
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑职位")
    @CommonLog("编辑职位")
    @PostMapping("/sys/position/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysPositionEditParam sysPositionEditParam) {
        sysPositionService.edit(sysPositionEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除职位
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除职位")
    @CommonLog("删除职位")
    @PostMapping("/sys/position/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<SysPositionIdParam> sysPositionIdParamList) {
        sysPositionService.delete(sysPositionIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取职位详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取职位详情")
    @GetMapping("/sys/position/detail")
    public CommonResult<SysPosition> detail(@Valid SysPositionIdParam sysPositionIdParam) {
        return CommonResult.data(sysPositionService.detail(sysPositionIdParam));
    }

    /* ====职位部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "获取组织树选择器")
    @GetMapping("/sys/position/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(sysPositionService.orgTreeSelector());
    }

    /**
     * 获取职位选择器
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取职位选择器")
    @GetMapping("/sys/position/positionSelector")
    public CommonResult<Page<SysPosition>> positionSelector(
            SysPositionSelectorPositionParam sysPositionSelectorPositionParam) {
        return CommonResult.data(sysPositionService.positionSelector(sysPositionSelectorPositionParam));
    }
}
