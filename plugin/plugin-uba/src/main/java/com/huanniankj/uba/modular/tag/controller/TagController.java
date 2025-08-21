package com.huanniankj.uba.modular.tag.controller;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.uba.modular.tag.param.TagAddParam;
import com.huanniankj.uba.modular.tag.param.TagEditParam;
import com.huanniankj.uba.modular.tag.param.TagIdParam;
import com.huanniankj.uba.modular.tag.param.TagListParam;
import com.huanniankj.uba.modular.tag.param.TagPageParam;
import com.huanniankj.uba.modular.tag.param.TagTreeParam;
import com.huanniankj.uba.modular.tag.service.TagService;
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
 * 标签控制器
 *
 * @author happynewyear
 */
@Tag(name = "标签控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 1)
@RestController
@Validated
public class TagController {

    @Resource
    private TagService tagService;

    /**
     * 获取标签分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取标签分页")
    @GetMapping("/uba/tag/page")
    public CommonResult<Page<com.huanniankj.uba.modular.tag.entity.Tag>> page(TagPageParam tagPageParam) {
        return CommonResult.data(tagService.page(tagPageParam));
    }

    /**
     * 获取标签列表
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "获取标签列表")
    @GetMapping("/uba/tag/list")
    public CommonResult<List<com.huanniankj.uba.modular.tag.entity.Tag>> list(TagListParam tagListParam) {
        return CommonResult.data(tagService.list(tagListParam));
    }

    /**
     * 获取标签树
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "获取标签树")
    @GetMapping("/uba/tag/tree")
    public CommonResult<List<Tree<String>>> tree(TagTreeParam tagTreeParam) {
        return CommonResult.data(tagService.tree(tagTreeParam));
    }

    /**
     * 添加标签
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "添加标签")
    @CommonLog("添加标签")
    @PostMapping("/uba/tag/add")
    public CommonResult<String> add(@RequestBody @Valid TagAddParam tagAddParam) {
        tagService.add(tagAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑标签
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "编辑标签")
    @CommonLog("编辑标签")
    @PostMapping("/uba/tag/edit")
    public CommonResult<String> edit(@RequestBody @Valid TagEditParam tagEditParam) {
        tagService.edit(tagEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除标签
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "删除标签")
    @CommonLog("删除标签")
    @PostMapping("/uba/tag/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<TagIdParam> tagIdParamList) {
        tagService.delete(tagIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取标签详情
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "获取标签详情")
    @GetMapping("/uba/tag/detail")
    public CommonResult<com.huanniankj.uba.modular.tag.entity.Tag> detail(@Valid TagIdParam tagIdParam) {
        return CommonResult.data(tagService.detail(tagIdParam));
    }

}
