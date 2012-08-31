package nutes.telecardio.modelo.operacional;

import javax.servlet.ServletContextEvent;
import javax.swing.JOptionPane;

import nutes.telecardio.modelo.Validador;
import nutes.telecardio.modelo.configuracao.Configuracao;
import nutes.telecardio.modelo.configuracao.MensagensNegocio;
import nutes.telecardio.modelo.configuracao.ServicoConfiguracao;
import nutes.telecardio.utils.Funcoes;

/**
 * Classe chama a thread de checagem dos e-mail ({@link EmailCheckerThread}) de
 * acordo com o tempo informado na {@link Configuracao} Ativa.
 * 
 * @author WaldsonMoura
 * 
 */
public class EmailChecker {

	private static EmailChecker instance;
	private Thread emailThread;
	private boolean stop = true;
	private Runnable emailRunnable = new EmailRunnable();
	private Configuracao configuracao;
	private long sleep;
	private ServletContextEvent sce;

	public ServletContextEvent getSCE() {
		return sce;
	}

	public void setSCE(ServletContextEvent sce) {
		this.sce = sce;
	}

	private EmailChecker() {

	}

	/**
	 * Retorna a instância desta Classe.
	 * 
	 * @return {@link EmailChecker}
	 */
	public static EmailChecker getInstance() {
		if (instance == null) {
			instance = new EmailChecker();
		}
		return instance;
	}

	/**
	 * Iniciar execução da Thread.
	 * 
	 * @return void
	 */
	public void startThread() {
		if (this.stop) {
			this.stop = false;
			this.emailThread = new Thread(emailRunnable);
			this.emailThread.start();
		}
	}

	/**
	 * Parar execução da Thread.
	 * 
	 * @return void
	 */
	public void stopThread() {
		this.stop = true;
	}

	/**
	 * Verifica se a checagem está em andamento.
	 * 
	 * @return boolean
	 */
	public boolean isAlive() {
		return emailThread.isAlive();
	}

	/**
	 * Consulta a configuração ativa, caso exista atribui o tempo de espera
	 * (sleep) da thread, de acordo com a configuração.
	 * 
	 * @return boolean true se existir configuracao ativa; false caso não
	 *         exista.
	 */
	private Validador prepararConfiguracaoAtiva() {
		Validador validador = new Validador();
		ServicoConfiguracao servicoConfiguracao = new ServicoConfiguracao();

		this.configuracao = servicoConfiguracao.consultarAtiva();

		configuracao.validarConfiguracaoAtiva(validador);

		if (validador.getMsgRetorno().size() <= 0) {
			this.sleep = Funcoes.ConvertMinutesToMilliseconds(this.configuracao
					.getIntervaloChecagemEmail());
		}

		return validador;
	}

	/**
	 * Classe interna que cria e executa a thread de checagem dos e-mail (
	 * {@link EmailCheckerThread}) caso exista uma {@link Configuracao} ativa na
	 * base de dados.
	 * 
	 * @author WaldsonMoura
	 * 
	 */
	class EmailRunnable implements Runnable {
		public void run() {
			Validador validador = new Validador();
			validador = prepararConfiguracaoAtiva();
			if (validador.getMsgRetorno().size() > 0) {
				JOptionPane.showMessageDialog(null,
						MensagensNegocio.configAtivaInexistente + "\n"
								+ validador.getMsgRetorno());
				stopThread();
			} else {
				EmailCheckerThread emailCheckerThread = new EmailCheckerThread(
						configuracao.getHostChecagemEmail(), configuracao
								.getEmailChecagemEmail(), configuracao
								.getSenhaChecagemEmail(), configuracao
								.getPortaChecagemEmail(), configuracao
								.getPastaExames(), sce.getServletContext()
								.getRealPath(""));
				while (!stop) {
					try {
						emailCheckerThread.execute();
						Thread.sleep(sleep);
					} catch (Throwable th) {
						JOptionPane.showMessageDialog(null,
								MensagensNegocio.erroChecagemEmail);
					}
				}
			}
		}
	}
}
