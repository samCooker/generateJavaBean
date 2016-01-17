package generatejavabean.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import generatejavabean.configbeans.ConnectionData;

public class DatabaseConnTool {

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 连接到数据库
	 * 
	 * @param data
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection(ConnectionData data) throws SQLException {
		Connection connection = DriverManager.getConnection(data.getUrl(), data.getUserName(), data.getPwd());
		return connection;
	}

	public static void main(String[] args) throws SQLException {
		ConnectionData data = new ConnectionData();
		data.setUrl("jdbc:mysql://localhost:3306/mysql");
		data.setUserName("root");
		data.setPwd("123456");
		Connection conn = getConnection(data);
		System.out.println(conn.toString());
	}
}
