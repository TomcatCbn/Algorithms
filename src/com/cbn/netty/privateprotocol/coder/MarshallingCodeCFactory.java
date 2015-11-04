package com.cbn.netty.privateprotocol.coder;

import org.jboss.marshalling.Marshaller;
import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

/**
 * MarshallingCodeCFactory工厂类创建解码器
 * @author boning
 *
 */
public final class MarshallingCodeCFactory {

	/**
	 * 创建Jboss Marshalling解码器MarshallingDecoder
	 * @return
	 */
	public static NettyMarshallingDecoder buildMarshallingDecoder(){
		//参数Serial表示创建的是Java序列化工厂对象
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration = new MarshallingConfiguration();
		configuration.setVersion(5);
		UnmarshallerProvider provider = new DefaultUnmarshallerProvider(marshallerFactory, configuration);
		//参数1024是单个消息序列化后的最大长度
		NettyMarshallingDecoder decoder = new NettyMarshallingDecoder(provider,1024);
		return decoder;
	}
	
	
	/**
	 * 创建Jboss Marshalling编码器MarshallingEncoder
	 * @return
	 */
	public static NettyMarshallingEncoder buildMarshallingEncoder(){
		final MarshallerFactory marshallerFactory = Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration configuration  = new MarshallingConfiguration();
		configuration.setVersion(5);
		MarshallerProvider provider = new DefaultMarshallerProvider(marshallerFactory, configuration);
		NettyMarshallingEncoder encoder = new NettyMarshallingEncoder(provider);
		return encoder;
	}


	public static Marshaller buildMarshalling() {
		
		return null;
	}
}
