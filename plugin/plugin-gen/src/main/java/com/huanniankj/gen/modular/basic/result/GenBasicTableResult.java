package com.huanniankj.gen.modular.basic.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据库表结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class GenBasicTableResult {

    /**
     * 表名称
     */
    @Schema(description = "表名称")
    private String tableName;

    /**
     * 表注释
     */
    @Schema(description = "表注释")
    private String tableRemark;
}
