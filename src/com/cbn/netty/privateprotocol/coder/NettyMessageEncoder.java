package com.cbn.netty.privateprotocol.coder;

import java.util.List;
import java.util.Map;

import com.cbn.netty.privateprotocol.model.NettyMessage;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

public final class NettyMessageEncoder extends MessageToMessageEncoder<NettyMessage>{
	private NettyMarshallingEncoder marshallingEncoder;
	public NettyMessageEncoder() {
		this.marshallingEncoder =MarshallingCodeCFactory.buildMarshallingEncoder();
	}
	
	
	
	
	@Override
	protected void encode(ChannelHandlerContext ctx, NettyMessage msg, List<Object> out) throws Exception {
		if(msg==null || msg.getHeader()==null)
			throw new Exception("The encode message is null");
		
		ByteBuf sendBuf = Unpooled.buffer();
		//将NettyMessage的Header写入buffer
		sendBuf.writeInt(msg.getHeader().getCrcCode());
		sendBuf.writeInt(msg.getHeader().getLength());
		sendBuf.writeLong(msg.getHeader().getSessionID());
		sendBuf.writeByte(msg.getHeader().getType());
		sendBuf.writeByte(msg.getHeader().getPriority());
		sendBuf.writeInt(msg.getHeader().getAttachment().size());
		
		String key = null;
		byte[] keyArray = null;
		Object value = null;
		for(Map.Entry<String, Object> param: msg.getHeader().getAttachment().entrySet()){
			key = param.getKey();
			keyArray = key.getBytes("UTF-8");//key的字节数组
			sendBuf.writeInt(keyArray.length);//先写入key的长度
			sendBuf.writeBytes(keyArray);
			value = param.getValue();
			marshallingEncoder.encode(ctx, value, sendBuf);//通过MarshallingEncoder写入sendBuf
			
		}
		
		key = null;
		keyArray=null;
		value=null;
		//处理消息体
		if(msg.getBody()!=null){
			marshallingEncoder.encode(ctx, msg.getBody(),sendBuf);
			
		}else{
			sendBuf.writeInt(0);
			sendBuf.setInt(4, sendBuf.readableBytes());
		}
		
		//把Message添加到List传递到下一个Handler
		out.add(sendBuf);
	}

}
