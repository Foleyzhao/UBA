package com.huanniankj.dev.modular.job.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * 定时任务编辑参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevJobEditParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 名称
     */
    @Schema(description = "名称")
    @NotBlank(message = "name不能为空")
    private String name;

    /**
     * 分类
     */
    @Schema(description = "分类")
    @NotBlank(message = "category不能为空")
    private String category;

    /**
     * 任务类名
     */
    @Schema(description = "任务类名")
    @NotBlank(message = "actionClass不能为空")
    private String actionClass;

    /**
     * cron表达式
     */
    @Schema(description = "cron表达式")
    @NotBlank(message = "cronExpression不能为空")
    private String cronExpression;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    @NotNull(message = "sortCode不能为空")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
