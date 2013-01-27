package tratador_eventos;

import gui.JanelaEditar;
import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import aplicacao.AcessoBanco;
import aplicacao.Aluno;

public class TratadorEventosConsulta implements ActionListener{

	private JanelaPrincipal janelaPrincipal;
	private JTable tabelaAlunos;
	private AcessoBanco acessoBanco;
	private JanelaEditar janelaEditar;
	private Aluno aluno = new Aluno();
	
	public TratadorEventosConsulta(JanelaPrincipal janelaPrincipal, JTable tabelaAlunos, AcessoBanco acessoBanco){
		this.janelaPrincipal = janelaPrincipal;
		this.tabelaAlunos = tabelaAlunos;
		this.acessoBanco = acessoBanco;
	}
	
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource()==janelaPrincipal.getBotaoEditar()){
			System.out.println("Botao EDITAR pressionado!");
			if (tabelaAlunos.getSelectedRowCount()==1){
				int linhaSelecionada = tabelaAlunos.getSelectedRow();
				aluno.setId((Integer) tabelaAlunos.getValueAt(linhaSelecionada, 0));
				aluno.setNome((String) tabelaAlunos.getValueAt(linhaSelecionada, 1));
				aluno.setTelefone((String) tabelaAlunos.getValueAt(linhaSelecionada, 2));
				aluno.setIdade((Integer) tabelaAlunos.getValueAt(linhaSelecionada, 3));
				aluno.setSexo((String) tabelaAlunos.getValueAt(linhaSelecionada, 4));
				abreJanelaEditar(aluno);
				try {
					janelaPrincipal.limparTela();
					janelaPrincipal.telaConsultar();
				} catch (IOException e) {
					System.out.println("Erro IOException- botao editar - actionPerformed()/TratadorEventosConsulta.java");
				}
			}
		} else if (evento.getSource()==janelaPrincipal.getBotaoExcluir()){
			System.out.println("Botao EXCLUIR pressionado!");
			if (tabelaAlunos.getSelectedRowCount()==1){
				int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir aluno?", "Exclusão", JOptionPane.YES_NO_CANCEL_OPTION);
				if (confirma==JOptionPane.YES_OPTION){
					try {
						int aluno = (Integer) tabelaAlunos.getValueAt(tabelaAlunos.getSelectedRow(), 0);
						acessoBanco.deletarAluno(aluno);
						janelaPrincipal.limparTela();
						janelaPrincipal.telaConsultar();
					} catch (IOException e) {
						System.out.println("Erro IOException - botao excluir - actionPerformed()/TratadorEventosConsulta.java");
					}
				}
			}
		}
	}
	
	public void abreJanelaEditar(Aluno aluno){
		janelaEditar = new JanelaEditar(acessoBanco, aluno);
		janelaEditar.montarJanelaEditar();
	}
}
