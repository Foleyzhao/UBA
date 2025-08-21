package com.huanniankj.gen.modular.basic.result;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * 数据库表字段结果
 *
 * @author happynewyear
 */
@Getter
@Setter
public class GenBasicTableColumnResult {

    /**
     * 字段名称
     */
    @Schema(description = "字段名称")
    private String columnName;

    /**
     * 字段类型
     */
    @Schema(description = "字段类型")
    private String typeName;

    /**
     * 字段注释
     */
    @Schema(description = "字段注释")
    private String columnRemark;
}
