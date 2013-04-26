package com.dpf.util;
/**
 * <p>
 * company：兴业证券版权所有
 * </p>
 * <Description>描述:</p>
 * <p>
 * create_time： 2013-3-20 下午2:28:09
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
public class TestQueueThreadPool {
	public static void main(String[] args){
		QueueThreadPool.putQueue(new QueueMethod() {
			public void callback() {
				System.out.println("1111");
			}
		});
	}
}
