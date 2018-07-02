package lfg.demo.socket.bio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import lfg.demo.utils.Closer;

public class BioServer {
	
	public static void main(String[] args) {
		server1();
	}
	
	public static void server1() {
		ServerSocket server = null;
		Socket socket = null;
		InputStream inputStream = null;
		OutputStream outputStream = null;
		BufferedReader reader = null;
		PrintWriter writer = null;
		
		try {
			server = new ServerSocket(BioConstants.SERVER_PORT);
			System.out.println("server startup. waiting for connection.");
			socket = server.accept();
			System.out.println("client has connected.");
			inputStream = socket.getInputStream();
			outputStream = socket.getOutputStream();
			reader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
			writer = new PrintWriter(new OutputStreamWriter(outputStream, "UTF-8"));
			System.out.println("Waiting for request from client.");
			String input = reader.readLine();
			while (!BioConstants.CLOSE_DIRECTIVE.equals(input) && input != null) {
				System.out.println("Received:" + input);
				writer.println("->" + input);
				writer.flush();
				System.out.println();
				System.out.println();
				System.out.println("Waiting for request from client.");
				input = reader.readLine();
			}
			System.out.println(BioConstants.END_REPLY);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			Closer.closeSilently(writer);
			Closer.closeSilently(outputStream);
			Closer.closeSilently(reader);
			Closer.closeSilently(inputStream);
			Closer.closeSilently(socket);
			Closer.closeSilently(server);
		}
		
	}
}
