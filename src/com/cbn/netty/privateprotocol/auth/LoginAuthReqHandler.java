package com.cbn.netty.privateprotocol.auth;

import com.cbn.netty.privateprotocol.model.Header;
import com.cbn.netty.privateprotocol.model.MessageType;
import com.cbn.netty.privateprotocol.model.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 客户端构造握手请求，不需要携带消息体，消息体为空
 * 
 * 判断消息是否是握手应答消息，如果不是，直接透传给后面的ChannelHandler
 * 如果是握手消息，则对应答结果进行判断
 * @author boning
 *
 */
public class LoginAuthReqHandler extends ChannelHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		NettyMessage message = buildLoginReq();
		System.out.println("Send login message : "+message);
		ctx.writeAndFlush(message);//发送请求认证
	}

	private NettyMessage buildLoginReq() {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_REQ);
		message.setHeader(header);
		//message.setBody("It is request");
		return message;
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message = (NettyMessage) msg;
		
		//如果握手应答消息，需要判断是否认证成功
		if(message.getHeader()!=null && message.getHeader().getType()==MessageType.LOGIN_RESP){
			byte loginResult = (byte) message.getBody();
			if(loginResult!=(byte)0){//应答结果非0，则认证失败，关闭链路，重新发起连接
				//握手失败，关闭连接
				ctx.close();
			}else{
				System.out.println("Login is ok : "+message);
				ctx.fireChannelRead(msg);
			}
		}else{
			ctx.fireChannelRead(msg);//不是握手应答消息，则透传给后面的ChannelHandler
		}
	}


	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		ctx.fireExceptionCaught(cause);
	}
	
	

}
