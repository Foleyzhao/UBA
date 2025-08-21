package com.huanniankj.dev.modular.config.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 配置实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("DEV_CONFIG")
public class DevConfig extends CommonEntity {

    /**
     * id
     */
    @Schema(description = "id")
    private String id;

    /**
     * 配置键
     */
    @Schema(description = "配置键")
    private String configKey;

    /**
     * 配置值
     */
    @Schema(description = "配置值")
    private String configValue;

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String category;

    /**
     * 备注
     */
    @Schema(description = "备注")
    private String remark;

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
