package com.huanniankj.uba.core.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

/**
 * Kafka Topic配置
 *
 * @author happynewyear
 */
@Configuration
public class KafkaTopic {

    /**
     * 原始数据Topic
     *
     * @return 原始数据Topic
     */
    @Bean
    public NewTopic rawTopic() {
        return TopicBuilder.name("raw_log")
                .partitions(3)
                .replicas(1)
                // 1天
                .config(TopicConfig.RETENTION_MS_CONFIG, "604800000")
                .build();
    }

    /**
     * 数据预处理Topic
     *
     * @return 数据预处理Topic
     */
    @Bean
    public NewTopic preprocessingTopic() {
        return TopicBuilder.name("preprocessing_log")
                .partitions(3)
                .replicas(1)
                // 1天
                .config(TopicConfig.RETENTION_MS_CONFIG, "604800000")
                .build();
    }

    /**
     * 数据增强Topic
     *
     * @return 数据增强Topic
     */
    @Bean
    public NewTopic enrichmentTopic() {
        return TopicBuilder.name("enrichment_log")
                .partitions(3)
                .replicas(1)
                // 1天
                .config(TopicConfig.RETENTION_MS_CONFIG, "604800000")
                .build();
    }

    /**
     * 数据清洗Topic
     *
     * @return 数据清洗Topic
     */
    @Bean
    public NewTopic cleansingTopic() {
        return TopicBuilder.name("cleansing_log")
                .partitions(3)
                .replicas(1)
                // 1天
                .config(TopicConfig.RETENTION_MS_CONFIG, "604800000")
                .build();
    }

    /**
     * 数据结构化Topic
     *
     * @return 数据结构化Topic
     */
    @Bean
    public NewTopic structuringTopic() {
        return TopicBuilder.name("structuring_log")
                .partitions(3)
                .replicas(1)
                // 1天
                .config(TopicConfig.RETENTION_MS_CONFIG, "604800000")
                .build();
    }

}
