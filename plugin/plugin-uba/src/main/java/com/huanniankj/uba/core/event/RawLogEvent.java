package com.huanniankj.uba.core.event;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 访问日志数原始日志事件
 *
 * @author happynewyear
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class RawLogEvent extends LogProcessingEvent {

}
