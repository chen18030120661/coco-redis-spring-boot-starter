package com.cxy.spring.boot.module.shardtable.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : xy.chen
 * @time : 2019/7/29
 * @desc : 工具类-分表
 */
public class ShardTableUtil {
	
	public static List<String> tables = new ArrayList<String>();
	static{
		tables.add("cl_user_contacts");
	}
	
	/**
	 * 根据主键Id生成分表名称
	 * @param shardId 拆分id段
	 * @return
	 */
	public static String generateTableNameById(String tableName, long id, long shardId){
		if(tables.contains(tableName)){
			long num = id/shardId + 1;
			return tableName + "_" + num;
		}else{
			return tableName;
		}
	}

}
