package com.springboot.cxy.redis.mapper;

import com.springboot.cxy.redis.module.msgqueue.MsgQueueEntity;

import java.util.List;

/**
 * @author : xy.chen
 * @time : 2019/7/24
 * @desc : Mapper
 */
public interface TestMapper {
    /**
     * 批量保存
     *
     * @param list
     * @return
     */
    int insertTestBath(List<MsgQueueEntity> list);

}
