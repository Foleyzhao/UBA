package com.huanniankj.uba.modular.tag.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 标签Id参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class TagIdParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;

}
