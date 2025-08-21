package com.huanniankj.sys.modular.org.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 组织扩展实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("SYS_ORG_EXT")
public class SysOrgExt extends CommonEntity {

    /**
     * id
     */
    @TableId
    @Schema(description = "id")
    private String id;

    /**
     * 组织id
     */
    @Schema(description = "组织id")
    private String orgId;

    /**
     * 来源类别
     */
    @Schema(description = "来源类别")
    private String sourceFromType;

}
