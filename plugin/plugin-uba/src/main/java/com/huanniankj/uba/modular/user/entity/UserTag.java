package com.huanniankj.uba.modular.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 运营用户标签实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("UBA_USER_TAG")
public class UserTag extends CommonEntity {

    /**
     * id
     */
    @TableId
    @Schema(description = "id")
    private String id;

    /**
     * 用户ID
     */
    @Schema(description = "用户ID")
    private String userId;

    /**
     * 标签ID
     */
    @Schema(description = "标签ID")
    private String tagId;

    /**
     * 添加标签来源
     */
    @Schema(description = "来源")
    private String source;

    /**
     * 添加标签时间
     */
    @Schema(description = "添加标签时间")
    private Date addTagTime;

}
