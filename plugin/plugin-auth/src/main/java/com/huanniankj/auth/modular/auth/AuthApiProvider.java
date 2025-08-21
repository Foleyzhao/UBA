package com.huanniankj.auth.modular.auth;


import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.huanniankj.auth.api.AuthApi;
import com.huanniankj.auth.core.util.StpClientUtil;
import com.huanniankj.auth.modular.third.service.AuthThirdService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 认证鉴权API实现类
 *
 * @author happynewyear
 */
@Service
public class AuthApiProvider implements AuthApi {

    @Resource
    private AuthThirdService authThirdService;

    @Override
    public JSONObject getUserSessionCount() {
        JSONObject resultObj = new JSONObject();
        List<JSONObject> sessionListB = StpUtil.searchSessionId("", -1, -1, true)
                .stream().map(sessionId -> {
                    JSONObject jsonObject = JSONUtil.createObj();
                    String userId = StrUtil.split(sessionId, StrUtil.COLON).get(3);
                    SaSession saSession = StpUtil.getSessionByLoginId(userId, false);
                    int tokenCount = saSession.getTokenSignList().size();
                    long createTime = saSession.getCreateTime();
                    jsonObject.set("userId", userId);
                    jsonObject.set("tokenCount", tokenCount);
                    jsonObject.set("createTime", DateTime.of(createTime));
                    return jsonObject;
                }).toList();

        List<JSONObject> sessionListC = StpClientUtil.searchSessionId("", -1, -1, true)
                .stream().map(sessionId -> {
                    JSONObject jsonObject = JSONUtil.createObj();
                    String userId = StrUtil.split(sessionId, StrUtil.COLON).get(3);
                    SaSession saSession = StpClientUtil.getSessionByLoginId(userId, false);
                    int tokenCount = saSession.getTokenSignList().size();
                    long createTime = saSession.getCreateTime();
                    jsonObject.set("userId", userId);
                    jsonObject.set("tokenCount", tokenCount);
                    jsonObject.set("createTime", DateTime.of(createTime));
                    return jsonObject;
                }).toList();
        resultObj.set("backUserSessionCount", sessionListB.size());
        resultObj.set("clientUserSessionCount", sessionListC.size());
        return resultObj;
    }

    @Override
    public Long getThirdUserCount() {
        return authThirdService.count();
    }
}
