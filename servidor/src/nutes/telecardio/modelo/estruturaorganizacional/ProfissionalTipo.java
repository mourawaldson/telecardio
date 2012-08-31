package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;

/**
 * Classe que representa a entidade profissionais_tipo
 * 
 * @author hvb
 * 
 */
public class ProfissionalTipo extends ObjetoNegocio<Integer> {

	// Atributos
	private OrgaoClasse orgaoClasse;
	private String nome;

	@Override
	public Validador validarEntidade(Validador validador) {

		return validador;
	}

	public void setOrgaoClasse(OrgaoClasse orgaoClasse) {
		this.orgaoClasse = orgaoClasse;
	}

	public OrgaoClasse getOrgaoClasse() {
		return orgaoClasse;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

}
