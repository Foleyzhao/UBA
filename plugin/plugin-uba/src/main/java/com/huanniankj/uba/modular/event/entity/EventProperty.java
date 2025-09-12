package com.huanniankj.uba.modular.event.entity;

import com.baomidou.mybatisplus.annotation.FieldStrategy;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 事件属性实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName(value = "UBA_EVENT_PROPERTY", autoResultMap = true)
public class EventProperty extends CommonEntity {

    /**
     * id
     */
    @TableId
    @Schema(description = "id")
    private String id;

    /**
     * 关联事件ID
     */
    @Schema(description = "关联事件ID")
    private String eventId;

    /**
     * 属性键
     */
    @Schema(description = "属性键")
    private String propKey;

    /**
     * 属性值
     */
    @Schema(description = "属性值")
    private String propValue;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    @TableField(insertStrategy = FieldStrategy.ALWAYS, updateStrategy = FieldStrategy.ALWAYS)
    private String extJson;

}
