package com.cxy.spring.boot.module.multidata.service;


import com.cxy.spring.boot.module.multidata.AboutUsEntity;

import java.util.List;

/**
 * 多数据源Service
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-06-20 10:48:24
 */
public interface MultiDataService {

    /**
     * 查询关于我们
     *
     * @return
     */
    List<AboutUsEntity> findAboutUsInfo();

}
