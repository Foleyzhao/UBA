package com.huanniankj.dev.modular.file.provider;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.huanniankj.dev.api.DevConfigApi;
import com.huanniankj.dev.api.DevFileApi;
import com.huanniankj.dev.modular.file.enums.DevFileEngineTypeEnum;
import com.huanniankj.dev.modular.file.param.DevFileIdParam;
import com.huanniankj.dev.modular.file.service.DevFileService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

/**
 * 文件API接口提供者
 *
 * @author happynewyear
 */
@Service
public class DevFileApiProvider implements DevFileApi {

    /**
     * 默认文件引擎
     */
    private static final String SYSTEM_SYS_DEFAULT_FILE_ENGINE_KEY = "SYSTEM_SYS_DEFAULT_FILE_ENGINE";

    @Resource
    private DevConfigApi devConfigApi;

    @Resource
    private DevFileService devFileService;

    @Override
    public String uploadDynamicReturnId(MultipartFile file) {
        return devFileService.uploadReturnId(devConfigApi.getValueByKey(SYSTEM_SYS_DEFAULT_FILE_ENGINE_KEY), file);
    }

    @Override
    public String uploadDynamicReturnUrl(MultipartFile file) {
        return devFileService.uploadReturnUrl(devConfigApi.getValueByKey(SYSTEM_SYS_DEFAULT_FILE_ENGINE_KEY), file);
    }

    @Override
    public String storageFileWithReturnUrlLocal(MultipartFile file) {
        return devFileService.uploadReturnUrl(DevFileEngineTypeEnum.LOCAL.getValue(), file);
    }

    @Override
    public String storageFileWithReturnIdLocal(MultipartFile file) {
        return devFileService.uploadReturnId(DevFileEngineTypeEnum.LOCAL.getValue(), file);
    }

    @Override
    public String storageFileWithReturnUrlAliyun(MultipartFile file) {
        return devFileService.uploadReturnUrl(DevFileEngineTypeEnum.ALIYUN.getValue(), file);
    }

    @Override
    public String storageFileWithReturnIdAliyun(MultipartFile file) {
        return devFileService.uploadReturnId(DevFileEngineTypeEnum.ALIYUN.getValue(), file);
    }

    @Override
    public String storageFileWithReturnUrlTencent(MultipartFile file) {
        return devFileService.uploadReturnUrl(DevFileEngineTypeEnum.TENCENT.getValue(), file);
    }

    @Override
    public String storageFileWithReturnIdTencent(MultipartFile file) {
        return devFileService.uploadReturnId(DevFileEngineTypeEnum.TENCENT.getValue(), file);
    }

    @Override
    public String storageFileWithReturnUrlMinio(MultipartFile file) {
        return devFileService.uploadReturnUrl(DevFileEngineTypeEnum.MINIO.getValue(), file);
    }

    @Override
    public String storageFileWithReturnIdMinio(MultipartFile file) {
        return devFileService.uploadReturnId(DevFileEngineTypeEnum.MINIO.getValue(), file);
    }

    @Override
    public JSONObject getFileInfoById(String id) {
        return Optional.ofNullable(devFileService.getById(id))
                .map(JSONUtil::parseObj)
                .orElse(new JSONObject());
    }

    @Override
    public void deleteAbsoluteById(String id) {
        DevFileIdParam devFileIdParam = new DevFileIdParam();
        devFileIdParam.setId(id);
        devFileService.deleteAbsolute(devFileIdParam);
    }
}
