package com.huanniankj.uba.core.manager;

import com.huanniankj.uba.core.event.CleansingFinishEvent;
import com.huanniankj.uba.core.event.EnrichmentFinishEvent;
import com.huanniankj.uba.core.event.EventConverter;
import com.huanniankj.uba.core.event.LogProcessingEvent;
import com.huanniankj.uba.modular.rule.entity.RuleItem;
import com.huanniankj.uba.modular.rule.enums.RuleCategoryEnum;
import com.huanniankj.uba.modular.rule.enums.RuleItemStatusEnum;
import com.huanniankj.uba.modular.rule.service.RuleItemService;
import com.huanniankj.uba.modular.rule.service.RuleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.regex.Pattern;

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

    private final RuleService ruleService;

    private final RuleItemService ruleItemService;

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
        // 1.数据清洗
        EventConverter converter = new EventConverter();
        CleansingFinishEvent cleansingFinishEvent = converter.convert(event);
        ruleService.list(RuleCategoryEnum.ACCESS_LOG.getValue()).forEach(rule -> {
            List<RuleItem> ruleItemList = ruleItemService.list(rule.getId(), RuleItemStatusEnum.ENABLE.getValue());
            for (RuleItem ruleItem : ruleItemList) {
                try {
                    String dealVale = Pattern.quote((String) PropertyUtils.getProperty(event, rule.getField()));
                    Pattern pattern = Pattern.compile(ruleItem.getContent());
                    if (pattern.matcher(dealVale).matches()) {
                        cleansingFinishEvent.getCleansingResult().put(rule.getName(), ruleItem.getResult());
                        break;
                    }
                } catch (Exception e) {
                    cleansingFinishEvent.setCleansingFinish(false);
                    break;
                }
            }
        });
        // 2.发布数据清洗消息至数据结构化流程
        kafkaTemplate.send("cleansing_log", cleansingFinishEvent);
    }

}
