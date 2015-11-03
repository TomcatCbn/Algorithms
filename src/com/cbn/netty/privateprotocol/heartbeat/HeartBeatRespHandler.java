package com.cbn.netty.privateprotocol.heartbeat;

import com.cbn.netty.privateprotocol.model.Header;
import com.cbn.netty.privateprotocol.model.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 服务端的心跳Handler非常简单，接收到心跳请求消息之后，构造心跳应答消息返回，并打印接受和发送的心跳消息
 * 
 * @author boning
 *
 */
public class HeartBeatRespHandler extends ChannelHandlerAdapter {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		// 返回心跳应答消息
		if (message.getHeader() != null && message.getHeader().getType() == (byte) 3) {
			System.out.println("Receive client heart beat message : -->" + message);// 打印接收到的心跳消息
			NettyMessage heartBeat = buildHeartBeat();
			System.out.println("Send heart beat response message to client : -->" + heartBeat);// 打印发出的心跳消息
			ctx.writeAndFlush(heartBeat);
		} else {
			ctx.fireChannelRead(msg);
		}
	}

	private NettyMessage buildHeartBeat() {
		// TODO Auto-generated method stub
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType((byte) 4);
		message.setHeader(header);
		return message;
	}

}
