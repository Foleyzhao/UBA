package com.huanniankj.uba.core.event;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 访问日志数据预处理完成事件
 *
 * @author happynewyear
 */
@NoArgsConstructor
@Data
public class PreprocessingFinishEvent extends RawLogEvent {

    /**
     * 预处理是否通过
     */
    @JsonProperty("preprocess_pass")
    private Boolean preprocessPass = true;

    /**
     * 预处理信息
     */
    @JsonProperty("preprocess_message")
    private String preprocessMessage = "";

}
