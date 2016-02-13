package generatejavabean.configbeans;

import java.util.List;

import generatejavabean.tools.ConfigTools;

public class JavaBeanData {

    /** 主键名 */
    private String             primaryColName;
    /** 包名 */
    private String             packageName;
    /** 表空间名 */
    private String             schemaName;
    /** 表名 */
    private String             tableName;
    /** 字段信息 */
    private List<ColumnDetail> columnDetailList;

    public List<ColumnDetail> getColumnDetailList() {
        return columnDetailList;
    }

    public void setColumnDetailList(List<ColumnDetail> columnDetailList) {
        this.columnDetailList = columnDetailList;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getSchemaName() {
        return schemaName;
    }

    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getPrimaryColName() {
        return primaryColName;
    }

    public void setPrimaryColName(String primaryColName) {
        this.primaryColName = primaryColName;
    }

    /**
     * 格式表名
     * 
     * @return
     */
    public String getTableNameFmt() {
        return ConfigTools.toJavaClassName(tableName);
    }

    /**
     * 格式字段名
     * 
     * @return
     */
    public String getPrimaryColNameFmt() {
        return ConfigTools.toJavaBeanName(primaryColName);
    }

}
