package com.dpf.controllers;

import java.util.Date;

import net.paoding.rose.web.Invocation;

/**
 * <p>
 * company：兴业证券版权所有
 * </p>
 * <Description>描述:</p>
 * <p>
 * create_time： 2013-3-22 上午10:10:48
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
public class HelloController {
	public String world(Invocation inv) {
		inv.addModel("now", new Date());
		return "hello-world";
	}
}
