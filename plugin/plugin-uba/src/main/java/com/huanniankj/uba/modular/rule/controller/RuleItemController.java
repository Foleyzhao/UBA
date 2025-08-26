package com.huanniankj.uba.modular.rule.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.uba.modular.rule.entity.RuleItem;
import com.huanniankj.uba.modular.rule.param.RuleItemAddParam;
import com.huanniankj.uba.modular.rule.param.RuleItemEditParam;
import com.huanniankj.uba.modular.rule.param.RuleItemIdParam;
import com.huanniankj.uba.modular.rule.param.RuleItemListParam;
import com.huanniankj.uba.modular.rule.param.RuleItemPageParam;
import com.huanniankj.uba.modular.rule.service.RuleItemService;
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
 * 数据清洗规则项控制器
 *
 * @author happynewyear
 */
@Tag(name = "数据清洗规则项控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 3)
@RestController
@Validated
public class RuleItemController {

    @Resource
    private RuleItemService ruleItemService;

    /**
     * 获取数据清洗规则项分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取数据清洗规则项分页")
    @GetMapping("/uba/ruleItem/page")
    public CommonResult<Page<RuleItem>> page(RuleItemPageParam ruleItemPageParam) {
        return CommonResult.data(ruleItemService.page(ruleItemPageParam));
    }

    /**
     * 获取数据清洗规则项列表
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取数据清洗规则项列表")
    @GetMapping("/uba/ruleItem/list")
    public CommonResult<List<RuleItem>> list(RuleItemListParam ruleItemListParam) {
        return CommonResult.data(ruleItemService.list(ruleItemListParam));
    }

    /**
     * 添加数据清洗规则项
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "添加数据清洗规则项")
    @CommonLog("添加数据清洗规则项")
    @PostMapping("/uba/ruleItem/add")
    public CommonResult<String> add(@RequestBody @Valid RuleItemAddParam ruleItemAddParam) {
        ruleItemService.add(ruleItemAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑数据清洗规则项
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "编辑数据清洗规则项")
    @CommonLog("编辑数据清洗规则项")
    @PostMapping("/uba/ruleItem/edit")
    public CommonResult<String> edit(@RequestBody @Valid RuleItemEditParam ruleItemEditParam) {
        ruleItemService.edit(ruleItemEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除数据清洗规则项
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "删除数据清洗规则项")
    @CommonLog("删除数据清洗规则项")
    @PostMapping("/uba/ruleItem/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<RuleItemIdParam> ruleItemIdParams) {
        ruleItemService.delete(ruleItemIdParams);
        return CommonResult.ok();
    }

    /**
     * 获取数据清洗规则项详情
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "获取数据清洗规则项详情")
    @GetMapping("/uba/ruleItem/detail")
    public CommonResult<RuleItem> detail(@Valid RuleItemIdParam ruleItemIdParam) {
        return CommonResult.data(ruleItemService.detail(ruleItemIdParam));
    }

}
