package com.dpf.controllers.demo;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import com.dpf.domain.Paging;
import com.dpf.services.hotproduct.ProductSellService;

/**
 * <p>
 * company：
 * </p>
 * <Description>描述:</p>
 * <p>
 * create_time： 2013-3-22 下午3:48:08
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
@Path("product")
public class ProductController {
	@Autowired
	private ProductSellService productSellService;
	
	@Get
	public String jqGrid(Invocation inv,Paging page){
		System.out.println("111111111111111111111111");
		return "jqGrid";
	}
	@Post("list")
	public Object queryClientIssueStock(Invocation inv,Paging page){
		Map<String, Object> map = new HashMap<String, Object>();	
		Set<String> key = inv.getRequest().getParameterMap().keySet();
		for (Iterator it = key.iterator(); it.hasNext();) {
			String s = (String) it.next();
			map.put(s, inv.getParameter(s));
		}
		page.setParamMap(map);
		return productSellService.productSellSelect(page);
	}
}
