package com.huanniankj.uba.core.manager;

import com.huanniankj.uba.core.event.EventConverter;
import com.huanniankj.uba.core.event.LogProcessingEvent;
import com.huanniankj.uba.core.event.PreprocessingFinishEvent;
import com.huanniankj.uba.core.event.RawLogEvent;
import com.huanniankj.uba.modular.config.entity.Config;
import com.huanniankj.uba.modular.config.service.ConfigService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 数据预处理服务
 *
 * @author happynewyear
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PreprocessingService {

    private final KafkaTemplate<String, LogProcessingEvent> kafkaTemplate;

    private final KieContainer kieContainer;

    private final ConfigService configService;

    @Async("kafkaMessageExecutor")
    @KafkaListener(topics = "raw_log", groupId = "backend")
    public void onEvent(List<RawLogEvent> records, Acknowledgment ack) {
        try {
            for (RawLogEvent record : records) {
                process(record);
            }
            // 批量确认
            ack.acknowledge();
        } catch (Exception e) {
            // 处理异常，不确认消息，消息会被重新投递
            log.error("preprocessing log error: {}", e.getMessage());
        }
    }

    public void process(RawLogEvent event) {
        EventConverter converter = new EventConverter();
        PreprocessingFinishEvent processedEvent = converter.convert(event);
        // 1. 执行预处理规则
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(processedEvent);
        kieSession.setGlobal("configMap", configService.ubaDefineList().stream()
                .collect(Collectors.toMap(Config::getConfigKey, config -> {
                    return config.getConfigValue().replace(",", "|");
                })));
        kieSession.fireAllRules();
        kieSession.dispose();
        // 2. 发布预处理消息至数增据强流程
        kafkaTemplate.send("preprocessing_log", processedEvent);
    }

}
