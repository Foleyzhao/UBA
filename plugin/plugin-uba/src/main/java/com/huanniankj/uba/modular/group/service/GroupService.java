package com.huanniankj.uba.modular.group.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.uba.modular.group.entity.Group;
import com.huanniankj.uba.modular.group.param.GroupAddParam;
import com.huanniankj.uba.modular.group.param.GroupEditParam;
import com.huanniankj.uba.modular.group.param.GroupIdParam;
import com.huanniankj.uba.modular.group.param.GroupPageParam;

import java.util.List;

/**
 * 运营用户组服务接口
 *
 * @author happynewyear
 */
public interface GroupService extends IService<Group> {

    /**
     * 获取运营用户组分页
     */
    Page<Group> page(GroupPageParam groupPageParam);

    /**
     * 添加运营用户组
     */
    void add(GroupAddParam groupAddParam);

    /**
     * 编辑运营用户组
     */
    void edit(GroupEditParam groupEditParam);

    /**
     * 删除运营用户组
     */
    void delete(List<GroupIdParam> groupIdParams);

    /**
     * 获取运营用户组详情
     */
    Group detail(GroupIdParam groupIdParam);

    /**
     * 获取运营用户组详情
     */
    Group queryEntity(String id);

}
