package com.cbn.netty.privateprotocol.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.cbn.netty.privateprotocol.auth.LoginAuthReqHandler;
import com.cbn.netty.privateprotocol.coder.NettyMessageDecoder;
import com.cbn.netty.privateprotocol.coder.NettyMessageEncoder;
import com.cbn.netty.privateprotocol.heartbeat.HeartBeatReqHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.timeout.ReadTimeoutHandler;

public class NettyClient {
	public static void main(String[] args) throws Exception {
		new NettyClient().connect(8080, "127.0.0.1");
	}
	private ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);
	EventLoopGroup group = new NioEventLoopGroup();

	public void connect(int port, String host) throws Exception {
		// 配置客户端NIO线程组
		try {
			Bootstrap b = new Bootstrap();
			b.group(group).channel(NioSocketChannel.class).option(ChannelOption.TCP_NODELAY, true)
					.handler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							// 防止由于单条消息过大导致的内存溢出或者畸形码流导致解码错位引起内存分配失败，对单条消息最大长度进行了上限
							ch.pipeline().addLast("MessageDecoder", new NettyMessageDecoder(1024 * 1024, 4, 4,-8,0));
							ch.pipeline().addLast("MessageEncoder", new NettyMessageEncoder());// 消息编码
							ch.pipeline().addLast("ReadTimeoutHandler", new ReadTimeoutHandler(50));// 读超时
							ch.pipeline().addLast("LoginAuthHandler", new LoginAuthReqHandler());// 握手请求
							ch.pipeline().addLast("HeartBeatHandler", new HeartBeatReqHandler());// 心跳消息

						}
					});
			ChannelFuture future = b.connect(new InetSocketAddress(host, port)).sync();
			future.channel().closeFuture().sync();
		} finally {
			// 所有资源释放完成之后，清空资源，再次发起重连操作
			executor.execute(new Runnable() {

				@Override
				public void run() {
					try {
						TimeUnit.SECONDS.sleep(15);
						try {
							connect(port, host);// 发起重连操作
						} catch (Exception e) {
							e.printStackTrace();
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
	}
	

	
}
