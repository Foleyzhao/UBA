package com.huanniankj.uba.modular.agent.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.huanniankj.common.pojo.CommonEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 采集器（agent）实体
 *
 * @author happynewyear
 */
@Getter
@Setter
@TableName("UBA_AGENT")
public class Agent extends CommonEntity {

    /**
     * id
     */
    @Schema(description = "主键")
    private String id;

    /**
     * agent ID
     */
    @Schema(description = "agent ID")
    private String agentId;

    /**
     * 状态
     */
    @Schema(description = "状态")
    private String status;

    /**
     * OS信息
     */
    @Schema(description = "OS信息")
    private String os;

    /**
     * 内网IP
     */
    @Schema(description = "内网IP")
    private String hostIp;

    /**
     * 外网IP
     */
    @Schema(description = "外网IP")
    private String publicIp;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;

}
