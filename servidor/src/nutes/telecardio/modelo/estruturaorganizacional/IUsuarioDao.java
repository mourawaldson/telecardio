package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface de UsuarioDao
 * 
 * @author hvb
 * 
 */
public interface IUsuarioDao extends IObjetoNegocioDao<Usuario> {

	/**
	 * Consulta um usuario pelo login e a senha
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Usuario usuario
	 */
	public Usuario consultarUsuarioPorLoginSenha(Usuario usuario);

	/**
	 * Faz uma consulta por campo de pesquisa e o valor no banco e retorna uma
	 * lista de usuarios
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @return List<Usuario> usuario
	 */
	public List<Usuario> consultarUsuarios(String campo, String valor);

	/**
	 * Consulta um usuario pela chave e retorna o usuario consultado
	 * 
	 * @param int chaveUsuario
	 * @return Usuario usuario
	 */
	public Usuario consultarUsuarioPorChave(int chaveUsuario);

}
