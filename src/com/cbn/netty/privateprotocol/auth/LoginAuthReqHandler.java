package com.cbn.netty.privateprotocol.auth;

import com.cbn.netty.privateprotocol.model.Header;
import com.cbn.netty.privateprotocol.model.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class LoginAuthReqHandler extends ChannelHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		ctx.writeAndFlush(buildLoginReq());
	}

	private NettyMessage buildLoginReq() {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType((byte)1);
		message.setHeader(header);
		message.setBody("It is request");
		return message;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		
		//如果握手应答消息，需要判断是否认证成功
		if(message.getHeader()!=null && message.getHeader().getType()==(byte)2){
			byte loginResult = (byte) message.getBody();
			if(loginResult!=(byte)0){
				//握手失败，关闭连接
				ctx.close();
			}else{
				ctx.fireChannelRead(msg);
			}
		}
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.close();
	}
	
	

}
