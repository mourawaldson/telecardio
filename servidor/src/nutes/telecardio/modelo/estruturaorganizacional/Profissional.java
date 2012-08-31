package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.NomesCampos;

/**
 * Classe que representa a entidade profissionais
 * 
 * @author hvb
 * 
 */
public class Profissional extends ObjetoNegocio<Integer> {

	private Usuario usuario;
	private ProfissionalTipo profissionalTipo;
	private List<ProfissionalOrgao> profissionaisOrgaos;

	@Override
	public Validador validarEntidade(Validador validador) {
		if (profissionalTipo == null) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.profissionalTipo));

		}

		if (profissionaisOrgaos.size() <= 0) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.profissionalOrgao));
		}

		return validador;
	}


	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setProfissionalTipo(ProfissionalTipo profissionalTipo) {
		this.profissionalTipo = profissionalTipo;
	}

	public ProfissionalTipo getProfissionalTipo() {
		return profissionalTipo;
	}

	public void setProfissionaisOrgaos(
			List<ProfissionalOrgao> profissionaisOrgaos) {
		this.profissionaisOrgaos = profissionaisOrgaos;
	}

	public List<ProfissionalOrgao> getProfissionaisOrgaos() {
		return profissionaisOrgaos;
	}

}
