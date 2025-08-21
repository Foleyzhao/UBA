package com.huanniankj.uba.core.event;

import org.springframework.beans.BeanUtils;

import java.time.Instant;

/**
 * 事件转化
 *
 * @author happynewyear
 */
public class EventConverter {

    public PreprocessingFinishEvent convert(RawLogEvent source) {
        PreprocessingFinishEvent target = new PreprocessingFinishEvent();
        // 复制同名属性
        BeanUtils.copyProperties(source, target);
        // 处理特殊字段
        target.setDealTime(Instant.now());
        target.setType("preprocessing");
        return target;
    }

    public EnrichmentFinishEvent convert(PreprocessingFinishEvent source) {
        EnrichmentFinishEvent target = new EnrichmentFinishEvent();
        // 复制同名属性
        BeanUtils.copyProperties(source, target);
        // 处理特殊字段
        target.setDealTime(Instant.now());
        target.setType("enrichment");
        return target;
    }

    public CleansingFinishEvent convert(EnrichmentFinishEvent source) {
        CleansingFinishEvent target = new CleansingFinishEvent();
        // 复制同名属性
        BeanUtils.copyProperties(source, target);
        // 处理特殊字段
        target.setDealTime(Instant.now());
        target.setType("cleansing");
        return target;
    }

    public StructuringFinishEvent convert(CleansingFinishEvent source) {
        StructuringFinishEvent target = new StructuringFinishEvent();
        // 复制同名属性
        BeanUtils.copyProperties(source, target);
        // 处理特殊字段
        target.setDealTime(Instant.now());
        target.setType("structuring");
        return target;
    }

}
