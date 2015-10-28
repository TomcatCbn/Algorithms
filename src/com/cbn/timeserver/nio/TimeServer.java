package com.cbn.timeserver.nio;

public class TimeServer {
	private static final int PORT = 8080;
	private static final String HOST = "localhost";
	public static void main(String[] args) {
//		try {
//			ServerSocketChannel acceptSvr = ServerSocketChannel.open();
//			acceptSvr.socket().bind(new InetSocketAddress(InetAddress.getByName(HOST), PORT));
//			acceptSvr.configureBlocking(false);
//			Selector selector = Selector.open();
//		} catch (UnknownHostException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
		
		MutiplexerServer timeServer = new MutiplexerServer(PORT);
		new Thread(timeServer,"NIO-MultiplxeerTimer-001").start();
		
	}
}
