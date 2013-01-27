package tratador_eventos;

import gui.JanelaPrincipal;
import gui.JanelaSobre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

public class TratadorEventosMenu implements ActionListener {

	private JanelaPrincipal janelaPrincipal;
	private JanelaSobre janelaSobre;
	
	public TratadorEventosMenu(JanelaPrincipal janelaPrincipal) {
		this.janelaPrincipal = janelaPrincipal;
	}

	public void actionPerformed(ActionEvent e){
		String aparencia = "";
		if (e.getSource() == janelaPrincipal.getMenuJanela().getCadastro()) {
			janelaPrincipal.limparTela();
			janelaPrincipal.telaCadastrar();
		} else if (e.getSource() == janelaPrincipal.getMenuJanela().getExibir()) {
			try {
				janelaPrincipal.limparTela();
				janelaPrincipal.telaConsultar();
			} catch (IOException e1) {
				System.out.println("Erro IOException - getexibir() - actionPerformed()/TratadorEventosMenu.java");
			}
		} else if (e.getSource()==janelaPrincipal.getMenuJanela().getAplicacao()){
			janelaSobre = new JanelaSobre();
			janelaSobre.montarJanelaSobre();
		} else if (e.getSource() == janelaPrincipal.getMenuJanela().getExit()) {
			System.exit(0);
		} else if (e.getSource() == janelaPrincipal.getMenuJanela().getMetal()){
			aparencia="javax.swing.plaf.metal.MetalLookAndFeel";
		} else if (e.getSource() == janelaPrincipal.getMenuJanela().getMotif()){
			aparencia="com.sun.java.swing.plaf.motif.MotifLookAndFeel";
		} else if (e.getSource() == janelaPrincipal.getMenuJanela().getGtk()){
			aparencia="com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
		} else if (e.getSource() == janelaPrincipal.getMenuJanela().getNimbus()){
			aparencia="com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel";
		}
		try {
			UIManager.setLookAndFeel(aparencia);
			SwingUtilities.updateComponentTreeUI(janelaPrincipal);
		} catch (Exception e1) {
		}
	}
}
