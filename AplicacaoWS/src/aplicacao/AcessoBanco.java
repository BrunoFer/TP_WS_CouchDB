package aplicacao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class AcessoBanco {
	private String urlMongoRest = "http://localhost:5984/";
	private String nomeBanco = "trabalho";
	private URL urlConsulta;
	private BufferedReader bReader;
	private InputStreamReader iReader;
	private String capturaJson;
	
	public AcessoBanco(){}
	
	public String getUrlmongorest() {
		return urlMongoRest;
	}
	
	public String getNomebanco() {
		return nomeBanco;
	}
	
	public void setUrlMongoRest(String urlMongoRest) {
		this.urlMongoRest = urlMongoRest;
	}
	
	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}
	
	public String getRegistro(){
		try {
			this.urlConsulta = new URL(this.urlMongoRest + this.nomeBanco);

			// Cria um stream de entrada do conteúdo.
			this.iReader = new InputStreamReader( this.urlConsulta.openStream() );
			this.bReader = new BufferedReader( this.iReader );
			this.capturaJson = "";
			
			// Capturando as linhas com a resposta da consulta ao site dos correios.
			while ( this.bReader.ready()){
				this.capturaJson += this.bReader.readLine();
			}
			//retorna a string de retorno da requisição
			return capturaJson;	
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return null;
	}
	
	public void setRegistro(String json) throws IOException{
		urlConsulta = new URL(urlMongoRest + nomeBanco);
		HttpURLConnection httpCon = (HttpURLConnection) urlConsulta
				.openConnection();
		httpCon.setDoOutput(true);
		httpCon.setRequestMethod("PUT");
		OutputStreamWriter out = new OutputStreamWriter(
				httpCon.getOutputStream());
		out.write(json);
		JOptionPane.showMessageDialog(null, "Tentativa de gravar no banco efetuada!",
				"Cadastro de alunos",
				JOptionPane.INFORMATION_MESSAGE);
		out.close();
	}
}
