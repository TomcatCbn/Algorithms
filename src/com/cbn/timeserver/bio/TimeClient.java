package com.cbn.timeserver.bio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {
/**
 * @param args
 */
public static void main(String[] args) {
	int port =8080;
	String host ="127.0.0.1";
	Socket socket=null;
	DataInputStream in =null;
	BufferedWriter out =null;
	
	try {
		socket = new Socket(host, port);
		in = new DataInputStream(socket.getInputStream());
		out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
		
		out.write("Query Time Order");
		out.flush();
		System.out.println("Send order to server succeed.");
		String res = in.readLine();
		System.out.println("Now is :"+res);
	} catch (UnknownHostException e) {
		e.printStackTrace();
	} catch (IOException e) {
		e.printStackTrace();
	}finally {
		if(out!=null)
			try {
				out.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		if(in!=null)
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		if(socket!=null)
			try {
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		socket=null;
	}
}
}
