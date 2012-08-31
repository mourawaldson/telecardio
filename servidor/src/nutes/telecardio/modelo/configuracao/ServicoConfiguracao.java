package nutes.telecardio.modelo.configuracao;

import java.util.List;

import javax.servlet.ServletContextEvent;

import nutes.telecardio.modelo.ServicoFuncionalidade;
import nutes.telecardio.modelo.Validador;
import nutes.telecardio.utils.IniFile;

/**
 * Serviço Configuração.
 * 
 * @author WaldsonMoura
 * 
 */
public class ServicoConfiguracao extends ServicoFuncionalidade {

	IConfiguracaoDao configuracaoDao = new ConfiguracaoDao();

	/**
	 * Inclui uma nova Configuração.
	 * 
	 * @param Configuracao
	 * @return boolean
	 */
	public Validador incluir(Configuracao configuracao) {
		Validador validador = new Validador();
		configuracao.validarEntidade(validador);

		if (validador.getMsgRetorno().size() <= 0) {
			if (configuracaoDao.incluir(configuracao) <= 0) {
				validador.getMsgRetorno().add(
						MensagensNegocio.configuracaoErroIncluir);
			}
		}

		return validador;
	}

	/**
	 * Atualiza as informações da Configuração.
	 * 
	 * @param Configuracao
	 * @return boolean
	 */
	public Validador atualizar(Configuracao configuracao) {
		Validador validador = new Validador();
		configuracao.validarEntidade(validador);

		if (validador.getMsgRetorno().size() <= 0) {
			if (!configuracaoDao.atualizar(configuracao)) {
				validador.getMsgRetorno().add(
						MensagensNegocio.configuracaoErroAtualizar);
			}
		}

		return validador;
	}

	/**
	 * Torna a Configuração Inativa no sistema.
	 * 
	 * @param Configuracao
	 * @return boolean
	 */
	public Validador ativar(Configuracao configuracao) {
		Validador validador = new Validador();
		configuracao.validarEntidade(validador);

		if (validador.getMsgRetorno().size() <= 0) {
			if (!configuracaoDao.ativar(configuracao)) {
				validador.getMsgRetorno().add(
						MensagensNegocio.configuracaoErroAtivar);
			}
		}

		return validador;
	}

	/**
	 * Retorna uma List<Configuracao> com todas as Configurações.
	 * 
	 * @return List<Configuracao>
	 */
	public List<Configuracao> consultarTodas() {
		return configuracaoDao.consultarTodas();
	}

	/**
	 * Retorna a Configuração Ativa.
	 * 
	 * @return Configuracao
	 */
	public Configuracao consultarAtiva() {
		return configuracaoDao.consultarAtiva();
	}

	/**
	 * Retorna a Configuração de acordo com o Id que foi passado.
	 * 
	 * @param int
	 * @return Configuracao
	 */
	public Configuracao consultarPorId(int id) {
		return configuracaoDao.consultarPorId(id);
	}

	/**
	 * Método que irá ler o arquivo config da raiz e carrega as configurações
	 * iniciais para o ConectorSql encontrar o bando de dados e assim carrega
	 * mais configurações do sistema que estão armazenadas no banco de dados
	 */
	public static void CarregarConfiguracaoSistema(ServletContextEvent sce) {
		IniFile iniFile = new IniFile(sce.getServletContext().getRealPath(
				NomesCampos.arquivoConfigIni));

		Configuracao.pathLocal = sce.getServletContext().getRealPath("");
		if (!iniFile.exists())
			iniFile.CriarIniFile();

		CarregarConfigConectorSqlServer(iniFile);
	}

	private static void CarregarConfigConectorSqlServer(IniFile iniFile) {
		ConectorSqlServer.setPortNumber(iniFile.LerValorChavePorSessao(
				NomesCampos.sessaoBanco, NomesCampos.chavePORTNUMBER));

		ConectorSqlServer.setDatabaseName(iniFile.LerValorChavePorSessao(
				NomesCampos.sessaoBanco, NomesCampos.chaveDATABASENAME));

		ConectorSqlServer.setPassword(iniFile.LerValorChavePorSessao(
				NomesCampos.sessaoBanco, NomesCampos.chavePASSWORD));

		ConectorSqlServer.setServerName(iniFile.LerValorChavePorSessao(
				NomesCampos.sessaoBanco, NomesCampos.chaveSERVERNAME));

		ConectorSqlServer.setUrl(iniFile.LerValorChavePorSessao(
				NomesCampos.sessaoBanco, NomesCampos.chaveUrl));

		ConectorSqlServer.setUserName(iniFile.LerValorChavePorSessao(
				NomesCampos.sessaoBanco, NomesCampos.chaveUSERNAME));

		ConectorSqlServer.setConfigCarregada(true);

	}

}
