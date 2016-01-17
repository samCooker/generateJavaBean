/*
 * FileName:    ConfigTools.java
 * Description:
 * Company:     ����������Ϣ�������޹�˾
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016��1��10�� (Administrator) 1.0 Create
 */

package generatejavabean.tools;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

import org.eclipse.core.resources.IFolder;

import generatejavabean.Activator;
import generatejavabean.configbeans.ConnectionData;

/**
 * @author Administrator
 *
 */
public class ConfigTools {

	/** ��ǰ�ļ��ж��� */
	public static IFolder folder;
	public static final String CONN_DATA_SAVE_PATH = "/connectionData4Connection.properties";
	public static final String CONN_DATA_SAVE_DESCRIPT = "database connection properties";

	public static final String DB_TYPE_MYSQL = "MySQL";
	public static final String DB_TYPE_ORCL = "Oracle";

	/**
	 * ��ȡ��·��
	 * 
	 * @return
	 */
	public static String getFolderPath() {
		if (folder != null) {
			return folder.getLocation().toOSString();
		}
		return null;
	}

	/**
	 * ����������Ϣ
	 * 
	 * @param connData
	 */
	public static void saveDbConfig(ConnectionData connData) {
		if (connData == null) {
			throw new RuntimeException("������ϢΪ��.");
		}
		Properties p = new Properties();
		String filePath = Activator.getDefault().getStateLocation().toOSString() + CONN_DATA_SAVE_PATH;
		OutputStream os = null;
		Field[] fields = connData.getClass().getDeclaredFields();
		// 1.��ȡ������Ϣ������properties��
		for (Field field : fields) {
			boolean accessible = field.isAccessible();
			// ��������ֵ�ɷ���
			if (!accessible) {
				field.setAccessible(true);
			}
			try {
				Object fieldVal = field.get(connData);
				p.put(field.getName(), fieldVal == null ? "" : fieldVal.toString());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
			// �ָ����ԵĿɷ�����
			if (!accessible) {
				field.setAccessible(false);
			}
		}
		// 2.���浽properties�ļ���
		try {
			os = new FileOutputStream(filePath);
			p.store(os, CONN_DATA_SAVE_DESCRIPT);
			os.flush();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (os != null) {
				try {
					os.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * ��ȡ������Ϣ
	 * 
	 * @return
	 */
	public static ConnectionData getDbConfig() {
		ConnectionData connData = new ConnectionData();
		String filePath = Activator.getDefault().getStateLocation().toOSString() + CONN_DATA_SAVE_PATH;
		File propertiesFile = new File(filePath);
		if (propertiesFile.exists()) {
			Properties p = new Properties();
			try {
				p.load(new FileInputStream(propertiesFile));
				Field[] fields = connData.getClass().getDeclaredFields();
				for (Field field : fields) {
					try {
						Method method = connData.getClass().getMethod(parSetName(field.getName()), field.getType());
						method.invoke(connData, p.getProperty(field.getName()));
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return connData;
	}

	/**
	 * ƴ��ĳ���Ե� get����
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String parGetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}

	/**
	 * ƴ����ĳ���Ե� set����
	 * 
	 * @param fieldName
	 * @return String
	 */
	public static String parSetName(String fieldName) {
		if (null == fieldName || "".equals(fieldName)) {
			return null;
		}
		return "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	}
}
