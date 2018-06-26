package lfg.demo.socket.bio;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import lfg.demo.utils.Closer;

public class BioClient {
	public static void main(String[] args) {
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		BufferedReader replyReader = null;
		PrintWriter requestWriter = null;
		BufferedReader userReader = null;
		
		try {
			socket = new Socket("127.0.0.1", BioConstants.SERVER_PORT);
			System.out.println("Connection has been established.");
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			replyReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			requestWriter = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			userReader = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
			System.out.println("Please input some request string, type " + BioConstants.CLOSE_DIRECTIVE + " to exit!");
			String input = userReader.readLine();
			String reply = null;
			
			while (true) {
				System.out.println("user input:" + input);
				requestWriter.println(input);
				requestWriter.flush();
				if (BioConstants.CLOSE_DIRECTIVE.equals(input)) {
					break;
				}
				reply = replyReader.readLine();
				System.out.println("server reply:" + reply);
				System.out.println();
				System.out.println();
				
				System.out.println("Please input some request string, type " + BioConstants.CLOSE_DIRECTIVE + " to exit!");
				input = userReader.readLine();
			}
			System.out.println(BioConstants.END_REPLY);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Closer.closeQuietly(userReader);
			Closer.closeQuietly(requestWriter);
			Closer.closeQuietly(outputStream);
			Closer.closeQuietly(replyReader);
			Closer.closeQuietly(inputStream);
			Closer.closeQuietly(socket);
		}
	}
}
