package com.cbn.timeserver.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MutiplexerServer implements Runnable {
	private Selector selector;
	private ServerSocketChannel servChannel;
	private volatile boolean stop;

	public MutiplexerServer(int port) {
		try {
			selector = Selector.open();
			servChannel = ServerSocketChannel.open();
			servChannel.configureBlocking(false);
			servChannel.socket().bind(new InetSocketAddress(port), 1024);
			servChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("The time server is start in port : " + port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void stop() {
		this.stop = true;
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				selector.select();
				Set<SelectionKey> selectedKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectedKeys.iterator();
				SelectionKey key = null;
				while (it.hasNext()) {
					key = it.next();
					it.remove();
					try {
						handleInput(key);
					} catch (Exception e) {
						if (key != null) {
							key.cancel();
							if (key.channel() != null) {
								key.channel().close();
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// 多路复用器关闭后，所有注册在上面的Channle和Pipe等资源都会被自动去注册并关闭，所以不需要重复释放资源
		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param key
	 * @throws IOException
	 */
	private void handleInput(SelectionKey key) throws IOException {
		// 处理新接入的消息请求
		if (key.isValid()) {
			if (key.isAcceptable()) {
				// Accept the new Connection
				ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
				SocketChannel sc = ssc.accept();
				sc.configureBlocking(false);
				// Add new Connection to the Selector
				sc.register(selector, SelectionKey.OP_READ);
			}

			if (key.isReadable()) {
				// Read the data
				SocketChannel sc = (SocketChannel) key.channel();
				ByteBuffer readBuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(readBuffer);
				if (readBytes > 0) {
					readBuffer.flip();
					byte[] bytes = new byte[readBuffer.remaining()];
					readBuffer.get(bytes);
					String body = new String(bytes, "UTF8");
					System.out.println("The time server receive order : " + body);
					String currentTime = "Query Time Order".equals(body)
							? new Date(System.currentTimeMillis()).toString() : "Bad Order";
					doWrite(sc, currentTime);
				} else if (readBytes < 0) {
					key.cancel();
					sc.close();
				} else {
					;
				}
			}

		}

	}

	/**
	 * @param sc
	 * @param currentTime
	 * @throws IOException
	 */
	private void doWrite(SocketChannel channel, String response) throws IOException {
		if (response != null && response.trim().length() > 0) {
			byte[] bytes = response.getBytes();
			ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
			writeBuffer.put(bytes);
			writeBuffer.flip();
			channel.write(writeBuffer);
			// 由于SocketChannel是异步非阻塞的，它并不保证一次能够把需要发送的字节数组发送完，
			// 此时会出现写半包问题，我们需要注册写操作，不断轮询Selector将没有发送完的ByteBuffer发送完毕，
			// 可以通过ByteBuffer的hasRemaining()方法判断消息是否发送完。
			
			System.out.println("Send response to server successfully");

		}
	}

}
