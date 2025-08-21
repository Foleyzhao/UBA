package com.huanniankj.uba.modular.event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.huanniankj.common.annotation.ClickHouseDataSource;
import com.huanniankj.uba.modular.event.entity.EventCh;

/**
 * 事件Mapper接口
 *
 * @author happynewyear
 */
@ClickHouseDataSource
public interface EventChMapper extends BaseMapper<EventCh> {

}
