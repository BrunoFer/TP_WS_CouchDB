package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class TratadorEventosMenu implements ActionListener {

	private JanelaPrincipal janelaPrincipal;
	
	public TratadorEventosMenu(JanelaPrincipal janelaPrincipal) {
		this.janelaPrincipal = janelaPrincipal;
	}

	public void actionPerformed(ActionEvent e){
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
			
		} else if (e.getSource() == janelaPrincipal.getMenuJanela().getExit()) {
			System.exit(0);
		}
	}

}
