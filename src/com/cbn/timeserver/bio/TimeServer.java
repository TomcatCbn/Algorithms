package com.cbn.timeserver.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 伪异步的TimeServer
 * @author boning
 *
 */
public class TimeServer {
	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		int port=8080;
		ServerSocket server= null;
		
		try {
			server = new ServerSocket(port);
			System.out.println("The time server is start in port :"+port);
			Socket socket = null;
			//创建I/O线程池
			TimeServerHandlerExecutePool singleExecutor = new TimeServerHandlerExecutePool(50,10000);
			
			while(true){
				socket=server.accept();
				
				singleExecutor.execute(new TimeServerHandler(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(server!=null){
				System.out.println("The time server is closed");
				server.close();
				server=null;
			}
		}
	}
}
