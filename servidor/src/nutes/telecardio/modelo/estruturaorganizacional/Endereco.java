package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.NomesCampos;
import nutes.telecardio.utils.Funcoes;

/**
 * Classe que representa a entidae endere;o
 * 
 * @author hvb
 * 
 */
public class Endereco extends ObjetoNegocio<Integer> {

	private Uf uf;
	private String bairro;
	private String logradouro;
	private String numero;
	private String complemento;
	private String cep;
	private String municipio;

	@Override
	public Validador validarEntidade(Validador validador) {

		if (!Funcoes.isValidStr(bairro, 2, 20)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.bairro));
		}

		if (!Funcoes.isValidStr(logradouro, 10, 200)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.logradouro));
		}

		if (!Funcoes.isValidStr(numero, 1, 10)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.numero));
		}

		if (!Funcoes.isValidStr(cep, 8, 8)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.cep));
		}

		if (uf == null) {
			validador.getMsgRetorno().add(MensagensNegocio.enderecoSemEstado);
		}

		return validador;
	}

	public void setUf(Uf uf) {
		this.uf = uf;
	}

	public Uf getUf() {
		return uf;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getNumero() {
		return numero;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCep() {
		return cep;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getBairro() {
		return bairro;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getMunicipio() {
		return municipio;
	}
}
