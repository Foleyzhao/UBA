package com.huanniankj.client.modular.user.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.huanniankj.client.modular.user.entity.ClientUser;
import com.huanniankj.client.modular.user.enums.ClientUserSourceFromTypeEnum;
import com.huanniankj.client.modular.user.param.ClientUserAddParam;
import com.huanniankj.client.modular.user.param.ClientUserEditParam;
import com.huanniankj.client.modular.user.param.ClientUserIdParam;
import com.huanniankj.client.modular.user.param.ClientUserPageParam;
import com.huanniankj.client.modular.user.service.ClientUserService;
import com.huanniankj.common.annotation.CommonLog;
import com.huanniankj.common.pojo.CommonResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * C端用户控制器
 *
 * @author happynewyear
 */
@Tag(name = "C端用户控制器")
@ApiSupport(author = "HUANNIAN_TEAM", order = 1)
@RestController
@Validated
public class ClientUserController {

    @Resource
    private ClientUserService clientUserService;

    /**
     * 获取用户分页
     */
    @ApiOperationSupport(order = 1)
    @Operation(summary = "获取用户分页")
    @GetMapping("/client/user/page")
    public CommonResult<Page<ClientUser>> page(ClientUserPageParam clientUserPageParam) {
        return CommonResult.data(clientUserService.page(clientUserPageParam));
    }

    /**
     * 添加用户
     */
    @ApiOperationSupport(order = 2)
    @Operation(summary = "添加用户")
    @CommonLog("添加用户")
    @PostMapping("/client/user/add")
    public CommonResult<String> add(@RequestBody @Valid ClientUserAddParam clientUserAddParam) {
        clientUserService.add(clientUserAddParam, ClientUserSourceFromTypeEnum.SYSTEM_ADD.getValue());
        return CommonResult.ok();
    }

    /**
     * 编辑用户
     */
    @ApiOperationSupport(order = 3)
    @Operation(summary = "编辑用户")
    @CommonLog("编辑用户")
    @PostMapping("/client/user/edit")
    public CommonResult<String> edit(@RequestBody @Valid ClientUserEditParam clientUserEditParam) {
        clientUserService.edit(clientUserEditParam);
        return CommonResult.ok();
    }

    /**
     * 删除用户
     */
    @ApiOperationSupport(order = 4)
    @Operation(summary = "删除用户")
    @CommonLog("删除用户")
    @PostMapping("/client/user/delete")
    public CommonResult<String> delete(@RequestBody @Valid @NotEmpty(message = "集合不能为空")
                                       List<ClientUserIdParam> clientUserIdParamList) {
        clientUserService.delete(clientUserIdParamList);
        return CommonResult.ok();
    }

    /**
     * 获取用户详情
     */
    @ApiOperationSupport(order = 5)
    @Operation(summary = "获取用户详情")
    @GetMapping("/client/user/detail")
    public CommonResult<ClientUser> detail(@Valid ClientUserIdParam clientUserIdParam) {
        return CommonResult.data(clientUserService.detail(clientUserIdParam));
    }
}
