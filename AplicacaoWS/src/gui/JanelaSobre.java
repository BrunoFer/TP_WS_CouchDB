package gui;

import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;
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
		setSize(610,500);
	}
	
	public void montarJanelaSobre(){
		JPanel panel1 = new JPanel();
		JLabel labelImagem1, labelImagem2;
		try {
			ImageIcon logoCouch = new ImageIcon(new File(".").getCanonicalPath()+"/img/couchdb.jpg");
			logoCouch.setImage(logoCouch.getImage().getScaledInstance(250, 200, 100));
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
			labelImagem1 = new JLabel(logoCouch);
			panel1.add(labelImagem1);
			panel1.add(projeto);
		    tabs.addTab("Projeto", panel1);   
		    tabs.setMnemonicAt(0, KeyEvent.VK_1);
		    
			ImageIcon logoIF = new ImageIcon(new File(".").getCanonicalPath()+"/img/ifsudestemg.jpg");
			logoIF.setImage(logoIF.getImage().getScaledInstance(300, 180, 100));
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
			labelImagem2 = new JLabel(logoIF);
			JPanel panel2 = new JPanel(); 
			panel2.add(labelImagem2);
			panel2.add(desenvolvimento);
		    tabs.addTab("Desenvolvimento", panel2);
		    tabs.setMnemonicAt(1, KeyEvent.VK_2);
		    
		    //Add the tabbed pane to this panel.   
		    add(tabs);
		    setVisible(true);
		    setResizable(false);
		    setLocationRelativeTo(null);
		    setModal(true);
			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	    
	    addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				dispose();
			}
		});
	}
	
}
