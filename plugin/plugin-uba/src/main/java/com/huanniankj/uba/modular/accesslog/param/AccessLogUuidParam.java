package com.huanniankj.uba.modular.accesslog.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 访问日志唯一ID参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class AccessLogUuidParam {

    /**
     * 请求ID
     */
    @Schema(description = "请求ID")
    @NotBlank(message = "请求ID不能为空")
    private String requestId;

}
