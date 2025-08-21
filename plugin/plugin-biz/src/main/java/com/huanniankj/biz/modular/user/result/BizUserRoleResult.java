package com.huanniankj.biz.modular.user.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色选择器结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizUserRoleResult {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

    /**
     * 组织id
     */
    @Schema(description = "机构id")
    private String orgId;

    /**
     * 名称
     */
    @Schema(description = "名称")
    private String name;

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String category;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    private Integer sortCode;
}
