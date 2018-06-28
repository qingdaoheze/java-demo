package lfg.demo.jdbc;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import lfg.demo.utils.StreamUtils;

/**
 * 普通对象的增删改查.<br/>
 * 
 * @author lifenggang
 *
 */
public class RawJdbc {
	private static String driver = "com.mysql.jdbc.Driver";
	private static String url = "jdbc:mysql://localhost:3306/java_demo?userUnicode=true&characterEncoding=utf-8&useSSL=false";
	private static String user = "root";
	private static String password = "123456";

	@Test
	public void test() {
		// insert
		Student student = new Student("Lucy", "Male", 18, "Avator");
		insert(student);

		// update
		student.setAge(21);
		update(student);

		// query
		Integer count = getAll();
		System.out.println("总记录数：" + count);

		// delete
		delete(student.getName());
	}

	public static Connection getConn() {
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 插入操作，包括blob等类型。
	 * @param student
	 * @return 插入的行数
	 */
	public static int insert(Student student) {
		int i = 0;
		String sql = "insert into student (name,sex,age, avator) values(?,?,?,?)";
		try (Connection conn = getConn();
				PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql,
						Statement.RETURN_GENERATED_KEYS)) {
			pstmt.setString(1, student.getName());
			pstmt.setString(2, student.getSex());
			pstmt.setInt(3, student.getAge());

			pstmt.setBlob(4, new ByteArrayInputStream(student.getAvator().getBytes()));
			// TODO 这里的具体用法还需进一步研究。包括Clob对象的处理方式。
			// 另一种写法
			// pstmt.setBinaryStream(4, new ByteArrayInputStream(student.getAvator().getBytes()));

			i = pstmt.executeUpdate();
			// 获取生成的主键值
			ResultSet generatedKeys = pstmt.getGeneratedKeys();
			while (generatedKeys.next()) {
				student.setId(generatedKeys.getInt(1));
			}
			System.out.println("Insert result: " + student.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	/**
	 * 更新
	 * 
	 * @param student
	 * @return 更新的行数
	 */
	public static int update(Student student) {
		int i = 0;
		String sql = "update student set Age='" + student.getAge() + "' where Name='" + student.getName() + "'";
		try (Connection conn = getConn(); PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql)) {
			i = pstmt.executeUpdate();
			System.out.println("Update rows: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public static Integer getAll() {
		int count = 0;
		String sql = "select * from student";
		try (Connection conn = getConn(); PreparedStatement pstmt = (PreparedStatement) conn.prepareStatement(sql)) {
			ResultSet rs = pstmt.executeQuery();
			ResultSetMetaData metaData = rs.getMetaData();
			int col = metaData.getColumnCount();
			System.out.println("============================================");
			for (int i = 1; i <= col; i++) {
				if (i > 1)	{
					System.out.print("\t");
				}
				String columnLabel = metaData.getColumnLabel(i);
				System.out.print(columnLabel);
			}
			System.out.println();
			System.out.println("============================================");
			
			while (rs.next()) {
				count++;
				for (int i = 1; i <= col; i++) {
					if (i == 5) {
						Blob blob = rs.getBlob(5);
						try {
							byte[] bytes = StreamUtils.readToBytes(blob.getBinaryStream());
							String value = new String(bytes);
							System.out.print(value + "\t");
						} catch (IOException e) {
							e.printStackTrace();
						}
					} else {
						System.out.print(rs.getString(i) + "\t");
					}
				}
				System.out.println("");
			}
			System.out.println("============================================");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	/**
	 * 删除
	 * 
	 * @param name
	 * @return 删除的行数
	 */
	public static int delete(String name) {
		Connection conn = null;
		int i = 0;
		String sql = "delete from student where Name='" + name + "'";
		PreparedStatement pstmt = null;
		try {
			conn = getConn();
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			i = pstmt.executeUpdate();
			System.out.println("Delete rows: " + i);
			pstmt.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return i;
	}
}
