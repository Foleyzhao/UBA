package com.huanniankj.sys.modular.index.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.huanniankj.sys.modular.index.param.SysIndexMessageIdParam;
import com.huanniankj.sys.modular.index.param.SysIndexMessageListParam;
import com.huanniankj.sys.modular.index.param.SysIndexScheduleAddParam;
import com.huanniankj.sys.modular.index.param.SysIndexScheduleIdParam;
import com.huanniankj.sys.modular.index.param.SysIndexScheduleListParam;
import com.huanniankj.sys.modular.index.result.SysBizDataCountResult;
import com.huanniankj.sys.modular.index.result.SysIndexMessageDetailResult;
import com.huanniankj.sys.modular.index.result.SysIndexMessageListResult;
import com.huanniankj.sys.modular.index.result.SysIndexOpLogListResult;
import com.huanniankj.sys.modular.index.result.SysIndexScheduleListResult;
import com.huanniankj.sys.modular.index.result.SysIndexVisLogListResult;
import com.huanniankj.sys.modular.index.result.SysOpDataCountResult;
import com.huanniankj.sys.modular.index.result.SysToolDataCountResult;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;
import com.huanniankj.auth.api.AuthApi;
import com.huanniankj.auth.core.pojo.SaBaseLoginUser;
import com.huanniankj.auth.core.util.StpLoginUserUtil;
import com.huanniankj.common.sse.CommonSseParam;
import com.huanniankj.dev.api.DevApi;
import com.huanniankj.dev.api.DevLogApi;
import com.huanniankj.dev.api.DevMessageApi;
import com.huanniankj.dev.api.DevSseApi;
import com.huanniankj.sys.modular.index.service.SysIndexService;
import com.huanniankj.sys.modular.org.service.SysOrgService;
import com.huanniankj.sys.modular.position.service.SysPositionService;
import com.huanniankj.sys.modular.relation.entity.SysRelation;
import com.huanniankj.sys.modular.relation.enums.SysRelationCategoryEnum;
import com.huanniankj.sys.modular.relation.service.SysRelationService;
import com.huanniankj.sys.modular.role.service.SysRoleService;
import com.huanniankj.sys.modular.user.service.SysUserService;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * 系统首页服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class SysIndexServiceImpl implements SysIndexService {

    @Resource
    private SysRelationService sysRelationService;

    @Resource
    private DevMessageApi devMessageApi;

    @Resource
    private DevLogApi devLogApi;

    @Resource
    private DevSseApi devSseApi;

    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService sysRoleService;

    @Resource
    private SysOrgService sysOrgService;

    @Resource
    private SysPositionService sysPositionService;

    @Resource
    private DevApi devApi;

    @Resource
    private AuthApi authApi;

    @Override
    public void addSchedule(SysIndexScheduleAddParam sysIndexScheduleAddParam) {
        SaBaseLoginUser loginUser = StpLoginUserUtil.getLoginUser();
        sysIndexScheduleAddParam.setScheduleUserId(loginUser.getId());
        sysIndexScheduleAddParam.setScheduleUserName(loginUser.getName());
        sysRelationService.saveRelationWithAppend(loginUser.getId(), sysIndexScheduleAddParam.getScheduleDate(),
                SysRelationCategoryEnum.SYS_USER_SCHEDULE_DATA.getValue(), JSONUtil.toJsonStr(sysIndexScheduleAddParam));
    }

    @Override
    public void deleteSchedule(List<SysIndexScheduleIdParam> sysIndexScheduleIdParamList) {
        List<String> scheduleIdList = sysIndexScheduleIdParamList.stream().map(SysIndexScheduleIdParam::getId)
                .collect(Collectors.toList());
        if (ObjectUtil.isNotEmpty(scheduleIdList)) {
            sysRelationService.removeByIds(scheduleIdList);
        }
    }

    @Override
    public List<SysIndexScheduleListResult> scheduleList(SysIndexScheduleListParam sysIndexScheduleListParam) {
        return sysRelationService.list(new LambdaQueryWrapper<SysRelation>().eq(SysRelation::getObjectId,
                                StpUtil.getLoginIdAsString())
                        .eq(SysRelation::getTargetId, sysIndexScheduleListParam.getScheduleDate())
                        .eq(SysRelation::getCategory, SysRelationCategoryEnum.SYS_USER_SCHEDULE_DATA.getValue()))
                .stream().map(sysRelation -> {
                    SysIndexScheduleListResult sysIndexScheduleListResult = JSONUtil.toBean(sysRelation.getExtJson(),
                            SysIndexScheduleListResult.class);
                    sysIndexScheduleListResult.setId(sysRelation.getId());
                    return sysIndexScheduleListResult;
                }).collect(Collectors.toList());
    }

    @Override
    public List<SysIndexMessageListResult> messageList(SysIndexMessageListParam sysIndexMessageListParam) {
        return devMessageApi.list(CollectionUtil.newArrayList(StpUtil.getLoginIdAsString()),
                        ObjectUtil.isEmpty(sysIndexMessageListParam.getLimit()) ?
                                10 : sysIndexMessageListParam.getLimit()).stream()
                .map(jsonObject -> JSONUtil.toBean(jsonObject, SysIndexMessageListResult.class))
                .collect(Collectors.toList());
    }

    @Override
    public SysIndexMessageDetailResult messageDetail(SysIndexMessageIdParam sysIndexMessageIdParam) {
        return JSONUtil.toBean(devMessageApi.detail(sysIndexMessageIdParam.getId()), SysIndexMessageDetailResult.class);
    }

    @Override
    public void allMessageMarkRead() {
        devMessageApi.allMessageMarkRead();
    }

    @Override
    public List<SysIndexVisLogListResult> visLogList() {
        return devLogApi.currentUserVisLogList().stream()
                .map(jsonObject -> JSONUtil.toBean(jsonObject, SysIndexVisLogListResult.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<SysIndexOpLogListResult> opLogList() {
        return devLogApi.currentUserOpLogList().stream()
                .map(jsonObject -> JSONUtil.toBean(jsonObject, SysIndexOpLogListResult.class))
                .collect(Collectors.toList());
    }

    @Override
    public SseEmitter createSseConnect(String clientId) {
        Consumer<CommonSseParam> consumer = m -> {
            //获取用户未读消息
            long unreadMessageNum = devMessageApi.unreadCount(m.getLoginId());
            //发送消息
            devSseApi.sendMessageToOneClient(m.getClientId(), String.valueOf(unreadMessageNum));
        };
        return devSseApi.createSseConnect(clientId, true, false, consumer);
    }

    @Override
    public SysBizDataCountResult getBizDataCount() {
        SysBizDataCountResult result = new SysBizDataCountResult();
        result.setUserCount(sysUserService.count());
        result.setRoleCount(sysRoleService.count());
        result.setOrgCount(sysOrgService.count());
        result.setPositionCount(sysPositionService.count());
        return result;
    }

    @Override
    public SysOpDataCountResult getOpDataCount() {
        JSONObject devObj = devApi.getDevOpCount();
        SysOpDataCountResult result = BeanUtil.toBean(devObj, SysOpDataCountResult.class);
        JSONObject authObj = authApi.getUserSessionCount();
        result.setBackUserSessionCount(authObj.getLong("backUserSessionCount"));
        result.setClientUserSessionCount(authObj.getLong("clientUserSessionCount"));
        result.setThirdUserCount(authApi.getThirdUserCount());
        return result;
    }

    @Override
    public SysToolDataCountResult getToolDataCount() {
        return BeanUtil.toBean(devApi.getToolDataCount(), SysToolDataCountResult.class);
    }
}
