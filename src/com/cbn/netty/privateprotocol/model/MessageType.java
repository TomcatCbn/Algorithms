package com.cbn.netty.privateprotocol.model;
/**
 * 
 * @author boning
 *
 */
public interface MessageType {
	public static final byte FAIL=(byte)-1;
	public static final byte LOGIN_REQ=(byte)1;
	public static final byte LOGIN_RESP=(byte)2;
	public static final byte HEART_BEAT_REQ=(byte)3;
	public static final byte HEART_BEAT_RESP=(byte)4;
	
}
