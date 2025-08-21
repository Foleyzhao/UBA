package com.huanniankj.biz.modular.org.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.huanniankj.biz.modular.org.entity.BizOrgExt;
import com.huanniankj.biz.modular.org.mapper.BizOrgExtMapper;
import com.huanniankj.biz.modular.org.service.BizOrgExtService;

/**
 * 机构扩展服务接口实现类
 *
 * @author happynewyear
 */
@Service
public class BizOrgExtServiceImpl extends ServiceImpl<BizOrgExtMapper, BizOrgExt> implements BizOrgExtService {

    @Override
    public void createExtInfo(String orgId, String sourceFromType) {
        BizOrgExt bizOrgExt = new BizOrgExt();
        bizOrgExt.setOrgId(orgId);
        bizOrgExt.setSourceFromType(sourceFromType);
        this.save(bizOrgExt);
    }
}
