package gui;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JanelaSobre extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabs = new JTabbedPane();
	
	public JanelaSobre(){
		setTitle("Sobre Application Student");
		setSize(400, 300);
	}
	
	public void montarJanelaSobre(){
		JPanel panel1 = new JPanel();
	    tabs.addTab("Tab 1", panel1);   
	    tabs.setMnemonicAt(0, KeyEvent.VK_1);   
	      
	    JPanel panel2 = new JPanel();   
	    tabs.addTab("Tab 2", panel2);
	    tabs.setMnemonicAt(1, KeyEvent.VK_2);   
	      
	    //Add the tabbed pane to this panel.   
	    add(tabs);
	    setVisible(true);
	    setResizable(false);
	    setLocationRelativeTo(null);
	    setModal(true);
	    
	    addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
}
