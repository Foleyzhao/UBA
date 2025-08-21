package com.huanniankj.dev.modular.message.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 站内信列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevMessageListParam {

    /**
     * 接收人id集合
     */
    @Schema(description = "接收人id集合")
    private List<String> receiverIdList;

    /**
     * 条数"
     */
    @Schema(description = "条数")
    private Integer limit;
}
