package com.dpf.services.hotproduct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * <p>
 * company：兴业证券版权所有22
 * </p>
 * <Description>描述:</p>
 * <p>
 * create_time： 2013-3-20 下午2:50:48
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
@SuppressWarnings("deprecation")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebRoot/WEB-INF/junit.xml" })
public class TestProductSellService {
	@Autowired
	private ProductSellService productSellService;
	
	@Test
	public void TestProductSellSelect(){
		productSellService.productSellSelect();
	}

}
