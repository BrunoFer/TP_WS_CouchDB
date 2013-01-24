package gui;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import aplicacao.AcessoBanco;

public class Aplicacao {

	public static void main(String[] args) throws ClientProtocolException, IOException {
		AcessoBanco acessoBanco = new AcessoBanco();
		
		JanelaPrincipal janela = new JanelaPrincipal(acessoBanco);
		
		janela.montarJanela();
		
	}

}
