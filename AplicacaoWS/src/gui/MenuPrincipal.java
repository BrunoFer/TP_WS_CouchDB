package gui;

import javax.swing.ButtonGroup;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;

public class MenuPrincipal extends JMenuBar {

	private static final long serialVersionUID = 1L;
	private JMenu menuArquivo, menuEditar, menuSobre, menuAparencia;
	private JMenuItem exit, cadastro, exibir, aplicacao;
	private JRadioButtonMenuItem metal, motif, gtk, nimbus;
	private ButtonGroup grupoAparencia;
	
	public MenuPrincipal() {
		montarMenu();
	}

	public void montarMenu() {

		// menu arquivo
		menuArquivo = new JMenu("Arquivo");
		exit = new JMenuItem("Sair");
		
		menuArquivo.add(exit);

		// menu alunos
		menuEditar = new JMenu("Editar");
		cadastro = new JMenuItem("Cadastro alunos");
		exibir = new JMenuItem("Exibir alunos");

		menuEditar.add(cadastro);
		menuEditar.add(exibir);
		
		
		// menu configuracoes
		menuAparencia = new JMenu("Aparência");
		metal = new JRadioButtonMenuItem("Metal");
		motif = new JRadioButtonMenuItem("Motif");
		gtk = new JRadioButtonMenuItem("Gtk");
		nimbus = new JRadioButtonMenuItem("Nimbus");
		grupoAparencia = new ButtonGroup();
		
		grupoAparencia.add(metal);
		grupoAparencia.add(motif);
		grupoAparencia.add(gtk);
		grupoAparencia.add(nimbus);
		menuAparencia.add(metal);
		menuAparencia.add(motif);
		menuAparencia.add(gtk);
		menuAparencia.add(nimbus);
		
		// menu sobre
		menuSobre = new JMenu("Sobre");
		aplicacao = new JMenuItem("Aplicação");
		menuSobre.add(aplicacao);
		
		add(menuArquivo);
		add(menuEditar);
		add(menuAparencia);
		add(menuSobre);
	}
	
	public JMenuItem getAplicacao() {
		return aplicacao;
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

	public JRadioButtonMenuItem getMetal() {
		return metal;
	}

	public JRadioButtonMenuItem getMotif() {
		return motif;
	}

	public JRadioButtonMenuItem getGtk() {
		return gtk;
	}

	public JRadioButtonMenuItem getNimbus() {
		return nimbus;
	}

	
}
