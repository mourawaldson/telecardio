package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface de Endereço
 * 
 * @author hvb
 * 
 */
public interface IEnderecoDao extends IObjetoNegocioDao<Endereco> {

	/**
	 * Consulta um endereço pelo usuário
	 * 
	 * @param Usuario
	 *            usuario
	 * @return Endereco endereco
	 */
	public Endereco ConsultarEnderecoPorUsuario(Usuario usuario);

	/**
	 * Retorna o {@link Endereco} de acordo com o Id fornecido.
	 * 
	 * @param id
	 * @return {@link Endereco}
	 */
	public Endereco ConsultarEnderecoPorId(int id);
}
