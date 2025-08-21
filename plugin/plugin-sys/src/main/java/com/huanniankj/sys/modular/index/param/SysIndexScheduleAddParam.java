package com.huanniankj.sys.modular.index.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 日程添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysIndexScheduleAddParam {

    /**
     * 日程日期
     */
    @Schema(description = "日程日期")
    @NotBlank(message = "scheduleDate不能为空")
    private String scheduleDate;

    /**
     * 日程时间
     */
    @Schema(description = "日程时间")
    @NotBlank(message = "scheduleTime不能为空")
    private String scheduleTime;

    /**
     * 日程内容
     */
    @Schema(description = "日程内容")
    @NotBlank(message = "scheduleContent不能为空")
    private String scheduleContent;

    /**
     * 用户id
     */
    @Schema(description = "用户id", hidden = true)
    private String scheduleUserId;

    /**
     * 用户姓名
     */
    @Schema(description = "用户姓名", hidden = true)
    private String scheduleUserName;
}
