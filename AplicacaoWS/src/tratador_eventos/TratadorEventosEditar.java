package tratador_eventos;

import gui.JanelaEditar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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
			if (janelaEditar.getTextoNome().getText().equals("")){
				JOptionPane.showMessageDialog(null, "Preencha campo nome!", "Edição de contato", JOptionPane.ERROR_MESSAGE);
			} else if ( janelaEditar.getTextoTelRes().getText().equals("") && janelaEditar.getTextoTelCel().getText().equals("")){
				JOptionPane.showMessageDialog(null, "Informe ao menos um telefone!", "Edição de contato", JOptionPane.ERROR_MESSAGE);
			} else{
				json = montarJson();
				acessoBanco.atualizarContato(janelaEditar.getIdDocumento(),json);
				janelaEditar.dispose();
			}
		}
	}
	
	/**
	 * Esta função realizada a formatação dos dados recebidos da janela editar
	 * de forma que fiquem em formato JSON para a realização da requisição Post
	 * ao CouchDB e retorna a string formatada.
	 * 
	 * @return
	 */
	
	public String montarJson(){
		String json = "";
		json += "\"nome\": \"" + janelaEditar.getTextoNome().getText() + "\"";
		json += ", \"apelido\": \"" + janelaEditar.getTextoApelido().getText() + "\"";
		json += ", \"telres\": \"" + janelaEditar.getTextoTelRes().getText()
				+ "\"";
		json += ", \"telcel\": \"" + janelaEditar.getTextoTelCel().getText()
				+ "\"";
		json += ", \"datanascimento\": \"" + janelaEditar.getTextoDataNascimento().getText()
				+ "\"";
		json += ", \"cidade\": \"" + janelaEditar.getTextoCidade().getText()+ "\"";
		json += ", \"estado\": \"" + janelaEditar.getComboEstados().getSelectedItem()+ "\"}";
		
		return json;
	}
}
