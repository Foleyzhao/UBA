package com.huanniankj.biz.modular.group.service;

import cn.hutool.core.lang.tree.Tree;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.biz.modular.group.entity.BizGroup;
import com.huanniankj.biz.modular.group.param.BizGroupAddParam;
import com.huanniankj.biz.modular.group.param.BizGroupEditParam;
import com.huanniankj.biz.modular.group.param.BizGroupGrantUserParam;
import com.huanniankj.biz.modular.group.param.BizGroupIdParam;
import com.huanniankj.biz.modular.group.param.BizGroupPageParam;
import com.huanniankj.biz.modular.group.param.BizGroupSelectorUserParam;
import com.huanniankj.biz.modular.user.entity.BizUser;

import java.util.List;

/**
 * 用户组服务接口
 *
 * @author happynewyear
 */
public interface BizGroupService extends IService<BizGroup> {

    /**
     * 获取用户组分页
     */
    Page<BizGroup> page(BizGroupPageParam bizGroupPageParam);

    /**
     * 添加用户组
     */
    void add(BizGroupAddParam bizGroupAddParam);

    /**
     * 编辑用户组
     */
    void edit(BizGroupEditParam bizGroupEditParam);

    /**
     * 删除用户组
     */
    void delete(List<BizGroupIdParam> bizGroupIdParamList);

    /**
     * 获取用户组详情
     */
    BizGroup detail(BizGroupIdParam bizGroupIdParam);

    /**
     * 获取用户组详情
     */
    BizGroup queryEntity(String id);

    /**
     * 获取用户组下的用户
     */
    List<String> ownUser(BizGroupIdParam sysGroupIdParam);

    /**
     * 获取机构树选择器
     */
    List<Tree<String>> orgTreeSelector();

    /**
     * 获取用户选择器
     */
    Page<BizUser> userSelector(BizGroupSelectorUserParam bizGroupSelectorUserParam);

    /**
     * 给用户组授权用户
     */
    void grantUser(BizGroupGrantUserParam bizGroupGrantUserParam);
}
