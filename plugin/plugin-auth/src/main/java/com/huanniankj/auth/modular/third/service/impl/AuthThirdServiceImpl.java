package com.huanniankj.auth.modular.third.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.auth.api.SaBaseLoginUserApi;
import com.huanniankj.auth.core.enums.SaClientTypeEnum;
import com.huanniankj.auth.core.pojo.SaBaseLoginUser;
import com.huanniankj.auth.modular.login.enums.AuthDeviceTypeEnum;
import com.huanniankj.auth.modular.login.service.AuthService;
import com.huanniankj.auth.modular.third.entity.AuthThirdUser;
import com.huanniankj.auth.modular.third.enums.AuthThirdPlatformEnum;
import com.huanniankj.auth.modular.third.mapper.AuthThirdMapper;
import com.huanniankj.auth.modular.third.param.AuthThirdCallbackParam;
import com.huanniankj.auth.modular.third.param.AuthThirdRenderParam;
import com.huanniankj.auth.modular.third.param.AuthThirdUserPageParam;
import com.huanniankj.auth.modular.third.result.AuthThirdRenderResult;
import com.huanniankj.auth.modular.third.service.AuthThirdService;
import com.huanniankj.common.enums.CommonSortOrderEnum;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.page.CommonPageRequest;
import com.huanniankj.dev.api.DevConfigApi;
import com.xkcoding.http.HttpUtil;
import com.xkcoding.http.support.hutool.HutoolImpl;
import jakarta.annotation.Resource;
import me.zhyd.oauth.config.AuthConfig;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthGiteeRequest;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.request.AuthWeChatOpenRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 第三方登录服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class AuthThirdServiceImpl extends ServiceImpl<AuthThirdMapper, AuthThirdUser> implements AuthThirdService {

    private static final String SYSTEM_THIRD_GITEE_CLIENT_ID_KEY = "SYSTEM_THIRD_GITEE_CLIENT_ID";

    private static final String SYSTEM_THIRD_GITEE_CLIENT_SECRET_KEY = "SYSTEM_THIRD_GITEE_CLIENT_SECRET";

    private static final String SYSTEM_THIRD_GITEE_REDIRECT_URL_KEY = "SYSTEM_THIRD_GITEE_REDIRECT_URL";

    private static final String SYSTEM_THIRD_WECHAT_CLIENT_ID_KEY = "SYSTEM_THIRD_WECHAT_CLIENT_ID";

    private static final String SYSTEM_THIRD_WECHAT_CLIENT_SECRET_KEY = "SYSTEM_THIRD_WECHAT_CLIENT_SECRET";

    private static final String SYSTEM_THIRD_WECHAT_REDIRECT_URL_KEY = "SYSTEM_THIRD_WECHAT_REDIRECT_URL";

    @Resource
    private DevConfigApi devConfigApi;

    @Resource
    private AuthService authService;

    @Resource(name = "loginUserApi")
    private SaBaseLoginUserApi loginUserApi;

    @Resource(name = "clientLoginUserApi")
    private SaBaseLoginUserApi clientLoginUserApi;

    @Override
    public AuthThirdRenderResult render(AuthThirdRenderParam authThirdRenderParam) {

        // 获取请求
        AuthRequest authRequest = this.getAuthRequest(authThirdRenderParam.getPlatform());

        // 获取状态
        String state = AuthStateUtils.createState();

        // 构造授权地址
        String authorizeUrl = authRequest.authorize(state);

        // 构造结果
        AuthThirdRenderResult authThirdRenderResult = new AuthThirdRenderResult();

        // 返回授权地址
        authThirdRenderResult.setAuthorizeUrl(authorizeUrl);

        // 返回状态码
        authThirdRenderResult.setState(state);
        return authThirdRenderResult;
    }

    @SuppressWarnings("ALL")
    @Transactional(rollbackFor = Exception.class)
    @Override
    public String callback(AuthThirdCallbackParam authThirdCallbackParam, AuthCallback authCallback) {

        // 获取请求
        AuthRequest authRequest = this.getAuthRequest(authThirdCallbackParam.getPlatform());

        // 执行请求
        AuthResponse<AuthUser> authResponse = authRequest.login(authCallback);
        if (authResponse.ok()) {

            // 授权的用户信息
            AuthUser authUser = authResponse.getData();

            // 获取第三方用户id
            String uuid = authUser.getUuid();

            // 获取第三方用户来源
            String source = authUser.getSource();

            // 根据第三方用户id和用户来源获取用户信息
            AuthThirdUser authThirdUser = this.getOne(new LambdaQueryWrapper<AuthThirdUser>()
                    .eq(AuthThirdUser::getThirdId, uuid)
                    .eq(AuthThirdUser::getCategory, source));

            // 定义系统用户id
            String userId;
            if (ObjectUtil.isEmpty(authThirdUser)) {

                // 如果用户不存在，则绑定用户并登录
                userId = this.bindUser(authUser);
            } else {
                // 否则直接获取用户id登录
                userId = authThirdUser.getUserId();
            }
            // TODO 此处使用PC端执行B端登录，返回token
            return authService.doLoginById(userId, AuthDeviceTypeEnum.PC.getValue(), SaClientTypeEnum.B.getValue());
        } else {
            throw new CommonException("第三方登录授权回调失败，原因：{}", authResponse.getMsg());
        }
    }

    @Override
    public Page<AuthThirdUser> page(AuthThirdUserPageParam authThirdUserPageParam) {
        QueryWrapper<AuthThirdUser> queryWrapper = new QueryWrapper<AuthThirdUser>().checkSqlInjection();
        if (ObjectUtil.isNotEmpty(authThirdUserPageParam.getCategory())) {
            queryWrapper.lambda().eq(AuthThirdUser::getCategory, authThirdUserPageParam.getCategory());
        }
        if (ObjectUtil.isNotEmpty(authThirdUserPageParam.getSearchKey())) {
            queryWrapper.and(q -> q.lambda().like(AuthThirdUser::getName,
                            authThirdUserPageParam.getSearchKey())
                    .or().like(AuthThirdUser::getNickname, authThirdUserPageParam.getSearchKey()));
        }
        if (ObjectUtil.isAllNotEmpty(authThirdUserPageParam.getSortField(), authThirdUserPageParam.getSortOrder())) {
            CommonSortOrderEnum.validate(authThirdUserPageParam.getSortOrder());
            queryWrapper.orderBy(true, authThirdUserPageParam.getSortOrder()
                            .equals(CommonSortOrderEnum.ASC.getValue()),
                    StrUtil.toUnderlineCase(authThirdUserPageParam.getSortField()));
        } else {
            queryWrapper.lambda().orderByDesc(AuthThirdUser::getCreateTime);
        }
        return this.page(CommonPageRequest.defaultPage(), queryWrapper);
    }

    /**
     * 绑定用户并返回用户id
     */
    private String bindUser(AuthUser authUser) {
        // TODO 此处固定绑定超管
        SaBaseLoginUser saBaseLoginUser = loginUserApi.getUserByAccount("admin");
        if (ObjectUtil.isEmpty(saBaseLoginUser)) {
            throw new CommonException("第三方登录失败，无法绑定账号admin，原因：账户admin不存在");
        }
        AuthThirdUser authThirdUser = new AuthThirdUser();
        authThirdUser.setThirdId(authUser.getUuid());
        authThirdUser.setUserId(saBaseLoginUser.getId());
        authThirdUser.setAvatar(authUser.getAvatar());
        authThirdUser.setName(authUser.getUsername());
        authThirdUser.setNickname(authUser.getNickname());
        authThirdUser.setGender(authUser.getGender().getDesc());
        authThirdUser.setCategory(authUser.getSource());
        authThirdUser.setExtJson(JSONUtil.toJsonStr(authUser.getRawUserInfo()));
        this.save(authThirdUser);
        return authThirdUser.getUserId();
    }

    /**
     * 创建授权请求
     */
    private AuthRequest getAuthRequest(String source) {
        AuthRequest authRequest = null;
        source = source.toUpperCase();
        HttpUtil.setHttp(new HutoolImpl());
        AuthThirdPlatformEnum.validate(source);
        if (source.equals(AuthThirdPlatformEnum.GITEE.getValue())) {
            // GITEE登录
            authRequest = new AuthGiteeRequest(AuthConfig.builder()
                    .clientId(devConfigApi.getValueByKey(SYSTEM_THIRD_GITEE_CLIENT_ID_KEY))
                    .clientSecret(devConfigApi.getValueByKey(SYSTEM_THIRD_GITEE_CLIENT_SECRET_KEY))
                    .redirectUri(devConfigApi.getValueByKey(SYSTEM_THIRD_GITEE_REDIRECT_URL_KEY))
                    .build());
        }
        if (source.equals(AuthThirdPlatformEnum.WECHAT.getValue())) {
            // 微信登录
            authRequest = new AuthWeChatOpenRequest(AuthConfig.builder()
                    .clientId(devConfigApi.getValueByKey(SYSTEM_THIRD_WECHAT_CLIENT_ID_KEY))
                    .clientSecret(devConfigApi.getValueByKey(SYSTEM_THIRD_WECHAT_CLIENT_SECRET_KEY))
                    .redirectUri(devConfigApi.getValueByKey(SYSTEM_THIRD_WECHAT_REDIRECT_URL_KEY))
                    .build());
        }
        return authRequest;
    }
}
