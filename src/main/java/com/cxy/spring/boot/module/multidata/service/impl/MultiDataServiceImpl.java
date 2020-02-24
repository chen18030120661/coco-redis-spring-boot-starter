package com.cxy.spring.boot.module.multidata.service.impl;


import com.cxy.spring.boot.module.common.mapper.SysConfigMapper;
import com.cxy.spring.boot.module.multidata.AboutUsEntity;
import com.cxy.spring.boot.module.multidata.service.MultiDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 系统参数ServiceImpl
 *
 * @author xy.chen
 * @version 1.0.0
 * @date 2019-06-20 10:48:24
 */
@Service(value = "multiDataService")
public class MultiDataServiceImpl implements MultiDataService {

    private static final Logger logger = LoggerFactory.getLogger(MultiDataServiceImpl.class);

	@Resource
	private DataSource secodDataSource;

	@Override
	public List<AboutUsEntity> findAboutUsInfo() {
		String nativeSql = "select company as company,business as business,version as version,QQ as QQ,my_email as myEmail from about_us";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(secodDataSource);
		List<AboutUsEntity> aboutUsEntityList =jdbcTemplate.query(nativeSql,new RowMapper<AboutUsEntity>(){
			@Override
			public AboutUsEntity mapRow(ResultSet resultSet, int i) throws SQLException {
				AboutUsEntity vo = new AboutUsEntity();
				vo.setCompany(resultSet.getString("company"));
				vo.setVersion(resultSet.getString("version"));
				vo.setQQ(resultSet.getString("QQ"));
				vo.setMyEmail(resultSet.getString("myEmail"));
				return vo;
			}
		});
		return aboutUsEntityList;
	}

}