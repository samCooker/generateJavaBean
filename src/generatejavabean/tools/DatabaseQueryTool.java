package generatejavabean.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import generatejavabean.configbeans.ConnectionData;

public class DatabaseQueryTool {

	// mysql
	public static final String SHOW_TABLES_MYSQL = "select table_name from information_schema.tables where table_schema=?";

	// oracle
	public static final String SHOW_TABLES_ORCL = "select table_name from user_tables where tablespace_name=?";

	public static List<String> getTablesList(ConnectionData connData) {
		ResultSet rs = null;
		PreparedStatement pre = null;
		Connection conn = null;
		List<String> tablesList = new ArrayList<>();
		try {
			conn = DatabaseConnTool.getConnection(connData);
			if (ConfigTools.DB_TYPE_MYSQL.equals(connData.getDbType())) {
				pre = conn.prepareStatement(SHOW_TABLES_MYSQL);
			} else if (ConfigTools.DB_TYPE_ORCL.equals(connData.getDbType())) {
				pre = conn.prepareStatement(SHOW_TABLES_MYSQL);
			}
			pre.setObject(1, connData.getSchemaName());
			rs = pre.executeQuery();
			while (rs.next()) {
				tablesList.add(rs.getString(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (pre != null) {
					pre.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return tablesList;
	}
}
