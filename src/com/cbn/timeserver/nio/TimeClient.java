package com.cbn.timeserver.nio;

public class TimeClient {
	private static final int PORT=8080;
	private static final String HOST="127.0.0.1";
public static void main(String[] args) {
	new Thread(new TimeClientHandler(HOST, PORT), "TImeClient-001").start();
}
}
