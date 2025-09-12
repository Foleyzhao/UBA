package com.huanniankj.uba.modular.event.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.uba.modular.event.entity.Event;
import com.huanniankj.uba.modular.event.mapper.EventMapper;
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
public class EventServiceImpl extends ServiceImpl<EventMapper, Event> implements EventService {

    @Override
    public Event add(Event event) {
        this.save(event);
        return event;
    }

}
