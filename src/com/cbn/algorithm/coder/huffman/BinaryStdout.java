package com.cbn.algorithm.coder.huffman;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class BinaryStdout {

	/**8位拼成一个字节，写入流中
	 * @param temp
	 * @param output
	 * @throws IOException 
	 * @throws NumberFormatException 
	 */
	public static void write(String temp, OutputStream output) throws NumberFormatException, IOException {
		int length = temp.length();
		int yushu = length%8;
		int size = length/8+1;
		for(int j=0;j<(8-yushu);j++){
			temp+="0";
		}
		String tempS =null;
		for(int i =0;i<size;i++){
			tempS=temp.substring(i,i+8);
			output.write((char)(int)Integer.valueOf(tempS, 2));
		}
		//写入最后一个字节的有效长度
		output.write((byte)yushu);
	}

}
