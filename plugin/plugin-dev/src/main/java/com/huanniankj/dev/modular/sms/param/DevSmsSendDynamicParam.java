package com.huanniankj.dev.modular.sms.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 短信发送——动态参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class DevSmsSendDynamicParam {

    /**
     * 手机号
     */
    @Schema(description = "手机号，多个逗号拼接")
    @NotBlank(message = "phoneNumbers不能为空")
    private String phoneNumbers;

    /**
     * 模板编码或id
     */
    @Schema(description = "模板编码或id")
    @NotBlank(message = "templateCodeOrId不能为空")
    private String templateCodeOrId;

    /**
     * 发送参数
     */
    @Schema(description = "短信模板变量对应的实际值，JSON格式")
    private String templateParam;
}
