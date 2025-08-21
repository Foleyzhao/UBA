package com.huanniankj.gen.core.util;

import cn.hutool.core.util.ObjectUtil;
import com.huanniankj.gen.modular.basic.enums.GenJavaTypeEnum;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据库类型工具类
 *
 * @author happynewyear
 */
@Slf4j
public class GenDbTypeUtil {

    /**
     * 根据数据库字段类型获取Java类型
     */
    public static String getJavaTypeBySqlType(String dataType) {
        if (ObjectUtil.isEmpty(dataType)) {
            log.info(">>> 字段的数据库类型为空，使用默认值String");
            return GenJavaTypeEnum.String.getValue();
        }
        dataType = dataType.toUpperCase();
        if (dataType.startsWith("INT")) {
            //如果以int开头，则直接返回int，兼容pgsql中int2 int8等
            return GenJavaTypeEnum.Integer.getValue();
        }
        switch (dataType) {
            case "NVARCHAR":
            case "NVARCHAR2":
            case "CHAR":
            case "VARCHAR":
            case "ENUM":
            case "SET":
            case "TEXT":
            case "LONGTEXT":
            case "NCHAR":
            case "BLOB":
            case "NCLOB":
            case "IMAGE":
                return GenJavaTypeEnum.String.getValue();
            case "INTEGER":
            case "BIGINT":
            case "NUMBER":
            case "ID":
                return GenJavaTypeEnum.Long.getValue();
            case "TINYINT":
            case "SMALLINT":
            case "MEDIUMINT":
                return GenJavaTypeEnum.Integer.getValue();
            case "BIT":
            case "BOOLEAN":
                return GenJavaTypeEnum.Boolean.getValue();
            case "FLOAT":
                return GenJavaTypeEnum.Float.getValue();
            case "DOUBLE":
            case "MONEY":
            case "SMALLMONEY":
                return GenJavaTypeEnum.Double.getValue();
            case "DECIMAL":
            case "NUMERIC":
            case "REAL":
                return GenJavaTypeEnum.BigDecimal.getValue();
            case "DATE":
            case "DATETIME":
            case "YEAR":
            case "TIME":
            case "TIMESTAMP":
                return GenJavaTypeEnum.Date.getValue();
            default:
                log.info(">>> 字段的数据库类型：{}无法匹配，使用默认值String", dataType);
                return GenJavaTypeEnum.String.getValue();
        }
    }
}
