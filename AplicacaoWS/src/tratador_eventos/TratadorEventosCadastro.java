package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.*;

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
		if (e.getSource() == janela.getBotaoSalvar()) {
			// System.out.println("Voce clicou no botao salvar");

			if (janela.getTextoNome().getText().equals(""))
				showMessageDialog(null, "Você deve preencher o campo nome!",
						"Cadastro de Contato", INFORMATION_MESSAGE);
			else {
				String json = montarJson();
				acessoBanco.setRegistro(json);
				janela.limparDados();
			}

		} else if (e.getSource() == janela.getBotaoLimpar()) {
			janela.limparDados();
		}
	}

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
