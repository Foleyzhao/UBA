package com.huanniankj.sys.modular.role.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户选择器参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysRoleSelectorUserParam {

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
     * 组织id
     */
    @Schema(description = "组织id")
    private String orgId;

    /**
     * 姓名关键词
     */
    @Schema(description = "姓名关键词")
    private String searchKey;
}
