package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.NomesCampos;
import nutes.telecardio.modelo.operacional.Paciente;
import nutes.telecardio.modelo.operacional.RegistroExame;
import nutes.telecardio.utils.Funcoes;

/**
 * Classe básica Exame.
 * 
 * @author WaldsonMoura
 * 
 */
public class Exame extends ObjetoNegocio<Integer> {
	private Paciente paciente = new Paciente();
	private Profissional profissional;
	private String emailRequisitante;
	private int medicoDesignadoCrm;
	private String parecerJustificativa;
	private int estado;
	private String caminhoArquivos;
	private String dataSolicitacao;
	private String dataConclusao;
	private float pesoPaciente;
	private float alturaPaciente;
	private int cid;
	private String observacoes;
	private int horasAberto;
	private boolean passouLimiteAberto;
	private RegistroExame registroExame;

	public enum Estados {
		ABERTO(0, "Aberto"), LAUDANDO(1, "Laudando"), LAUDADO(2, "Laudado"), NAO_LAUDADO(
				3, "Não laudado"), ABERTO_LIMITE(4, "Aberto no tempo limite");

		private final int id;
		private final String nome;

		private Estados(int id, String nome) {
			this.id = id;
			this.nome = nome;
		}

		final int getId() {
			return id;
		}

		final String getNome() {
			return nome;
		}
	}

	@Override
	public Validador validarEntidade(Validador validador) {
		if (!Funcoes.isValidStr(this.getCaminhoArquivos(), null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.caminhoArquivos));
		}

		if (!Funcoes.isValidStr(this.getDataSolicitacao(), 19, 19)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.dataSolicitacao));
		}

		if (!Funcoes.isValidFloat(this.getPesoPaciente(), null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.dataSolicitacao));
		}

		if (!Funcoes.isValidFloat(this.getAlturaPaciente(), null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.dataSolicitacao));
		}

		return validador;
	}

	public Validador validarEntidadeLaudar(Validador validador) {
		if (Funcoes.isNullOrEmpty(this.getParecerJustificativa())) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.parecerJustificativa));
		}

		return validador;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Profissional getProfissional() {
		return profissional;
	}

	public void setProfissional(Profissional profissional) {
		this.profissional = profissional;
	}

	public String getEmailRequisitante() {
		return emailRequisitante;
	}

	public void setEmailRequisitante(String emailRequisitante) {
		this.emailRequisitante = emailRequisitante;
	}

	public int getMedicoDesignadoCrm() {
		return medicoDesignadoCrm;
	}

	public void setMedicoDesignadoCrm(int medicoDesignadoCrm) {
		this.medicoDesignadoCrm = medicoDesignadoCrm;
	}

	public String getParecerJustificativa() {
		return parecerJustificativa;
	}

	public void setParecerJustificativa(String parecerJustificativa) {
		this.parecerJustificativa = parecerJustificativa;
	}

	public int getEstado() {
		return estado;
	}

	public void setEstado(int estado) {
		this.estado = estado;
	}

	public String getCaminhoArquivos() {
		return caminhoArquivos;
	}

	public void setCaminhoArquivos(String caminhoArquivos) {
		this.caminhoArquivos = caminhoArquivos;
	}

	public String getDataSolicitacao() {
		return dataSolicitacao;
	}

	public void setDataSolicitacao(String dataSolicitacao) {
		this.dataSolicitacao = dataSolicitacao;
	}

	public String getDataConclusao() {
		return dataConclusao;
	}

	public void setDataConclusao(String dataConclusao) {
		this.dataConclusao = dataConclusao;
	}

	public float getPesoPaciente() {
		return pesoPaciente;
	}

	public void setPesoPaciente(float pesoPaciente) {
		this.pesoPaciente = pesoPaciente;
	}

	public float getAlturaPaciente() {
		return alturaPaciente;
	}

	public void setAlturaPaciente(float alturaPaciente) {
		this.alturaPaciente = alturaPaciente;
	}

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}

	public int getHorasAberto() {
		return horasAberto;
	}

	public void setHorasAberto(int horasAberto) {
		this.horasAberto = horasAberto;
	}

	public boolean isPassouLimiteAberto() {
		return passouLimiteAberto;
	}

	public void setPassouLimiteAberto(boolean passouLimiteAberto) {
		this.passouLimiteAberto = passouLimiteAberto;
	}

	public RegistroExame getRegistroExame() {
		return registroExame;
	}

	public void setRegistroExame(RegistroExame registroExame) {
		this.registroExame = registroExame;
	}

	/**
	 * Função que retorna o IMC (Índice de Massa Corporal).
	 * 
	 * @return float imc
	 */
	public float calcularImc() {
		float imc;
		imc = (this.pesoPaciente) / (this.alturaPaciente * this.alturaPaciente);
		return imc;
	}

	/**
	 * Função que retorna a situação de acordo com o IMC.
	 * 
	 * @param imc
	 * @return string situacao
	 */
	public String determinarSituacaoImc(float imc) {
		String situacao = null;
		// Abaixo de 17
		if (imc < 17)
			situacao = MensagensNegocio.imcMuitoAbaixo;
		// Entre 17 e 18,49
		else if (imc >= 17 && imc <= 18.49)
			situacao = MensagensNegocio.imcAbaixo;
		// Entre 18,5 e 24,99
		else if (imc >= 18.5 && imc <= 24.99)
			situacao = MensagensNegocio.imcNormal;
		// Entre 25 e 29,99
		else if (imc >= 25 && imc <= 29.99)
			situacao = MensagensNegocio.imcAcima;
		// Entre 30 e 34,99
		else if (imc >= 30 && imc <= 34.99)
			situacao = MensagensNegocio.imcObesidadeI;
		// Entre 35 e 39,99
		else if (imc >= 35 && imc <= 39.99)
			situacao = MensagensNegocio.imcObesidadeII;
		// Acima de 40
		else if (imc > 40)
			situacao = MensagensNegocio.imcObesidadeIII;

		return situacao;
	}
}
