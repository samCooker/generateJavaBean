/*
 * FileName:    PropertiesSaveTest.java
 * Description:
 * Company:     ����������Ϣ�������޹�˾
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016��1��10�� (Administrator) 1.0 Create
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
