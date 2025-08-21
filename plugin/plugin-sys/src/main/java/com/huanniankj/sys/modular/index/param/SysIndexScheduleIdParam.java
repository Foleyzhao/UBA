package com.huanniankj.sys.modular.index.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 日程Id参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysIndexScheduleIdParam {

    /**
     * 日程id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;
}
