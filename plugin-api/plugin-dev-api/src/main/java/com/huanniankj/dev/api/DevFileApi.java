package com.huanniankj.dev.api;

import cn.hutool.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件API接口，可参考com.huanniankj.dev.core.util.file包下的工具类扩展更多需要的方法
 *
 * @author happynewyear
 */
public interface DevFileApi {

    /**
     * 动态上传文件返回id（使用系统配置的默认文件引擎）
     */
    String uploadDynamicReturnId(MultipartFile file);

    /**
     * 动态上传文件返回url（使用系统配置的默认文件引擎）
     */
    String uploadDynamicReturnUrl(MultipartFile file);

    /* =========本地文件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     */
    String storageFileWithReturnUrlLocal(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     */
    String storageFileWithReturnIdLocal(MultipartFile file);

    /* =========阿里云文件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     */
    String storageFileWithReturnUrlAliyun(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     */
    String storageFileWithReturnIdAliyun(MultipartFile file);

    /* =========腾讯云件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     */
    String storageFileWithReturnUrlTencent(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     */
    String storageFileWithReturnIdTencent(MultipartFile file);

    /* =========MINIO件========= */

    /**
     * 上传文件返回Url
     *
     * @param file 文件
     */
    String storageFileWithReturnUrlMinio(MultipartFile file);

    /**
     * 上传文件返回Id
     *
     * @param file 文件
     */
    String storageFileWithReturnIdMinio(MultipartFile file);

    /**
     * 通过文件id查询文件详情
     */
    JSONObject getFileInfoById(String id);


    /**
     * 根据文件id物理删除文件
     */
    void deleteAbsoluteById(String id);
}
