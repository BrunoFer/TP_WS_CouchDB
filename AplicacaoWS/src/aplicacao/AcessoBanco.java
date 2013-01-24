package aplicacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONException;
import org.json.JSONObject;

public class AcessoBanco {
	private String urlMongoRest = "http://localhost:5984/";
	private String nomeBanco = "trabalho";
	private int nomeDocumento = 1;
	private URL urlConsulta;
	private BufferedReader bReader;
	private InputStreamReader iReader;
	private String capturaJson;

	public AcessoBanco() throws ClientProtocolException, IOException {
		if (!verificaBancoCriado())
			inicializaBanco();
	}

	public int getNomeDocumento() {
		return nomeDocumento;
	}

	public void setNomeDocumento(int nomeDocumento) {
		this.nomeDocumento = nomeDocumento;
	}

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

	public String getRegistro(String url) throws IOException {
		try {
			this.urlConsulta = new URL(url);

			// Cria um stream de entrada do conteúdo.
			this.iReader = new InputStreamReader(this.urlConsulta.openStream());
			this.bReader = new BufferedReader(this.iReader);
			System.out.println("print 2");
			this.capturaJson = "";

			// Capturando as linhas com a resposta da consulta ao site dos
			// correios.
			while (this.bReader.ready()) {
				this.capturaJson += this.bReader.readLine();
			}
			// retorna a string de retorno da requisição
			return capturaJson;
		} catch (FileNotFoundException e1) {
			System.out.println(capturaJson);
			return null;
		}
	}

	public boolean verificaBancoCriado() throws IOException {
		setNomeBanco("trabalho");
		String url = getUrlmongorest() + getNomebanco();
		String jsonRetornado = getRegistro(url);
		if (jsonRetornado == null)
			return false;
		return true;
	}

	public void inicializaBanco() throws ClientProtocolException, IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPut putRequest = new HttpPut(URI.create(urlMongoRest + nomeBanco));

		HttpResponse response = httpClient.execute(putRequest);
		System.out.println(response);
	}

	public void atualizaNomeDocumento() throws IOException {
		setNomeBanco("trabalho");
		String url = getUrlmongorest() + getNomebanco();
		String jsonRetornado = getRegistro(url);
		JSONObject json;
		try {
			json = new JSONObject(jsonRetornado.toString());
			jsonRetornado = json.getString("doc_count");
			setNomeDocumento(Integer.parseInt(jsonRetornado));
		} catch (JSONException e) {
		}
	}

	public void setRegistro(String json) throws IOException {
		atualizaNomeDocumento();
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPut putRequest = new HttpPut(URI.create(urlMongoRest + nomeBanco
				+ "/" + nomeDocumento));
		HttpEntity input = new StringEntity(json, ContentType.APPLICATION_JSON);

		putRequest.setEntity(input);
		HttpResponse response = httpClient.execute(putRequest);
		System.out.println(response);
		setNomeDocumento(getNomeDocumento() + 1);
	}
}
