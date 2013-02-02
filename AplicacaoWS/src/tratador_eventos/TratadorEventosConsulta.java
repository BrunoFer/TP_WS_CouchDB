package tratador_eventos;

import gui.JanelaEditar;
import gui.JanelaPrincipal;
import gui.TabelaContatos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	private Contato contato;

	public TratadorEventosConsulta(JanelaPrincipal janelaPrincipal,
			JTable tabela, AcessoBanco acessoBanco,
			TabelaContatos tabelaContatos) {
		this.janelaPrincipal = janelaPrincipal;
		this.tabela = tabela;
		this.acessoBanco = acessoBanco;
		this.tabelaContatos = tabelaContatos;
	}

	public void actionPerformed(ActionEvent evento) {
		
		//Caso o botão selecionado na janela consulta seja o botão editar.
		
		if (evento.getSource() == janelaPrincipal.getBotaoEditar()) {
			// System.out.println("Botao EDITAR pressionado!");
			if (tabela.getSelectedRowCount() == 1) {
				int linhaSelecionada = tabela.getSelectedRow();
				contato = new Contato();
				contato = tabelaContatos.getContato(linhaSelecionada);
				abreJanelaEditar(contato);
				janelaPrincipal.limparTela();
				janelaPrincipal.telaConsultar("todos");
			}
		
		// Caso o botão selecionado na janela Consulta seja o botão excluir
			
		} else if (evento.getSource() == janelaPrincipal.getBotaoExcluir()) {
			// System.out.println("Botao EXCLUIR pressionado!");
			if (tabela.getSelectedRowCount() == 1) {
				int confirma = JOptionPane.showConfirmDialog(null,
						"Deseja excluir contato?", "Exclusão",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (confirma == JOptionPane.YES_OPTION) {
					String contato = (String) tabelaContatos.getValueAt(
							tabela.getSelectedRow(), 0);
					acessoBanco.deletarContato(contato);
					janelaPrincipal.limparTela();
					janelaPrincipal.telaConsultar("todos");
				}
			}

		// Caso o botão selecionado na janela de consulta seja o botão cadastrar
			
		} else if (evento.getSource() == janelaPrincipal.getBotaoCadastrar()) {
			janelaPrincipal.limparTela();
			janelaPrincipal.telaCadastrar();
			
		// Caso o botão selecionado na janela de consulta seja o botão condição	
		
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
				} else {
					condicao += "&limit=1";
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

			janelaPrincipal.limparTela();
			janelaPrincipal.telaConsultar(condicao);
		}
	}

	/**
	 * Esse método será acionado sempre que o botão editar for pressionado.
	 * Ela recebe o objeto Contato instanciado com os dados da linha selecionada
	 * da tabela e faz a chamada a função montarJanelaEditar() da classe JanelaEditar.
	 * 
	 * @param contato
	 */
	public void abreJanelaEditar(Contato contato) {
		janelaEditar = new JanelaEditar(acessoBanco, contato);
		janelaEditar.montarJanelaEditar();
	}
}
