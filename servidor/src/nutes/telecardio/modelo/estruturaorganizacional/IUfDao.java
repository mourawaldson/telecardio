package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface de UfDao
 * 
 * @author hvb
 * 
 */
public interface IUfDao extends IObjetoNegocioDao<Uf> {

	/**
	 * Retorna uma lista dos estados Uf que existe cadastrado no sistema
	 * consultado por orgao de classe
	 * 
	 * @return List<Uf> uf
	 */
	public List<Uf> consultarListaUfPorOrgaoClasse(OrgaoClasse orgaoClasse);

	/**
	 * Retorna uma lista das siglas dos estados que existe cadastrado no sistema
	 * 
	 * @return List<Uf> nomesSiglasUf
	 */
	public List<Uf> consultarListaUf();

	/**
	 * Consulta um Uf pelo endereço
	 * 
	 * @param Endereco
	 *            endereco
	 * @return Uf uf
	 */
	public Uf ConsultarUfPorEndereco(Endereco endereco);

	/**
	 * Consulta uma {@link Uf} por um id.
	 * 
	 * @return {@link Uf}
	 */
	public Uf consultarUfPorId(int id);

}
