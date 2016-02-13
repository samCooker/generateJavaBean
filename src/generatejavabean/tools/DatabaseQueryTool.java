package generatejavabean.tools;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ListModel;

import generatejavabean.configbeans.ColumnDetail;
import generatejavabean.configbeans.ConnectionData;
import generatejavabean.configbeans.JavaBeanData;

public class DatabaseQueryTool {

    // mysql
    public static final String SELECT_TABLES_MYSQL                = "select table_name from information_schema.tables where table_schema=?";
    // 获取主键列
    public static final String SELECT_PRIMARY_COLUMN_MYSQL        = "select column_name from information_schema.columns where table_name=? and column_key='PRI'";
    // 获取除了主键外的所有列信息
    public static final String SELECT_COLUMN_NAME_WITHOUT_P_MYSQL = "select column_name,column_type from information_schema.columns where table_name=? and column_name <>?";

    /** oracle */
    // 获取表名
    public static final String SELECT_TABLES_ORCL                 = "select table_name from user_tables where tablespace_name=?";
    // 获取主键列
    public static final String SELECT_PRIMARY_COLUMN              = "select column_name from user_cons_columns where constraint_name = (select constraint_name from user_constraints where table_name = ?  and  constraint_type ='P')";
    // 获取除了主键外的所有列信息
    public static final String SELECT_COLUMN_NAME_WITHOUT_P       = "select column_name,data_type from user_tab_columns where table_name=? and column_name <>?";

    /**
     * 获取表名集合
     * 
     * @param connData
     * @return
     */
    public static List<String> getTablesList(ConnectionData connData) {
        ResultSet rs = null;
        PreparedStatement pre = null;
        Connection conn = null;
        List<String> tablesList = new ArrayList<>();
        try {
            conn = DatabaseConnTool.getConnection(connData);
            if (ConfigTools.DB_TYPE_MYSQL.equals(connData.getDbType())) {
                pre = conn.prepareStatement(SELECT_TABLES_MYSQL);
            } else if (ConfigTools.DB_TYPE_ORCL.equals(connData.getDbType())) {
                pre = conn.prepareStatement(SELECT_TABLES_ORCL);
            }
            pre.setObject(1, connData.getSchemaName().toUpperCase());
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

    /**
     * 获取字段集合
     * 
     * @param connData
     * @return
     */
    public static List<JavaBeanData> getDataList(ConnectionData connData, ListModel<String> tableNames) {
        ResultSet rsPrimary = null;
        ResultSet rsOther = null;
        PreparedStatement pstsPrimaryCol = null;
        PreparedStatement pstsOtherCol = null;
        Connection conn = null;
        try {
            conn = DatabaseConnTool.getConnection(connData);
            if (ConfigTools.DB_TYPE_MYSQL.equals(connData.getDbType())) {
                pstsPrimaryCol = conn.prepareStatement(SELECT_PRIMARY_COLUMN_MYSQL);
                pstsOtherCol = conn.prepareStatement(SELECT_COLUMN_NAME_WITHOUT_P_MYSQL);
            } else if (ConfigTools.DB_TYPE_ORCL.equals(connData.getDbType())) {
                pstsPrimaryCol = conn.prepareStatement(SELECT_PRIMARY_COLUMN);
                pstsOtherCol = conn.prepareStatement(SELECT_COLUMN_NAME_WITHOUT_P);
            }
            List<JavaBeanData> beansList = new ArrayList<>();
            for (int i = 0; i < tableNames.getSize(); i++) {
                JavaBeanData bean = new JavaBeanData();
                List<ColumnDetail> columnDetailList = new ArrayList<>();
                String tableName = tableNames.getElementAt(i);
                bean.setTableName(tableName);
                // 主键
                pstsPrimaryCol.setObject(1, tableName);
                rsPrimary = pstsPrimaryCol.executeQuery();
                rsPrimary.next();
                bean.setPrimaryColName(rsPrimary.getString(1));
                // 其他字段
                pstsOtherCol.setObject(1, tableName);
                pstsOtherCol.setObject(2, rsPrimary.getString(1));
                rsOther = pstsOtherCol.executeQuery();
                while (rsOther.next()) {
                    ColumnDetail column = new ColumnDetail();
                    column.setColumnName(rsOther.getString(1));
                    column.setColumnType(rsOther.getString(2));
                    columnDetailList.add(column);
                }
                bean.setColumnDetailList(columnDetailList);
                beansList.add(bean);
            }
            return beansList;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rsPrimary != null) {
                    rsPrimary.close();
                }
                if (pstsPrimaryCol != null) {
                    pstsPrimaryCol.close();
                }
                if (pstsOtherCol != null) {
                    pstsOtherCol.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
