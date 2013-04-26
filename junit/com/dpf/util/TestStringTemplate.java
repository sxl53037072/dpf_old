package com.dpf.util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;


/**
 * <p>
 * company：
 * </p>
 * <Description>描述:</p>
 * <p>
 * create_time： 2013-3-25 下午4:00:52
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
public class TestStringTemplate {
	@Test
	public void testApply() {
		StringTemplate REG_TEXT = new StringTemplate(
				"([{$param}{$variable}])(\\w+)");
		String PARAM = ":";
		String VARIABLE = "&";
		Map attrs = new HashMap();
		attrs.put("param", PARAM);
		attrs.put("variable", VARIABLE);
		System.out.println(REG_TEXT.apply(attrs));

	}
	
	
	public static void main(String[] args){
		Pattern p = Pattern.compile("cat");
		 Matcher m = p.matcher("one cat two cats in the yard");
		 StringBuffer sb = new StringBuffer();
		 while (m.find()) {
		     m.appendReplacement(sb, "dog");
		 }
		 m.appendTail(sb);
		 System.out.println(sb.toString());
	}
}
