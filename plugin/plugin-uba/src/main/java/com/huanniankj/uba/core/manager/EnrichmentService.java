package com.huanniankj.uba.core.manager;

import com.huanniankj.uba.core.event.EnrichmentFinishEvent;
import com.huanniankj.uba.core.event.EventConverter;
import com.huanniankj.uba.core.event.LogProcessingEvent;
import com.huanniankj.uba.core.event.PreprocessingFinishEvent;
import com.huanniankj.uba.core.tools.geoip.GeoIpService;
import com.huanniankj.uba.core.tools.useragent.UserAgentService;
import com.maxmind.geoip2.exception.GeoIp2Exception;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * 数据增强服务
 *
 * @author happynewyear
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class EnrichmentService {

    private final KafkaTemplate<String, LogProcessingEvent> kafkaTemplate;

    private final GeoIpService geoIpService;

    private final UserAgentService userAgentService;

    @Async("kafkaMessageExecutor")
    @KafkaListener(topics = "preprocessing_log", groupId = "backend")
    public void onEvent(List<PreprocessingFinishEvent> records, Acknowledgment ack) {
        try {
            for (PreprocessingFinishEvent record : records) {
                process(record);
            }
            // 批量确认
            ack.acknowledge();
        } catch (Exception e) {
            // 处理异常，不确认消息，消息会被重新投递
            log.error("enrichment log error: {}", e.getMessage());
        }
    }

    public void process(PreprocessingFinishEvent event) {
        // 1.忽略未通过预处理事件
        if (!event.getPreprocessPass()) {
            return;
        }
        EventConverter converter = new EventConverter();
        EnrichmentFinishEvent enrichmentFinishEvent = converter.convert(event);
        // 2.进行数据增强
        // 2.1.城市信息增强
        try {
            enrichmentFinishEvent.setCityInfo(geoIpService.getCityInfo(event.getRemoteAddr()).toString());
        } catch (IOException | GeoIp2Exception e) {
            enrichmentFinishEvent.setCityInfo("unknown");
        }
        // 2.2.用户代理信息增强
        try {
            enrichmentFinishEvent.setAgentInfo(userAgentService.parseUserAgent(event.getHttpUserAgent()).toString());
        } catch (Exception e) {
            enrichmentFinishEvent.setAgentInfo("unknown");
        }
        // 3. 发布数增据强消息至数据清洗流程
        kafkaTemplate.send("enrichment_log", enrichmentFinishEvent);
    }

}
