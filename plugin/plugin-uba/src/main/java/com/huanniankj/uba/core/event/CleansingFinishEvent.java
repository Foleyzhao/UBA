package com.huanniankj.uba.core.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

/**
 * 访问日志数据清洗完成事件
 *
 * @author happynewyear
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class CleansingFinishEvent extends EnrichmentFinishEvent {

    /**
     * 数据清洗完成
     */
    @JsonProperty("cleansing_finish")
    private Boolean cleansingFinish = true;

    /**
     * 清洗结果
     */
    @JsonProperty("cleansing_result")
    private Map<String, String> cleansingResult = new HashMap<>();

}
