package com.huanniankj.sys.core.timer;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import com.huanniankj.common.timer.CommonTimerTaskRunner;
import com.huanniankj.sys.modular.user.service.SysUserService;

/**
 * 通知用户密码即将到期定时类
 *
 * @author happynewyear
 */
@Slf4j
@Component
public class SysUserPasswordExpiredNoticeTimerTaskRunner implements CommonTimerTaskRunner {

    @Resource
    private SysUserService sysUserService;

    @Override
    public void action(String extJson) {
        // 通知用户密码即将到期
        sysUserService.noticeUserPasswordAboutToExpired();
    }
}
