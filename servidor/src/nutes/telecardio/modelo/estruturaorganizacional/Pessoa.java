package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.utils.Datas;

/**
 * Classe que representa a entidade pessoa
 * 
 * @author hvb
 * 
 */
public class Pessoa extends ObjetoNegocio<Integer> {

	private int chavePessoa;
	private Endereco endereco;
	private String nome;
	private String cpf;
	private String dataNascimento;
	private String sexo;

	/**
	 * Metodos da entidade que retorna a data de nascimento no formato e tipo
	 * escolhido
	 * 
	 * @param Date
	 *            data
	 * @param String
	 *            formato
	 * @return String dataNascimentoFormtatado
	 */
	public String getDataHoraEua() {
		String dataHoraEua = Datas.formatarData(Datas.criarData(
				getDataNascimento(), Datas.dataBrasil), Datas.dataHoraEua);
		return dataHoraEua;
	}

	@Override
	public Validador validarEntidade(Validador validador) {

		return validador;
	}

	public void setChavePessoa(int chavePessoa) {
		this.chavePessoa = chavePessoa;
	}

	public int getChavePessoa() {
		return chavePessoa;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setDataNascimento(String dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public String getDataNascimento() {
		return dataNascimento;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getSexo() {
		return sexo;
	}

}
