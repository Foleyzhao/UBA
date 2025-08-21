package com.huanniankj.gen.modular.basic.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 预览代码生成结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class GenBasicPreviewResult {

    /**
     * SQL代码结果集
     */
    @Schema(description = "SQL代码结果集")
    private List<GenBasicCodeResult> genBasicCodeSqlResultList;

    /**
     * 前端代码结果集
     */
    @Schema(description = "前端代码结果集")
    private List<GenBasicCodeResult> genBasicCodeFrontendResultList;

    /**
     * 后端代码结果集
     */
    @Schema(description = "后端代码结果集")
    private List<GenBasicCodeResult> genBasicCodeBackendResultList;

    /**
     * 前端代码结果集
     */
    @Schema(description = "移动端代码结果集")
    private List<GenBasicCodeResult> genBasicCodeMobileResultList;

    @Getter
    @Setter
    public static class GenBasicCodeResult {

        /**
         * 代码文件名称
         */
        @Schema(description = "代码文件名称")
        private String codeFileName;

        /**
         * 代码文件带路径名称
         */
        @Schema(description = "代码文件带路径名称")
        private String codeFileWithPathName;

        /**
         * 代码文件内容
         */
        @Schema(description = "代码文件内容")
        private String codeFileContent;
    }
}
