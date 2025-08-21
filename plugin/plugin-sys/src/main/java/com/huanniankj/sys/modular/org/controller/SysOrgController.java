package com.huanniankj.sys.modular.org.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.sys.modular.org.entity.SysOrg;
import com.huanniankj.sys.modular.org.enums.SysOrgSourceFromTypeEnum;
import com.huanniankj.sys.modular.org.param.SysOrgAddParam;
import com.huanniankj.sys.modular.org.param.SysOrgEditParam;
import com.huanniankj.sys.modular.org.param.SysOrgIdParam;
import com.huanniankj.sys.modular.org.param.SysOrgPageParam;
import com.huanniankj.sys.modular.org.param.SysOrgSelectorUserParam;
import com.huanniankj.sys.modular.org.service.SysOrgService;
import com.huanniankj.sys.modular.user.entity.SysUser;
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
 * 组织控制器
 *
 * @author happynewyear
 */
@Tag(name = "组织控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 1)
@RestController
@Validated
public class SysOrgController {

    @Resource
    private SysOrgService sysOrgService;

    /**
     * 获取组织分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取组织分页")
    @GetMapping("/sys/org/page")
    public CommonResult<Page<SysOrg>> page(SysOrgPageParam sysOrgPageParam) {
        return CommonResult.data(sysOrgService.page(sysOrgPageParam));
    }

    /**
     * 获取组织树
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取组织树")
    @GetMapping("/sys/org/tree")
    public CommonResult<List<Tree<String>>> tree() {
        return CommonResult.data(sysOrgService.tree());
    }

    /**
     * 添加组织
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "添加组织")
    @CommonLog("添加组织")
    @PostMapping("/sys/org/add")
    public CommonResult<String> add(@RequestBody @Valid SysOrgAddParam sysOrgAddParam) {
        sysOrgService.add(sysOrgAddParam, SysOrgSourceFromTypeEnum.SYSTEM_ADD.getValue());
        return CommonResult.ok();
    }

    /**
     * 编辑组织
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "编辑组织")
    @CommonLog("编辑组织")
    @PostMapping("/sys/org/edit")
    public CommonResult<String> edit(@RequestBody @Valid SysOrgEditParam sysOrgEditParam) {
        sysOrgService.edit(sysOrgEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除组织
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "删除组织")
    @CommonLog("删除组织")
    @PostMapping("/sys/org/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<SysOrgIdParam> sysOrgIdParamList) {
        sysOrgService.delete(sysOrgIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取组织详情
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "获取组织详情")
    @GetMapping("/sys/org/detail")
    public CommonResult<SysOrg> detail(@Valid SysOrgIdParam sysOrgIdParam) {
        return CommonResult.data(sysOrgService.detail(sysOrgIdParam));
    }

    /* ====组织部分所需要用到的选择器==== */

    /**
     * 获取组织树选择器
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取组织树选择器")
    @GetMapping("/sys/org/orgTreeSelector")
    public CommonResult<List<Tree<String>>> orgTreeSelector() {
        return CommonResult.data(sysOrgService.orgTreeSelector());
    }

    /**
     * 获取用户选择器
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "获取用户选择器")
    @GetMapping("/sys/org/userSelector")
    public CommonResult<Page<SysUser>> userSelector(SysOrgSelectorUserParam sysOrgSelectorUserParam) {
        return CommonResult.data(sysOrgService.userSelector(sysOrgSelectorUserParam));
    }

}
