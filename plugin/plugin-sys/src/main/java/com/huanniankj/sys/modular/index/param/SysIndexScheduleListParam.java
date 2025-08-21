package com.huanniankj.sys.modular.index.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 日程列表查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysIndexScheduleListParam {

    /**
     * 日程日期
     */
    @Schema(description = "日程日期")
    @NotBlank(message = "scheduleDate不能为空")
    private String scheduleDate;
}
