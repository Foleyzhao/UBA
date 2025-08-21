package com.huanniankj.dev.modular.file.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.spring.SpringUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import cn.hutool.system.SystemUtil;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.dev.api.DevConfigApi;
import com.huanniankj.dev.modular.file.enums.DevFileBucketAuthEnum;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * 本地文件工具类
 *
 * @author happynewyear
 */
@Slf4j
public class DevFileLocalUtil {

    /**
     * 操作的客户端
     */
    @Getter
    private static JSONObject client;

    private static final String SYSTEM_FILE_LOCAL_FOLDER_FOR_WINDOWS_KEY = "SYSTEM_FILE_LOCAL_FOLDER_FOR_WINDOWS";

    private static final String SYSTEM_FILE_LOCAL_FOLDER_FOR_UNIX_KEY = "SYSTEM_FILE_LOCAL_FOLDER_FOR_UNIX";

    /**
     * 初始化操作的客户端
     */
    private static void initClient() {

        String uploadFileFolder;

        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);

        if (SystemUtil.getOsInfo().isWindows()) {

            /* 本地文件上传的位置 windows系统 */
            String localFolderForWindows = devConfigApi.getValueByKey(SYSTEM_FILE_LOCAL_FOLDER_FOR_WINDOWS_KEY);

            if (ObjectUtil.isEmpty(localFolderForWindows)) {
                throw new CommonException("本地文件操作客户端未正确配置：SYSTEM_FILE_LOCAL_FOLDER_FOR_WINDOWS为空");
            }
            uploadFileFolder = localFolderForWindows;
        } else {

            /* 本地文件上传的位置 unix系列系统（linux、mac等） */
            String localFolderForUnix = devConfigApi.getValueByKey(SYSTEM_FILE_LOCAL_FOLDER_FOR_UNIX_KEY);

            if (ObjectUtil.isEmpty(localFolderForUnix)) {
                throw new CommonException("本地文件操作客户端未正确配置：SYSTEM_FILE_LOCAL_FOLDER_FOR_UNIX为空");
            }
            uploadFileFolder = localFolderForUnix;
        }
        if (!FileUtil.exist(uploadFileFolder)) {
            FileUtil.mkdir(uploadFileFolder);
        }
        client = JSONUtil.createObj();
        client.set("localFileUploadFolder", uploadFileFolder);
    }

    /**
     * 销毁操作的客户端
     */
    public static void destroyClient() {
        client.clear();
    }

    /**
     * 获取上传地址
     */
    public static String getUploadFileFolder() {
        return client.getStr("localFileUploadFolder");
    }

    /**
     * 查询存储桶是否存在
     * 例如：传入参数examplebucket-1250000000，返回true代表存在此桶
     *
     * @param bucketName 桶名称
     */
    public static boolean doesBucketExist(String bucketName) {
        initClient();
        return FileUtil.exist(getUploadFileFolder() + FileUtil.FILE_SEPARATOR + bucketName);
    }

    /**
     * 设置预定义策略
     * 预定义策略如公有读、公有读写、私有读
     *
     * @param bucketName            桶名称
     * @param devFileBucketAuthEnum 存储桶权限
     */
    public static void setBucketAcl(String bucketName, DevFileBucketAuthEnum devFileBucketAuthEnum) {
        // 无需
    }

    /**
     * 判断是否存在文件
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public static boolean isExistingFile(String bucketName, String key) {
        initClient();
        return FileUtil.exist(getUploadFileFolder() + FileUtil.FILE_SEPARATOR + bucketName
                + FileUtil.FILE_SEPARATOR + key);
    }

    /**
     * 存储文件，不返回地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param file       文件
     */
    public static void storageFile(String bucketName, String key, File file) {
        BufferedInputStream inputStream;
        try {
            inputStream = FileUtil.getInputStream(file);
        } catch (IORuntimeException e) {
            throw new CommonException("获取文件流异常，名称是：{}", file.getName());
        }
        storageFile(bucketName, key, inputStream);
    }

    /**
     * 存储文件，不返回地址
     *
     * @param bucketName    桶名称
     * @param key           唯一标示id，例如a.txt, doc/a.txt
     * @param multipartFile 文件
     */
    public static void storageFile(String bucketName, String key, MultipartFile multipartFile) {
        InputStream inputStream;
        try {
            inputStream = multipartFile.getInputStream();
        } catch (IOException e) {
            throw new CommonException("获取文件流异常，名称是：{}", multipartFile.getName());
        }
        storageFile(bucketName, key, inputStream);
    }

    /**
     * 存储文件，不返回地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param bytes      文件字节数组
     */
    public static void storageFile(String bucketName, String key, byte[] bytes) {
        initClient();
        FileUtil.writeBytes(bytes, getUploadFileFolder() + FileUtil.FILE_SEPARATOR
                + bucketName + FileUtil.FILE_SEPARATOR + key);
    }

    /**
     * 存储文件，不返回地址
     *
     * @param bucketName  桶名称
     * @param key         唯一标示id，例如a.txt, doc/a.txt
     * @param inputStream 文件流
     */
    public static void storageFile(String bucketName, String key, InputStream inputStream) {
        initClient();
        FileUtil.writeFromStream(inputStream, getUploadFileFolder() + FileUtil.FILE_SEPARATOR + bucketName
                + FileUtil.FILE_SEPARATOR + key);
    }

    /**
     * 存储文件，返回存储的地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param file       文件
     */
    public static String storageFileWithReturnUrl(String bucketName, String key, File file) {
        storageFile(bucketName, key, file);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 存储文件，返回存储的地址
     *
     * @param bucketName    桶名称
     * @param key           唯一标示id，例如a.txt, doc/a.txt
     * @param multipartFile 文件
     */
    public static String storageFileWithReturnUrl(String bucketName, String key, MultipartFile multipartFile) {
        storageFile(bucketName, key, multipartFile);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 存储文件，返回存储的地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param bytes      文件字节数组
     */
    public static String storageFileWithReturnUrl(String bucketName, String key, byte[] bytes) {
        storageFile(bucketName, key, bytes);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 存储文件，返回存储的地址
     *
     * @param bucketName  桶名称
     * @param key         唯一标示id，例如a.txt, doc/a.txt
     * @param inputStream 文件流
     */
    public static String storageFileWithReturnUrl(String bucketName, String key, InputStream inputStream) {
        storageFile(bucketName, key, inputStream);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 获取某个bucket下的文件字节
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public static byte[] getFileBytes(String bucketName, String key) {
        File file = getFileByBucketNameAndKey(bucketName, key);
        return FileUtil.readBytes(file);
    }

    /**
     * 设置文件访问权限管理
     *
     * @param bucketName            桶名称
     * @param key                   唯一标示id，例如a.txt, doc/a.txt
     * @param devFileBucketAuthEnum 文件权限
     */
    public static void setFileAcl(String bucketName, String key, DevFileBucketAuthEnum devFileBucketAuthEnum) {
        // 无需
    }

    /**
     * 拷贝文件
     *
     * @param originBucketName 源文件桶
     * @param originFileKey    源文件名称
     * @param newBucketName    新文件桶
     * @param newFileKey       新文件名称
     */
    public static void copyFile(String originBucketName, String originFileKey, String newBucketName, String newFileKey) {
        initClient();
        File file = getFileByBucketNameAndKey(originBucketName, originFileKey);
        File newFile = FileUtil.file(getUploadFileFolder() + FileUtil.FILE_SEPARATOR + newBucketName
                + FileUtil.FILE_SEPARATOR + newFileKey);
        FileUtil.copy(file, newFile, true);
    }

    /**
     * 获取文件的实际存储地址
     *
     * @param bucketName 文件桶
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public static String getFileAuthUrl(String bucketName, String key) {
        initClient();
        File file = getFileByBucketNameAndKey(bucketName, key);
        return file.getAbsolutePath();
    }

    /**
     * 删除文件
     *
     * @param bucketName 文件桶
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public static void deleteFile(String bucketName, String key) {
        File file = getFileByBucketNameAndKey(bucketName, key);
        FileUtil.del(file);
    }

    /**
     * 根据桶名称和文件key获取文件
     *
     * @param bucketName 文件桶
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public static File getFileByBucketNameAndKey(String bucketName, String key) {
        initClient();
        String path = getUploadFileFolder() + FileUtil.FILE_SEPARATOR + bucketName + FileUtil.FILE_SEPARATOR + key;
        File file = FileUtil.file(path);
        if (!FileUtil.exist(file)) {
            throw new CommonException("文件{}不存在", path);
        }
        return file;
    }
}
