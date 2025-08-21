package com.huanniankj.client.core.timer;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.huanniankj.client.modular.user.service.ClientUserService;
import com.huanniankj.common.timer.CommonTimerTaskRunner;

/**
 * 通知用户密码即将到期定时类
 *
 * @author happynewyear
 */
@Slf4j
@Component
public class ClientUserPasswordExpiredNoticeTimerTaskRunner implements CommonTimerTaskRunner {

    @Resource
    private ClientUserService clientUserService;

    @Override
    public void action(String extJson) {
        // 通知用户密码即将到期
        clientUserService.noticeUserPasswordAboutToExpired();
    }
}
