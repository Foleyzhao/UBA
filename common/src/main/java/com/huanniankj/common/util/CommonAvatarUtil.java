package com.huanniankj.common.util;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.util.RandomUtil;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.regex.Pattern;

/**
 * 通用头像工具类，生成文字头像
 *
 * @author happynewyear
 */
public class CommonAvatarUtil {

    /**
     * 中文正则
     */
    public static final Pattern CHINESE_PATTERN = Pattern.compile("[\\u4e00-\\u9fa5]+");

    /**
     * 预置颜色
     */
    private static final int[] BEAUTIFUL_COLORS = {
            0x7265E6,
            0xFCBF00,
            0x00A2AE,
            0xF56A00,
            0x1890FF,
            0x606D80
    };

    /**
     * 此工具类不可被实例化
     */
    private CommonAvatarUtil() {
    }

    /**
     * 绘制字体头像，如果是英文名，只显示首字母大写，
     * 如果是中文名，只显示最后两个字
     * 返回图片base64
     */
    public static String generateImg(final String name) {
        final int width = 100;
        final int height = 100;
        final int nameLength = name.length();
        // 如果用户输入的姓名少于等于2个字符，不用截取
        String nameWritten = name;
        if (nameLength > 2) {
            // 如果用户输入的姓名大于等于3个字符，截取后面两位
            if (isChinese(StringUtils.substring(name, 0, 1))) {
                // 截取倒数两位汉字
                nameWritten = name.substring(nameLength - 2);
            } else {
                // 截取前面的两个英文字母
                nameWritten = StringUtils.substring(name, 0, 1).toUpperCase();
            }
        }
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = (Graphics2D) bufferedImage.getGraphics();
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2.setBackground(getRandomColor());
        g2.clearRect(0, 0, width, height);
        g2.setPaint(Color.WHITE);
        // 两个字及以上
        if(nameWritten.length() >= 2) {
            g2.setFont(new Font("微软雅黑", Font.BOLD, 30));
            String firstWritten = StringUtils.substring(nameWritten, 0, 1);
            String secondWritten = StringUtils.substring(nameWritten, 0, 2);
            // 两个中文 如 言曌
            if (isChinese(firstWritten) && isChinese(secondWritten)) {
                g2.drawString(nameWritten, 20, 60);
            }
            // 首中次英 如 罗Q
            else if (isChinese(firstWritten) && !isChinese(secondWritten)) {
                g2.drawString(nameWritten, 27, 60);
                // 首英 如 AB
            } else {
                nameWritten = nameWritten.substring(0,1);
            }
        }
        // 一个字
        if(nameWritten.length() == 1) {
            // 中文
            if(isChinese(nameWritten)) {
                g2.setFont(new Font("微软雅黑", Font.PLAIN, 50));
                g2.drawString(nameWritten, 25, 70);
            } else {
                g2.setFont(new Font("微软雅黑", Font.PLAIN, 55));
                g2.drawString(nameWritten.toUpperCase(), 33, 67);
            }
        }
        return ImgUtil.toBase64DataUri(bufferedImage, "jpg");
    }

    /**
     * 获得随机颜色
     */
    private static Color getRandomColor() {
        return new Color(BEAUTIFUL_COLORS[RandomUtil.randomInt(BEAUTIFUL_COLORS.length)]);
    }

    /**
     * 判断字符串是否为中文
     */
    private static boolean isChinese(String str) {
        return CHINESE_PATTERN.matcher(str).find();
    }
}
