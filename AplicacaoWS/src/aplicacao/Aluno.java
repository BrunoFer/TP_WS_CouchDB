package aplicacao;

public class Aluno {
	String nome, telefone;
	int idade;
	String sexo;
	int qtdeAlunos;

	public Aluno() {
		this.qtdeAlunos++;
	}

	public Aluno(String nome, String telefone, int idade, String sexo) {
		super();
		this.nome = nome;
		this.telefone = telefone;
		this.idade = idade;
		this.sexo = sexo;
		this.qtdeAlunos++;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public int getQtdeAlunos() {
		return qtdeAlunos;
	}

}
