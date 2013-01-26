package aplicacao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
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
	private int nomeDocumento;
	private URL urlConsulta;
	private String capturaJson;

	public AcessoBanco() throws ClientProtocolException, IOException {
		if (!verificaBancoCriado()) {
			inicializaBanco();
			this.nomeDocumento = 1;
		} else {
			this.nomeDocumento = maiorNumeroDocumento()+1;
		}
	}

	public List<Aluno> buscaDocumentos() throws IOException {
		List<Aluno> listaAlunos = new ArrayList<Aluno>();
		JSONObject json, documento, dadosAluno;
		JSONArray arrayDocumentos;

		String url = getUrlmongorest() + getNomebanco()
				+ "/_all_docs?include_docs=true";
		String jsonString = getRegistro(url);

		try {
			json = new JSONObject(jsonString.toString());
			arrayDocumentos = json.getJSONArray("rows");
			for (int i = 0; i < arrayDocumentos.length(); i++) {
				Aluno aluno = new Aluno();
				documento = arrayDocumentos.getJSONObject(i);
				dadosAluno = documento.getJSONObject("doc");
				aluno.setId(Integer.parseInt(dadosAluno.getString("_id")));
				aluno.setNome(dadosAluno.getString("nome"));
				aluno.setTelefone(dadosAluno.getString("telefone"));
				aluno.setIdade(Integer.parseInt(dadosAluno.getString("idade")));
				aluno.setSexo(dadosAluno.getString("sexo"));
				listaAlunos.add(aluno);
			}
			return listaAlunos;
		} catch (JSONException e1) {
			System.out.println("Não conseguiu recuperar o json!");
		}
		return null;
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

	public int maiorNumeroDocumento() throws IOException {
		String sufixo = "/_all_docs";
		String url = getUrlmongorest() + getNomebanco() + sufixo;
		String jsonRetornado = getRegistro(url);
		JSONArray arrayDocumentos;
		JSONObject documento;
		if (jsonRetornado == null) {
			return 0;
		} else {
			JSONObject json;
			int maiorNumero = 0;
			try {
				json = new JSONObject(jsonRetornado.toString());
				arrayDocumentos = json.getJSONArray("rows");
				for (int i = 0; i < arrayDocumentos.length(); i++) {
					documento = arrayDocumentos.getJSONObject(i);
					int numeroDocumento = Integer.parseInt(documento.getString("id"));
					if (numeroDocumento>maiorNumero){
						maiorNumero=numeroDocumento;
					}
				}
			} catch (JSONException e) {
				System.out.println("Erro ao manipular JSON!");
			}
			System.out.println(maiorNumero);
			return maiorNumero;
		}
	}

	public void atualizarAluno(int numeroDocumento) {
		System.out.println("Cheguei aqui - numero do documento:"
				+ numeroDocumento);

	}

	public void deletarAluno(int numeroDocumento) throws IOException {
		String url = getUrlmongorest() + getNomebanco() + "/" + numeroDocumento;

		// fazendo a requisicao do hash do documento do aluno
		String jsonRetornado = getRegistro(url);
		JSONObject json;
		try {
			json = new JSONObject(jsonRetornado.toString());
			jsonRetornado = json.getString("_rev");

			// montando a url para deletar o documento do aluno
			url = getUrlmongorest() + getNomebanco() + "/" + numeroDocumento
					+ "?rev=" + jsonRetornado;
			removeRegistro(url);
		} catch (JSONException e) {
		}
	}

	public void removeRegistro(String url) throws ClientProtocolException,
			IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpDelete deleteRequest = new HttpDelete(url);
		HttpResponse response = httpClient.execute(deleteRequest);
		System.out.println(response);

	}

	public void setRegistro(String json) throws IOException {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPut putRequest = new HttpPut(URI.create(urlMongoRest + nomeBanco
				+ "/" + nomeDocumento));
		HttpEntity input = new StringEntity(json, ContentType.APPLICATION_JSON);

		putRequest.setEntity(input);
		HttpResponse response = httpClient.execute(putRequest);
		System.out.println(response);
		setNomeDocumento(nomeDocumento+1);
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
}
