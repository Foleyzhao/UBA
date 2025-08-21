package com.huanniankj.common.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.util.URLUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;

/**
 * 文件下载工具类，使用本类前，对参数校验的异常使用CommonResponseUtil.renderError()方法进行渲染
 *
 * @author happynewyear
 */
@Slf4j
public class CommonDownloadUtil {

    /**
     * 下载文件
     *
     * @param file     要下载的文件
     * @param response 响应
     */
    public static void download(File file, HttpServletResponse response) {
        download(file.getName(), FileUtil.readBytes(file), response);
    }

    /**
     * 下载文件
     */
    public static void download(String fileName, byte[] fileBytes, HttpServletResponse response) {
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + URLUtil.encode(fileName));
            response.addHeader("Content-Length", "" + fileBytes.length);
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setContentType("application/octet-stream;charset=UTF-8");
            IoUtil.write(response.getOutputStream(), true, fileBytes);
        } catch (IOException e) {
            log.error(">>> 文件下载异常：", e);
        }
    }
}
