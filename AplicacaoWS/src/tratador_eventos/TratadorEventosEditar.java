package tratador_eventos;

import gui.JanelaEditar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import aplicacao.AcessoBanco;

public class TratadorEventosEditar implements ActionListener{

	private AcessoBanco acessoBanco;
	private JanelaEditar janelaEditar;
	private String json;
	
	public TratadorEventosEditar(AcessoBanco acessoBanco, JanelaEditar janelaEditar){
		this.acessoBanco = acessoBanco;
		this.janelaEditar = janelaEditar;
	}
	
	@Override
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource()==janelaEditar.getBotaoCancelar()){
			janelaEditar.dispose();
		} else if (evento.getSource() == janelaEditar.getBotaoSalvar()){
			json = montarJson();
			acessoBanco.atualizarAluno(janelaEditar.getNumeroDocumento(),json);
			janelaEditar.dispose();
		}
	}
	
	public String montarJson(){
		String json = "";
		json += "\"nome\": \"" + janelaEditar.getTextoNome().getText() + "\"";
		json += ", \"telefone\": \"" + janelaEditar.getTextoTel().getText()
				+ "\"";
		json += ", \"idade\": "
				+ Integer
						.parseInt(janelaEditar.getTextoIdade().getText());
		if (janelaEditar.getMasc().isSelected())
			json += ", \"sexo\": \"M\"}";
		else
			json += ", \"sexo\": \"F\"}";
		
		return json;
	}
}
