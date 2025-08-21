package com.huanniankj.biz.modular.group.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户组授权用户参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizGroupGrantUserParam {

    /**
     * 用户组id
     */
    @Schema(description = "用户组id", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "id不能为空")
    private String id;

    /**
     * 授权用户信息
     */
    @Schema(description = "授权用户信息", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotNull(message = "grantInfoList不能为空")
    private List<String> grantInfoList;
}
