package com.huanniankj.common.util;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.system.SystemUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.StringTokenizer;

/**
 * 通用获取当前网速工具类
 *
 * @author happynewyear
 */
@Slf4j
public class CommonNetWorkInfoUtil {

    /**
     * 网速测速时间2s
     */
    private static final int SLEEP_SECONDS = 2;

    /**
     * 获取网络上下行速率，格式{"UP": "123KB/S, "DOWN": "345KB/S"}
     */
    public static Map<String, String> getNetworkUpRate() {
        Map<String, String> result = new HashMap<>();
        Process pro = null;
        Runtime r = Runtime.getRuntime();
        BufferedReader input = null;
        try {
            boolean isWindows = SystemUtil.getOsInfo().isWindows();
            String command = isWindows ? "netstat -e" : "ifconfig";
            pro = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            long[] result1 = readInLine(input, isWindows);
            Thread.sleep(SLEEP_SECONDS * 1000);
            pro.destroy();
            input.close();
            pro = r.exec(command);
            input = new BufferedReader(new InputStreamReader(pro.getInputStream()));
            long[] result2 = readInLine(input, isWindows);
            String upSpeed = FileUtil.readableFileSize(Convert.toLong(NumberUtil
                    .div(NumberUtil.sub(result2[1], result1[1]), SLEEP_SECONDS)));
            String downSpeed = FileUtil.readableFileSize(Convert.toLong(NumberUtil
                    .div(NumberUtil.sub(result2[0], result1[0]), SLEEP_SECONDS)));
            result.put("UP", upSpeed + (upSpeed.endsWith("B") ? "/S" : "B/S"));
            result.put("DOWN", downSpeed + (downSpeed.endsWith("B") ? "/S" : "B/S"));
        } catch (Exception e) {
            log.info(">>> 网络测速失败：", e);
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    log.info(">>> 网络测速失败：", e);
                }
            }
            Optional.ofNullable(pro).ifPresent(Process::destroy);
        }
        return result;
    }

    private static String formatNumber(double f) {
        return new Formatter().format("%.2f", f).toString();
    }

    private static long[] readInLine(BufferedReader input, boolean isWindows) {
        long[] arr = new long[2];
        StringTokenizer tokenStat;
        try {
            if (isWindows) {
                // 获取windows环境下的网口上下行速率
                input.readLine();
                input.readLine();
                input.readLine();
                input.readLine();
                tokenStat = new StringTokenizer(input.readLine());
                tokenStat.nextToken();
                arr[0] = Long.parseLong(tokenStat.nextToken());
                arr[1] = Long.parseLong(tokenStat.nextToken());
            } else {
                // 获取linux环境下的网口上下行速率
                long rx = 0, tx = 0;
                String line = null;
                //RX packets:4171603 errors:0 dropped:0 overruns:0 frame:0
                //TX packets:4171603 errors:0 dropped:0 overruns:0 carrier:0
                while ((line = input.readLine()) != null) {
                    if (line.contains("RX packets")) {
                        rx += Long.parseLong(line.substring(line.indexOf("RX packets") + 11, line.indexOf(" ",
                                line.indexOf("RX packets") + 11)));
                    } else if (line.contains("TX packets")) {
                        tx += Long.parseLong(line.substring(line.indexOf("TX packets") + 11, line.indexOf(" ",
                                line.indexOf("TX packets") + 11)));
                    }
                }
                arr[0] = rx;
                arr[1] = tx;
            }
        } catch (Exception e) {
            log.error(">>> 网络测速异常：", e);
        }
        return arr;
    }
}
