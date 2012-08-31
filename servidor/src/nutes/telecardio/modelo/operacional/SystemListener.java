package nutes.telecardio.modelo.operacional;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import nutes.telecardio.modelo.configuracao.ConectorSqlServer;
import nutes.telecardio.modelo.configuracao.ServicoConfiguracao;

/**
 * Implementações desta interface recebem notificações sobre as mudanças para o
 * contexto do servlet que fazem parte da aplicação web.
 * 
 * @author WaldsonMoura
 * 
 */
public class SystemListener implements ServletContextListener {

	private EmailChecker emailChecker = EmailChecker.getInstance();

	/**
	 * Notificação de que o contexto do servlet está prestes a ser desligado.
	 */
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		this.emailChecker.stopThread();
	}

	/**
	 * Notificação de que a aplicação web está pronto para processar
	 * solicitações.
	 */
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServicoConfiguracao.CarregarConfiguracaoSistema(sce);
		while (true) {
			if (ConectorSqlServer.isConfigCarregada()) {
				this.emailChecker.setSCE(sce);
				this.emailChecker.startThread();
				break;
			}

		}

	}

}