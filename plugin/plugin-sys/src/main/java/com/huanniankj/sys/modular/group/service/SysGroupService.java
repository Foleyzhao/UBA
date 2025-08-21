package com.huanniankj.sys.modular.group.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.sys.modular.group.entity.SysGroup;
import com.huanniankj.sys.modular.group.param.SysGroupAddParam;
import com.huanniankj.sys.modular.group.param.SysGroupEditParam;
import com.huanniankj.sys.modular.group.param.SysGroupGrantUserParam;
import com.huanniankj.sys.modular.group.param.SysGroupIdParam;
import com.huanniankj.sys.modular.group.param.SysGroupPageParam;
import com.huanniankj.sys.modular.group.param.SysGroupSelectorParam;
import com.huanniankj.sys.modular.group.param.SysGroupSelectorUserParam;
import com.huanniankj.sys.modular.user.entity.SysUser;

import java.util.List;

/**
 * 用户组服务接口
 *
 * @author happynewyear
 */
public interface SysGroupService extends IService<SysGroup> {

    /**
     * 获取用户组分页
     */
    Page<SysGroup> page(SysGroupPageParam sysGroupPageParam);

    /**
     * 添加用户组
     */
    void add(SysGroupAddParam sysGroupAddParam);

    /**
     * 编辑用户组
     */
    void edit(SysGroupEditParam sysGroupEditParam);

    /**
     * 删除用户组
     */
    void delete(List<SysGroupIdParam> sysGroupIdParamList);

    /**
     * 获取用户组详情
     */
    SysGroup detail(SysGroupIdParam sysGroupIdParam);

    /**
     * 获取用户组详情
     */
    SysGroup queryEntity(String id);

    /**
     * 获取用户组下的用户
     */
    List<String> ownUser(SysGroupIdParam sysGroupIdParam);

    /**
     * 获取组织树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取用户选择器
     */
    Page<SysUser> userSelector(SysGroupSelectorUserParam sysGroupSelectorUserParam);

    /**
     * 给用户组授权用户
     */
    void grantUser(SysGroupGrantUserParam sysGroupGrantUserParam);

    /**
     * 获取用户组选择器
     */
    Page<SysGroup> groupSelector(SysGroupSelectorParam sysGroupSelectorParam);

}
