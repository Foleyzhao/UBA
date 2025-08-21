package com.huanniankj.dev.modular.log.provider;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huanniankj.auth.core.util.StpLoginUserUtil;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.dev.api.DevLogApi;
import com.huanniankj.dev.modular.log.entity.DevLog;
import com.huanniankj.dev.modular.log.enums.DevLogCategoryEnum;
import com.huanniankj.dev.modular.log.service.DevLogService;
import com.huanniankj.dev.modular.log.util.DevLogUtil;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 日志API接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevLogApiProvider implements DevLogApi {

    @Resource
    private DevLogService devLogService;

    @Override
    public void executeLoginLog(String userName) {
        DevLogUtil.executeLoginLog(userName);
    }

    @Override
    public void executeLogoutLog(String userName) {
        DevLogUtil.executeLogoutLog(userName);
    }

    @Override
    public List<JSONObject> currentUserVisLogList() {
        return devLogService.page(CommonPageRequest.defaultPage(), new LambdaQueryWrapper<DevLog>()
                        .select(DevLog::getName, DevLog::getOpUser, DevLog::getOpTime,
                                DevLog::getOpAddress, DevLog::getOpIp)
                        .eq(DevLog::getOpUser, StpLoginUserUtil.getLoginUser().getName())
                        .in(DevLog::getCategory, DevLogCategoryEnum.LOGIN.getValue(),
                                DevLogCategoryEnum.LOGOUT.getValue())
                        .orderByDesc(DevLog::getCreateTime))
                .getRecords().stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }

    @Override
    public List<JSONObject> currentUserOpLogList() {
        return devLogService.page(CommonPageRequest.defaultPage(), new LambdaQueryWrapper<DevLog>()
                        .select(DevLog::getName, DevLog::getOpUser, DevLog::getOpTime,
                                DevLog::getOpAddress, DevLog::getOpIp)
                        .eq(DevLog::getOpUser, StpLoginUserUtil.getLoginUser().getName())
                        .in(DevLog::getCategory, DevLogCategoryEnum.OPERATE.getValue(),
                                DevLogCategoryEnum.EXCEPTION.getValue())
                        .orderByDesc(DevLog::getCreateTime))
                .getRecords().stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }
}
