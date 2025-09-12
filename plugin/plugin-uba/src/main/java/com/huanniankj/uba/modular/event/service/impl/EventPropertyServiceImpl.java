package com.huanniankj.uba.modular.event.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.huanniankj.uba.modular.event.entity.EventProperty;
import com.huanniankj.uba.modular.event.mapper.EventPropertyMapper;
import com.huanniankj.uba.modular.event.service.EventPropertyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 事件属性服务接口实现类
 *
 * @author happynewyear
 */
@Slf4j
@Service
public class EventPropertyServiceImpl extends ServiceImpl<EventPropertyMapper, EventProperty>
        implements EventPropertyService {

    @Override
    public void add(EventProperty eventProperty) {
        this.save(eventProperty);
    }

}
