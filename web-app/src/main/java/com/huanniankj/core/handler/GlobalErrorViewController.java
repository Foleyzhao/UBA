package com.huanniankj.core.handler;

import cn.hutool.core.util.ObjectUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.huanniankj.common.exception.CommonException;
import com.huanniankj.common.pojo.CommonResult;

import java.io.IOException;

/**
 * 全局异常页面处理器，覆盖默认的Whitelabel Error Page
 *
 * @author happynewyear
 */
@Slf4j
@RestController
public class GlobalErrorViewController {

    /**
     * Error页面视图，直接响应JSON
     */
    @RequestMapping("/errorView")
    public CommonResult<String> globalError(HttpServletRequest request, HttpServletResponse response) throws IOException {
        CommonResult<String> commonResult = new CommonResult<>(404, "路径不存在", null);
        Object model = request.getAttribute("model");
        if (ObjectUtil.isNotEmpty(model)) {
            if (model instanceof Exception) {
                if (model instanceof CommonException exception) {
                    Integer code = exception.getCode();
                    String msg = exception.getMsg();
                    if (ObjectUtil.isAllNotEmpty(code, msg)) {
                        commonResult.setCode(code).setMsg(msg);
                    } else if (ObjectUtil.isNotEmpty(msg)) {
                        commonResult = CommonResult.error(msg);
                    } else {
                        commonResult = CommonResult.error();
                    }
                } else {
                    commonResult = CommonResult.error();
                    log.error(">>> 服务器未知异常，具体信息：", (Exception) model);
                }
            }
        }
        return commonResult;
    }
}
