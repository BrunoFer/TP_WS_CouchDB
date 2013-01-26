package gui;


import java.io.File;
import java.io.IOException;

import aplicacao.AcessoBanco;

public class Aplicacao {
	
	public static void main(String[] args) throws IOException{
		AcessoBanco acessoBanco = new AcessoBanco();
			
		JanelaPrincipal janela = new JanelaPrincipal(acessoBanco);
		
		setandoCaminhosImagens(janela);
		
		janela.montarJanela();
		
	}
	
	public static void setandoCaminhosImagens(JanelaPrincipal janela) throws IOException{
		janela.setCaminhoImgEditar(new File(".").getCanonicalPath()+"/img/editar.png");
		janela.setCaminhoImgExcluir(new File(".").getCanonicalPath()+"/img/excluir.png");
	}

}
