package com.cxy.spring.boot.module.disruptor;

import io.swagger.annotations.ApiModel;
import lombok.Data;

@Data
@ApiModel("Event事件实体")
public class LongEvent {
    private long value;
}
