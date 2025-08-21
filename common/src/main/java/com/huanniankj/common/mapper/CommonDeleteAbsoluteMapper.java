package com.huanniankj.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.io.Serializable;

/**
 * 通用物理删除Mapper，实现此Mapper并调用此方法可实现物理删除数据
 *
 * @author happynewyear
 */
public interface CommonDeleteAbsoluteMapper<T> extends BaseMapper<T> {

    /**
     * 物理删除
     *
     * @param id 主键
     * @return int
     */
    @SuppressWarnings("ALL")
    int deleteAbsoluteById(Serializable id);
}
