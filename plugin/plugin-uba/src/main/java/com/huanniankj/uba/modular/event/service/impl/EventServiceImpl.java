package com.huanniankj.uba.modular.event.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.uba.modular.event.entity.EventCh;
import com.huanniankj.uba.modular.event.mapper.EventChMapper;
import com.huanniankj.uba.modular.event.service.EventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 事件服务接口实现类
 *
 * @author happynewyear
 */
@Slf4j
@Service
public class EventServiceImpl extends ServiceImpl<EventChMapper, EventCh> implements EventService {

}
