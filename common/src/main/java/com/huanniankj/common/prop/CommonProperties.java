package com.huanniankj.common.prop;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 通用基础配置
 *
 * @author happynewyear
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "uba.config.common")
public class CommonProperties {

    /**
     * 后端地址
     */
    private String backendUrl;
}
