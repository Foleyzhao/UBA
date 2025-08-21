package com.huanniankj.common.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fhs.core.trans.anno.Trans;
import com.fhs.core.trans.constant.TransType;
import com.fhs.core.trans.vo.TransPojo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用基础字段实体：创建时间、创建人、修改时间、修改人，需要此通用字段的实体可继承此类，
 * 继承此类要求数据表有对应的字段
 *
 * @author happynewyear
 */
@Getter
@Setter
public class CommonEntity implements Serializable, TransPojo {

    /**
     * 删除标志
     */
    @JsonIgnore
    @TableLogic
    @Schema(description = "删除标志")
    @TableField(fill = FieldFill.INSERT)
    private String deleteFlag;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @TableField(fill = FieldFill.INSERT)
    @Trans(type = TransType.RPC, targetClassName = "com.huanniankj.sys.modular.user.entity.SysUser",
            fields = "name", alias = "createUser", ref = "createUserName")
    private String createUser;

    /**
     * 创建人名称
     */
    @Schema(description = "创建人名称")
    @TableField(exist = false)
    private String createUserName;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    @Trans(type = TransType.RPC, targetClassName = "com.huanniankj.sys.modular.user.entity.SysUser",
            fields = "name", alias = "updateUser", ref = "updateUserName")
    private String updateUser;

    /**
     * 更新人名称
     */
    @Schema(description = "更新人名称")
    @TableField(exist = false)
    private String updateUserName;

}
