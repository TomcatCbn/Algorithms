package com.cbn.netty.privateprotocol.server;

import com.cbn.netty.privateprotocol.auth.LoginAuthRespHandler;
import com.cbn.netty.privateprotocol.coder.NettyMessageDecoder;
import com.cbn.netty.privateprotocol.coder.NettyMessageEncoder;
import com.cbn.netty.privateprotocol.heartbeat.HeartBeatRespHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.ReadTimeoutHandler;

/**
 * 服务端只要的工作就是握手的接入认证等，不用关心断线重连事件
 * 
 * @author boning
 *
 */
public class NettyServer {
	public void bind() throws Exception {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boss, worker).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 100)
					.handler(new LoggingHandler(LogLevel.INFO)).childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast("MessageDecoder", new NettyMessageDecoder(1024 * 1024, 4, 4,-8,0));
							ch.pipeline().addLast("MessageEncoder", new NettyMessageEncoder());
							ch.pipeline().addLast("ReadTimeOutHandler", new ReadTimeoutHandler(50));
							ch.pipeline().addLast("LoginAuthRespHandler", new LoginAuthRespHandler());
							ch.pipeline().addLast("HeartBeatHandler", new HeartBeatRespHandler());
						}
					});
			ChannelFuture f = b.bind(8080).sync();
			System.out.println("Netty server start ok : ");
			f.channel().closeFuture().sync();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		new NettyServer().bind();
	}
}
