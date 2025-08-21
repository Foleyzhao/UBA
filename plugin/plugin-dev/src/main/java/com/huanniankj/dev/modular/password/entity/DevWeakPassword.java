package com.huanniankj.dev.modular.password.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 弱密码库实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("DEV_WEAK_PASSWORD")
public class DevWeakPassword extends CommonEntity {

    /**
     * id
     */
    @TableId
    @Schema(description = "id")
    private String id;

    /**
     * 密码
     */
    @Schema(description = "密码")
    private String password;

}
