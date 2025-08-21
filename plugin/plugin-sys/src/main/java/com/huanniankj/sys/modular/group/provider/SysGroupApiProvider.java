package com.huanniankj.sys.modular.group.provider;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.huanniankj.sys.api.SysGroupApi;
import com.huanniankj.sys.modular.group.param.SysGroupGrantUserParam;
import com.huanniankj.sys.modular.group.param.SysGroupIdParam;
import com.huanniankj.sys.modular.group.param.SysGroupSelectorParam;
import com.huanniankj.sys.modular.group.service.SysGroupService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户组API接口提供者
 *
 * @author happynewyear
 */
@Service
public class SysGroupApiProvider implements SysGroupApi {

    @Resource
    private SysGroupService sysGroupService;

    @Override
    public List<String> ownUser(String groupId) {
        SysGroupIdParam sysGroupIdParam = new SysGroupIdParam();
        sysGroupIdParam.setId(groupId);
        return sysGroupService.ownUser(sysGroupIdParam);
    }

    @Override
    public void grantUser(String groupId, List<String> userIdList) {
        SysGroupGrantUserParam sysGroupGrantUserParam = new SysGroupGrantUserParam();
        sysGroupGrantUserParam.setId(groupId);
        sysGroupGrantUserParam.setGrantInfoList(userIdList);
        sysGroupService.grantUser(sysGroupGrantUserParam);
    }

    @SuppressWarnings("ALL")
    @Override
    public Page<JSONObject> groupSelector(String searchKey, int current, int size) {
        SysGroupSelectorParam sysGroupSelectorParam = new SysGroupSelectorParam();
        sysGroupSelectorParam.setCurrent(current);
        sysGroupSelectorParam.setSize(size);
        sysGroupSelectorParam.setSearchKey(searchKey);
        return BeanUtil.toBean(sysGroupService.groupSelector(sysGroupSelectorParam), Page.class);
    }
    
}
