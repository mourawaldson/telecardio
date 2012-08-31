package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface Exame DAO.
 * 
 * @author WaldsonMoura
 * 
 */
public interface IExameDao extends IObjetoNegocioDao<Exame> {

	/**
	 * Inclui um novo Exame (solicitação), deixando seu estado 0 - Aberto.
	 * 
	 * @param Exame
	 * @return int rows affected
	 */
	public int incluir(Exame exame);

	/**
	 * Atualiza as informações do Exame.
	 * 
	 * @param Exame
	 * @return boolean
	 */
	public boolean atualizar(Exame exame);

	/**
	 * Consulta o estado de um {@link Exame} de acordo com o Id que foi passado.
	 * 
	 * @return int
	 */
	public int consultarEstado(int id);

	/**
	 * Laudar a soliciticação.
	 * 
	 * @param {@link Exame}
	 * @return boolean true caso tenha laudado, false caso não tenha laudado.
	 */
	public boolean laudar(Exame exame);

	/**
	 * Definir a solicitação como Laudado.
	 * 
	 * @param id
	 * @return boolean true caso tenha laudado, false caso não tenha laudado.
	 */
	public boolean definirLaudando(int id);

	/**
	 * Cancelar laudo (deixar solicitação no estado anterior).
	 * 
	 * @param {@link Exame}
	 * @return boolean true caso tenha cancelado, false caso não tenha
	 *         cancelado.
	 */
	public boolean cancelar(Exame exame);

	/**
	 * Retorna um {@link Exame} de acordo com o Id que foi passado..
	 * 
	 * @return {@link Exame}
	 */
	public Exame consultarPorId(int id);

	/**
	 * Retorna uma List<Exame> com todos os exames abertos.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @param int crm
	 * 
	 * @return List<Exame>
	 */
	public List<Exame> consultarTodosAbertos(String campo, String valor, int crm);

	/**
	 * Retorna uma List<{@link Exame}> com todos os exames laudados ou não
	 * laudados, contanto que os não laudados não tenham encaminhamento, ou
	 * seja, o crm do médico designado seja null. * @param String campo
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * 
	 * @return List<Exame>
	 */
	public List<Exame> consultarTodosConcluidos(String campo, String valor);
}
