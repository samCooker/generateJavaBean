/*
 * FileName:    PropertiesSaveTest.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月10日 (Administrator) 1.0 Create
 */

package test;

import generatejavabean.configbeans.ConnectionData;
import generatejavabean.tools.ConfigTools;

/**
 * @author Administrator
 *
 */
public class PropertiesSaveTest {

    public static void main(String[] args) {
        // ConnectionData connData = new ConnectionData();
        // connData.setDbType("oracle");
        ConnectionData connData = ConfigTools.getDbConfig();
        System.out.println(connData.toString());
    }
}
