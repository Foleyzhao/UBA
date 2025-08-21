package com.huanniankj.auth.core.util;

import cn.dev33.satoken.exception.DisableServiceException;
import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import cn.dev33.satoken.exception.NotRoleException;
import cn.dev33.satoken.exception.SaTokenException;
import cn.hutool.http.HttpStatus;
import com.huanniankj.common.pojo.CommonResult;
import com.huanniankj.common.util.CommonServletUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证相关异常处理工具类
 *
 * @author happynewyear
 */
@Slf4j
public class AuthExceptionUtil {

    /**
     * 根据错误类型获取对应的CommonResult（只处理SaToken相关异常）
     */
    public static CommonResult<String> getCommonResult(Exception e) {
        CommonResult<String> commonResult;
        if (e instanceof NotLoginException notLoginException) {
            // 如果是未登录异常 401
            commonResult = CommonResult.get(HttpStatus.HTTP_UNAUTHORIZED, notLoginException.getMessage(), null);
        } else if (e instanceof NotRoleException notRoleException) {
            // 如果是角色异常 403
            commonResult = CommonResult.get(HttpStatus.HTTP_FORBIDDEN, "无此角色：" + notRoleException.getRole() +
                    "，接口地址：" + CommonServletUtil.getRequest().getServletPath(), null);
        } else if (e instanceof NotPermissionException notPermissionException) {
            // 如果是权限异常 403
            commonResult = CommonResult.get(HttpStatus.HTTP_FORBIDDEN, "无此权限："
                    + notPermissionException.getPermission(), null);
        } else if (e instanceof DisableServiceException disableServiceException) {

            // 如果是被封禁异常 403
            commonResult = CommonResult.get(HttpStatus.HTTP_FORBIDDEN, "账号被封禁："
                    + disableServiceException.getDisableTime() + "秒后解封", null);
        } else if (e instanceof SaTokenException saTokenException) {
            // 如果是SaToken异常 直接返回
            commonResult = CommonResult.error(saTokenException.getMessage());
        } else {
            // 未知异常才打印
            log.error(">>> 服务器未知异常，请求地址：{}，具体信息：", CommonServletUtil.getRequest().getRequestURL(), e);
            // 未知异常返回服务器异常（此处不可能执行进入，因为本方法处理的一定是SaToken的异常，此处仅为安全性考虑）
            commonResult = CommonResult.error();
        }
        return commonResult;
    }
}
