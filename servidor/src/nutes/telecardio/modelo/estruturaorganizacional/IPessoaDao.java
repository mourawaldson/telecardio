package nutes.telecardio.modelo.estruturaorganizacional;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface de Pessoa
 * 
 * @author hvb
 * 
 */
public interface IPessoaDao extends IObjetoNegocioDao<Pessoa> {

	/**
	 * Consulta uma {@link Pessoa} por um id.
	 * 
	 * @return {@link Pessoa}
	 */
	public Pessoa consultarPorId(int id);

	/**
	 * Verifica se existe algum registro com o cpf informado. O idPessoa serve
	 * apenas para o alterar, pois o proprio registro não vai ser consultado na
	 * existência do cpf.
	 * 
	 * @return boolean
	 */
	public boolean consultarExistenciaCpf(String cpf, int idPessoa);
}
