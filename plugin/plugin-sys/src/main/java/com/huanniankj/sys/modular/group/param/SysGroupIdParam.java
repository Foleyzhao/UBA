package com.huanniankj.sys.modular.group.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户组Id参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysGroupIdParam {

    /**
     * 主键
     */
    @Schema(description = "主键", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "id不能为空")
    private String id;

}
