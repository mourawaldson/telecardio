package nutes.telecardio.modelo.operacional;

import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.NomesCampos;
import nutes.telecardio.modelo.estruturaorganizacional.Pessoa;
import nutes.telecardio.utils.Funcoes;

/**
 * Classe básica Paciente.
 * 
 * @author WaldsonMoura
 * 
 */
public class Paciente extends Pessoa {
	private int idConvenio;
	private String dataUltimoContato;
	private String nomeMae;
	private TipoSanguineo tipoSanguineo;

	public Paciente() {
		this.tipoSanguineo = new TipoSanguineo();
	}

	@Override
	public Validador validarEntidade(Validador validador) {
		if (!Funcoes.isValidStr(this.getNome(), null, 100))
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.nome));

		if (!Funcoes.isValidStr(this.getDataNascimento(), 10, 10))
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.dataNascimento));

		if (!Funcoes.isValidStr(this.getSexo(), 1, 1))
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.sexo));

		if ((!Funcoes.isNullOrEmpty(this.getCpf()))
				&& !Funcoes.isValidCPF(this.getCpf()))
			validador.getMsgRetorno().add(MensagensNegocio.cpfInvalido);

		if (this.tipoSanguineo == null) {
			this.tipoSanguineo = new TipoSanguineo();
			this.tipoSanguineo.setChave(TipoSanguineo.sTS);
		}

		return validador;
	}

	public int getIdConvenio() {
		return idConvenio;
	}

	public void setIdConvenio(int idConvenio) {
		this.idConvenio = idConvenio;
	}

	public String getDataUltimoContato() {
		return dataUltimoContato;
	}

	public void setDataUltimoContato(String dataUltimoContato) {
		this.dataUltimoContato = dataUltimoContato;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public TipoSanguineo getTipoSanguineo() {
		return tipoSanguineo;
	}

	public void setTipoSanguineo(TipoSanguineo tipoSanguineo) {
		this.tipoSanguineo = tipoSanguineo;
	}

}
