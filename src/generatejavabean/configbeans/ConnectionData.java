/*
 * FileName:    ConnectionData.java
 * Description:
 * Company:     ����������Ϣ�������޹�˾
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016��1��10�� (Administrator) 1.0 Create
 */

package generatejavabean.configbeans;

/**
 * @author Administrator
 * 
 *         ���ݿ����Ӳ������ᱣ�����ļ��У�ÿ�δ򿪶Ի��򶼴��ļ���ȡ�ò�������Ի�����
 */
public class ConnectionData {

	/** ���ݿ����� */
	private String dbType;
	/** ���ӵ�ַ */
	private String url;
	/** �û��� */
	private String userName;
	/** ��ռ��� */
	private String schemaName;
	/** ���� */
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
		return "ConnectionData [dbType=" + dbType + ", url=" + url + ", userName=" + userName + ", schemaName="
				+ schemaName + ", pwd=" + pwd + "]";
	}

}
