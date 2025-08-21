package com.huanniankj;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * SpringBoot方式启动类
 *
 * @author happynewyear
 */
@Slf4j
@EnableKafka
@EnableScheduling
@RestController
@SpringBootApplication
public class Application {

    static {
        // 解决druid 日志报错：discard long time none received connection:xxx
        System.setProperty("druid.mysql.usePingMethod", "false");
    }

    /**
     * 主启动函数
     *
     * @param args 启动参数
     */
    @SneakyThrows
    public static void main(String[] args) {
        SpringApplication springApplication = new SpringApplication(Application.class);
        // springApplication.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext configurableApplicationContext = springApplication.run(args);
        Environment env = configurableApplicationContext.getEnvironment();
        log.info("""
                        
                        ----------------------------------------------------------
                        UBA Backend is running! Access URLs:
                        API URL:    http://localhost:{}
                        API Doc:    http://localhost:{}/doc.html
                        ----------------------------------------------------------""", env.getProperty("server.port"),
                env.getProperty("server.port"));
    }

    /**
     * 首页
     *
     * @return 提示信息
     */
    @GetMapping("/")
    public String index() {
        return "UBA BACKEND RUNNING";
    }

}
