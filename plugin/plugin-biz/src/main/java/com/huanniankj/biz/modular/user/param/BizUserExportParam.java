package com.huanniankj.biz.modular.user.param;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 人员导出参数
 *
 * @author happynewyear
 */
@Getter
@Setter
public class BizUserExportParam {

    /**
     * 人员状态
     */
    @Schema(description = "人员状态")
    private String userStatus;

    /**
     * 账号、姓名、手机号关键词
     */
    @Schema(description = "账号、姓名、手机号关键词")
    private String searchKey;

    /**
     * 人员id集合
     */
    @Schema(description = "人员id集合")
    private String userIds;
}
