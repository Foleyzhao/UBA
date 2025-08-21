package com.huanniankj.dev.modular.relation.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

/**
 * 关系实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("DEV_RELATION")
public class DevRelation {

    /**
     * id
     */
    private String id;

    /**
     * 对象id
     */
    private String objectId;

    /**
     * 目标id
     */
    private String targetId;

    /**
     * 分类
     */
    private String category;

    /**
     * 扩展信息
     */
    private String extJson;
}
