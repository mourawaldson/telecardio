package nutes.telecardio.webservice;

public class ClasseTesteWebService {
	private String nome;
	private String senha;
	private TesteFuncionalidade[] testeFuncionalidade;

	public void setTesteFuncionalidade(TesteFuncionalidade[] testeFuncionalidade) {
		this.testeFuncionalidade = testeFuncionalidade;
	}

	public TesteFuncionalidade[] getTesteFuncionalidade() {
		return testeFuncionalidade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

}
