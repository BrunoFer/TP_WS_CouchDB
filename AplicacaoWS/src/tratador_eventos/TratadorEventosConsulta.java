package tratador_eventos;

import gui.JanelaEditar;
import gui.JanelaPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JOptionPane;
import javax.swing.JTable;

import aplicacao.AcessoBanco;
import aplicacao.Contato;

public class TratadorEventosConsulta implements ActionListener{

	private JanelaPrincipal janelaPrincipal;
	private JTable tabelaContatos;
	private AcessoBanco acessoBanco;
	private JanelaEditar janelaEditar;
	private Contato contato = new Contato();
	
	public TratadorEventosConsulta(JanelaPrincipal janelaPrincipal, JTable tabelaContatos, AcessoBanco acessoBanco){
		this.janelaPrincipal = janelaPrincipal;
		this.tabelaContatos = tabelaContatos;
		this.acessoBanco = acessoBanco;
	}
	
	public void actionPerformed(ActionEvent evento) {
		if (evento.getSource()==janelaPrincipal.getBotaoEditar()){
			//System.out.println("Botao EDITAR pressionado!");
			if (tabelaContatos.getSelectedRowCount()==1){
				int linhaSelecionada = tabelaContatos.getSelectedRow();
				contato.setId((Integer) tabelaContatos.getValueAt(linhaSelecionada, 0));
				contato.setNome((String) tabelaContatos.getValueAt(linhaSelecionada, 1));
				contato.setApelido((String) tabelaContatos.getValueAt(linhaSelecionada, 2));
				contato.setTelefoneResidencial((String) tabelaContatos.getValueAt(linhaSelecionada, 3));
				contato.setTelefoneCelular((String) tabelaContatos.getValueAt(linhaSelecionada, 4));
				contato.setCidade((String) tabelaContatos.getValueAt(linhaSelecionada, 5));
				contato.setEstado((String) tabelaContatos.getValueAt(linhaSelecionada, 6));
				
				abreJanelaEditar(contato);
				try {
					janelaPrincipal.limparTela();
					janelaPrincipal.telaConsultar();
				} catch (IOException e) {
					System.out.println("Erro IOException- botao editar - actionPerformed()/TratadorEventosConsulta.java");
				}
			}
		} else if (evento.getSource()==janelaPrincipal.getBotaoExcluir()){
			//System.out.println("Botao EXCLUIR pressionado!");
			if (tabelaContatos.getSelectedRowCount()==1){
				int confirma = JOptionPane.showConfirmDialog(null, "Deseja excluir contato?", "Exclusão", JOptionPane.YES_NO_CANCEL_OPTION);
				if (confirma==JOptionPane.YES_OPTION){
					try {
						int contato = (Integer) tabelaContatos.getValueAt(tabelaContatos.getSelectedRow(), 0);
						acessoBanco.deletarContato(contato);
						janelaPrincipal.limparTela();
						janelaPrincipal.telaConsultar();
					} catch (IOException e) {
						System.out.println("Erro IOException - botao excluir - actionPerformed()/TratadorEventosConsulta.java");
					}
				}
			}
		}
	}
	
	public void abreJanelaEditar(Contato contato){
		janelaEditar = new JanelaEditar(acessoBanco, contato);
		janelaEditar.montarJanelaEditar();
	}
}
