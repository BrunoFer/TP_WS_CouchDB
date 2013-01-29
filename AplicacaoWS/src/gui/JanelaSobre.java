package gui;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class JanelaSobre extends JDialog{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTabbedPane tabs = new JTabbedPane();
	
	public JanelaSobre(){
		setTitle("Sobre Application Contact");
		setSize(510,400);
	}
	
	public void montarJanelaSobre(){
		JPanel panel1 = new JPanel();
		
		JLabel projeto = new JLabel();
		String texto1 = "<html><b>Aplicação desenvolvida para a aula de WebServices.</b> <br><br>" +
				"O objetivo da aplicação era utilizar um banco de dados não relacional,porém<br>" +
				"a comunicação da aplicação com o banco deveria ser usando os métodos <br>" +
				"do Protocolo HTTP (GET,POST,PUT e DELETE), através do REST.<br><br>" +
				"<b>Ferramentas utilizadas:</b> <br>" +
				" <b>-</b> CouchDB: Banco de dados não relacional, da empresa Apache;<br>" +
				" <b>-</b> Biblioteca HTTPClient, da empresa Apache;<br>" +
				" <b>-</b> Biblioteca JSON;<br>" +
				" <b>-</b> IDE Eclipse Indigo.</html>";
		projeto.setText(texto1);
		panel1.add(projeto);
	    tabs.addTab("Projeto", panel1);   
	    tabs.setMnemonicAt(0, KeyEvent.VK_1);
	    
	    
	    JLabel desenvolvimento = new JLabel();
		String texto2 = "<html><b>Desenvolvido por:</b><br><br>" +
				"<b>Alunos:</b>Bruno F. da Costa e Guilherme J. A. Moreira<br>" +
				"<b>Professor:</b>Rafael Alencar<br>" +
				"<b>Curso:</b>Superior em Sistemas para Internet<br>" +
				"<b>Disciplina:</b>WebServices<br>" +
				"<b>Intituto Federal de Educação, Ciência e Tecnologia do Sudeste<br>" +
				"de Minas Gerais - Câmpus Barbacena</b><br>" +
				"<b>Data de Entrega:</b>05/02/2013</html>";
		desenvolvimento.setText(texto2);
		JPanel panel2 = new JPanel(); 
		panel2.add(desenvolvimento);
	    tabs.addTab("Desenvolvimento", panel2);
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
