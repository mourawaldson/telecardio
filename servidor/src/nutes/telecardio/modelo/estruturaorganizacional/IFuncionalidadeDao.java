package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface de Funcionalidade
 * 
 * @author hvb
 * 
 */
public interface IFuncionalidadeDao extends IObjetoNegocioDao<Funcionalidade> {

	/**
	 * Consulta uma lista de funcionalidade para o perfil ou para um usuario
	 * 
	 * @param Usuario
	 *            usuario
	 * @param PerfilWebService
	 *            perfil
	 * @return List<Funcionalidade> funcionalidades
	 */
	public List<Funcionalidade> ConsultarFuncionalidadesPorPerfilOuUsuario(
			Usuario usuario, Perfil perfil);

}
