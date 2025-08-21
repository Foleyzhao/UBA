package com.huanniankj.uba.core.config;

import lombok.extern.slf4j.Slf4j;
import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieModule;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.internal.io.ResourceFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * Drools 配置类
 *
 * @author happynewyear
 */
@Slf4j
@Configuration
public class DroolsConfig {

    private static final String RULES_PATH = "com/huanniankj/uba/accesslog/";

    @Bean
    public KieServices kieServices() {
        return KieServices.get();
    }

    @Bean
    @DependsOn("kieServices")
    public KieContainer kieContainer(KieServices kieServices) throws IOException {
        // 1. 创建 KieFileSystem
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();
        // 2. 加载规则文件
        Resource[] ruleFiles = getRuleFiles();
        for (Resource file : ruleFiles) {
            String filePath = RULES_PATH + file.getFilename();
            log.info("Loading Drools rule file: " + filePath);
            kieFileSystem.write(ResourceFactory.newClassPathResource(filePath, "UTF-8"));
        }
        // 3. 构建 KieModule
        KieBuilder kieBuilder = kieServices.newKieBuilder(kieFileSystem);
        kieBuilder.buildAll();
        // 4. 验证构建结果
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            throw new IllegalStateException("Error building rules: " + results.getMessages());
        }
        // 5. 创建 KieContainer
        KieModule kieModule = kieBuilder.getKieModule();
        KieContainer kieContainer = kieServices.newKieContainer(kieModule.getReleaseId());
        return kieContainer;
    }

    /**
     * 获取规则文件
     *
     * @return 规则文件
     * @throws IOException IO异常
     */
    private Resource[] getRuleFiles() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        return resourcePatternResolver.getResources("classpath*:" + RULES_PATH + "**/*.drl");
    }

}
