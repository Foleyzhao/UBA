package com.huanniankj.sys.modular.user.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户未读消息结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysUserMessageResult {

    /**
     * id
     */
    @Schema(description = "主键")
    private String id;

    /**
     * 分类
     */
    @Schema(description = "分类")
    private String category;

    /**
     * 主题
     */
    @Schema(description = "主题")
    private String subject;

    /**
     * 正文
     */
    @Schema(description = "正文")
    private String content;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;

    /**
     * 是否已读
     */
    @Schema(description = "是否已读")
    private Boolean read;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    private String createUser;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    private Date updateTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    private String updateUser;

}
