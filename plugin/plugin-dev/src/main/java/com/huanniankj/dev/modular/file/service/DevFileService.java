package com.huanniankj.dev.modular.file.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.file.entity.DevFile;
import com.huanniankj.dev.modular.file.param.DevFileIdParam;
import com.huanniankj.dev.modular.file.param.DevFileListParam;
import com.huanniankj.dev.modular.file.param.DevFilePageParam;
import com.huanniankj.dev.modular.file.param.DevFileUrlListParam;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件服务接口
 *
 * @author happynewyear
 */
public interface DevFileService extends IService<DevFile> {

    /**
     * MultipartFile文件上传，返回文件id
     */
    String uploadReturnId(String engine, MultipartFile file);

    /**
     * MultipartFile文件上传，返回文件Url
     */
    String uploadReturnUrl(String engine, MultipartFile file);

    /**
     * 文件分页列表接口
     */
    Page<DevFile> page(DevFilePageParam devFilePageParam);

    /**
     * 文件列表接口
     */
    List<DevFile> list(DevFileListParam devFileListParam);

    /**
     * 下载文件
     */
    void download(DevFileIdParam devFileIdParam, HttpServletResponse response) throws IOException;


    /**
     * 授权下载文件
     */
    void authDownload(DevFileIdParam devFileIdParam, HttpServletResponse response) throws IOException;


    /**
     * 删除文件
     */
    void delete(List<DevFileIdParam> devFileIdParamList);

    /**
     * 物理删除文件
     */
    void deleteAbsolute(DevFileIdParam devFileIdParam);

    /**
     * 获取文件详情
     */
    DevFile detail(DevFileIdParam devFileIdParam);

    /**
     * 根据文件url集合获取文件集合
     */
    List<DevFile> getFileListByUrlList(DevFileUrlListParam devFileUrlListParam);

    /**
     * 获取文件详情
     */
    DevFile queryEntity(String id);
}
