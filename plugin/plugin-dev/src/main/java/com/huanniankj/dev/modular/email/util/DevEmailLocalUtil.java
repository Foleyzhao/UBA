package com.huanniankj.dev.modular.email.util;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.extra.mail.MailAccount;
import cn.hutool.extra.mail.MailUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.dev.api.DevConfigApi;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 本地邮件工具类
 *
 * @author happynewyear
 */
@Slf4j
public class DevEmailLocalUtil {

    private static MailAccount mailAccount;

    private static final String SYSTEM_EMAIL_LOCAL_FROM_KEY = "SYSTEM_EMAIL_LOCAL_FROM";

    private static final String SYSTEM_EMAIL_LOCAL_PASSWORD_KEY = "SYSTEM_EMAIL_LOCAL_PASSWORD";

    private static final String SYSTEM_EMAIL_LOCAL_SMTP_HOST_KEY = "SYSTEM_EMAIL_LOCAL_SMTP_HOST";

    private static final String SYSTEM_EMAIL_LOCAL_SMTP_PORT_KEY = "SYSTEM_EMAIL_LOCAL_SMTP_PORT";

    private static final String SYSTEM_EMAIL_LOCAL_AUTH_KEY = "SYSTEM_EMAIL_LOCAL_AUTH";

    private static final String SYSTEM_EMAIL_LOCAL_SSL_ENABLE_KEY = "SYSTEM_EMAIL_LOCAL_SSL_ENABLE";

    private static final String SYSTEM_EMAIL_LOCAL_STARTTLS_ENABLE_KEY = "SYSTEM_EMAIL_LOCAL_STARTTLS_ENABLE";

    /**
     * 初始化操作的客户端
     */
    private static void initClient() {

        DevConfigApi devConfigApi = SpringUtil.getBean(DevConfigApi.class);

        /* 发件人（必须正确，否则发送失败） */
        String from = devConfigApi.getValueByKey(SYSTEM_EMAIL_LOCAL_FROM_KEY);

        if (ObjectUtil.isEmpty(from)) {
            throw new CommonException("本地邮件操作客户端未正确配置：from为空");
        }

        /* 密码（注意，某些邮箱需要为SMTP服务单独设置授权码，详情查看相关帮助） */
        String pass = devConfigApi.getValueByKey(SYSTEM_EMAIL_LOCAL_PASSWORD_KEY);

        if (ObjectUtil.isEmpty(pass)) {
            throw new CommonException("本地邮件操作客户端未正确配置：pass为空");
        }

        mailAccount = new MailAccount();
        mailAccount.setFrom(from);
        mailAccount.setPass(pass);

        /* SMTP服务器域名 */
        String host = devConfigApi.getValueByKey(SYSTEM_EMAIL_LOCAL_SMTP_HOST_KEY);
        if (ObjectUtil.isNotEmpty(host)) {
            mailAccount.setHost(host);
        }
        /* SMTP服务端口 */
        String port = devConfigApi.getValueByKey(SYSTEM_EMAIL_LOCAL_SMTP_PORT_KEY);
        if (ObjectUtil.isNotEmpty(port)) {
            mailAccount.setPort(Integer.parseInt(port));
        }
        /* 是否需要用户名密码验证 */
        String auth = devConfigApi.getValueByKey(SYSTEM_EMAIL_LOCAL_AUTH_KEY);
        mailAccount.setAuth(Objects.equals(auth, "true"));

        /* 是否使用SSL安全连接 */
        String sslEnable = devConfigApi.getValueByKey(SYSTEM_EMAIL_LOCAL_SSL_ENABLE_KEY);
        mailAccount.setSslEnable(Objects.equals(sslEnable, "true"));

        /* 是否使用STARTTLS安全连接 */
        String starttlsEnable = devConfigApi.getValueByKey(SYSTEM_EMAIL_LOCAL_STARTTLS_ENABLE_KEY);
        mailAccount.setStarttlsEnable(Objects.equals(starttlsEnable, "true"));
    }

    public static MailAccount getClient() {
        initClient();
        return mailAccount;
    }

    /**
     * 发送纯文本邮件
     *
     * @param tos     收件人邮箱，逗号拼接
     * @param subject 邮件主题
     * @param content 邮件内容
     * @param files   附件列表
     * @return 发送成功的回执id
     */
    public static String sendTextEmail(String tos, String subject, String content, List<File> files) {
        try {
            initClient();
            return MailUtil.send(mailAccount, tos, subject, content, false, ArrayUtil.toArray(files, File.class));
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }

    /**
     * 发送HTML邮件
     *
     * @param tos      收件人邮箱列表，逗号拼接
     * @param subject  邮件主题
     * @param content  邮件内容
     * @param imageMap – 图片与占位符，占位符格式为cid:$IMAGE_PLACEHOLDER
     * @param files    附件列表
     * @return 发送成功的回执id
     */
    public static String sendHtmlEmail(String tos, String subject, String content, Map<String, InputStream> imageMap,
                                       List<File> files) {
        try {
            initClient();
            return MailUtil.send(mailAccount, tos, subject, content, imageMap, true,
                    ArrayUtil.toArray(files, File.class));
        } catch (Exception e) {
            throw new CommonException(e.getMessage());
        }
    }
}
