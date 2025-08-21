package com.huanniankj.dev.modular.file.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 文件分页列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevFilePageParam {

    /**
     * 文件引擎
     */
    @Schema(description = "文件引擎")
    private String engine;

    /**
     * 文件名关键词
     */
    @Schema(description = "文件名关键词")
    private String searchKey;
}
