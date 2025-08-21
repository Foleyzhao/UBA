package com.huanniankj.sys.modular.org.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.huanniankj.sys.modular.org.entity.SysOrgExt;

/**
 * 组织扩展服务接口
 *
 * @author happynewyear
 */
public interface SysOrgExtService extends IService<SysOrgExt> {

    /**
     * 插入扩展信息
     */
    void createExtInfo(String orgId, String sourceFromType);
    
}
