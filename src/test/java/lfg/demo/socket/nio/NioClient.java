package lfg.demo.socket.nio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import lfg.demo.utils.Closer;

/**
 * NIO client跟BIO client没有区别，只是为了演示方便，分别创建了类。
 * 
 * @author lifenggang
 *
 */
public class NioClient {

	public static void main(String[] args) {
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		BufferedReader replyReader = null;
		PrintWriter requestWriter = null;
		BufferedReader userReader = null;
		
		try {
			socket = new Socket("127.0.0.1", NioConstants.SERVER_PORT);
			System.out.println("Connection has been established.");
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			replyReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			requestWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			userReader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
			System.out.println("Please input some request string, type " + NioConstants.CLOSE_DIRECTIVE + " to exit!");
			String input = userReader.readLine();
			String reply = null;
			
			while (true) {
				System.out.println("user input:" + input);
				requestWriter.println(input);
				requestWriter.flush();
				if (NioConstants.CLOSE_DIRECTIVE.equals(input)) {
					break;
				}
				reply = replyReader.readLine();
				System.out.println("server reply:" + reply);
				System.out.println();
				System.out.println();
				
				System.out.println("Please input some request string, type " + NioConstants.CLOSE_DIRECTIVE + " to exit!");
				input = userReader.readLine();
			}
			System.out.println(NioConstants.END_REPLY);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Closer.closeSilently(userReader);
			Closer.closeSilently(requestWriter);
			Closer.closeSilently(outputStream);
			Closer.closeSilently(replyReader);
			Closer.closeSilently(inputStream);
			Closer.closeSilently(socket);
		}
	}

}
