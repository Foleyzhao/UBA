package com.huanniankj.biz.modular.org.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 机构扩展实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("SYS_ORG_EXT")
public class BizOrgExt extends CommonEntity {

    /**
     * id
     */
    @TableId
    @Schema(description = "id")
    private String id;

    /**
     * 机构id
     */
    @Schema(description = "机构id")
    private String orgId;

    /**
     * 来源类别
     */
    @Schema(description = "来源类别")
    private String sourceFromType;

}
