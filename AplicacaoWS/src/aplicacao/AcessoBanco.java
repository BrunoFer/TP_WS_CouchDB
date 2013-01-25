package aplicacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AcessoBanco {
	private String urlMongoRest = "http://localhost:5984/";
	private String nomeBanco = "trabalho";
	private int nomeDocumento = 1;
	private URL urlConsulta;
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

	public String buscaDocumentos() throws IOException {
		JSONObject json, documento, dadosAluno;
		JSONArray arrayDocumentos;
		String resultado = "";

		String url = getUrlmongorest() + getNomebanco()
				+ "/_all_docs?include_docs=true";
		System.out.println(url);
		String jsonString = getRegistro(url);

		try {
			json = new JSONObject(jsonString.toString());
			resultado = "Documentos no banco: " + json.getString("total_rows")
					+ "\n\n";

			arrayDocumentos = json.getJSONArray("rows");
			for (int i = 0; i < arrayDocumentos.length(); i++) {
				documento = arrayDocumentos.getJSONObject(i);
				dadosAluno = documento.getJSONObject("doc");
				resultado += "Nome: " + dadosAluno.getString("nome") + "\n"
						+ "Telefone: " + dadosAluno.getString("telefone")
						+ "\n" + "Idade: " + dadosAluno.getString("idade")
						+ "\n" + "Sexo: " + dadosAluno.getString("sexo")
						+ "\n\n";
			}

			return resultado;
		} catch (JSONException e1) {
			System.out.println("Não conseguiu recuperar o json!");
		}
		return null;
	}

	public String getRegistro(String url) throws IOException {
		HttpURLConnection conexao;
		BufferedReader rd;
		String linha;
		try {
			this.urlConsulta = new URL(url);
			capturaJson = "";
			conexao = (HttpURLConnection) urlConsulta.openConnection();
			conexao.setRequestMethod("GET");
			rd = new BufferedReader(new InputStreamReader(
					conexao.getInputStream()));
			while ((linha = rd.readLine()) != null) {
				capturaJson += linha;
			}
			rd.close();
			// retorna a string de retorno da requisição
			return capturaJson;
		} catch (FileNotFoundException e1) {
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
