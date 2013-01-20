package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class TratadorEventosCadastro implements ActionListener {
	private JanelaPrincipal janela;
	public static final String acessoBanco = "http://localhost:28017/";
	public static final String nomeBanco = "bancodobrasil";
	public static final String nomeColecao = "alunos";

	public TratadorEventosCadastro(JanelaPrincipal janela) {
		super();
		this.janela = janela;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == janela.getBotaoSalvar()) {
			System.out.println("Voce clicou no botao salvar");
			String json = "{";
			if (janela.getTextoNome().getText().equals(""))
				JOptionPane.showMessageDialog(null,
						"Você deve preencher o campo nome!",
						"Cadastro de Aluno", JOptionPane.INFORMATION_MESSAGE);
			else {
				if (janela.getTextoIdade().getText().equals(""))
					JOptionPane.showMessageDialog(null,
							"Você deve preencher o campo idade!",
							"Cadastro de Aluno",
							JOptionPane.INFORMATION_MESSAGE);
				else {
					json += "nome: \"" + janela.getTextoNome().getText() + "\"";
					json += ", telefone: \"" + janela.getTextoTel().getText()
							+ "\"";
					json += ", idade: "
							+ Integer
									.parseInt(janela.getTextoIdade().getText());
					if (janela.getMasc().isSelected())
						json += ", sexo: \"M\"}";
					else
						json += ", sexo: \"F\"}";
					System.out.print(json);

					URL url;
					try {
						url = new URL(acessoBanco + nomeBanco + "/"
								+ nomeColecao);
						HttpURLConnection httpCon = (HttpURLConnection) url
								.openConnection();
						httpCon.setDoOutput(true);
						httpCon.setRequestMethod("PUT");
						OutputStreamWriter out = new OutputStreamWriter(
								httpCon.getOutputStream());
						out.write(json);
						JOptionPane.showMessageDialog(null, "Tentativa de gravar no banco efetuada!",
								"Cadastro de alunos",
								JOptionPane.INFORMATION_MESSAGE);
						out.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

		} else if (e.getSource() == janela.getBotaoLimpar()) {
			System.out.println("Voce clicou no botao limpar");
			janela.getTextoNome().setText("");
			janela.getTextoIdade().setText("");
			janela.getTextoTel().setText("");
		}
	}
}
