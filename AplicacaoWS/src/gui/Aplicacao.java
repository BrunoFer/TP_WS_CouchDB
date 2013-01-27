package gui;


import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import aplicacao.AcessoBanco;

public class Aplicacao {
	
	public static void main(String[] args) throws IOException{
		AcessoBanco acessoBanco = new AcessoBanco();
			
		JanelaPrincipal janela = new JanelaPrincipal(acessoBanco);
		
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.gtk.GTKLookAndFeel");
			SwingUtilities.updateComponentTreeUI(janela);
		} catch (Exception e) {
			System.out.println("Não conseguiu carregar o tema inicial!");
		}
		
		setandoCaminhosImagens(janela);
		
		janela.montarJanela();
	}
	
	public static void setandoCaminhosImagens(JanelaPrincipal janela) throws IOException{
		janela.setCaminhoImgEditar(new File(".").getCanonicalPath()+"/img/editar.png");
		janela.setCaminhoImgExcluir(new File(".").getCanonicalPath()+"/img/excluir.png");
	}

}
