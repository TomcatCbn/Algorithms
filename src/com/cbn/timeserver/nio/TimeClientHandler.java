package com.cbn.timeserver.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class TimeClientHandler implements Runnable {
	private String host;
	private int port;
	private Selector selector;
	private SocketChannel sc;
	private volatile boolean stop;

	public TimeClientHandler(String host, int port) {
		this.host = host;
		this.port = port;
		try {
			selector = Selector.open();
			sc = SocketChannel.open();
			sc.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	public void run() {
		try {
			doConnect();
		} catch (IOException e) {
			e.printStackTrace();
		}

		while (!stop) {
			try {
				selector.select(1000);
				Set<SelectionKey> selectionKeys = selector.selectedKeys();
				Iterator<SelectionKey> it = selectionKeys.iterator();
				while (it.hasNext()) {
					SelectionKey key = it.next();
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
				System.exit(1);
			}

		}
		if (selector != null) {
			try {
				selector.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void handleInput(SelectionKey key) throws IOException {
		if (key.isValid()) {
			//SocketChannel sc3 = (SocketChannel) key.channel();
			if (key.isConnectable()) {
				if (sc.finishConnect()) {
					sc.register(selector, SelectionKey.OP_READ);
					doWrite(sc);
				} else
					System.exit(1);
			}
			if (key.isReadable()) {
				ByteBuffer bytebuffer = ByteBuffer.allocate(1024);
				int readBytes = sc.read(bytebuffer);
				if (readBytes > 0) {
					bytebuffer.flip();
					byte[] bytes = new byte[bytebuffer.remaining()];
					bytebuffer.get(bytes);
					String body = new String(bytes, "UTF8");
					System.out.println("Now is " + body);
					//this.stop = true;
				} else if (readBytes < 0) {
					key.cancel();
					sc.close();
				}
			}
		}
	}

	private void doConnect() throws IOException {
		if (sc.connect(new InetSocketAddress(host, port))) {
			sc.register(selector, SelectionKey.OP_READ);
			doWrite(sc);
		} else {
			sc.register(selector, SelectionKey.OP_CONNECT);
		}

	}

	private void doWrite(SocketChannel sc2) throws IOException {
		byte[] req = "Query Time Order".getBytes();
		ByteBuffer writeBuffer = ByteBuffer.allocate(req.length);
		writeBuffer.put(req);
		writeBuffer.flip();
		sc.write(writeBuffer);
		if (!writeBuffer.hasRemaining()) {
			System.out.println("Send order to server succeed");
		}
	}

}
