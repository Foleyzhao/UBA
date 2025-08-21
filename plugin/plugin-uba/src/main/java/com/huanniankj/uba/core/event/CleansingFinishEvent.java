package com.huanniankj.uba.core.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访问日志数据清洗完成事件
 *
 * @author happynewyear
 */
@NoArgsConstructor
@Data
public class CleansingFinishEvent extends EnrichmentFinishEvent {

    /**
     * 数据清洗完成
     */
    @JsonProperty("cleansing_finish")
    private Boolean cleansingFinish = true;

}
