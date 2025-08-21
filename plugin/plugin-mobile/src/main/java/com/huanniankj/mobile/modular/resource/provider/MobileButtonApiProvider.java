package com.huanniankj.mobile.modular.resource.provider;

import com.huanniankj.mobile.api.MobileButtonApi;
import com.huanniankj.mobile.modular.resource.entity.MobileButton;
import com.huanniankj.mobile.modular.resource.service.MobileButtonService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 移动端按钮API接口提供者
 *
 * @author happynewyear
 */
@Service
public class MobileButtonApiProvider implements MobileButtonApi {

    @Resource
    private MobileButtonService mobileButtonService;

    @Override
    public List<String> listButtonCodeListByIdList(List<String> buttonIdList) {
        return mobileButtonService.listByIds(buttonIdList).stream().map(MobileButton::getCode).collect(Collectors.toList());
    }
}
