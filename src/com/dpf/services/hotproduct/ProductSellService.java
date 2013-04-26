package com.dpf.services.hotproduct;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpf.dao.hotproduct.ProductSellDao;
import com.dpf.domain.Paging;
import com.dpf.util.JsonBiz;


/**
 * <p>
 * company：
 * </p>
 * <Description>描述:</p>
 * <p>
 * create_time： 2013-3-20 下午2:47:01
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
@Service("productSellService")
public class ProductSellService {
	private static Logger logger = Logger
			.getLogger(ProductSellService.class.getName());
	@Autowired
	private ProductSellDao productSellDao;
	public Object productSellSelect(Paging p) {
		List<Map<String, Object>> list = productSellDao.productSellSelect();
		return JsonBiz.getJsonDataForJQGridUi(list, productSellDao.productSellSelectCount(), p);
	}
	public List<Map<String, Object>> execSql(Map<String, Object> map) {
		return productSellDao.execSql(map);
	}

}
