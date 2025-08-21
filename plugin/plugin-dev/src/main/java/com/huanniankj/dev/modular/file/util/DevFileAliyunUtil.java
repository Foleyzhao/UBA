package com.huanniankj.dev.modular.file.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IORuntimeException;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.dev.api.DevConfigApi;
import com.huanniankj.dev.modular.file.enums.DevFileBucketAuthEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;
import java.util.List;

/**
 * 阿里云文件工具类
 * 参考文档：<a href="https://help.aliyun.com/document_detail/32010.html">参考文档</a>
 *
 * @author happynewyear
 */
@Slf4j
public class DevFileAliyunUtil {

    private static OSS client;

    private static String defaultBucketName;

    private static final String SYSTEM_FILE_ALIYUN_ACCESS_KEY_ID_KEY = "SYSTEM_FILE_ALIYUN_ACCESS_KEY_ID";

    private static final String SYSTEM_FILE_ALIYUN_ACCESS_KEY_SECRET_KEY = "SYSTEM_FILE_ALIYUN_ACCESS_KEY_SECRET";

    private static final String SYSTEM_FILE_ALIYUN_END_POINT_KEY = "SYSTEM_FILE_ALIYUN_END_POINT";

    private static final String SYSTEM_FILE_ALIYUN_DEFAULT_BUCKET_NAME = "SYSTEM_FILE_ALIYUN_DEFAULT_BUCKET_NAME";

    /**
     * 初始化操作的客户端
     */
    private static void initClient() {

        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);

        /* accessKeyId */
        String accessKeyId = devConfigApi.getValueByKey(SYSTEM_FILE_ALIYUN_ACCESS_KEY_ID_KEY);

        if (ObjectUtil.isEmpty(accessKeyId)) {
            throw new CommonException("阿里云文件操作客户端未正确配置：accessKeyId为空");
        }

        /* accessKeySecret */
        String accessKeySecret = devConfigApi.getValueByKey(SYSTEM_FILE_ALIYUN_ACCESS_KEY_SECRET_KEY);

        if (ObjectUtil.isEmpty(accessKeySecret)) {
            throw new CommonException("阿里云文件操作客户端未正确配置：accessKeySecret为空");
        }

        /* endpoint */
        String endpoint = devConfigApi.getValueByKey(SYSTEM_FILE_ALIYUN_END_POINT_KEY);

        if (ObjectUtil.isEmpty(endpoint)) {
            throw new CommonException("阿里云文件操作客户端未正确配置：endpoint为空");
        }

        /* 默认BucketName */
        defaultBucketName = devConfigApi.getValueByKey(SYSTEM_FILE_ALIYUN_DEFAULT_BUCKET_NAME);

        if (ObjectUtil.isEmpty(defaultBucketName)) {
            throw new CommonException("阿里云文件操作客户端未正确配置：defaultBucketName为空");
        }

        client = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
    }

    /**
     * 获取默认存储桶名称
     */
    public static String getDefaultBucketName() {
        initClient();
        return defaultBucketName;
    }

    /**
     * 销毁操作的客户端
     */
    public static void destroyClient() {
        initClient();
        client.shutdown();
    }

    /**
     * 获取操作的客户端
     */
    public OSS getClient() {
        initClient();
        return client;
    }

    /**
     * 查询存储桶是否存在
     * 例如：传入参数examplebucket-1250000000，返回true代表存在此桶
     *
     * @param bucketName 桶名称
     */
    public boolean doesBucketExist(String bucketName) {
        try {
            initClient();
            return client.doesBucketExist(bucketName);
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 设置预定义策略
     * 预定义策略如公有读、公有读写、私有读
     *
     * @param bucketName            桶名称
     * @param devFileBucketAuthEnum 存储桶权限
     */
    public static void setBucketAcl(String bucketName, DevFileBucketAuthEnum devFileBucketAuthEnum) {
        try {
            initClient();
            if (devFileBucketAuthEnum.equals(DevFileBucketAuthEnum.PRIVATE)) {
                client.setBucketAcl(bucketName, CannedAccessControlList.Private);
            } else if (devFileBucketAuthEnum.equals(DevFileBucketAuthEnum.PUBLIC_READ)) {
                client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
            } else if (devFileBucketAuthEnum.equals(DevFileBucketAuthEnum.PUBLIC_READ_WRITE)) {
                client.setBucketAcl(bucketName, CannedAccessControlList.PublicReadWrite);
            }
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 判断是否存在文件
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public static boolean isExistingFile(String bucketName, String key) {
        try {
            initClient();
            return client.doesObjectExist(bucketName, key);
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        }
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
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            initClient();
            byteArrayInputStream = new ByteArrayInputStream(bytes);
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(getFileContentType(key));
            client.putObject(bucketName, key, byteArrayInputStream, objectMetadata);
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        } finally {
            IoUtil.close(byteArrayInputStream);
        }
    }

    /**
     * 存储文件，不返回地址
     *
     * @param bucketName  桶名称
     * @param key         唯一标示id，例如a.txt, doc/a.txt
     * @param inputStream 文件流
     */
    public static void storageFile(String bucketName, String key, InputStream inputStream) {
        try {
            initClient();
            ObjectMetadata objectMetadata = new ObjectMetadata();
            objectMetadata.setContentType(getFileContentType(key));
            client.putObject(bucketName, key, inputStream, objectMetadata);
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        } finally {
            IoUtil.close(inputStream);
        }
    }

    /**
     * 存储文件，返回外网地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param file       文件
     */
    public static String storageFileWithReturnUrl(String bucketName, String key, File file) {
        storageFile(bucketName, key, file);
        setFileAcl(bucketName, key, DevFileBucketAuthEnum.PUBLIC_READ);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 存储文件，返回外网地址
     *
     * @param bucketName    桶名称
     * @param key           唯一标示id，例如a.txt, doc/a.txt
     * @param multipartFile 文件
     */
    public static String storageFileWithReturnUrl(String bucketName, String key, MultipartFile multipartFile) {
        storageFile(bucketName, key, multipartFile);
        setFileAcl(bucketName, key, DevFileBucketAuthEnum.PUBLIC_READ);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 存储文件，返回外网地址
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     * @param bytes      文件字节数组
     */
    public static String storageFileWithReturnUrl(String bucketName, String key, byte[] bytes) {
        storageFile(bucketName, key, bytes);
        setFileAcl(bucketName, key, DevFileBucketAuthEnum.PUBLIC_READ);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 存储文件，返回外网地址
     *
     * @param bucketName  桶名称
     * @param key         唯一标示id，例如a.txt, doc/a.txt
     * @param inputStream 文件流
     */
    public static String storageFileWithReturnUrl(String bucketName, String key, InputStream inputStream) {
        storageFile(bucketName, key, inputStream);
        setFileAcl(bucketName, key, DevFileBucketAuthEnum.PUBLIC_READ);
        return getFileAuthUrl(bucketName, key);
    }

    /**
     * 获取某个bucket下的文件字节
     *
     * @param bucketName 桶名称
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public static byte[] getFileBytes(String bucketName, String key) {
        InputStream objectContent = null;
        try {
            initClient();
            OSSObject ossObject = client.getObject(bucketName, key);
            objectContent = ossObject.getObjectContent();
            return IoUtil.readBytes(objectContent);
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        } finally {
            IoUtil.close(objectContent);
        }
    }

    /**
     * 设置文件访问权限管理
     *
     * @param bucketName            桶名称
     * @param key                   唯一标示id，例如a.txt, doc/a.txt
     * @param devFileBucketAuthEnum 文件权限
     */
    public static void setFileAcl(String bucketName, String key, DevFileBucketAuthEnum devFileBucketAuthEnum) {
        try {
            initClient();
            if (devFileBucketAuthEnum.equals(DevFileBucketAuthEnum.PRIVATE)) {
                client.setObjectAcl(bucketName, key, CannedAccessControlList.Private);
            } else if (devFileBucketAuthEnum.equals(DevFileBucketAuthEnum.PUBLIC_READ)) {
                client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicRead);
            } else if (devFileBucketAuthEnum.equals(DevFileBucketAuthEnum.PUBLIC_READ_WRITE)) {
                client.setObjectAcl(bucketName, key, CannedAccessControlList.PublicReadWrite);
            }
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 拷贝文件
     *
     * @param originBucketName 源文件桶
     * @param originFileKey    源文件名称
     * @param newBucketName    新文件桶
     * @param newFileKey       新文件名称
     */
    public static void copyFile(String originBucketName, String originFileKey, String newBucketName,
                                String newFileKey) {
        try {
            initClient();
            client.copyObject(originBucketName, originFileKey, newBucketName, newFileKey);
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 获取文件的下载地址（带鉴权和有效时间的），生成外网地址
     *
     * @param bucketName    文件桶
     * @param key           唯一标示id，例如a.txt, doc/a.txt
     * @param timeoutMillis 时效
     */
    public static String getFileAuthUrl(String bucketName, String key, Long timeoutMillis) {
        initClient();
        GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, key, HttpMethod.GET);
        Date expirationDate = new Date(System.currentTimeMillis() + timeoutMillis);
        request.setExpiration(expirationDate);
        URL url;
        try {
            url = client.generatePresignedUrl(request);
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        }
        return url.toString();
    }

    /**
     * 获取文件的下载地址（永久的，文件必须为公有读），生成外网地址
     *
     * @param bucketName 文件桶
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public static String getFileAuthUrl(String bucketName, String key) {
        try {
            initClient();
            OSSClient ossClient = (OSSClient) client;
            List<String> urlList = StrUtil.split(ossClient.getEndpoint().toString(),
                    StrUtil.COLON + StrUtil.SLASH + StrUtil.SLASH);
            return urlList.get(0) + StrUtil.COLON + StrUtil.SLASH + StrUtil.SLASH + bucketName + StrUtil.DOT
                    + urlList.get(1) + StrUtil.SLASH + key;
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param bucketName 文件桶
     * @param key        唯一标示id，例如a.txt, doc/a.txt
     */
    public static void deleteFile(String bucketName, String key) {
        try {
            initClient();
            client.deleteObject(bucketName, key);
        } catch (OSSException | ClientException e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 根据文件名获取ContentType
     */
    private static String getFileContentType(String key) {
        // 根据文件名获取contentType
        String contentType = "application/octet-stream";
        if (key.contains(".")) {
            contentType = MimetypesFileTypeMap.getDefaultFileTypeMap().getContentType(key);
        }
        return contentType;
    }
}
