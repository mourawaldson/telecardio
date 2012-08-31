package nutes.telecardio.modelo.configuracao;

import nutes.telecardio.modelo.ObjetoNegocio;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.utils.Funcoes;

/**
 * Classe básica Configuração.
 * 
 * @author WaldsonMoura
 * 
 */
public class Configuracao extends ObjetoNegocio<Integer> {
	/**
	 * Nome apenas pada identificação.
	 */
	private String nome;

	/**
	 * Caminho onde os xmls dos exames serão salvos.
	 */
	private String pastaExames;

	/**
	 * Limite de habilitações de um médico.
	 */
	private int limiteHabilitacoesMedico;

	/**
	 * Limite de habilitações de um enfermeiro.
	 */
	private int limiteHabilitacoesEnfermeiro;

	/**
	 * Limite em minutos do tempo que um médico pode ficar com o estado do exame
	 * Laudando.
	 */
	private int limiteExameLaudando;

	/**
	 * Limite em minutos do tempo que um exame pode ficar em Aberto.
	 */
	private int limiteExameAberto;

	/**
	 * E-mail que será checado. Ex: systems.agility@gmail.com
	 */
	private String emailChecagemEmail;

	/**
	 * Senha de acesso ao e-mail (será criptografada).
	 */
	private String senhaChecagemEmail;

	/**
	 * Porta de acesso ao e-mail. Ex: 993
	 */
	private int portaChecagemEmail;

	/**
	 * Host de acesso ao e-mail. Ex: imap.google.com
	 */
	private String hostChecagemEmail;

	/**
	 * Intervado de tempo entre uma checagem e outra na caixa de e-mail
	 * definida.
	 */
	private int intervaloChecagemEmail;

	private char status;

	// Caminho completo do local onde está rodando o servidor!
	public static String pathLocal;

	@Override
	public Validador validarEntidade(Validador validador) {
		if (!Funcoes.isValidStr(this.nome, null, 50)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.nome));
		}

		if (!Funcoes.isValidStr(this.pastaExames, null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.pastaExames));
		}

		if (!Funcoes.isValidInt(this.limiteHabilitacoesMedico, null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.limiteHabilitacoesMedico));
		}

		if (!Funcoes.isValidInt(this.limiteHabilitacoesEnfermeiro, null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.limiteHabilitacoesEnfermeiro));
		}

		if (!Funcoes.isValidInt(this.limiteExameLaudando, null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.limiteExameLaudando));
		}

		if (!Funcoes.isValidInt(this.limiteExameAberto, null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.limiteExameAberto));
		}

		if (!Funcoes.isValidEmail(this.emailChecagemEmail)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.emailChecagemEmail));
		}

		if (!Funcoes.isValidStr(this.senhaChecagemEmail, null, 32)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.senha));
		}

		if (!Funcoes.isValidInt(this.portaChecagemEmail, null, 99999)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.portaChecagemEmail));
		}

		if (!Funcoes.isValidStr(this.hostChecagemEmail, null, 50)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.hostChecagemEmail));
		}

		if (!Funcoes.isValidInt(this.intervaloChecagemEmail, null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.portaChecagemEmail));
		}

		return validador;
	}

	public Validador validarConfiguracaoAtiva(Validador validador) {

		if (!Funcoes.isValidEmail(this.emailChecagemEmail)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.emailChecagemEmail));
		}

		if (!Funcoes.isValidStr(this.senhaChecagemEmail, null, 32)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.senha));
		}

		if (!Funcoes.isValidInt(this.portaChecagemEmail, null, 99999)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.portaChecagemEmail));
		}

		if (!Funcoes.isValidStr(this.hostChecagemEmail, null, 50)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.hostChecagemEmail));
		}

		if (!Funcoes.isValidInt(this.intervaloChecagemEmail, null, null)) {
			validador.getMsgRetorno().add(
					String.format(MensagensNegocio.campoObrigatorioString,
							NomesCampos.portaChecagemEmail));
		}

		return validador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getPastaExames() {
		return pastaExames;
	}

	public void setPastaExames(String pastaExames) {
		this.pastaExames = pastaExames;
	}

	public int getLimiteHabilitacoesMedico() {
		return limiteHabilitacoesMedico;
	}

	public void setLimiteHabilitacoesMedico(int limiteHabilitacoesMedico) {
		this.limiteHabilitacoesMedico = limiteHabilitacoesMedico;
	}

	public int getLimiteHabilitacoesEnfermeiro() {
		return limiteHabilitacoesEnfermeiro;
	}

	public void setLimiteHabilitacoesEnfermeiro(int limiteHabilitacoesEnfermeiro) {
		this.limiteHabilitacoesEnfermeiro = limiteHabilitacoesEnfermeiro;
	}

	public int getLimiteExameLaudando() {
		return limiteExameLaudando;
	}

	public void setLimiteExameLaudando(int limiteExameLaudando) {
		this.limiteExameLaudando = limiteExameLaudando;
	}

	public int getLimiteExameAberto() {
		return limiteExameAberto;
	}

	public void setLimiteExameAberto(int limiteExameAberto) {
		this.limiteExameAberto = limiteExameAberto;
	}

	public String getEmailChecagemEmail() {
		return emailChecagemEmail;
	}

	public void setEmailChecagemEmail(String emailChecagemEmail) {
		this.emailChecagemEmail = emailChecagemEmail;
	}

	public String getSenhaChecagemEmail() {
		return senhaChecagemEmail;
	}

	public void setSenhaChecagemEmail(String senhaChecagemEmail) {
		this.senhaChecagemEmail = senhaChecagemEmail;
	}

	public int getPortaChecagemEmail() {
		return portaChecagemEmail;
	}

	public void setPortaChecagemEmail(int portaChecagemEmail) {
		this.portaChecagemEmail = portaChecagemEmail;
	}

	public String getHostChecagemEmail() {
		return hostChecagemEmail;
	}

	public void setHostChecagemEmail(String hostChecagemEmail) {
		this.hostChecagemEmail = hostChecagemEmail;
	}

	public int getIntervaloChecagemEmail() {
		return intervaloChecagemEmail;
	}

	public void setIntervaloChecagemEmail(int intervaloChecagemEmail) {
		this.intervaloChecagemEmail = intervaloChecagemEmail;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getStatus() {
		return status;
	}

}
