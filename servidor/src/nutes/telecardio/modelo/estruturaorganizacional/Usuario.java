package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.NomesCampos;
import nutes.telecardio.utils.Funcoes;


/**
 * Classe que representa a entidade usuario
 * 
 * @author hvb
 * 
 */
public class Usuario extends Pessoa {

	private String login;
	private String senha;
	private String email;
	private Perfil perfil;
	private List<Funcionalidade> funcionalidades;
	private Profissional profissional;

	@Override
	public Validador validarEntidade(Validador validador) {

		if (getProfissional() != null) {
			getProfissional().validarEntidade(validador);
		}

		if (getEndereco() == null) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.endereco));

		}

		if (!Funcoes.isValidStr(login, 3, 20)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.login));
		}

		if (!Funcoes.isValidStr(getNome(), 5, 100)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.nome));
		}
		if (!Funcoes.isValidEmail(getEmail())) {

			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.email));
		}

		if (!Funcoes.isValidCPF(getCpf())) {
			validador.getMsgRetorno().add(MensagensNegocio.cpfInvalido);
		}

		if (!Funcoes.isValidStr(getDataNascimento(), 8, 16)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.dataNascimento));

		}

		if (!Funcoes.isValidStr(getSexo(), 1, 1)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.sexo));

		}

		if (getPerfil() == null) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.perfil));
		}

		return validador;
	}


	public void setLogin(String login) {
		this.login = login;
	}

	public String getLogin() {
		return login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getSenha() {
		return senha;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Perfil getPerfil() {
		return perfil;
	}

	public void setFuncionalidades(List<Funcionalidade> funcionalidades) {
		this.funcionalidades = funcionalidades;
	}

	public List<Funcionalidade> getFuncionalidades() {
		return funcionalidades;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public Profissional getProfissional() {
		return profissional;
	}

}
