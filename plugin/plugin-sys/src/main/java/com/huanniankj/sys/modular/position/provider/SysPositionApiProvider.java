package com.huanniankj.sys.modular.position.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.sys.api.SysPositionApi;
import com.huanniankj.sys.modular.position.param.SysPositionSelectorPositionParam;
import com.huanniankj.sys.modular.position.service.SysPositionService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * 职位API接口提供者
 *
 * @author happynewyear
 */
@Service
public class SysPositionApiProvider implements SysPositionApi {

    @Resource
    private SysPositionService sysPositionService;

    @Override
    public String getNameById(String positionId) {
        return sysPositionService.queryEntity(positionId).getName();
    }

    @SuppressWarnings("ALL")
    @Override
    public Page<JSONObject> positionSelector(String orgId, String searchKey, Integer current, Integer size) {
        SysPositionSelectorPositionParam sysPositionSelectorPositionParam = new SysPositionSelectorPositionParam();
        sysPositionSelectorPositionParam.setOrgId(orgId);
        sysPositionSelectorPositionParam.setSearchKey(searchKey);
        return BeanUtil.toBean(sysPositionService.positionSelector(sysPositionSelectorPositionParam), Page.class);
    }
    
}
