package lfg.demo.utils;

/**
 * 关闭操作
 * 
 * @author lifenggang
 *
 */
public class Closer {
	/**
	 * 静默调用java.lang.AutoCloseable接口的close方法。
	 * 
	 * @param closable
	 */
	public static final void closeQuietly(AutoCloseable closable) {
		try {
			closable.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
