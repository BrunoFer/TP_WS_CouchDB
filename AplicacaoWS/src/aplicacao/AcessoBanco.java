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

import javax.swing.JOptionPane;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AcessoBanco {
	private String urlMongoRest = "http://localhost:5984/";
	private String nomeBanco = "agenda";
	private int nomeDocumento;
	private URL urlConsulta;
	private String capturaJson;

	public AcessoBanco() throws ClientProtocolException, IOException {
		if (!verificaBancoCriado()) {
			inicializaBanco();
			this.nomeDocumento = 1;
		} else {
			this.nomeDocumento = maiorNumeroDocumento() + 1;
		}
	}

	public List<Contato> buscaDocumentos(String condicao) throws IOException {
		List<Contato> listaContatos = new ArrayList<Contato>();
		JSONObject json, documento, dadosContato;
		JSONArray arrayDocumentos;
		String url = getUrlmongorest() + getNomebanco()
				+ "/_all_docs?include_docs=true";
		
		if (!condicao.equals("todos"))
			url += condicao;
		
		String jsonString = getRegistro(url);
		try {
			json = new JSONObject(jsonString.toString());
			arrayDocumentos = json.getJSONArray("rows");
			for (int i = 0; i < arrayDocumentos.length(); i++) {
				Contato contato = new Contato();
				documento = arrayDocumentos.getJSONObject(i);
				dadosContato = documento.getJSONObject("doc");
				contato.setId(Integer.parseInt(dadosContato.getString("_id")));
				contato.setNome(dadosContato.getString("nome"));
				contato.setApelido(dadosContato.getString("apelido"));
				contato.setTelefoneResidencial(dadosContato.getString("telres"));
				contato.setTelefoneCelular(dadosContato.getString("telcel"));
				contato.setCidade(dadosContato.getString("cidade"));
				contato.setEstado(dadosContato.getString("estado"));
				listaContatos.add(contato);
			}
			return listaContatos;
		} catch (JSONException e1) {
			System.out
					.println("Erro ao manipular JSON! - buscaDocumentos()/AcessoBanco.java");
		}
		return null;
	}

	public boolean verificaBancoCriado() throws IOException {
		setNomeBanco("agenda");
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
					int numeroDocumento = Integer.parseInt(documento
							.getString("id"));
					if (numeroDocumento > maiorNumero) {
						maiorNumero = numeroDocumento;
					}
				}
			} catch (JSONException e) {
				System.out
						.println("Erro ao manipular JSON! - maiorNumeroDocumento()/AcessoBanco.java");
			}
			// System.out.println(maiorNumero);
			return maiorNumero;
		}
	}

	public String buscarHashContato(int numeroDocumento) throws IOException {
		String url = getUrlmongorest() + getNomebanco() + "/" + numeroDocumento;

		// fazendo a requisicao do hash do documento do aluno
		String jsonRetornado = getRegistro(url);
		JSONObject json;
		try {
			json = new JSONObject(jsonRetornado.toString());
			String hash = json.getString("_rev");
			return hash;
		} catch (JSONException e) {
			System.out
					.println("Erro ao manipular JSON! - buscarHashAluno()/AcessoBanco.java");
			return null;
		}
	}

	public void deletarContato(int numeroDocumento) {
		// busca o hash do aluno que será deletado
		String hash;
		try {
			hash = buscarHashContato(numeroDocumento);
			// montando a url para deletar o documento do aluno
			String url = getUrlmongorest() + getNomebanco() + "/"
					+ numeroDocumento + "?rev=" + hash;
			removeRegistro(url);
		} catch (IOException e) {
			System.out
					.println("Erro IOException - deletarAluno()/AcessoBanco.java");
		}
	}

	public void atualizarAluno(int numeroDocumento, String json) {
		// System.out.println("Cheguei aqui - numero do documento:"+
		// numeroDocumento);
		try {
			String hash = buscarHashContato(numeroDocumento);
			// montando a url para deletar o documento do aluno
			String url = getUrlmongorest() + getNomebanco() + "/";
			/*
			 * acrescenta o id e o hash do aluno à string json, sem isso o
			 * documento não é alterado corretamente
			 */
			String jsonFinal = "{ \"_id\":\"" + numeroDocumento
					+ "\",\"_rev\":\"" + hash + "\"," + json;
			updateRegistro(url, jsonFinal);
		} catch (IOException e) {
			System.out
					.println("Erro IOException - atualizarAluno()/AcessoBanco.java");
		}

	}

	public void updateRegistro(String url, String json) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost requisicaoPost = new HttpPost(URI.create(url));
		HttpEntity input = new StringEntity(json, ContentType.APPLICATION_JSON);

		requisicaoPost.setEntity(input);
		HttpResponse response;
		try {
			response = httpClient.execute(requisicaoPost);
			System.out.println(response);
			JOptionPane.showMessageDialog(null,
					"Registro alterado com sucesso!", "Cadastro",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			System.out
					.println("Erro na conexão! - updateRegistro()/AcessoBanco.java");
		}
	}

	public void removeRegistro(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpDelete deleteRequest = new HttpDelete(url);
		HttpResponse response;
		try {
			response = httpClient.execute(deleteRequest);
			System.out.println(response);
			JOptionPane.showMessageDialog(null,
					"Registro excluído com sucesso!", "Exclusão",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			System.out
					.println("Erro na conexão! - removeRegistro()/AcessoBanco.java");
		}
	}

	public void setRegistro(String json) {
		System.out.println(json);
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPut putRequest = new HttpPut(URI.create(urlMongoRest + nomeBanco
				+ "/" + nomeDocumento));
		HttpEntity input = new StringEntity(json, ContentType.APPLICATION_JSON);

		putRequest.setEntity(input);
		HttpResponse response;
		try {
			response = httpClient.execute(putRequest);
			System.out.println(response);
			setNomeDocumento(nomeDocumento + 1);
			JOptionPane.showMessageDialog(null,
					"Registro incluído com sucesso!", "Cadastro",
					JOptionPane.INFORMATION_MESSAGE);
		} catch (IOException e) {
			System.out
					.println("Erro na conexão! - setRegistro()/AcessoBanco.java");
		}
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
			System.out
					.println("Erro na conexão! - getRegistro()/AcessoBanco.java");
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
