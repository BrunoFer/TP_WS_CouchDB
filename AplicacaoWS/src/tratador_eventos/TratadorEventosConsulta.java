package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TratadorEventosConsulta implements ActionListener{

	private JanelaPrincipal janela;
	
	public TratadorEventosConsulta(JanelaPrincipal janela){
		this.janela = janela;
	}
	
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource()==janela.getBotaoEditar()){
			System.out.println("Botao EDITAR pressionado!");
		} else if (evento.getSource()==janela.getBotaoExcluir()){
			System.out.println("Botao EXCLUIR pressionado!");
		}
	}

}
