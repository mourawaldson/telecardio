package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;

/**
 * Classe que representa a entidade orgao_classe
 * 
 * @author hvb
 * 
 */
public class OrgaoClasse extends ObjetoNegocio<Integer> {

	private String sigla;
	private String nome;

	@Override
	public Validador validarEntidade(Validador validador) {

		return validador;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public String getSigla() {
		return sigla;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
