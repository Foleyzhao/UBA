package com.huanniankj.biz.modular.index.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 通知公告列表参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizIndexNoticeListParam {

    /**
     * 条数"
     */
    @Schema(description = "条数")
    private Integer limit;
}
