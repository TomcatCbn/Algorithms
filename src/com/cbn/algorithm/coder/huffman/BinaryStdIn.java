package com.cbn.algorithm.coder.huffman;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BinaryStdIn {
	private final InputStream in;
	private  long length;
	private long readed;
	
	public BinaryStdIn(InputStream in) {
		this.in=in;
		try {
			this.length=in.available();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * 读取1位返回一个boolean值
	 * @return
	 * @throws IOException 
	 */
	public boolean readBoolean() throws IOException{
		byte[] b = new byte[1];
		in.read(b);
		return b[0]==(byte)1;
	}
	/**
	 * 读取8位数据并返回一个char值
	 * @return
	 */
	public char readChar(){
		byte[]
	}
	/**
	 * 读取1-16位数据并返回一个char值
	 * @param r
	 * @return
	 */
	public char readCHar(int r){
		
	}
/**
 * 比特流是否为空
 * @return
 */
	public boolean isEmpty(){
		return length == readed;
	}
	/**
	 * 关闭比特流
	 * @throws IOException 
	 */
	public void close() throws IOException{
		in.close();
	}
}
