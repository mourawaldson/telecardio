package nutes.telecardio.modelo.operacional;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;

/**
 * Classe básica de Tipo Sanguíneo.
 * 
 * @author WaldsonMoura
 * 
 */
public class TipoSanguineo extends ObjetoNegocio<Integer> {
	private String nome;

	/**
	 * STS - Sem tipo sanguíneo.
	 */
	public static final int sTS = -1;

	@Override
	public Validador validarEntidade(Validador validador) {
		return validador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
}
