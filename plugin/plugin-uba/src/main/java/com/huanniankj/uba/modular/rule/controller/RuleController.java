package com.huanniankj.uba.modular.rule.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.uba.modular.rule.entity.Rule;
import com.huanniankj.uba.modular.rule.param.RuleAddParam;
import com.huanniankj.uba.modular.rule.param.RuleEditParam;
import com.huanniankj.uba.modular.rule.param.RuleIdParam;
import com.huanniankj.uba.modular.rule.param.RuleListParam;
import com.huanniankj.uba.modular.rule.param.RulePageParam;
import com.huanniankj.uba.modular.rule.service.RuleService;
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
 * 数据清洗规则控制器
 *
 * @author happynewyear
 */
@Tag(name = "数据清洗规则控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 3)
@RestController
@Validated
public class RuleController {

    @Resource
    private RuleService ruleService;

    /**
     * 获取数据清洗规则分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取数据清洗规则分页")
    @GetMapping("/uba/rule/page")
    public CommonResult<Page<Rule>> page(RulePageParam rulePageParam) {
        return CommonResult.data(ruleService.page(rulePageParam));
    }

    /**
     * 获取数据清洗规则列表
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取数据清洗规则列表")
    @GetMapping("/uba/rule/list")
    public CommonResult<List<Rule>> list(RuleListParam ruleListParam) {
        return CommonResult.data(ruleService.list(ruleListParam));
    }

    /**
     * 添加数据清洗规则
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "添加数据清洗规则")
    @CommonLog("添加数据处理字典")
    @PostMapping("/uba/rule/add")
    public CommonResult<String> add(@RequestBody @Valid RuleAddParam ruleAddParam) {
        ruleService.add(ruleAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑数据清洗规则
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "编辑数据清洗规则")
    @CommonLog("编辑数据清洗规则")
    @PostMapping("/uba/rule/edit")
    public CommonResult<String> edit(@RequestBody @Valid RuleEditParam ruleEditParam) {
        ruleService.edit(ruleEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除数据清洗规则
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "删除数据清洗规则")
    @CommonLog("删除数据清洗规则")
    @PostMapping("/uba/rule/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<RuleIdParam> ruleIdParamList) {
        ruleService.delete(ruleIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取数据清洗规则详情
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取数据清洗规则详情")
    @GetMapping("/uba/rule/detail")
    public CommonResult<Rule> detail(@Valid RuleIdParam ruleIdParam) {
        return CommonResult.data(ruleService.detail(ruleIdParam));
    }

}
