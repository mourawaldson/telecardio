package nutes.telecardio.modelo.operacional;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface Paciente DAO.
 * 
 * @author WaldsonMoura
 * 
 */
public interface ITipoSanguineoDao extends IObjetoNegocioDao<TipoSanguineo> {

	/**
	 * Retorna uma List<TipoSanguineo> com todos os tipos sanguíneos.
	 * 
	 * @return List<TipoSanguineo>
	 */
	public List<TipoSanguineo> consultarTodos();

	/**
	 * Retorna o Tipo Sanguíneo de acordo com o Id que foi passado.
	 * 
	 * @param int
	 * @return TipoSanguineo
	 */
	public TipoSanguineo consultarPorId(int id);
}