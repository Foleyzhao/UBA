package com.huanniankj.biz.modular.org.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.biz.modular.org.entity.BizOrg;
import com.huanniankj.biz.modular.org.enums.BizOrgSourceFromTypeEnum;
import com.huanniankj.biz.modular.org.param.BizOrgAddParam;
import com.huanniankj.biz.modular.org.param.BizOrgEditParam;
import com.huanniankj.biz.modular.org.param.BizOrgIdParam;
import com.huanniankj.biz.modular.org.param.BizOrgPageParam;
import com.huanniankj.biz.modular.org.param.BizOrgSelectorUserParam;
import com.huanniankj.biz.modular.org.service.BizOrgService;
import com.huanniankj.biz.modular.user.entity.BizUser;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
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
 * 机构控制器
 *
 * @author happynewyear
 */
@Tag(name = "机构控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 1)
@RestController
@Validated
public class BizOrgController {

    @Resource
    private BizOrgService bizOrgService;

    /**
     * 获取机构分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取机构分页")
    @SaCheckPermission("/biz/org/page")
    @GetMapping("/biz/org/page")
    public CommonResult<Page<BizOrg>> page(BizOrgPageParam bizOrgPageParam) {
        return CommonResult.data(bizOrgService.page(bizOrgPageParam));
    }

    /**
     * 获取机构树
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取机构树")
    @SaCheckPermission("/biz/org/tree")
    @GetMapping("/biz/org/tree")
    public CommonResult<List<Tree<String>>> tree() {
        return CommonResult.data(bizOrgService.tree());
    }

    /**
     * 添加机构
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "添加机构")
    @CommonLog("添加机构")
    @SaCheckPermission("/biz/org/add")
    @PostMapping("/biz/org/add")
    public CommonResult<String> add(@RequestBody @Valid BizOrgAddParam bizOrgAddParam) {
        bizOrgService.add(bizOrgAddParam, BizOrgSourceFromTypeEnum.SYSTEM_ADD.getValue());
        return CommonResult.ok();
    }

    /**
     * 编辑机构
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "编辑机构")
    @CommonLog("编辑机构")
    @SaCheckPermission("/biz/org/edit")
    @PostMapping("/biz/org/edit")
    public CommonResult<String> edit(@RequestBody @Valid BizOrgEditParam bizOrgEditParam) {
        bizOrgService.edit(bizOrgEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除机构
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "删除机构")
    @CommonLog("删除机构")
    @SaCheckPermission("/biz/org/delete")
    @PostMapping("/biz/org/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<BizOrgIdParam> bizOrgIdParamList) {
        bizOrgService.delete(bizOrgIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取机构详情
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "获取机构详情")
    @SaCheckPermission("/biz/org/detail")
    @GetMapping("/biz/org/detail")
    public CommonResult<BizOrg> detail(@Valid BizOrgIdParam bizOrgIdParam) {
        return CommonResult.data(bizOrgService.detail(bizOrgIdParam));
    }

    /* ====机构部分所需要用到的选择器==== */

    /**
     * 获取机构树选择器
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取机构树选择器")
    @SaCheckPermission("/biz/org/orgTreeSelector")
    @GetMapping("/biz/org/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(bizOrgService.orgTreeSelector());
    }

    /**
     * 获取人员选择器
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "获取人员选择器")
    @SaCheckPermission("/biz/org/userSelector")
    @GetMapping("/biz/org/userSelector")
    public CommonResult<Page<BizUser>> userSelector(BizOrgSelectorUserParam bizOrgSelectorUserParam) {
        return CommonResult.data(bizOrgService.userSelector(bizOrgSelectorUserParam));
    }
}
