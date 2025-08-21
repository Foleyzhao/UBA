package com.huanniankj.biz.modular.org.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.biz.api.BizOrgApi;
import com.huanniankj.biz.modular.org.param.BizOrgSelectorOrgListParam;
import com.huanniankj.biz.modular.org.service.BizOrgService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 组织API接口提供者
 *
 * @author happynewyear
 */
@Service
public class BizOrgApiProvider implements BizOrgApi {

    @Resource
    private BizOrgService bizOrgService;

    @Override
    public List<Tree<String>> orgTreeSelector() {
        return bizOrgService.orgTreeSelector();
    }

    @SuppressWarnings("ALL")
    @Override
    public Page<JSONObject> orgListSelector(String parentId) {
        BizOrgSelectorOrgListParam bizOrgSelectorOrgListParam = new BizOrgSelectorOrgListParam();
        bizOrgSelectorOrgListParam.setParentId(parentId);
        return BeanUtil.toBean(bizOrgService.orgListSelector(bizOrgSelectorOrgListParam), Page.class);
    }
}
