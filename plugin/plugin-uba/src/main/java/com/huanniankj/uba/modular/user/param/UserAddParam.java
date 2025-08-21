package com.huanniankj.uba.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * 运营用户添加参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class UserAddParam {

    /**
     * 账号
     */
    @Schema(description = "账号")
    @NotBlank(message = "account不能为空")
    private String account;

    /**
     * 昵称
     */
    @Schema(description = "昵称")
    private String nickname;

    /**
     * 排序码
     */
    @Schema(description = "排序码")
    private Integer sortCode;

    /**
     * 扩展信息
     */
    @Schema(description = "扩展信息")
    private String extJson;

}
