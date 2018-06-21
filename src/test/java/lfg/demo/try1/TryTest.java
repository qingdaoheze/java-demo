package lfg.demo.try1;

import java.io.Closeable;
import java.io.IOException;

import org.junit.Test;

public class TryTest {
	/**
	 * try语句时序图
	 */
	@Test
	public void try1() {
		StringBuilder sb = new StringBuilder();
		sb.append("Start -> ");

		TryTool.tryRoadMap(sb);

		sb.append("End -> ");
		System.out.println(sb);
	}

	/**
	 * try语句时序图，try语句块里的返回值变量在finally块中的变化不会影响最终的返回值。
	 */
	@Test
	public void try2() {
		StringBuilder sb = new StringBuilder();
		sb.append("Start -> ");

		String result = TryTool.tryRoadMapWithReturn(sb);

		sb.append("End (" + result + ") -> ");
		System.out.println(sb);
	}

	/**
	 * try-catch-with-resource语句的用法。<br />
	 * catch语句中捕捉的异常包括try代码块中的代码，也包括资源关闭语句抛出的异常。<br />
	 * 同时有多个异常时，优先级最高的异常将被catch块捕捉，其它异常以suppressed的形式记录在此异常中。<br />
	 * 优先级顺序为：逻辑代码 > 后声明的资源关闭时异常 > 先声明的资源关闭时异常。
	 * 
	 */
	@Test
	public  void tryWithResources() {
		StringBuilder sb = new StringBuilder();
		try (TryTool in1 = new TryTool("in1");
				TryTool in2 = new TryTool("in2");) {
			sb.append("try-");
			in1.throw1();
		} catch (Exception e) {
			sb.append("catche-");
			e.printStackTrace();
			Throwable[] suppressed = e.getSuppressed();
			String speratorLine = "------------------------------------";
			System.err.println(speratorLine);
			for (Throwable t : suppressed) {
				t.printStackTrace();
				System.err.println(speratorLine);
			}
		} finally {
			sb.append("finally-");
		}
		sb.append("end");
		System.out.println(sb);
	}

	static class TryTool implements Closeable {
		private String name;

		public static void tryRoadMap(StringBuilder sb) {
			sb.append("before try -> ");
			try {
				sb.append(" try block -> ");
				throw new RuntimeException();
			} catch (Exception e) {
				sb.append(" catch block -> ");
			} finally {
				sb.append(" finally block -> ");
			}
		}

		public static String tryRoadMapWithReturn(StringBuilder sb) {
			String result = "start";
			sb.append("before try (" + result + ") -> ");
			try {
				result = "result";
				sb.append(" try (" + result + ") block -> ");
				return result;
			} catch (Exception e) {
				result = "catch";
				sb.append(" catch block (" + result + ") -> ");
			} finally {
				result = "finally";
				sb.append(" finally block (" + result + ") -> ");
			}
			sb.append(" before return (" + result + ") -> ");
			return result;
		}

		public TryTool(String name) {
			this.name = name;
		}

		public int throw1() throws IOException {
			throw new RuntimeException("throw1 method");
		}
		
		@Override
		public void close() throws IOException {
			throw new RuntimeException("close：" + this.toString());
		}

		public String getName() {
			return name;
		}

		public String toString() {
			return name;
		}
	}
}
