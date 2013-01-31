package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

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

			if (janela.getTextoNome().getText().equals(""))
				JOptionPane.showMessageDialog(null,
						"Você deve preencher o campo nome!",
						"Cadastro de Contato", JOptionPane.ERROR_MESSAGE);
			else if (janela.getTextoTelRes().getText().equals("")
					&& janela.getTextoTelCel().getText().equals("")) {
				JOptionPane.showMessageDialog(null,
						"Informe ao menos um telefone!", "Cadastro de Contato",
						JOptionPane.ERROR_MESSAGE);
			} else {
				String json = montarJson();
				acessoBanco.setRegistro(json);
				janela.limparDados();
			}

		/*
		 * Caso o botão pressionado na janela de cadastro seja o botão limpar
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
		json += ", \"telres\": \"" + janela.getTextoTelRes().getText() + "\"";
		json += ", \"telcel\": \"" + janela.getTextoTelCel().getText() + "\"";
		json += ", \"cidade\": \"" + janela.getTextoCidade().getText() + "\"";
		json += ", \"estado\": \"" + janela.getComboEstados().getSelectedItem()
				+ "\"}";

		return json;
	}

}
