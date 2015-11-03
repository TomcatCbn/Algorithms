package com.cbn.netty.privateprotocol.heartbeat;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import com.cbn.netty.privateprotocol.model.Header;
import com.cbn.netty.privateprotocol.model.MessageType;
import com.cbn.netty.privateprotocol.model.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 心跳检测机制，由客户端主动发送心跳消息，服务端接收到心跳消息之后，返回心跳应答消息 由于心跳消息的目的是为了检测链路的可用性，因此不需要携带消息体
 * 
 * @author boning
 *
 */
public class HeartBeatReqHandler extends ChannelHandlerAdapter {
	private volatile ScheduledFuture<?> heartBeat;

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		// 握手成功，主动发送心跳信息
		if (message.getHeader() != null && message.getHeader().getType() == (byte) 1) {
			heartBeat = ctx.executor().scheduleAtFixedRate(new HeartBeatReqHandler.HeartBeatTask(ctx), 0, 5000,
					TimeUnit.MILLISECONDS);

		} else if (message.getHeader() != null && message.getHeader().getType() == (byte) 4) {
			// MessageType.HeartBeat_Resp
			System.out.println("Client receive server heart beat message : --->" + message);
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	private class HeartBeatTask implements Runnable {
		private final ChannelHandlerContext ctx;

		public HeartBeatTask(final ChannelHandlerContext ctx) {
			this.ctx = ctx;
		}

		@Override
		public void run() {
			NettyMessage heartBeat = buildHeartBeat();
			System.out.println("Client send heart beat message to server : ---> " + heartBeat);
			ctx.writeAndFlush(heartBeat);
		}

		private NettyMessage buildHeartBeat() {
			NettyMessage message = new NettyMessage();
			Header header = new Header();
			header.setType((byte) 3);
			message.setHeader(header);
			return message;
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (heartBeat != null) {
			heartBeat.cancel(true);
			heartBeat = null;
		}
		ctx.fireExceptionCaught(cause);// 传递
	}

}
