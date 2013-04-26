package com.dpf.dao.hotproduct;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * company：
 * </p>
 * <Description>描述:热销产品dao</p>
 * <p>
 * create_time： 2013-3-20 下午2:34:36
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
public interface ProductSellDao {                          
	/**
	 * 功能：查询热销产品表
	 * @return 返回记录
	 */
	public List<Map<String, Object>>  productSellSelect();
	/**
	 * 功能：查询热销产品表记录数
	 * @return 返回条数
	 */
	public int  productSellSelectCount();
	/**
	 * 功能：动态执行语句
	 * @return 返回记录
	 */
	public List<Map<String, Object>>  execSql(Map<String, Object> map);
}
