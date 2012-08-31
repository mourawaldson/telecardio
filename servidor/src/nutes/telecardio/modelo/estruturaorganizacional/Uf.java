package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;

/**
 * Classe que representa a entidade estado
 * 
 * @author hvb
 * 
 */
public class Uf extends ObjetoNegocio<Integer> {

	private String nome;
	private String sigla;

	@Override
	public Validador validarEntidade(Validador validador) {

		return validador;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

}
