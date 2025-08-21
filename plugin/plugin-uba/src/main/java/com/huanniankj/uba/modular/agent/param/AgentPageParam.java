package com.huanniankj.uba.modular.agent.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 采集器（agent）查询参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AgentPageParam {

    /**
     * 当前页
     */
    @Schema(description = "当前页码")
    private Integer current;

    /**
     * 每页条数
     */
    @Schema(description = "每页条数")
    private Integer size;

    /**
     * 排序字段
     */
    @Schema(description = "排序字段，字段驼峰名称，如：userName")
    private String sortField;

    /**
     * 排序方式
     */
    @Schema(description = "排序方式，升序：ASCEND；降序：DESCEND")
    private String sortOrder;

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
     * 状态
     */
    @Schema(description = "状态")
    private String status;

}
