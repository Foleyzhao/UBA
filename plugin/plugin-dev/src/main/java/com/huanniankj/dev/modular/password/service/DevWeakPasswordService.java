package com.huanniankj.dev.modular.password.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.dev.modular.password.entity.DevWeakPassword;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordAddParam;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordEditParam;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordIdParam;
import com.huanniankj.dev.modular.password.param.DevWeakPasswordPageParam;

import java.util.List;

/**
 * 弱密码库服务接口
 *
 * @author happynewyear
 */
public interface DevWeakPasswordService extends IService<DevWeakPassword> {

    /**
     * 获取弱密码库分页
     */
    Page<DevWeakPassword> page(DevWeakPasswordPageParam devWeakPasswordPageParam);

    /**
     * 添加弱密码库
     */
    void add(DevWeakPasswordAddParam devWeakPasswordAddParam);

    /**
     * 编辑弱密码库
     */
    void edit(DevWeakPasswordEditParam devWeakPasswordEditParam);

    /**
     * 删除弱密码库
     */
    void delete(List<DevWeakPasswordIdParam> devWeakPasswordIdParamList);

    /**
     * 获取弱密码库详情
     */
    DevWeakPassword detail(DevWeakPasswordIdParam devWeakPasswordIdParam);

    /**
     * 获取弱密码库详情
     */
    DevWeakPassword queryEntity(String id);

}
