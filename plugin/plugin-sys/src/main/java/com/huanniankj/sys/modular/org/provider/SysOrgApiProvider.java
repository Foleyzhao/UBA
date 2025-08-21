package com.huanniankj.sys.modular.org.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.lang.tree.Tree;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.sys.api.SysOrgApi;
import com.huanniankj.sys.modular.org.entity.SysOrg;
import com.huanniankj.sys.modular.org.param.SysOrgSelectorOrgListParam;
import com.huanniankj.sys.modular.org.service.SysOrgService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 组织API接口提供者
 *
 * @author happynewyear
 */
@Service
public class SysOrgApiProvider implements SysOrgApi {

    @Resource
    private SysOrgService sysOrgService;

    @Override
    public String getNameById(String orgId) {
        return sysOrgService.queryEntity(orgId).getName();
    }

    @Override
    public String getSupervisorIdByOrgId(String orgId) {
        SysOrg sysOrg = sysOrgService.getById(orgId);
        if (ObjectUtil.isNotEmpty(sysOrg)) {
            return sysOrg.getDirectorId();
        }
        return null;
    }

    @Override
    public List<Tree<String>> orgTreeSelector() {
        return sysOrgService.orgTreeSelector();
    }

    @SuppressWarnings("ALL")
    @Override
    public Page<JSONObject> orgListSelector(String parentId) {
        SysOrgSelectorOrgListParam sysOrgSelectorOrgListParam = new SysOrgSelectorOrgListParam();
        sysOrgSelectorOrgListParam.setParentId(parentId);
        return BeanUtil.toBean(sysOrgService.orgListSelector(sysOrgSelectorOrgListParam), Page.class);
    }

    @Override
    public List<String> getParentIdListByOrgId(String orgId) {
        return sysOrgService.getParentIdListByOrgId(orgId);
    }

    @Override
    public List<JSONObject> getOrgListByIdListWithoutException(List<String> orgIdList) {
        return sysOrgService.listByIds(orgIdList).stream().map(JSONUtil::parseObj).collect(Collectors.toList());
    }
}
