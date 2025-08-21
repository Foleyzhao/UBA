package com.huanniankj.biz.modular.org.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 人员选择器参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizOrgSelectorUserParam {

    /**
     * 当前页
     */
    @Schema(description = "当前页码")
    private Integer current;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数")
    private Integer size;

    /**
     * 机构id
     */
    @Schema(description = "机构id")
    private String orgId;

    /**
     * 姓名关键词
     */
    @Schema(description = "姓名关键词")
    private String searchKey;
}
