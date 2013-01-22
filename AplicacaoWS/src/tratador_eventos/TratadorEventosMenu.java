package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorEventosMenu implements ActionListener {

	private JanelaPrincipal janelaPrincipal;
	
	public TratadorEventosMenu(JanelaPrincipal janelaPrincipal) {
		this.janelaPrincipal = janelaPrincipal;
	}

	public void actionPerformed(ActionEvent e){
		if (e.getSource() == janelaPrincipal.getMenuJanela().getCadastro()) {
			janelaPrincipal.Limpar();
			janelaPrincipal.telaCadastrar();
		} else if (e.getSource() == janelaPrincipal.getMenuJanela().getExibir()) {
			janelaPrincipal.Limpar();
			janelaPrincipal.telaConsultar();
			
		} else if (e.getSource() == janelaPrincipal.getMenuJanela().getExit()) {
			System.exit(0);
		}
	}

}
