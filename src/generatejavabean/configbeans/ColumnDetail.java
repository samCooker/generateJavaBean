/*
 * FileName:    ColumnDetail.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2015
 * History:     2015年8月24日 (Shicx) 1.0 Create
 */

package generatejavabean.configbeans;

import java.util.HashMap;
import java.util.Map;

import generatejavabean.tools.ConfigTools;

/**
 * @author Shicx
 *
 */
public class ColumnDetail {

    /** 字段名 */
    private String columnName;
    /** 字段类型 */
    private String columnType;
    /** 字段名格式化 */
    private String colNameFmt;

    /**
     * oracle,mysql数据库列类型对应的java类型
     * 
     * @return
     */
    public static Map<String, String> dataTypeToJavaType() {
        Map<String, String> typeMap = new HashMap<String, String>();
        // oracle
        typeMap.put("NUMBER", "Long");
        typeMap.put("TIMESTAMP(6)", "Date");
        // mysql
        typeMap.put("INT", "Integer");
        typeMap.put("INTEGER", "Integer");
        typeMap.put("DOUBLE", "Double");
        typeMap.put("FLOAT", "Float");
        typeMap.put("DATETIME", "Date");
        typeMap.put("TIMESTAMP", "Date");

        typeMap.put("DATE", "Date");
        return typeMap;
    }

    /**
     * @return the columnName
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * @param columnName
     *            the columnName to set
     */
    public void setColumnName(String columnName) {
        this.columnName = columnName;
        this.colNameFmt = ConfigTools.toJavaBeanName(columnName);
    }

    /**
     * @return the columnType
     */
    public String getColumnType() {
        return columnType;
    }

    /**
     * 根据oracle列数据类型转成对应的java类型
     * 
     * @param columnType
     *            the columnType to set
     */
    public void setColumnType(String columnType) {
        Map<String, String> typeMap = dataTypeToJavaType();
        String javaType = typeMap.get(columnType);
        if (javaType == null) {
            javaType = "String";// 为空则设置为String类型
        }
        this.columnType = javaType;
    }

    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ColumnDetail [columnName=" + columnName + ", columnType=" + columnType + "]";
    }

    /**
     * 返回规范化的名称
     */
    public String getColNameFmt() {
        return colNameFmt;
    }

    /**
     * 返回首字母大写的名称
     * 
     * @return
     */
    public String getUpColNameFmt() {
        return ConfigTools.upperFirstChar(colNameFmt);
    }

}
