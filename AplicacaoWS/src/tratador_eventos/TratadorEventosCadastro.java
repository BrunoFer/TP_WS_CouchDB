package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.JOptionPane.*;

import aplicacao.AcessoBanco;

public class TratadorEventosCadastro implements ActionListener {
	private JanelaPrincipal janela;
	private AcessoBanco acessoBanco;

	public TratadorEventosCadastro(JanelaPrincipal janela, AcessoBanco acessoBanco) {
		super();
		this.janela = janela;
		this.acessoBanco = acessoBanco;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == janela.getBotaoSalvar()) {
			//System.out.println("Voce clicou no botao salvar");
			
			if (janela.getTextoNome().getText().equals(""))
				showMessageDialog(null, "Você deve preencher o campo nome!",
						"Cadastro de Aluno", INFORMATION_MESSAGE);
			else {
				if (janela.getTextoIdade().getText().equals(""))
					showMessageDialog(null, "Você deve preencher o campo idade!",
							"Cadastro de Aluno", INFORMATION_MESSAGE);
				else {
					String json = montarJson();
					try {
						acessoBanco.setRegistro(json);
						janela.limparDados();
					} catch (IOException e1) {
						System.out.println("Erro IOException- botao salvar - actionPerformed()/TratadorEventosCadastro.java");
					}
				}
			}

		} else if (e.getSource() == janela.getBotaoLimpar()) {
			janela.limparDados();
		}
	}
	
	public String montarJson(){
		String json = "{";
		json += "\"nome\": \"" + janela.getTextoNome().getText() + "\"";
		json += ", \"telefone\": \"" + janela.getTextoTel().getText()
				+ "\"";
		json += ", \"idade\": "
				+ Integer
						.parseInt(janela.getTextoIdade().getText());
		if (janela.getMasc().isSelected())
			json += ", \"sexo\": \"M\"}";
		else
			json += ", \"sexo\": \"F\"}";
		
		return json;
	}
	
}
