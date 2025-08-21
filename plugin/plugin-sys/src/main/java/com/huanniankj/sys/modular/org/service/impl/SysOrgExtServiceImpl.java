package com.huanniankj.sys.modular.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.sys.modular.org.entity.SysOrgExt;
import com.huanniankj.sys.modular.org.mapper.SysOrgExtMapper;
import com.huanniankj.sys.modular.org.service.SysOrgExtService;
import org.springframework.stereotype.Service;

/**
 * 组织扩展服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class SysOrgExtServiceImpl extends ServiceImpl<SysOrgExtMapper, SysOrgExt> implements SysOrgExtService {

    @Override
    public void createExtInfo(String orgId, String sourceFromType) {
        SysOrgExt sysOrgExt = new SysOrgExt();
        sysOrgExt.setOrgId(orgId);
        sysOrgExt.setSourceFromType(sourceFromType);
        this.save(sysOrgExt);
    }

}
