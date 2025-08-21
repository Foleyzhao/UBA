package com.huanniankj.sys.modular.index.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 站内信列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysIndexMessageListParam {

    /**
     * 条数"
     */
    @Schema(description = "条数")
    private Integer limit;
}
