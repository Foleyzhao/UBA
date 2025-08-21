package com.huanniankj.dev.modular.password.provider;

import com.huanniankj.dev.api.DevWeakPasswordApi;
import com.huanniankj.dev.modular.password.entity.DevWeakPassword;
import com.huanniankj.dev.modular.password.service.DevWeakPasswordService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 弱密码库API接口实现类
 *
 * @author happynewyear
 */
@Service
public class DevWeakPasswordApiProvider implements DevWeakPasswordApi {

    @Resource
    private DevWeakPasswordService devWeakPasswordService;

    @Override
    public List<String> weakPasswordList() {
        return devWeakPasswordService.list().stream().map(DevWeakPassword::getPassword).collect(Collectors.toList());
    }
}
