package com.huanniankj.sys.modular.index.result;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 访问日志结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysIndexVisLogListResult {

    /**
     * id
     */
    @Schema(description = "主键")
    private String id;

    /**
     * 日志分类
     */
    @Schema(description = "日志分类")
    private String category;

    /**
     * 日志名称
     */
    @Schema(description = "日志名称")
    private String name;

    /**
     * 执行状态
     */
    @Schema(description = "执行状态")
    private String exeStatus;

    /**
     * 具体消息
     */
    @Schema(description = "具体消息")
    private String exeMessage;

    /**
     * 操作ip
     */
    @Schema(description = "操作ip")
    private String opIp;

    /**
     * 操作地址
     */
    @Schema(description = "操作地址")
    private String opAddress;

    /**
     * 操作浏览器
     */
    @Schema(description = "操作浏览器")
    private String opBrowser;

    /**
     * 操作系统
     */
    @Schema(description = "操作系统")
    private String opOs;

    /**
     * 操作时间
     */
    @Schema(description = "操作时间")
    private Date opTime;

    /**
     * 操作人姓名
     */
    @Schema(description = "操作人姓名")
    private String opUser;

    /**
     * 签名数据
     */
    @Schema(description = "签名数据")
    private String signData;

    /**
     * 创建时间
     */
    @Schema(description = "创建时间")
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @Schema(description = "创建人")
    @TableField(fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 更新时间
     */
    @Schema(description = "更新时间")
    @TableField(fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 更新人
     */
    @Schema(description = "更新人")
    @TableField(fill = FieldFill.UPDATE)
    private String updateUser;
}
