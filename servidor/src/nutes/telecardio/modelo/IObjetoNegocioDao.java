package nutes.telecardio.modelo;

/**
 * Entidade comum a todas as interfaces do sistema
 * 
 * @author hvb
 * 
 * @param <TObjetoNegocio>
 */
public interface IObjetoNegocioDao<TObjetoNegocio> {

	/**
	 * Atualiza o obejto de negocio no banco de dados e retorna true se tiver
	 * sucesso e false se nao tiver sucesso
	 * 
	 * @param ObjetoNegocio
	 *            objetoNegocio
	 * @return boolean
	 */
	boolean atualizar(TObjetoNegocio objetoNegocio);

	/**
	 * Inclui o objeto de negocio no banco de dados e retorna o id do registro
	 * 
	 * @param ObjetoNegocio
	 *            objetoNegocio
	 * @return int rows affected
	 */
	int incluir(TObjetoNegocio objetoNegocio);

	/**
	 * Exclui o obejto de negocio do sistema e retorna vardadeiro se tiver
	 * sucesso e falso em caso contrario
	 * 
	 * @param Usuario
	 *            usuario
	 * @return boolean
	 */
	public boolean excluir(TObjetoNegocio objetoNegocio);

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fecharConexao();

}
