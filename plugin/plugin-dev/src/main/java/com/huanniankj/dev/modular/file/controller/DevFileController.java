package com.huanniankj.dev.modular.file.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.dev.api.DevConfigApi;
import com.huanniankj.dev.modular.file.entity.DevFile;
import com.huanniankj.dev.modular.file.enums.DevFileEngineTypeEnum;
import com.huanniankj.dev.modular.file.param.DevFileIdParam;
import com.huanniankj.dev.modular.file.param.DevFileListParam;
import com.huanniankj.dev.modular.file.param.DevFilePageParam;
import com.huanniankj.dev.modular.file.param.DevFileUrlListParam;
import com.huanniankj.dev.modular.file.service.DevFileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

/**
 * 文件控制器
 *
 * @author happynewyear
 */
@Tag(name = "文件控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 4)
@RestController
@Validated
public class DevFileController {

    /**
     * 默认文件引擎
     */
    private static final String SYSTEM_SYS_DEFAULT_FILE_ENGINE_KEY = "SYSTEM_SYS_DEFAULT_FILE_ENGINE";

    @Resource
    private DevConfigApi devConfigApi;

    @Resource
    private DevFileService devFileService;

    /**
     * 动态上传文件返回id
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "动态上传文件返回id")
    @CommonLog("动态上传文件返回id")
    @PostMapping("/dev/file/uploadDynamicReturnId")
    public CommonResult<String> uploadDynamicReturnId(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnId(devConfigApi.getValueByKey(SYSTEM_SYS_DEFAULT_FILE_ENGINE_KEY), file));
    }

    /**
     * 动态上传文件返回url
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "动态上传文件返回url")
    @CommonLog("动态上传文件返回url")
    @PostMapping("/dev/file/uploadDynamicReturnUrl")
    public CommonResult<String> uploadDynamicReturnUrl(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnUrl(devConfigApi.getValueByKey(SYSTEM_SYS_DEFAULT_FILE_ENGINE_KEY), file));
    }

    /**
     * 本地文件上传，返回文件id
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "上传本地文件返回id")
    @CommonLog("上传本地文件返回id")
    @PostMapping("/dev/file/uploadLocalReturnId")
    public CommonResult<String> uploadLocalReturnId(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnId(DevFileEngineTypeEnum.LOCAL.getValue(), file));
    }

    /**
     * 本地文件上传，返回文件Url
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "上传本地文件返回url")
    @CommonLog("上传本地文件返回url")
    @PostMapping("/dev/file/uploadLocalReturnUrl")
    public CommonResult<String> uploadLocalReturnUrl(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnUrl(DevFileEngineTypeEnum.LOCAL.getValue(), file));
    }

    /**
     * 阿里云文件上传，返回文件id
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "上传阿里云文件返回id")
    @CommonLog("上传阿里云文件返回id")
    @PostMapping("/dev/file/uploadAliyunReturnId")
    public CommonResult<String> uploadAliyunReturnId(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnId(DevFileEngineTypeEnum.ALIYUN.getValue(), file));
    }

    /**
     * 阿里云文件上传，返回文件Url
     */
    @ApiOperationSupport(order = 6)
    @Operation(summary = "上传阿里云文件返回url")
    @CommonLog("上传阿里云文件返回url")
    @PostMapping("/dev/file/uploadAliyunReturnUrl")
    public CommonResult<String> uploadAliyunReturnUrl(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnUrl(DevFileEngineTypeEnum.ALIYUN.getValue(), file));
    }

    /**
     * 腾讯云文件上传，返回文件id
     */
    @ApiOperationSupport(order = 7)
    @Operation(summary = "上传腾讯云文件返回id")
    @CommonLog("上传腾讯云文件返回id")
    @PostMapping("/dev/file/uploadTencentReturnId")
    public CommonResult<String> uploadTencentReturnId(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnId(DevFileEngineTypeEnum.TENCENT.getValue(), file));
    }

    /**
     * 腾讯云文件上传，返回文件Url
     */
    @ApiOperationSupport(order = 8)
    @Operation(summary = "上传腾讯云文件返回url")
    @CommonLog("上传腾讯云文件返回url")
    @PostMapping("/dev/file/uploadTencentReturnUrl")
    public CommonResult<String> uploadTencentReturnUrl(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnUrl(DevFileEngineTypeEnum.TENCENT.getValue(), file));
    }

    /**
     * MINIO文件上传，返回文件id
     */
    @ApiOperationSupport(order = 9)
    @Operation(summary = "上传MINIO文件返回id")
    @CommonLog("上传MINIO文件返回id")
    @PostMapping("/dev/file/uploadMinioReturnId")
    public CommonResult<String> uploadMinioReturnId(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnId(DevFileEngineTypeEnum.MINIO.getValue(), file));
    }

    /**
     * MINIO文件上传，返回文件Url
     */
    @ApiOperationSupport(order = 10)
    @Operation(summary = "上传MINIO文件返回url")
    @CommonLog("上传MINIO文件返回url")
    @PostMapping("/dev/file/uploadMinioReturnUrl")
    public CommonResult<String> uploadMinioReturnUrl(@RequestPart("file") MultipartFile file) {
        return CommonResult.data(devFileService.uploadReturnUrl(DevFileEngineTypeEnum.MINIO.getValue(), file));
    }

    /**
     * 获取文件分页列表
     */
    @ApiOperationSupport(order = 11)
    @Operation(summary = "获取文件分页列表")
    @GetMapping("/dev/file/page")
    public CommonResult<Page<DevFile>> page(DevFilePageParam devFilePageParam) {
        return CommonResult.data(devFileService.page(devFilePageParam));
    }

    /**
     * 获取文件列表
     */
    @ApiOperationSupport(order = 12)
    @Operation(summary = "获取文件列表")
    @GetMapping("/dev/file/list")
    public CommonResult<List<DevFile>> list(DevFileListParam devFileListParam) {
        return CommonResult.data(devFileService.list(devFileListParam));
    }

    /**
     * 下载文件
     */
    @ApiOperationSupport(order = 13)
    @Operation(summary = "下载文件")
    @CommonLog("下载文件")
    @GetMapping(value = "/dev/file/download", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void download(@Valid DevFileIdParam devFileIdParam, HttpServletResponse response) throws IOException {
        devFileService.download(devFileIdParam, response);
    }

    /**
     * 下载文件
     */
    @ApiOperationSupport(order = 14)
    @Operation(summary = "授权下载文件")
    @CommonLog("授权下载文件")
    @GetMapping(value = "/dev/file/authDownload", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void authDownload(@Valid DevFileIdParam devFileIdParam, HttpServletResponse response) throws IOException {
        devFileService.authDownload(devFileIdParam, response);
    }

    /**
     * 删除文件
     */
    @ApiOperationSupport(order = 15)
    @Operation(summary = "删除文件")
    @CommonLog("删除文件")
    @PostMapping(value = "/dev/file/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<DevFileIdParam> devFileIdParamList) {
        devFileService.delete(devFileIdParamList);
        return CommonResult.ok();
    }

    /**
     * 物理删除文件
     */
    @ApiOperationSupport(order = 16)
    @Operation(summary = "物理删除文件")
    @CommonLog("物理删除文件")
    @PostMapping(value = "/dev/file/deleteAbsolute")
    public CommonResult<String> deleteAbsolute(@RequestBody DevFileIdParam devFileIdParam) {
        devFileService.deleteAbsolute(devFileIdParam);
        return CommonResult.ok();
    }

    /**
     * 获取文件详情
     */
    @ApiOperationSupport(order = 17)
    @Operation(summary = "获取文件详情")
    @GetMapping("/dev/file/detail")
    public CommonResult<DevFile> detail(@Valid DevFileIdParam devFileIdParam) {
        return CommonResult.data(devFileService.detail(devFileIdParam));
    }

    /**
     * 根据文件url集合获取文件集合
     */
    @ApiOperationSupport(order = 18)
    @Operation(summary = "根据文件url集合获取文件集合")
    @PostMapping("/dev/file/getFileListByUrlList")
    public CommonResult<List<DevFile>> getFileListByUrlList(
            @RequestBody @Valid DevFileUrlListParam devFileUrlListParam) {
        return CommonResult.data(devFileService.getFileListByUrlList(devFileUrlListParam));
    }
}
