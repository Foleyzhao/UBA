package com.huanniankj.sys.modular.role.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 角色选择器参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysRoleSelectorRoleParam {

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
     * 角色分类
     */
    @Schema(description = "角色分类")
    private String category;

    /**
     * 名称关键词
     */
    @Schema(description = "名称关键词")
    private String searchKey;

    /**
     * 数据范围信息
     */
    @Schema(description = "数据范围信息")
    private List<String> dataScopeList;

    /**
     * 是否排除超管
     */
    @Schema(description = "是否排除超管")
    private boolean excludeSuperAdmin = false;
}
