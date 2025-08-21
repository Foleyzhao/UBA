package com.huanniankj.dev.modular.slideshow.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.dev.modular.slideshow.entity.DevSlideshow;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowAddParam;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowEditParam;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowIdParam;
import com.huanniankj.dev.modular.slideshow.param.DevSlideshowPageParam;
import com.huanniankj.dev.modular.slideshow.service.DevSlideshowService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 轮播图控制器
 *
 * @author happynewyear
 */
@Tag(name = "轮播图控制器")
@RestController
@Validated
public class DevSlideshowController {

    @Resource
    private DevSlideshowService devSlideshowService;

    /**
     * 获取轮播图分页
     */
    @Operation(summary = "获取轮播图分页")
    @GetMapping("/dev/slideshow/page")
    public CommonResult<Page<DevSlideshow>> page(DevSlideshowPageParam devSlideshowPageParam) {
        return CommonResult.data(devSlideshowService.page(devSlideshowPageParam));
    }

    /**
     * 添加轮播图
     */
    @Operation(summary = "添加轮播图")
    @CommonLog("添加轮播图")
    @PostMapping("/dev/slideshow/add")
    public CommonResult<String> add(@RequestBody @Valid DevSlideshowAddParam devSlideshowAddParam) {
        devSlideshowService.add(devSlideshowAddParam);
        return CommonResult.ok();
    }

    /**
     * 编辑轮播图
     */
    @Operation(summary = "编辑轮播图")
    @CommonLog("编辑轮播图")
    @PostMapping("/dev/slideshow/edit")
    public CommonResult<String> edit(@RequestBody @Valid DevSlideshowEditParam devSlideshowEditParam) {
        devSlideshowService.edit(devSlideshowEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除轮播图
     */
    @Operation(summary = "删除轮播图")
    @CommonLog("删除轮播图")
    @PostMapping("/dev/slideshow/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<DevSlideshowIdParam> devSlideshowIdParamList) {
        devSlideshowService.delete(devSlideshowIdParamList);
        return CommonResult.ok();
    }

    /**
     * 禁用轮播图
     */
    @Operation(summary = "禁用轮播图")
    @CommonLog("禁用轮播图")
    @PostMapping("/dev/slideshow/disableStatus")
    public CommonResult<String> disableStatus(@RequestBody @Valid DevSlideshowIdParam devSlideshowIdParam) {
        devSlideshowService.disableStatus(devSlideshowIdParam);
        return CommonResult.ok();
    }

    /**
     * 启用轮播图
     */
    @Operation(summary = "启用轮播图")
    @CommonLog("启用轮播图")
    @PostMapping("/dev/slideshow/enableStatus")
    public CommonResult<String> enableStatus(@RequestBody @Valid DevSlideshowIdParam devSlideshowIdParam) {
        devSlideshowService.enableStatus(devSlideshowIdParam);
        return CommonResult.ok();
    }
}
