/*
 * FileName:    WindowTest.java
 * Description:
 * Company:     南宁超创信息工程有限公司
 * Copyright:   ChaoChuang (c) 2016
 * History:     2016年1月10日 (Administrator) 1.0 Create
 */

package test;

import java.awt.CardLayout;

import javax.swing.JFrame;

import generatejavabean.popup.actions.GenerateJavaBeanAction;
import generatejavabean.ui.DbConnPanel;
import generatejavabean.ui.SelectTablePanel;

/**
 * @author Administrator
 *
 */
public class WindowTest {

	public static void main(String args[]) {
		JFrame mainFrame = new JFrame(GenerateJavaBeanAction.MAIN_TITLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout cardLayout = new CardLayout();
		DbConnPanel panel1 = new DbConnPanel();
		SelectTablePanel panel2 = new SelectTablePanel();
		mainFrame.getContentPane().setLayout(cardLayout);
		mainFrame.getContentPane().add(panel2, GenerateJavaBeanAction.TABLE_PANEL_NAME);
		mainFrame.getContentPane().add(panel1, GenerateJavaBeanAction.DB_PANEL_NAME);
		mainFrame.pack();
		mainFrame.setVisible(true);
		cardLayout.show(mainFrame.getContentPane(), GenerateJavaBeanAction.DB_PANEL_NAME);
	}
}
