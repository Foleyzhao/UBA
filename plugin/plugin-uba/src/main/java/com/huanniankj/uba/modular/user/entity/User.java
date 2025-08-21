package com.huanniankj.uba.modular.user.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 运营用户实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName(value = "UBA_USER", autoResultMap = true)
public class User extends CommonEntity {

    /**
     * id
     */
    @TableId
    @Schema(description = "id")
    private String id;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private String userStatus;

    /**
     * 关联账号（第三方）
     */
    @Schema(description = "账号")
    private String account;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String nickname;

    /**
     * 来源类型
     */
    @Schema(description = "来源类型")
    private String source;

    /**
     * 上次操作时间
     */
    @Schema(description = "上次操作时间")
    private Date lastOperateTime;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String extJson;

}
