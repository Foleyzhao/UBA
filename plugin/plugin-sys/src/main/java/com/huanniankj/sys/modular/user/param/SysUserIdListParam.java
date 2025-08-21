package com.huanniankj.sys.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 用户id集合参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserIdListParam {

    /**
     * id集合
     */
    @Schema(description = "id集合")
    @NotNull(message = "idList不能为空")
    private List<String> idList;
}
