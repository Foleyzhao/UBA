package com.huanniankj.sys.modular.user.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 用户职位信息
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserPositionResult {

    /**
     * 组织id
     */
    @Schema(description = "组织id")
    private String orgId;

    /**
     * 组织名称
     */
    @Schema(description = "组织名称")
    private String orgName;

    /**
     * 职位id
     */
    @Schema(description = "职位id")
    private String positionId;

    /**
     * 职位名称
     */
    @Schema(description = "职位名称")
    private String positionName;

    /**
     * 组织分类
     */
    @Schema(description = "组织分类")
    private String category;

    /**
     * 职位类型
     */
    @Schema(description = "职位类型")
    private String type;

}
