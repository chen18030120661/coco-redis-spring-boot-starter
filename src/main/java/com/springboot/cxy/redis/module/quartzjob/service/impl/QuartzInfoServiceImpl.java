package com.springboot.cxy.redis.module.quartzjob.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.springboot.cxy.redis.module.quartzjob.entity.QuartzInfoEntity;
import com.springboot.cxy.redis.module.quartzjob.entity.QuartzInfoModel;
import com.springboot.cxy.redis.module.quartzjob.mapper.QuartzInfoMapper;
import com.springboot.cxy.redis.module.quartzjob.mapper.QuartzLogMapper;
import com.springboot.cxy.redis.module.quartzjob.service.QuartzInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import tool.util.StringUtil;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author : xy.chen
 * @time : 2019/7/31
 * @desc : 定时任务详情ServiceImpl
 */
@Service("quartzInfoService")
public class QuartzInfoServiceImpl implements QuartzInfoService {

	private static final Logger logger = LoggerFactory.getLogger(QuartzInfoServiceImpl.class);

    @Resource
    private QuartzInfoMapper quartzInfoMapper;

    @Resource
    private QuartzLogMapper quartzLogMapper;

	@Override
	public boolean save(QuartzInfoEntity qi) {
		int result = quartzInfoMapper.save(qi);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean update(Map<String, Object> search) {
		int result = quartzInfoMapper.updateSelective(search);
		if (result > 0) {
			return true;
		}
		return false;
	}

	@Override
	public List<QuartzInfoEntity> list(Map<String, Object> result) {
		return quartzInfoMapper.listSelective(result);
	}

	@Override
	public Page<QuartzInfoModel> page(Map<String, Object> searchMap,
									  int current, int pageSize) {
		PageHelper.startPage(current, pageSize);
		List<QuartzInfoModel> list = quartzInfoMapper.page(searchMap);

		for (QuartzInfoModel quartzInfoModel : list) {
			String lastStartTime = quartzLogMapper.findLastTimeByQzInfoId(quartzInfoModel.getId());
			if(StringUtil.isNotBlank(lastStartTime)){
				quartzInfoModel.setLastStartTime(lastStartTime);
			}

		}

		return (Page<QuartzInfoModel>) list;
	}

	@Override
	public QuartzInfoEntity findByCode(String code){
		try {
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("code", code);
			QuartzInfoEntity quartzInfo = quartzInfoMapper.findSelective(paramMap);
			if (null != quartzInfo) {
				return quartzInfo;
			}
		} catch (Exception e) {
			logger.error("查询定时任务异常", e);
		}
		return null;
	}

	@Override
	public QuartzInfoEntity findSelective(Map<String, Object> paramMap) {
		try {
			QuartzInfoEntity quartzInfo = quartzInfoMapper.findSelective(paramMap);
			if (null != quartzInfo) {
				return quartzInfo;
			}
		} catch (Exception e) {
			logger.error("查询定时任务异常", e);
		}
		return null;
	}

	@Override
	public List<QuartzInfoModel> quartzInfoList(Map<String, Object> searchMap) {
		return  quartzInfoMapper.page(searchMap);
	}

	@Override
	public QuartzInfoEntity getById(Long id) {
		return quartzInfoMapper.findByPrimary(id);
	}
}