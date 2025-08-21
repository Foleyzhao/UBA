package com.huanniankj.sys.modular.resource.service;

import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.sys.modular.resource.entity.SysModule;
import com.huanniankj.sys.modular.resource.param.module.SysModuleAddParam;
import com.huanniankj.sys.modular.resource.param.module.SysModuleEditParam;
import com.huanniankj.sys.modular.resource.param.module.SysModuleIdParam;
import com.huanniankj.sys.modular.resource.param.module.SysModulePageParam;

import java.util.List;

/**
 * 模块服务接口
 *
 * @author happynewyear
 */
public interface SysModuleService extends IService<SysModule> {

    /**
     * 获取模块分页
     */
    Page<SysModule> page(SysModulePageParam sysModulePageParam);

    /**
     * 添加模块
     */
    void add(SysModuleAddParam sysModuleAddParam);

    /**
     * 编辑模块
     */
    void edit(SysModuleEditParam sysModuleEditParam);

    /**
     * 删除模块
     */
    void delete(List<SysModuleIdParam> sysModuleIdParamList);

    /**
     * 获取所有模块
     */
    List<JSONObject> moduleSelector();

    /**
     * 获取模块详情
     */
    SysModule detail(SysModuleIdParam sysModuleIdParam);

    /**
     * 获取模块详情
     */
    SysModule queryEntity(String id);
}
