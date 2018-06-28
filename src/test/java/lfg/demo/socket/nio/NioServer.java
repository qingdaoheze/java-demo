package lfg.demo.socket.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServer {
	private InetSocketAddress address;
	private Selector selector;
	
	public static void main(String[] args) {
		NioServer nioServer = new NioServer(NioConstants.SERVER_PORT);
		nioServer.listen();
	}
	
	public NioServer(int port) {
		this.address = new InetSocketAddress(port);
		try {
			ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			serverSocketChannel.socket().bind(address);
			selector = Selector.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			System.out.println("服务器启动完成,将服务端channel注册到了选择器中……端口：" + NioConstants.SERVER_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 
	 */
	public void listen() {
		try {
			while (true) {
				System.out.println("-----------------------------------------------");
				int wait  = this.selector.select(); // accept是阻塞的，select也是阻塞的
				System.out.println("选择器接到了" + wait + "个消息");
				if (wait == 0) {
					continue;
				}
				Set<SelectionKey> selectedKeys = this.selector.selectedKeys();
				Iterator<SelectionKey> iterator = selectedKeys.iterator();
				while (iterator.hasNext()) {
					SelectionKey key = iterator.next();
					System.out.println(key.channel().toString());
					if (key.isValid()) {
						System.out.println("This key is valid.");
					}
					if (key.isAcceptable()) {
						System.out.println("isAcceptable");
					}
					if (key.isReadable()) {
						System.out.println("isReadable");
					}
					if (key.isWritable()) {
						System.out.println("isWritable");
					}
					process(key);
					iterator.remove();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 处理每一个客户端key
	 */
	public void process(SelectionKey key) throws Exception {
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		if (key.isAcceptable()) {
			ServerSocketChannel serverSocketChannel = (ServerSocketChannel)key.channel();
			SocketChannel socketChannel = serverSocketChannel.accept();
			socketChannel.configureBlocking(false);
			socketChannel.register(this.selector, SelectionKey.OP_READ);
		} else if (key.isReadable()) {
			SocketChannel socketChannel = (SocketChannel)key.channel();
			int len = socketChannel.read(buffer);
			if (len > 0) {
				buffer.flip();
				String content = new String(buffer.array(), 0, len);
				socketChannel.register(selector, SelectionKey.OP_WRITE);
				System.out.println(content);
			}
			buffer.clear();
		} else if (key.isWritable()) {
			SocketChannel socketChannel = (SocketChannel)key.channel();
			socketChannel.write(ByteBuffer.wrap("HelloWorld".getBytes()));
			socketChannel.close();
		}
	}
}
