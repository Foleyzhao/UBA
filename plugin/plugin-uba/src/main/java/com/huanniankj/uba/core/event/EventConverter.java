package com.huanniankj.uba.core.event;

import com.huanniankj.uba.modular.event.entity.Event;
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

    public Event convert(CleansingFinishEvent source) {
        Event target = new Event();
        target.setEventTime(source.getDate());
        target.setEventType("page_view");
        // 处理原始日志数据
        target.setIpAddress(source.getRemoteAddr());
        target.setReferer(source.getHttpReferer());
        target.setUserAgent(source.getHttpUserAgent());
        // 处理数据增强阶段数据
        target.setCountry(source.getCountryInfo());
        target.setCity(source.getCityInfo());
        target.setOs(source.getOsInfo());
        target.setBrowser(source.getBrowserInfo());
        target.setDeviceType(source.getDeviceTypeInfo());
        // 处理数据清洗阶段数据
        target.setUserId("unknown");

        return target;
    }

}
