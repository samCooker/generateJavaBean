/*
 * FileName:    ConnectionData.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月10日 (Administrator) 1.0 Create
 */

package generatejavabean.configbeans;

/**
 * @author Administrator
 * 
 *         数据库连接参数，会保存在文件中，每次打开对话框都从文件获取该参数填入对话框中
 */
public class ConnectionData {

    /** 数据库类型 */
    private String dbType;
    /** 连接地址 */
    private String url;
    /** 用户名 */
    private String userName;
    /** 表空间名 */
    private String schemaName;
    /** 密码 */
    private String pwd;

    /**
     * @return the dbType
     */
    public String getDbType() {
        return dbType;
    }

    /**
     * @param dbType
     *            the dbType to set
     */
    public void setDbType(String dbType) {
        this.dbType = dbType;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     *            the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the userName
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName
     *            the userName to set
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the schemaName
     */
    public String getSchemaName() {
        return schemaName;
    }

    /**
     * @param schemaName
     *            the schemaName to set
     */
    public void setSchemaName(String schemaName) {
        this.schemaName = schemaName;
    }

    /**
     * @return the pwd
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd
     *            the pwd to set
     */
    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    /**
     * (non-Javadoc)
     * 
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "ConnectionData [dbType=" + dbType + ", url=" + url + ", userName=" + userName + ", schemaName=" + schemaName + ", pwd=" + pwd + "]";
    }

}
