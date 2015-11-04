package com.cbn.netty.privateprotocol.auth;

import java.net.InetSocketAddress;
import java.util.concurrent.ConcurrentHashMap;

import com.cbn.netty.privateprotocol.model.Header;
import com.cbn.netty.privateprotocol.model.MessageType;
import com.cbn.netty.privateprotocol.model.NettyMessage;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * 服务端响应Login的业务ChannelHandler
 * 
 * 
 * @author boning
 *
 */
public class LoginAuthRespHandler extends ChannelHandlerAdapter{
	//缓存
	private ConcurrentHashMap<String, Boolean> nodeCheck= new ConcurrentHashMap<>();
	//白名单
	private String[] whileList= {"127.0.0.1","192.168.1.104"};

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		nodeCheck.remove(ctx.channel().remoteAddress().toString());//删除缓存
		ctx.close();
		ctx.fireExceptionCaught(cause);
	}

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		NettyMessage message=(NettyMessage) msg;
		//如果是握手消息，处理，其他消息透传
		if(message.getHeader()!=null && message.getHeader().getType()== MessageType.LOGIN_REQ){
			String nodeIndex = ctx.channel().remoteAddress().toString();
			//System.out.println("Login is ok , come from "+nodeIndex);
			NettyMessage loginResp =null;
			//重复登录，拒绝
			if(nodeCheck.containsKey(nodeIndex)){
				loginResp=buildResponse((byte)-1);
			}else{
				//握手请求校验
				InetSocketAddress address = (InetSocketAddress) ctx.channel().remoteAddress();
				String ip=address.getAddress().getHostAddress();
				boolean isOk= false;
				//白名单检验
				for(String wip:whileList){
					if(wip.equals(ip)){
						isOk=true;
						break;
					}
				}
				loginResp=isOk?buildResponse((byte)0):buildResponse((byte)-1);//0成功，-1失败
				if(isOk)
					nodeCheck.put(nodeIndex, true);
			}
			System.out.println("The login response is : "+loginResp+" body ["+loginResp.getBody()+"]");
			ctx.writeAndFlush(loginResp);
		}else{
			ctx.fireChannelRead(msg);
		}
	}

	private NettyMessage buildResponse(byte result) {
		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setType(MessageType.LOGIN_RESP);
		message.setHeader(header);
		message.setBody(result);
		return message;
	}

	
}
