package nutes.telecardio.modelo.configuracao;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface Configuracao DAO.
 * 
 * @author WaldsonMoura
 * 
 */
public interface IConfiguracaoDao extends IObjetoNegocioDao<Configuracao> {

	/**
	 * Inclui uma nova Configuração.
	 * 
	 * @param Configuracao
	 * @return boolean
	 */
	public int incluir(Configuracao configuracao);

	/**
	 * Atualiza as informações da Configuração.
	 * 
	 * @param Configuracao
	 * @return boolean
	 */
	public boolean atualizar(Configuracao configuracao);

	/**
	 * Torna a Configuração Inativa no sistema.
	 * 
	 * @param Configuracao
	 * @return boolean
	 */
	public boolean ativar(Configuracao configuracao);

	/**
	 * Retorna uma List<Configuracao> com todas as Configurações.
	 * 
	 * @return List<Configuracao>
	 */
	public List<Configuracao> consultarTodas();

	/**
	 * Retorna a Configuração Ativa.
	 * 
	 * @return Configuracao
	 */
	public Configuracao consultarAtiva();

	/**
	 * Retorna a Configuração de acordo com o Id que foi passado.
	 * 
	 * @param int
	 * @return Configuracao
	 */
	public Configuracao consultarPorId(int id);
}
