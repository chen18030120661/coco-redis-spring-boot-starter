package com.springboot.cxy.redis.module.quartz.mapper;


import com.springboot.cxy.redis.module.quartz.entity.QuartzInfoEntity;
import com.springboot.cxy.redis.module.quartz.entity.QuartzInfoModel;

import java.util.List;
import java.util.Map;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/30
 * @desc : 定时任务详情mapper
 */
public interface QuartzInfoMapper {

    /**
     * 定时任务查询
     *
     * @param map
     * @return
     */
    List<QuartzInfoModel> page(Map<String, Object> map);

    QuartzInfoEntity findSelective(Map<String, Object> paramMap);

    int save(QuartzInfoEntity qi);

    int updateSelective(Map<String, Object> search);

    List<QuartzInfoEntity> listSelective(Map<String, Object> result);
    
    QuartzInfoEntity findByPrimary(Long id);
}
