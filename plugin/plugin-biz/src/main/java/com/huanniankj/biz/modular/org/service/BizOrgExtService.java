package com.huanniankj.biz.modular.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.biz.modular.org.entity.BizOrgExt;

/**
 * 机构扩展服务接口
 *
 * @author happynewyear
 */
public interface BizOrgExtService extends IService<BizOrgExt> {

    /**
     * 插入扩展信息
     */
    void createExtInfo(String orgId, String sourceFromType);
}
