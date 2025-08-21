package com.huanniankj.biz.modular.index.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 通知公告详情参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizIndexNoticeIdParam {

    /**
     * ID
     */
    @Schema(description = "ID")
    private String id;
}
