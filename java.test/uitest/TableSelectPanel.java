package uitest;

import java.awt.CardLayout;

import javax.swing.JFrame;

import generatejavabean.popup.actions.GenerateJavaBeanAction;
import generatejavabean.ui.DbConnPanel;
import generatejavabean.ui.SelectTablePanel;

public class TableSelectPanel {

	public static void main(String[] args) {
		JFrame mainFrame = new JFrame(GenerateJavaBeanAction.MAIN_TITLE);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		CardLayout cardLayout = new CardLayout();
		DbConnPanel panel1 = new DbConnPanel();
		SelectTablePanel panel2 = new SelectTablePanel();
		mainFrame.getContentPane().setLayout(cardLayout);
		mainFrame.getContentPane().add(panel1, GenerateJavaBeanAction.DB_PANEL_NAME);
		mainFrame.getContentPane().add(panel2, GenerateJavaBeanAction.TABLE_PANEL_NAME);
		mainFrame.pack();
		mainFrame.setVisible(true);
		// 每次都显示连接数据库的面板
		cardLayout.show(mainFrame.getContentPane(), GenerateJavaBeanAction.DB_PANEL_NAME);
	}
}
