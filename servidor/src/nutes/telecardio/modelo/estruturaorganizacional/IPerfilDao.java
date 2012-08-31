package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface de PerfilDao
 * 
 * @author hvb
 * 
 */
public interface IPerfilDao extends IObjetoNegocioDao<Perfil> {

	/**
	 * Retorna uma lista de perfil do bando de dados
	 * 
	 * @return List<Perfil> perfis;
	 */
	List<Perfil> consultarListaPerfil();

	/**
	 * Consulta um perfil pelo usuario
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Perfil perfil
	 */
	Perfil ConsultarPerfilPorUsuario(Usuario usuario);

}
