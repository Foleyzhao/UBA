package com.huanniankj.uba.modular.dict.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据处理字典Id参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DictIdParam {

    /**
     * id
     */
    @Schema(description = "id")
    @NotBlank(message = "id不能为空")
    private String id;
}
