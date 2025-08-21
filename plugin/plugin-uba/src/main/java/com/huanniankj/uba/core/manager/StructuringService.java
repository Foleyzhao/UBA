package com.huanniankj.uba.core.manager;

import com.huanniankj.uba.core.event.CleansingFinishEvent;
import com.huanniankj.uba.core.event.EventConverter;
import com.huanniankj.uba.core.event.LogProcessingEvent;
import com.huanniankj.uba.core.event.PreprocessingFinishEvent;
import com.huanniankj.uba.core.event.StructuringFinishEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 数据结构化服务
 *
 * @author happynewyear
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StructuringService {

    private final KafkaTemplate<String, LogProcessingEvent> kafkaTemplate;

    @Async("kafkaMessageExecutor")
    @KafkaListener(topics = "cleansing_log", groupId = "backend")
    public void onEvent(List<CleansingFinishEvent> records, Acknowledgment ack) {
        try {
            for (CleansingFinishEvent record : records) {
                process(record);
            }
            // 批量确认
            ack.acknowledge();
        } catch (Exception e) {
            // 处理异常，不确认消息，消息会被重新投递
            log.error("structuring log error: {}", e.getMessage());
        }
    }

    public void process(CleansingFinishEvent event) {
        // 2. 发布数据结构化消息
        EventConverter converter = new EventConverter();
        StructuringFinishEvent structuringFinishEvent = converter.convert(event);
        kafkaTemplate.send("structuring_log", structuringFinishEvent);
    }


}
