package com.huanniankj.uba.modular.agent.enums;

import lombok.Getter;

/**
 * 采集器（agent）状态枚举
 *
 * @author happynewyear
 */
@Getter
public enum AgentStatusEnum {

    /**
     * 在线
     */
    ON_LINE("ON_LINE"),

    /**
     * 离线
     */
    OFF_LINE("OFF_LINE");


    private final String value;

    AgentStatusEnum(String value) {
        this.value = value;
    }
    
}
