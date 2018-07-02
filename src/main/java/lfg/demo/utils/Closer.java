package lfg.demo.utils;

/**
 * 关闭操作
 * 
 * @author lifenggang
 *
 */
public class Closer {
	/**
	 * 
	 * 关闭一个AutoCloseable对象。如果关闭成功，且无异常，则返回true。否则返回false。
	 * 
	 * @param closeable
	 * @return	如果对象被成功关闭，则返回true；否则，返回false。
	 */
	public static final boolean closeSilently(AutoCloseable closeable) {
		try {
			close(closeable);
			return true;
		} catch (final Exception ignored) {
			return false;
		}
	}

	/**
	 * 关闭AutoCloseable接口对象，如果此对象为null，则忽略。
	 * 
	 * @param closeable
	 * @throws Exception
	 */
	public static void close(final AutoCloseable closeable) throws Exception {
		if (closeable != null) {
			closeable.close();
		}
	}

}
