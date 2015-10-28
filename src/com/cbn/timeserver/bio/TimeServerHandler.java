package com.cbn.timeserver.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;

public class TimeServerHandler implements java.lang.Runnable {
private  Socket socket;
	
	public TimeServerHandler(Socket socket) {
		this.socket=socket;
	}

	@Override
	public void run() {
		BufferedReader in =null;
		PrintWriter out = null;
		
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out = new PrintWriter(this.socket.getOutputStream(),true);
			String currentTime = null;
			String body = null;
			while(true){
				body= in.readLine();
				if(body==null)break;
				System.out.println("The time server receive order : "+ body);
				currentTime = "Query time order".equals(body)?new Date(System.currentTimeMillis()).toString():"Bad Order";
				out.println(currentTime);
			}
		} catch (IOException e) {
			e.printStackTrace();
			if(in!=null){
				try {
					in.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(out!=null){
				out.close();
				out = null;
			}
			if(this.socket!=null){
				try {
					socket.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}

}
