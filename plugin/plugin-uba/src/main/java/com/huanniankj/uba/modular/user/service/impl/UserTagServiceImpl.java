package com.huanniankj.uba.modular.user.service.impl;

import cn.hutool.core.date.DateTime;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.uba.modular.user.entity.UserTag;
import com.huanniankj.uba.modular.user.mapper.UserTagMapper;
import com.huanniankj.uba.modular.user.service.UserTagService;
import org.springframework.stereotype.Service;

/**
 * 运营用户标签接口实现类
 *
 * @author happynewyear
 */
@Service
public class UserTagServiceImpl extends ServiceImpl<UserTagMapper, UserTag> implements UserTagService {

    @Override
    public void createUserTag(String userId, String tagId, String source) {
        UserTag userTag = new UserTag();
        userTag.setUserId(userId);
        userTag.setTagId(tagId);
        userTag.setSource(source);
        userTag.setAddTagTime(DateTime.now());
        this.save(userTag);
    }

}
