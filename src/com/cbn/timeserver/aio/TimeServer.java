package com.cbn.timeserver.aio;

/**
 * AIO服务
 * @author boning
 *
 */
public class TimeServer {
	public static void main(String[] args) {
		AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(8080);
		new Thread(timeServer).start();
	}
}
