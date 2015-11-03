package com.cbn.netty.privateprotocol.coder;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.cbn.netty.privateprotocol.model.Header;
import com.cbn.netty.privateprotocol.model.NettyMessage;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.marshalling.MarshallingDecoder;

/**
 * Netty消息解码类
 * 
 * @author boning
 *
 */
public class NettyMessageDecoder extends LengthFieldBasedFrameDecoder {
	private NettyMarshallingDecoder nettyMarshallingDecoder;

	public NettyMessageDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength) throws IOException {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength);
		nettyMarshallingDecoder = MarshallingCodeCFactory.buildMarshallingDecoder();

	}

	@Override
	protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
		//对于业务解码器来说，调用父类LengthFieldBasedFrameDecoder的解码方法后，返回的就是整包消息或者为空，如果为空说明是半个包消息
		//直接返回继续由I/O线程读取后续的码流
		ByteBuf frame = (ByteBuf) super.decode(ctx, in);
		if (frame == null)
			return null;

		NettyMessage message = new NettyMessage();
		Header header = new Header();
		header.setCrcCode(in.readInt());
		header.setLength(in.readInt());
		header.setSessionID(in.readLong());
		header.setType(in.readByte());
		header.setPriority(in.readByte());

		int size = in.readInt();
		if (size > 0) {
			Map<String, Object> attch = new HashMap<String, Object>(size);
			int keySize = 0;
			byte[] keyArray = null;
			String key = null;
			for (int i = 0; i < size; i++) {
				keySize = in.readInt();
				keyArray = new byte[keySize];
				in.readBytes(keyArray);
				key = new String(keyArray, "UTF-8");
				attch.put(key, nettyMarshallingDecoder.decode(ctx, in));
			}
			keyArray = null;
			key = null;
			header.setAttachment(attch);
		}
		if (in.readableBytes() > 4) {// 存在消息体
			message.setBody(nettyMarshallingDecoder.decode(ctx, in));
		}
		message.setHeader(header);
		return message;
	}

}
