package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

public class TratadorEventosTabela implements MouseListener{
	JanelaPrincipal janela;
	
	public TratadorEventosTabela(JanelaPrincipal janela){
		this.janela = janela;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getClickCount()==2){
			try {
				int linha = janela.getTabelaContatos().getSelectedRow();
				System.out.println("Linha selecionada foi "+linha);
			} catch (IOException e) {
				System.out.println("Erro IOException! - mouseClicked()/TratadorEventosTabela.java");
			}
		} else if (event.getClickCount()==1){
			
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
	}

}
