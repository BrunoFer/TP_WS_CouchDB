package tratador_eventos;

import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import aplicacao.AcessoBanco;

public class TratadorEventosConsulta implements ActionListener{

	private JanelaPrincipal janela;
	private JTable tabelaAlunos;
	private AcessoBanco acessoBanco;
	
	public TratadorEventosConsulta(JanelaPrincipal janela, JTable tabelaAlunos, AcessoBanco acessoBanco){
		this.janela = janela;
		this.tabelaAlunos = tabelaAlunos;
		this.acessoBanco = acessoBanco;
	}
	
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource()==janela.getBotaoEditar()){
			System.out.println("Botao EDITAR pressionado!");
			if (tabelaAlunos.getSelectedRowCount()==1){
				int linhaSelecionada = tabelaAlunos.getSelectedRow();
				acessoBanco.atualizarAluno(linhaSelecionada);
				try {
					janela.limparTela();
					janela.telaConsultar();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} else if (evento.getSource()==janela.getBotaoExcluir()){
			System.out.println("Botao EXCLUIR pressionado!");
			if (tabelaAlunos.getSelectedRowCount()==1){
				int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir aluno?", "Exclusão", JOptionPane.YES_NO_CANCEL_OPTION);
				if (confirma==JOptionPane.YES_OPTION){
					int linhaSelecionada = tabelaAlunos.getSelectedRow();
					try {
						acessoBanco.deletarAluno(linhaSelecionada);
						janela.limparTela();
						janela.telaConsultar();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}

}
