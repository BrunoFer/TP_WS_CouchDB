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

	public JMenu getMenuSobre() {
		return menuSobre;
	}

	public void setMenuSobre(JMenu menuSobre) {
		this.menuSobre = menuSobre;
	}

	public JMenuItem getExit() {
		return exit;
	}

	public void setExit(JMenuItem exit) {
		this.exit = exit;
	}

	public JMenuItem getCadastro() {
		return cadastro;
	}

	public void setCadastro(JMenuItem cadastro) {
		this.cadastro = cadastro;
	}

	public JMenuItem getExibir() {
		return exibir;
	}

	public void setExibir(JMenuItem exibir) {
		this.exibir = exibir;
	}
	
	
}
