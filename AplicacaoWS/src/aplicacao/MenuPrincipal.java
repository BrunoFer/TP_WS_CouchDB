package aplicacao;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MenuPrincipal extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	private JMenu menuArquivo,menuAluno, menuSobre;
	private JMenuItem exit, cadastro, exibir;
	
	public MenuPrincipal() {
		montarMenu();
	}

	public void montarMenu(){
		menuAluno = new JMenu("Alunos");
		menuArquivo = new JMenu("Arquivo");
		menuSobre = new JMenu("Sobre");
		
		exit = new JMenuItem("Sair");
		cadastro = new JMenuItem("Cadastro");
		exibir = new JMenuItem("Exibir");
		
		menuArquivo.add(exit);
		
		menuAluno.add(cadastro);
		menuAluno.add(exibir);
		
		add(menuArquivo);
		add(menuAluno);
		add(menuSobre);
	}
}
