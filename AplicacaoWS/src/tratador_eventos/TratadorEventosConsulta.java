package tratador_eventos;

import gui.JanelaEditar;
import gui.JanelaPrincipal;
import gui.TabelaContatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import aplicacao.AcessoBanco;
import aplicacao.Contato;

public class TratadorEventosConsulta implements ActionListener {

	private JanelaPrincipal janelaPrincipal;
	private JTable tabela;
	private TabelaContatos tabelaContatos;
	private AcessoBanco acessoBanco;
	private JanelaEditar janelaEditar;
	private Contato contato = new Contato();

	public TratadorEventosConsulta(JanelaPrincipal janelaPrincipal,
			JTable tabela, AcessoBanco acessoBanco,
			TabelaContatos tabelaContatos) {
		this.janelaPrincipal = janelaPrincipal;
		this.tabela = tabela;
		this.acessoBanco = acessoBanco;
		this.tabelaContatos = tabelaContatos;
	}

	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource() == janelaPrincipal.getBotaoEditar()) {
			// System.out.println("Botao EDITAR pressionado!");
			if (tabela.getSelectedRowCount() == 1) {
				int linhaSelecionada = tabela.getSelectedRow();
				contato = tabelaContatos.getContato(linhaSelecionada);
				abreJanelaEditar(contato);
				try {
					janelaPrincipal.limparTela();
					janelaPrincipal.telaConsultar("todos");
				} catch (IOException e) {
					System.out
							.println("Erro IOException- botao editar - actionPerformed()/TratadorEventosConsulta.java");
				}
			}
		} else if (evento.getSource() == janelaPrincipal.getBotaoExcluir()) {
			// System.out.println("Botao EXCLUIR pressionado!");
			if (tabela.getSelectedRowCount() == 1) {
				int confirma = JOptionPane.showConfirmDialog(null,
						"Deseja excluir contato?", "Exclusão",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (confirma == JOptionPane.YES_OPTION) {
					try {
						int contato = (Integer) tabelaContatos.getValueAt(
								tabela.getSelectedRow(), 0);
						acessoBanco.deletarContato(contato);
						janelaPrincipal.limparTela();
						janelaPrincipal.telaConsultar("todos");
					} catch (IOException e) {
						System.out
								.println("Erro IOException - botao excluir - actionPerformed()/TratadorEventosConsulta.java");
					}
				}
			}
		} else if (evento.getSource() == janelaPrincipal.getBotaoCadastrar()) {
			janelaPrincipal.limparTela();
			janelaPrincipal.telaCadastrar();
		} else if (evento.getSource() == janelaPrincipal.getBotaoCondicao()) {
			String condicao = "";
			String inicio = janelaPrincipal.getRegistroInicio().getText();
			String limite = janelaPrincipal.getTextoLimite().getText();
			if (!inicio.equals("")) {
				condicao += "?startkey=\"" + inicio + "\"";
				if (!limite.equals("")) {
					try {
						int i = Integer.parseInt(limite);
						condicao += "&limit=" + i;
					} catch (NumberFormatException event) {
						JOptionPane.showMessageDialog(null,
								"Limite deve ser um inteiro",
								"Edição de contato", JOptionPane.ERROR_MESSAGE);
					}
				}
				if (janelaPrincipal.getDecrescente().isSelected())
					condicao += "&descending=true";
			} else if (!limite.equals("")) {
				try {
					int i = Integer.parseInt(limite);
					condicao += "?limit=" + i;
					if (janelaPrincipal.getDecrescente().isSelected())
						condicao += "&descending=true";
				} catch (NumberFormatException event) {
					JOptionPane.showMessageDialog(null,
							"Limite deve ser um inteiro", "Edição de contato",
							JOptionPane.ERROR_MESSAGE);
				}
			} else {
				if (janelaPrincipal.getDecrescente().isSelected())
					condicao += "?descending=true";
			}

			try {
				janelaPrincipal.limparTela();
				janelaPrincipal.telaConsultar(condicao);
			} catch (IOException e) {
				System.out
						.println("Erro de condicao! - TratadorEventosConsulta");
			}
		}
	}

	public void abreJanelaEditar(Contato contato) {
		janelaEditar = new JanelaEditar(acessoBanco, contato);
		janelaEditar.montarJanelaEditar();
	}
}
