package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import validacao_campos.ValidaData;

import aplicacao.AcessoBanco;

public class TratadorEventosCadastro implements ActionListener {
	private JanelaPrincipal janela;
	private AcessoBanco acessoBanco;

	public TratadorEventosCadastro(JanelaPrincipal janela,
			AcessoBanco acessoBanco) {
		super();
		this.janela = janela;
		this.acessoBanco = acessoBanco;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		/*
		 * Caso o botão pressionado na janela de cadastro seja o botão salvar
		 */
		if (e.getSource() == janela.getBotaoSalvar()) {
			String nome = janela.getTextoNome().getText();
			nome = nome.replace(" ", "");
			String data = janela.getTextoDataNascimento().getText();
			String telRes = janela.getTextoTelRes().getText();
			String telCel = janela.getTextoTelCel().getText();
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
				String json = montarJson();
				acessoBanco.setRegistro(json);
				janela.limparDados();
			}
			/*
			 * Caso o botão pressionado na janela de cadastro seja o botão
			 * limpar
			 */
		} else if (e.getSource() == janela.getBotaoLimpar()) {
			janela.limparDados();
		}
	}

	/**
	 * Esta função realizada a formatação dos dados recebidos da janela cadastro
	 * de forma que fiquem em formato JSON para a realização da requisição Post
	 * ao CouchDB e retorna a string formatada.
	 * 
	 * @return
	 */
	public String montarJson() {
		String json = "{";
		json += "\"nome\": \"" + janela.getTextoNome().getText() + "\"";
		json += ", \"apelido\": \"" + janela.getTextoApelido().getText() + "\"";
		json += ", \"datanascimento\": \""
				+ janela.getTextoDataNascimento().getText() + "\"";
		json += ", \"telres\": \"" + janela.getTextoTelRes().getText() + "\"";
		json += ", \"telcel\": \"" + janela.getTextoTelCel().getText() + "\"";
		json += ", \"cidade\": \"" + janela.getTextoCidade().getText() + "\"";
		json += ", \"estado\": \"" + janela.getComboEstados().getSelectedItem()
				+ "\"}";

		return json;
	}

}
