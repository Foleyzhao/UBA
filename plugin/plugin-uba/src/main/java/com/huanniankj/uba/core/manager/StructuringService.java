package com.huanniankj.uba.core.manager;

import com.huanniankj.uba.core.event.CleansingFinishEvent;
import com.huanniankj.uba.core.event.EventConverter;
import com.huanniankj.uba.core.event.LogProcessingEvent;
import com.huanniankj.uba.core.event.StructuringFinishEvent;
import com.huanniankj.uba.modular.event.entity.Event;
import com.huanniankj.uba.modular.event.entity.EventProperty;
import com.huanniankj.uba.modular.event.service.EventPropertyService;
import com.huanniankj.uba.modular.event.service.EventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final EventService eventService;

    private final EventPropertyService eventPropertyService;

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
        Map<String, String> params = parseQueryString(event.getQueryString());
        // 1. 保存事件
        EventConverter converter = new EventConverter();
        Event result = converter.convert(event);
        String eventId = eventService.add(result).getId();
        // 2. 保存事件属性
        for (Map.Entry<String, String> entry : params.entrySet()) {
            EventProperty eventProperty = new EventProperty();
            eventProperty.setEventId(eventId);
            eventProperty.setPropKey(entry.getKey());
            eventProperty.setPropValue(entry.getValue());
            eventPropertyService.add(eventProperty);
        }
    }

    /**
     * 解析URL查询字符串为键值对映射
     *
     * @param queryString 查询字符串（可以包含或不包含开头的?）
     * @return 包含所有参数的Map
     */
    public static Map<String, String> parseQueryString(String queryString) {
        Map<String, String> params = new HashMap<>();

        if (queryString == null || queryString.isEmpty()) {
            return params;
        }
        // 移除开头的?（如果存在）
        if (queryString.startsWith("?")) {
            queryString = queryString.substring(1);
        }
        // 解码URL编码的字符串
        queryString = URLDecoder.decode(queryString, StandardCharsets.UTF_8);
        // 按&分割参数
        String[] pairs = queryString.split("&");
        for (String pair : pairs) {
            // 按=分割键值对
            String[] keyValue = pair.split("=", 2);

            String key = keyValue[0];
            String value = keyValue.length > 1 ? keyValue[1] : "";

            params.put(key, value);
        }

        return params;
    }

}
