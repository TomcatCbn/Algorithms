package com.cbn.http.fileserver;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * 文件服务器使用http协议对外提供服务，当客户端通过浏览器访问文件服务器时，对访问路径进行检查检查失败时返回
 * http403错误，该网页无法访问；如果校验通过，以链接的方式打开当前文件目录，每个目录或者文件都是个超链接，可以 递归访问
 * 
 * @author boning
 *
 */
public class HttpFileServer {
	private static final String DEFAULT_URL = "/src/";

	public void run(final int port, final String url) throws Exception {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();

		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(boss, worker).channel(NioServerSocketChannel.class)
					.childHandler(new ChannelInitializer<SocketChannel>() {

						@Override
						protected void initChannel(SocketChannel ch) throws Exception {
							ch.pipeline().addLast("http-decoder", new HttpRequestDecoder());
							ch.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
							ch.pipeline().addLast("http-encoder", new HttpResponseEncoder());
							ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
							ch.pipeline().addLast("fileServerHandler", new HttpFileServerHandler(url));
						}
					});

			ChannelFuture future = b.bind("127.0.0.1", 8080).sync();
			System.out.println("Http文件目录服务器启动，网址是：" + "http://127.0.0.1:" + port + url);
			future.channel().closeFuture().sync();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			boss.shutdownGracefully();
			worker.shutdownGracefully();
		}
	}

	public static void main(String[] args) throws Exception {
		int port = 8080;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}
		String url = DEFAULT_URL;
		if (args.length > 1)
			url = args[1];
		new HttpFileServer().run(port, url);
	}
}
