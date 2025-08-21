package com.huanniankj.biz.modular.user.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.biz.api.BizUserApi;
import com.huanniankj.biz.modular.user.param.BizUserSelectorUserParam;
import com.huanniankj.biz.modular.user.service.BizUserService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 用户API接口提供者
 *
 * @author happynewyear
 */
@Service
public class BizUserApiProvider implements BizUserApi {

    @Resource
    private BizUserService bizUserService;

    @SuppressWarnings("ALL")
    @Override
    public Page<JSONObject> userSelector(String orgId, String searchKey) {
        BizUserSelectorUserParam bizUserSelectorUserParam = new BizUserSelectorUserParam();
        bizUserSelectorUserParam.setOrgId(orgId);
        bizUserSelectorUserParam.setSearchKey(searchKey);
        return BeanUtil.toBean(bizUserService.userSelector(bizUserSelectorUserParam), Page.class);
    }

}
