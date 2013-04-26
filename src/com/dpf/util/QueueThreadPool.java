package com.dpf.util;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;

/**
 * <p>
 * company：
 * </p>
 * <Description>描述:队列线程池</p>
 * <p>
 * create_time： 2013-3-15 下午3:14:49
 * </p>
 * 
 * @author songxl
 * @version ver 1.0
 * @since jdk1.6
 */
public class QueueThreadPool {
	private static Logger logger = Logger.getLogger(QueueThreadPool.class);
	private static BlockingQueue<QueueMethod> linkedQueue = new LinkedBlockingQueue<QueueMethod>();
	private static ScheduledExecutorService scheduledExecutorService;
	//轮循时间 [毫秒]
	private static String queueTime = "1";
	static{
		scheduledExecutorService = Executors.newScheduledThreadPool(10);
		scheduledExecutorService.scheduleWithFixedDelay(new Runnable() {
			public void run() {
				if(!linkedQueue.isEmpty()){
					try {
						logger.debug("队列数="+linkedQueue.size());
						QueueMethod method = linkedQueue.take();
						method.callback();
					} catch (InterruptedException e) {
						logger.error("exception queue take error: " + e.getMessage());
					}					
				}
			}
		}, 1000, Long.parseLong(queueTime), TimeUnit.MILLISECONDS);
	}
	
	
	/**
	 * 添加接口至跟踪队列
	 * 调用方式：
	 *     QueueThreadPool.putQueue(new QueueMethod(){
     *			public void callback(){
     *				//执行动作
     *			}
     *		});   
	 * 
	 * @param e
	 */
	public static void putQueue(QueueMethod e) {
//		logger.debug("========================================添加接口至跟踪队列开始======================================");
		linkedQueue.add(e);
//		logger.debug("========================================添加接口至跟踪队列结束======================================");
	}
}
