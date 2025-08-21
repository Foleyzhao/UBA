package com.huanniankj.uba.modular.dict.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.uba.modular.dict.entity.Dict;
import com.huanniankj.uba.modular.dict.param.DictAddParam;
import com.huanniankj.uba.modular.dict.param.DictEditParam;
import com.huanniankj.uba.modular.dict.param.DictIdParam;
import com.huanniankj.uba.modular.dict.param.DictListParam;
import com.huanniankj.uba.modular.dict.param.DictPageParam;
import com.huanniankj.uba.modular.dict.param.DictTreeParam;
import com.huanniankj.uba.modular.dict.service.DictService;
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
 * 数据处理字典控制器
 *
 * @author happynewyear
 */
@Tag(name = "数据处理字典控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 3)
@RestController
@Validated
public class DictController {

    @Resource
    private DictService dictService;

    /**
     * 获取数据处理字典分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取数据处理字典分页")
    @GetMapping("/uba/dict/page")
    public CommonResult<Page<Dict>> page(DictPageParam dictPageParam) {
        return CommonResult.data(dictService.page(dictPageParam));
    }

    /**
     * 获取数据处理字典列表
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取数据处理字典列表")
    @GetMapping("/uba/dict/list")
    public CommonResult<List<Dict>> list(DictListParam dictListParam) {
        return CommonResult.data(dictService.list(dictListParam));
    }

    /**
     * 获取数据处理字典树
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "获取数据处理字典树")
    @GetMapping("/uba/dict/tree")
    public CommonResult<List<Tree<String>>> tree(DictTreeParam dictTreeParam) {
        return CommonResult.data(dictService.tree(dictTreeParam));
    }

    /**
     * 添加数据处理字典
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "添加数据处理字典")
    @CommonLog("添加数据处理字典")
    @PostMapping("/uba/dict/add")
    public CommonResult<String> add(@RequestBody @Valid DictAddParam dictAddParam) {
        dictService.add(dictAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑数据处理字典
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "编辑数据处理字典")
    @CommonLog("编辑数据处理字典")
    @PostMapping("/uba/dict/edit")
    public CommonResult<String> edit(@RequestBody @Valid DictEditParam dictEditParam) {
        dictService.edit(dictEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除数据处理字典
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "删除数据处理字典")
    @CommonLog("删除数据处理字典")
    @PostMapping("/uba/dict/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<DictIdParam> dictIdParamList) {
        dictService.delete(dictIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取数据处理字典详情
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取数据处理字典详情")
    @GetMapping("/uba/dict/detail")
    public CommonResult<Dict> detail(@Valid DictIdParam dictIdParam) {
        return CommonResult.data(dictService.detail(dictIdParam));
    }
}
