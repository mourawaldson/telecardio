package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.SQLException;
import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface de ProfissionalDao
 * 
 * @author hvb
 * 
 */
public interface IProfissionalDao extends IObjetoNegocioDao<Profissional> {

	/**
	 * Consulta um profissional pelo usuario e retorna o mesmo
	 * 
	 * @param usuario
	 * @return Profissional profissional
	 * @throws SQLException
	 */
	public Profissional ConsultarProfissionalPorUsuario(Usuario usuario);

	/**
	 * Exclui um registro de profissional permanentemente pelo usuario ao qual
	 * ele esta relacionado
	 * 
	 * @return boolean
	 */
	public boolean excluirProfissionalPermanentePorUsuario(Usuario usuario);

	/**
	 * Consulta todos os profissionais do tipo médico, menos o passado por
	 * parâmetro!
	 * 
	 * @param id
	 * @return List<{@link Profissional}>
	 */
	public List<Profissional> consultarMedicosMenosParametro(int id);

	/**
	 * Consulta um profissional pelo usuario e retorna o mesmo
	 * 
	 * @param int chaveProfissional
	 * @return Profissional profissional
	 * @throws SQLException
	 */
	public Profissional ConsultarProfissionalPorChave(int chaveProfissional);

	/**
	 * Verifica se a habilitação do profissional ja existe
	 * 
	 * @param Profisional
	 *            profissional
	 * @return boolean
	 * @throws SQLException
	 */
	public boolean verificarExistenciaHabilitacao(Profissional profissional,
			boolean isAlteracao);

}
