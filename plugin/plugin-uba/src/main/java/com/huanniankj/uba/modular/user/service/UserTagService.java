package com.huanniankj.uba.modular.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.uba.modular.user.entity.UserTag;

/**
 * 运营用户标签服务接口
 *
 * @author happynewyear
 */
public interface UserTagService extends IService<UserTag> {

    /**
     * 插入标签信息
     */
    void createUserTag(String userId, String tagId, String source);

}
