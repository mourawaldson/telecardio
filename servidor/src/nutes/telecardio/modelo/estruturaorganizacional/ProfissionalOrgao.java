package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;

/**
 * Classe que representa a entidade profissionais_orgaos
 * 
 * @author hvb
 * 
 */
public class ProfissionalOrgao extends ObjetoNegocio<Integer> {

	private Profissional profissional;
	private int habilitacao;
	private OrgaoClasse orgaoClasse;
	private Uf uf;

	@Override
	public Validador validarEntidade(Validador validador) {

		return validador;
	}
	
	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setHabilitacao(int habilitacao) {
		this.habilitacao = habilitacao;
	}

	public int getHabilitacao() {
		return habilitacao;
	}

	public void setOrgaoClasse(OrgaoClasse orgaoClasse) {
		this.orgaoClasse = orgaoClasse;
	}

	public OrgaoClasse getOrgaoClasse() {
		return orgaoClasse;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Uf getUf() {
		return uf;
	}

}
