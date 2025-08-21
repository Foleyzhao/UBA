package com.huanniankj.biz.modular.dict.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.biz.modular.dict.entity.BizDict;
import com.huanniankj.biz.modular.dict.param.BizDictEditParam;
import com.huanniankj.biz.modular.dict.param.BizDictPageParam;
import com.huanniankj.biz.modular.dict.service.BizDictService;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * 业务字典控制器
 *
 * @author happynewyear
 */
@Tag(name = "业务字典控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 4)
@RestController
@Validated
public class BizDictController {

    @Resource
    private BizDictService bizDictService;

    /**
     * 获取业务字典分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取业务字典分页")
    @SaCheckPermission("/biz/dict/page")
    @GetMapping("/biz/dict/page")
    public CommonResult<Page<BizDict>> page(BizDictPageParam bizDictPageParam) {
        return CommonResult.data(bizDictService.page(bizDictPageParam));
    }

    /**
     * 获取业务字典树
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取业务字典树")
    @SaCheckPermission("/biz/dict/tree")
    @GetMapping("/biz/dict/tree")
    public CommonResult<List<Tree<String>>> tree() {
        return CommonResult.data(bizDictService.tree());
    }

    /**
     * 获取所有字典树
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "获取所有字典树")
    @GetMapping("/biz/dict/treeAll")
    public CommonResult<List<Tree<String>>> treeAll() {
        return CommonResult.data(bizDictService.treeAll());
    }

    /**
     * 编辑业务字典
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "编辑业务字典")
    @CommonLog("编辑业务字典")
    @SaCheckPermission("/biz/dict/edit")
    @PostMapping("/biz/dict/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizDictEditParam bizDictEditParam) {
        bizDictService.edit(bizDictEditParam);
        return CommonResult.ok();
    }
}
