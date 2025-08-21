package com.huanniankj.biz.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 组织列表选择器参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizUserSelectorOrgListParam {

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
     * 父id
     */
    @Schema(description = "父id")
    private String parentId;

    /**
     * 名称关键词
     */
    @Schema(description = "名称关键词")
    private String searchKey;
}
