package com.cbn.timeserver.aio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.util.concurrent.CountDownLatch;

public class AsyncTimeServerHandler implements Runnable{
	private int port ;
	
	CountDownLatch latch ;
	AsynchronousServerSocketChannel asynServerSocketChannel;
	
	public AsyncTimeServerHandler(int port) {
		this.port=port;
		try {
			asynServerSocketChannel=AsynchronousServerSocketChannel.open();
			asynServerSocketChannel.bind(new InetSocketAddress(port));
			System.out.println("AIO Time Server is started ");
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void run() {
		latch = new CountDownLatch(1);
		doAccept();
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void doAccept() {
		asynServerSocketChannel.accept(this,new AcceptCompletionHandler());
	}

}
