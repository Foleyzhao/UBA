package com.huanniankj.dev.modular.push.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 消息推送实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("DEV_PUSH")
public class DevPush extends CommonEntity {

    /**
     * id
     */
    @Schema(description = "主键")
    private String id;

    /**
     * 消息引擎
     */
    @Schema(description = "消息引擎")
    private String engine;

    /**
     * 消息类别
     */
    @Schema(description = "消息类别")
    private String type;

    /**
     * 消息标题
     */
    @Schema(description = "消息标题")
    private String title;

    /**
     * 消息内容
     */
    @Schema(description = "消息内容")
    private String content;

    /**
     * 回执信息
     */
    @Schema(description = "回执信息")
    private String receiptInfo;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;
}
