package com.huanniankj.uba.modular.event.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * 事件实体
 *
 * @author happynewyear
 */
@Data
@TableName("event")
public class EventCh {

    /**
     * 请求ID
     */
    @Id
    private String requestId;

}
