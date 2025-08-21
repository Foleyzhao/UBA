package com.huanniankj.uba.core.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访问日志数据结构化完成事件
 *
 * @author happynewyear
 */
@NoArgsConstructor
@Data
public class StructuringFinishEvent extends CleansingFinishEvent {

    /**
     * 数据增强完成
     */
    @JsonProperty("structuring_finish")
    private Boolean structuringFinish = true;

}
