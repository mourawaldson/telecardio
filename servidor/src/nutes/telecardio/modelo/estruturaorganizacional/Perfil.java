package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;

/**
 * Classe que representa a entidade perfil
 * 
 * @author hvb
 * 
 */
public class Perfil extends ObjetoNegocio<Integer> {

	private List<Funcionalidade> funcionalidades;
	private String nome;

	@Override
	public Validador validarEntidade(Validador validador) {

		return validador;
	}
	

	public void setFuncionalidades(List<Funcionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List<Funcionalidade> getFuncionalidades() {
		return funcionalidades;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
