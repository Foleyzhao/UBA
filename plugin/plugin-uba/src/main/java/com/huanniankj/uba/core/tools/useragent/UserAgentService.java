package com.huanniankj.uba.core.tools.useragent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.stereotype.Service;
import ua_parser.Client;
import ua_parser.Parser;


/**
 * UserAgent解析服务
 *
 * @author happynewyear
 */
@Service
public class UserAgentService {

    /**
     * 客户端信息
     */
    @ToString
    @Getter
    @AllArgsConstructor
    public static class UserAgentInfo {

        /**
         * 浏览器名称
         */
        private String browser;

        /**
         * 浏览器主版本号
         */
        private String browserVersion;

        /**
         * 操作系统
         */
        private String os;

        /**
         * 操作系统主版本号
         */
        private String osVersion;

        /**
         * 设备类型
         */
        private String deviceType;
    }

    private final Parser uaParser;

    public UserAgentService() {
        this.uaParser = new Parser();
    }

    /**
     * 解析 UserAgent
     *
     * @param userAgentHeader UserAgent
     * @return 客户端信息
     */
    public UserAgentInfo parseUserAgent(String userAgentHeader) {
        Client client = uaParser.parse(userAgentHeader);
        return new UserAgentInfo(
                client.userAgent.family,
                client.userAgent.major,
                client.os.family,
                client.os.major,
                client.device.family
        );
    }

}
