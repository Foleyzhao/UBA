package com.huanniankj.sys.modular.index.result;

import lombok.Getter;
import lombok.Setter;

/**
 * 系统业务数量
 *
 * @author happynewyear
 */
@Getter
@Setter
public class SysBizDataCountResult {

    /**
     * 用户数量
     */
    private Long userCount;

    /**
     * 角色数量
     */
    private Long roleCount;

    /**
     * 机构数量
     */
    private Long orgCount;

    /**
     * 职位数量
     */
    private Long positionCount;
}
