package aplicacao;

import gui.JanelaPrincipal;

import java.io.File;
import java.io.IOException;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

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
	
	/**
	 * Função utilizada para setar os caminhos das imagens utilizadas durante a execução da
	 * aplicação.
	 * 
	 * @param janela
	 */
	public static void setandoCaminhosImagens(JanelaPrincipal janela){
		try {
			janela.setCaminhoImgEditar(new File(".").getCanonicalPath()+"/img/editar.png");
			janela.setCaminhoImgExcluir(new File(".").getCanonicalPath()+"/img/excluir.png");
			janela.setCaminhoImgCadastrar(new File(".").getCanonicalPath()+"/img/cadastro.png");
			janela.setImagemFundo(new File(".").getCanonicalPath()+"/img/images.jpg");
			janela.setIconeTitulo(new File(".").getCanonicalPath()+"/img/couchdb.jpg");
		} catch (IOException e) {
		}
	}

}
