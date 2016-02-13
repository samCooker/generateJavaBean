package generatejavabean.popup.actions;

import java.awt.CardLayout;

import javax.swing.JFrame;

import org.eclipse.core.resources.IFolder;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IActionDelegate;
import org.eclipse.ui.IObjectActionDelegate;
import org.eclipse.ui.IWorkbenchPart;

import generatejavabean.tools.ConfigTools;
import generatejavabean.ui.DbConnPanel;
import generatejavabean.ui.SelectTablePanel;

public class GenerateJavaBeanAction implements IObjectActionDelegate {

    public static final String   MAIN_TITLE       = "javaBean tool";
    public static final String   DB_PANEL_NAME    = "connectPanel";
    public static final String   TABLE_PANEL_NAME = "selectPanel";
    private JFrame               mainFrame;
    private CardLayout           cardLayout;
    private IStructuredSelection iStructuredSelection;

    /**
     * Constructor for Action1.
     */
    public GenerateJavaBeanAction() {
        super();
    }

    /**
     * @see IObjectActionDelegate#setActivePart(IAction, IWorkbenchPart)
     */
    public void setActivePart(IAction action, IWorkbenchPart targetPart) {
        targetPart.getSite().getShell();
    }

    /**
     * @see IActionDelegate#run(IAction)
     */
    public void run(IAction action) {
        ConfigTools.folder = (IFolder) iStructuredSelection.getFirstElement();
        DbConnPanel dbConnPanel = new DbConnPanel();
        if (mainFrame == null) {
            mainFrame = new JFrame(MAIN_TITLE);
            mainFrame.setLocationRelativeTo(null);
            cardLayout = new CardLayout();
            SelectTablePanel selectTabPanel = new SelectTablePanel();
            mainFrame.getContentPane().setLayout(cardLayout);
            mainFrame.getContentPane().add(dbConnPanel, DB_PANEL_NAME);
            mainFrame.getContentPane().add(selectTabPanel, TABLE_PANEL_NAME);
            mainFrame.pack();
        }
        mainFrame.setVisible(true);
        // 每次都显示连接数据库的面板
        cardLayout.show(mainFrame.getContentPane(), GenerateJavaBeanAction.DB_PANEL_NAME);
    }

    /**
     * @see IActionDelegate#selectionChanged(IAction, ISelection)
     */
    public void selectionChanged(IAction action, ISelection selection) {
        if (selection instanceof IStructuredSelection)
            iStructuredSelection = (IStructuredSelection) selection;
    }

}
