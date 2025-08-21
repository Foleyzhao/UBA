package com.huanniankj.client.modular.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * C端用户扩展实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("CLIENT_USER_EXT")
public class ClientUserExt extends CommonEntity {

    /**
     * id
     */
    @TableId
    @Schema(description = "id")
    private String id;

    /**
     * 用户id
     */
    @Schema(description = "用户id")
    private String userId;

    /**
     * 来源类别
     */
    @Schema(description = "来源类别")
    private String sourceFromType;

    /**
     * 密码修改日期
     */
    @Schema(description = "密码修改日期")
    private Date passwordUpdateTime;

}
