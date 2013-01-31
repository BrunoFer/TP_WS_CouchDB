package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class TratadorEventosTabela implements MouseListener{
	JanelaPrincipal janela;
	
	public TratadorEventosTabela(JanelaPrincipal janela){
		this.janela = janela;
	}
	
	@Override
	public void mouseClicked(MouseEvent event) {
		if(event.getClickCount()==2){
			int linha = janela.getTabelaContatos("todos").getSelectedRow();
			System.out.println("Linha selecionada foi "+linha);
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
