package nutes.telecardio.modelo.operacional;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface {@link Paciente} DAO.
 * 
 * @author WaldsonMoura
 * 
 */
public interface IPacienteDao extends IObjetoNegocioDao<Paciente> {

	/**
	 * Inclui um novo {@link Paciente}.
	 * 
	 * @param {@link Paciente}
	 * @return int rows affected
	 */
	public int incluir(Paciente paciente);

	/**
	 * Atualiza as informações do {@link Paciente}.
	 * 
	 * @param {@link Paciente}
	 * @return boolean
	 */
	public boolean atualizar(Paciente paciente);

	/**
	 * Retorna uma List<{@link Paciente}> com todos os pacientes.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @return List<{@link Paciente}>
	 */
	public List<Paciente> consultarTodos(String campo, String valor);

	/**
	 * Consulta pelo nome e pela data de nascimento se o {@link Paciente} já
	 * está cadastrado.
	 * 
	 * @param {@link Paciente}
	 * @return int id do {@link Paciente} caso ele exista e 0 se não existir.
	 */
	public int consultarExistencia(Paciente paciente);

	/**
	 * Retorna o {@link Paciente} de acordo com o Id que foi passado.
	 * 
	 * @param int
	 * @return {@link Paciente}
	 */
	public Paciente consultarPorId(int id);
}