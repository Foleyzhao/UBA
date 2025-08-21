package com.huanniankj.gen.modular.basic.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.gen.modular.basic.entity.GenBasic;
import com.huanniankj.gen.modular.basic.param.GenBasicAddParam;
import com.huanniankj.gen.modular.basic.param.GenBasicEditParam;
import com.huanniankj.gen.modular.basic.param.GenBasicIdParam;
import com.huanniankj.gen.modular.basic.param.GenBasicPageParam;
import com.huanniankj.gen.modular.basic.param.GenBasicSelectorMenuParam;
import com.huanniankj.gen.modular.basic.param.GenBasicTableColumnParam;
import com.huanniankj.gen.modular.basic.result.GenBasicMobileModuleSelectorResult;
import com.huanniankj.gen.modular.basic.result.GenBasicModuleSelectorResult;
import com.huanniankj.gen.modular.basic.result.GenBasicPreviewResult;
import com.huanniankj.gen.modular.basic.result.GenBasicTableColumnResult;
import com.huanniankj.gen.modular.basic.result.GenBasicTableResult;
import com.huanniankj.gen.modular.basic.service.GenBasicService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

/**
 * 代码生成基础控制器
 *
 * @author happynewyear
 */
@Tag(name = "代码生成基础控制器")
@RestController
@Validated
public class GenBasicController {

    @Resource
    private GenBasicService genBasicService;

    /**
     * 获取代码生成基础分页
     */
    @Operation(summary = "获取代码生成基础分页")
    @GetMapping("/gen/basic/page")
    public CommonResult<Page<GenBasic>> page(GenBasicPageParam genBasicPageParam) {
        return CommonResult.data(genBasicService.page(genBasicPageParam));
    }

    /**
     * 添加代码生成基础
     */
    @Operation(summary = "添加代码生成基础")
    @CommonLog("添加代码生成基础")
    @PostMapping("/gen/basic/add")
    public CommonResult<GenBasic> add(@RequestBody @Valid GenBasicAddParam genBasicAddParam) {
        return CommonResult.data(genBasicService.add(genBasicAddParam));
    }

    /**
     * 编辑代码生成基础
     */
    @Operation(summary = "编辑代码生成基础")
    @CommonLog("编辑代码生成基础")
    @PostMapping("/gen/basic/edit")
    public CommonResult<GenBasic> edit(@RequestBody @Valid GenBasicEditParam genBasicEditParam) {
        return CommonResult.data(genBasicService.edit(genBasicEditParam));
    }

    /**
     * 删除代码生成基础
     */
    @Operation(summary = "删除代码生成基础")
    @CommonLog("删除代码生成基础")
    @PostMapping("/gen/basic/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<GenBasicIdParam> genBasicIdParamList) {
        genBasicService.delete(genBasicIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取代码生成基础详情
     */
    @Operation(summary = "获取代码生成基础详情")
    @GetMapping("/gen/basic/detail")
    public CommonResult<GenBasic> detail(@Valid GenBasicIdParam genBasicIdParam) {
        return CommonResult.data(genBasicService.detail(genBasicIdParam));
    }

    /**
     * 获取所有表信息
     */
    @Operation(summary = "获取所有表信息")
    @GetMapping("/gen/basic/tables")
    public CommonResult<List<GenBasicTableResult>> dbsTable() {
        return CommonResult.data(genBasicService.tables());
    }

    /**
     * 获取表内所有字段信息
     */
    @Operation(summary = "获取表内所有字段信息")
    @GetMapping("/gen/basic/tableColumns")
    public CommonResult<List<GenBasicTableColumnResult>> tableColumns(
            GenBasicTableColumnParam genBasicTableColumnParam) {
        return CommonResult.data(genBasicService.tableColumns(genBasicTableColumnParam));
    }

    /**
     * 执行代码生成
     */
    @Operation(summary = "执行代码生成（压缩包）")
    @CommonLog("执行代码生成（压缩包）")
    @GetMapping(value = "/gen/basic/execGenZip", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void execGenZip(@Valid GenBasicIdParam genBasicIdParam, HttpServletResponse response) throws IOException {
        genBasicService.execGenZip(genBasicIdParam, response);
    }

    /**
     * 执行代码生成
     */
    @Operation(summary = "执行代码生成（项目内）")
    @CommonLog("执行代码生成（项目内）")
    @PostMapping(value = "/gen/basic/execGenPro")
    public CommonResult<String> execGenPro(@RequestBody @Valid GenBasicIdParam genBasicIdParam,
                                           HttpServletResponse response) throws IOException {
        genBasicService.execGenPro(genBasicIdParam, response);
        return CommonResult.ok();
    }

    /**
     * 预览代码生成
     */
    @Operation(summary = "预览代码生成")
    @CommonLog("预览代码生成")
    @GetMapping(value = "/gen/basic/previewGen")
    public CommonResult<GenBasicPreviewResult> previewGen(@Valid GenBasicIdParam genBasicIdParam) {
        return CommonResult.data(genBasicService.previewGen(genBasicIdParam));
    }

    /**
     * 获取所有移动端模块
     */
    @Operation(summary = "获取所有移动端模块")
    @GetMapping("/gen/basic/mobileModuleSelector")
    public CommonResult<List<GenBasicMobileModuleSelectorResult>> mobileModuleSelector() {
        return CommonResult.data(genBasicService.mobileModuleSelector());
    }

    /**
     * 获取所有模块
     */
    @Operation(summary = "获取所有模块")
    @GetMapping("/gen/basic/moduleSelector")
    public CommonResult<List<GenBasicModuleSelectorResult>> moduleSelector() {
        return CommonResult.data(genBasicService.moduleSelector());
    }

    /**
     * 代码生成获取所有菜单树包括未授权的
     */
    @Operation(summary = "代码生成获取所有菜单树包括未授权的")
    @GetMapping("/gen/basic/menuTreeSelector")
    public CommonResult<List<Tree<String>>> menuTreeSelector(
            @Valid GenBasicSelectorMenuParam genBasicSelectorMenuParam) {
        return CommonResult.data(genBasicService.menuTreeSelector(genBasicSelectorMenuParam));
    }
}

