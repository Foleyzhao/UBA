package com.huanniankj.auth.modular.third.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import com.huanniankj.common.pojo.CommonEntity;

/**
 * 第三方登录实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("AUTH_THIRD_USER")
public class AuthThirdUser extends CommonEntity {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

    /**
     * 三方用户id
     */
    @Schema(description = "三方用户id")
    private String thirdId;

    /**
     * 系统用户id
     */
    @Schema(description = "系统用户id")
    private String userId;

    /**
     * 头像
     */
    @Schema(description = "头像")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String avatar;

    /**
     * 姓名
     */
    @Schema(description = "姓名")
    private String name;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String nickname;

    /**
     * 性别
     */
    @Schema(description = "性别")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String gender;

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String category;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String extJson;
}
