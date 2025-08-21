package com.huanniankj.uba.core.manager;

import com.huanniankj.uba.core.event.CleansingFinishEvent;
import com.huanniankj.uba.core.event.EnrichmentFinishEvent;
import com.huanniankj.uba.core.event.EventConverter;
import com.huanniankj.uba.core.event.LogProcessingEvent;
import com.huanniankj.uba.core.event.RawLogEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据清洗服务
 *
 * @author happynewyear
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CleansingService {

    private final KafkaTemplate<String, LogProcessingEvent> kafkaTemplate;

    @Async("kafkaMessageExecutor")
    @KafkaListener(topics = "enrichment_log", groupId = "backend")
    public void onEvent(List<EnrichmentFinishEvent> records, Acknowledgment ack) {
        try {
            for (EnrichmentFinishEvent record : records) {
                process(record);
            }
            // 批量确认
            ack.acknowledge();
        } catch (Exception e) {
            // 处理异常，不确认消息，消息会被重新投递
            log.error("cleansing log error: {}", e.getMessage());
        }
    }

    public void process(EnrichmentFinishEvent event) {
        // 2. 发布数据清洗消息至数据结构化流程
        EventConverter converter = new EventConverter();
        CleansingFinishEvent cleansingFinishEvent = converter.convert(event);
        kafkaTemplate.send("cleansing_log", cleansingFinishEvent);
    }

}
