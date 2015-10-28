package com.cbn.timeserver.bio;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 请求线程池
 * @author boning
 *
 */
public class TimeServerHandlerExecutePool {
private ExecutorService executor;
	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize) {
//		executor=new ThreadPoolExecutor(10, maxPoolSize, 120L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(queueSize));
		executor = Executors.newFixedThreadPool(10);
	}
	

	public void execute(java.lang.Runnable task) {
		executor.execute(task);
	}

}
