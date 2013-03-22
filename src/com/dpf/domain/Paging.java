package com.dpf.domain;

import java.util.List;
import java.util.Map;

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
public class Paging {

	private int total;//总数
	private int rows = 10;//每页显示数
	private int page = 1;//当前页数
	private int beginNum;//当前最小值
	private int endNum;//当前最大值
	private String sort;//排序列
	private String order;//asc desc
	private List<?> list;//查询结果
	private Map<String,Object> paramMap;//查询条件
	private List<String> dicList;//数据字典的数组
	
	
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getBeginNum() {
		beginNum = (page-1)*rows+1;
		return beginNum;
	}

	public void setBeginNum(int begin) {
		this.beginNum = begin;
	}

	public int getEndNum() {
		endNum = page*rows;
		return endNum;
	}

	public void setEndNum(int end) {
		this.endNum = end;
	}
	
	public List<?> getList() {
		return list;
	}
	public void setList(List<?> list) {
		this.list = list;
	}
	public Map<String, Object> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public List<String> getDicList() {
		return dicList;
	}

	public void setDicList(List<String> dicList) {
		this.dicList = dicList;
	}

	
}
