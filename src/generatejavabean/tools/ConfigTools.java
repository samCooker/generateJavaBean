/*
 * FileName:    ConfigTools.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月10日 (Administrator) 1.0 Create
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
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;

import generatejavabean.Activator;
import generatejavabean.configbeans.ConnectionData;

/**
 * @author Administrator
 *
 */
public class ConfigTools {

    /** 当前文件夹对象 */
    public static IFolder      folder;
    public static final String CONN_DATA_SAVE_PATH     = "/connectionData4Connection.properties";
    public static final String CONN_DATA_SAVE_DESCRIPT = "database connection properties";

    public static final String DB_TYPE_MYSQL           = "MySQL";
    public static final String DB_TYPE_ORCL            = "Oracle";

    /**
     * 获取包路径
     * 
     * @return
     */
    public static String getFolderPath() {
        if (folder != null) {
            return folder.getLocation().toOSString();
        }
        return null;
    }

    public static void refreshLocal() {
        try {
            folder.refreshLocal(IResource.DEPTH_INFINITE, null);
        } catch (CoreException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据包路径获取包名
     * 
     * @param folderPath
     * @return
     */
    public static String getPackageName(String folderPath) {
        if (folderPath != null && folderPath.trim() != "") {
            StringBuilder builder = new StringBuilder("");
            String[] paths = folderPath.split("\\\\");
            int start = 1;
            for (int i = 0; i < paths.length; ++i) {
                if ("src".equals(paths[i])) {
                    start = i + 1;
                }
                if ("base".equals(paths[i])) {
                    start = i + 1;
                }
                // maven路径
                if ("java".equals(paths[i])) {
                    start = i + 1;
                }
            }
            for (int i = start; i < paths.length; i++) {
                builder.append(paths[i] + ".");
            }
            if (builder.lastIndexOf(".") != -1) {
                builder.deleteCharAt(builder.lastIndexOf("."));
            }
            return builder.toString();
        }

        return null;
    }

    /**
     * 替换包路径，如 aaa.bbb.ccc 替换ccc成ddd ==> aaa.bbb.ddd
     * 
     * @param packageName
     * @param replaceName
     * @return
     */
    public static String replaceLastPackageName(String packageName, String replaceName) {
        if (packageName == null) {
            return null;
        }
        int i = packageName.lastIndexOf(".");
        if (i == -1) {
            return packageName + "." + replaceName;
        }
        return packageName.substring(0, i + 1) + replaceName;
    }

    /**
     * 保存连接信息
     * 
     * @param connData
     */
    public static void saveDbConfig(ConnectionData connData) {
        if (connData == null) {
            throw new RuntimeException("连接信息为空.");
        }
        Properties p = new Properties();
        String filePath = Activator.getDefault().getStateLocation().toOSString() + CONN_DATA_SAVE_PATH;
        OutputStream os = null;
        Field[] fields = connData.getClass().getDeclaredFields();
        // 1.获取连接信息，放入properties中
        for (Field field : fields) {
            boolean accessible = field.isAccessible();
            // 设置属性值可访问
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
            // 恢复属性的可访问性
            if (!accessible) {
                field.setAccessible(false);
            }
        }
        // 2.保存到properties文件中
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
     * 获取连接信息
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
     * 拼接某属性的 get方法
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
     * 拼接在某属性的 set方法
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

    /**
     * 将数据库的名称转成驼峰命名的格式，如table_name==>tableName
     * 
     * @param string
     * @return
     */
    public static String toJavaBeanName(String name) {
        if (name == null || name.trim() == "") {
            return null;
        }
        name = name.toLowerCase();
        String[] sns = name.split("_");
        if (sns.length > 0) {
            StringBuffer sb = new StringBuffer(sns[0]);
            for (int i = 1; i < sns.length; i++) {
                sb.append(upperFirstChar(sns[i]));
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 将数据库的名称转成驼峰命名的格式，如table_name==>TableName
     * 
     * @param string
     * @return
     */
    public static String toJavaClassName(String name) {
        if (name == null || name.trim() == "") {
            return null;
        }
        name = name.toLowerCase();
        String[] sns = name.split("_");
        if (sns.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < sns.length; i++) {
                sb.append(upperFirstChar(sns[i]));
            }
            return sb.toString();
        }
        return null;
    }

    /**
     * 首字母大写
     * 
     * @param src
     * @return
     */
    public static String upperFirstChar(String src) {
        if (src == null || src.length() == 0) {
            return "";
        }
        if (src.length() == 1) {
            return src.toUpperCase();
        }
        return src.substring(0, 1).toUpperCase().concat(src.substring(1));
    }

}
