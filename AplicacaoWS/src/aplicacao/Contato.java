package aplicacao;

public class Contato {
	String nome, apelido, dataNascimento, telefoneResidencial, telefoneCelular;
	String cidade, estado;
	String id;
	int qtdeAlunos;

	public Contato() {
		this.qtdeAlunos++;
	}

	public Contato(String id, String nome, String apelido, String dataNascimento,
			String telefoneResidencial, String telefoneCelular, String cidade,
			String estado) {
		super();
		this.id = id;
		this.nome = nome;
		this.apelido = apelido;
		this.dataNascimento = dataNascimento;
		this.telefoneResidencial = telefoneResidencial;
		this.telefoneCelular = telefoneCelular;
		this.cidade = cidade;
		this.estado = estado;
		this.qtdeAlunos++;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getApelido() {
		return apelido;
	}

	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	
	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getTelefoneResidencial() {
		return telefoneResidencial;
	}

	public void setTelefoneResidencial(String telefoneResidencial) {
		this.telefoneResidencial = telefoneResidencial;
	}

	public String getTelefoneCelular() {
		return telefoneCelular;
	}

	public void setTelefoneCelular(String telefoneCelular) {
		this.telefoneCelular = telefoneCelular;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getQtdeAlunos() {
		return qtdeAlunos;
	}

}
