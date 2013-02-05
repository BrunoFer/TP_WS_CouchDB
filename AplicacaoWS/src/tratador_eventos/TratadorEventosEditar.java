package tratador_eventos;

import gui.JanelaEditar;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import validacao_campos.ValidaData;

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
			String nome = janelaEditar.getTextoNome().getText();
			nome = nome.replace(" ", "");
			String data = janelaEditar.getTextoDataNascimento().getText();
			String telRes = janelaEditar.getTextoTelRes().getText();
			String telCel = janelaEditar.getTextoTelCel().getText();
			boolean passou = true;
			System.out.println(data);

			if (nome.isEmpty()) {
				JOptionPane.showMessageDialog(null,
						"Preencha o campo nome corretamente!",
						"Cadastro de Contato", JOptionPane.ERROR_MESSAGE);
				passou = false;
			} else if (!data.isEmpty()) {
				if (data.length() != 10) {
					JOptionPane.showMessageDialog(null, "Data inválida",
							"Cadastro de Contato", JOptionPane.ERROR_MESSAGE);
					passou = false;
				} else {
					ValidaData valida = new ValidaData();
					String resposta = valida.validaData(data);
					if (resposta != null) {
						JOptionPane.showMessageDialog(null, "Data inválida: "
								+ resposta + " incorreto!",
								"Cadastro de Contato",
								JOptionPane.ERROR_MESSAGE);
						passou = false;
					}
				}
			}
			if (passou) {
				if (telRes.isEmpty() && telCel.isEmpty()) {
					JOptionPane.showMessageDialog(null,
							"Informe ao menos um telefone!",
							"Cadastro de Contato", JOptionPane.ERROR_MESSAGE);
					passou = false;
				} else if (!telRes.isEmpty() && !telCel.isEmpty()) {
					if (telRes.length() < 10 || telRes.length() > 11) {
						JOptionPane.showMessageDialog(null,
								"Telefone Residencial inválido",
								"Cadastro de Contato",
								JOptionPane.ERROR_MESSAGE);
						passou = false;
					}
					if (telCel.length() < 10 || telCel.length() > 11) {
						JOptionPane.showMessageDialog(null,
								"Telefone Celular inválido",
								"Cadastro de Contato",
								JOptionPane.ERROR_MESSAGE);
						passou = false;
					}
				} else if (!telCel.isEmpty()) {
					if (telCel.length() < 10 || telCel.length() > 11) {
						JOptionPane.showMessageDialog(null,
								"Telefone Celular inválido",
								"Cadastro de Contato",
								JOptionPane.ERROR_MESSAGE);
						passou = false;
					}
				} else if (!telRes.isEmpty()) {
					if (telRes.length() < 10 || telRes.length() > 11) {
						JOptionPane.showMessageDialog(null,
								"Telefone Residencial inválido",
								"Cadastro de Contato",
								JOptionPane.ERROR_MESSAGE);
						passou = false;
					}
				}
			}

			if (passou) {
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
		json += ", \"datanascimento\": \"" + janelaEditar.getTextoDataNascimento().getText()
				+ "\"";
		json += ", \"telres\": \"" + janelaEditar.getTextoTelRes().getText()
				+ "\"";
		json += ", \"telcel\": \"" + janelaEditar.getTextoTelCel().getText()
				+ "\"";
		json += ", \"cidade\": \"" + janelaEditar.getTextoCidade().getText()+ "\"";
		json += ", \"estado\": \"" + janelaEditar.getComboEstados().getSelectedItem()+ "\"}";
		
		return json;
	}
}
