package com.huanniankj.dev.modular.job.task;

import com.huanniankj.common.timer.CommonTimerTaskRunner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * 定时器的一个示例
 *
 * @author happynewyear
 */
@Slf4j
@Component
public class DevJobTimerTaskRunner implements CommonTimerTaskRunner {

    private int n = 1;

    @Override
    public void action(String extJson) {
        log.info("我是一个定时任务，正在在被执行第{}次", n);
        n = n + 1;
    }
}
