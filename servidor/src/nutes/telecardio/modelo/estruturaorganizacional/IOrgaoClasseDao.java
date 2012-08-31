package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface de OrgaoClasseDao
 * 
 * @author hvb
 * 
 */
public interface IOrgaoClasseDao extends IObjetoNegocioDao<OrgaoClasse> {

	/**
	 * Retorna uma lista de nome dos orgão de classes que existe cadastrado no
	 * sistema
	 * 
	 * @return List<OrgaoClasse> orgaoClasses
	 * @param List
	 *            <OrgaoClasse> orgaClasse
	 */
	public List<OrgaoClasse> consultarListaOrgaosClasse();

}
