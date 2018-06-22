package lfg.demo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 流处理通用代码
 * 
 * @author lifenggang
 *
 */
public class StreamUtils {
	/**
	 * 将输入流的内容读取出来，放到一个字节数组中。
	 * @param is 输入流
	 * @return 字节数组
	 * @throws IOException
	 */
	public static final byte[] readToBytes(InputStream is) throws IOException {
		byte[] bytes = new byte[100];

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int len = 0;
		while((len = is.read(bytes)) > 0) {
			baos.write(bytes, 0, len);
		}
		return baos.toByteArray();
	}
}
