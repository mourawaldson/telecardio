package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;

/**
 * Classe que representa a entidade funcionalidade
 * 
 * @author hvb
 * 
 */
public class Funcionalidade extends ObjetoNegocio<Integer> {

	private String nome;
	private String descricao;

	@Override
	public Validador validarEntidade(Validador validador) {

		return validador;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}
}
