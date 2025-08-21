package com.huanniankj.dev.modular.job.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevJobListParam {

    /**
     * 任务分类
     */
    @Schema(description = "任务分类")
    private String category;

    /**
     * 名称关键词
     */
    @Schema(description = "名称关键词")
    private String searchKey;

    /**
     * 任务状态
     */
    @Schema(description = "任务状态")
    private String jobStatus;
}
