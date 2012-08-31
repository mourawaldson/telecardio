package nutes.telecardio.modelo.estruturaorganizacional;

import java.util.List;

import nutes.telecardio.modelo.IObjetoNegocioDao;

/**
 * Interface de ProfissionalTipoDao
 * 
 * @author hvb
 * 
 */
public interface IProfissionaisTipoDao extends
		IObjetoNegocioDao<ProfissionalTipo> {

	/**
	 * Retorna uma lista de tipos de profissionais cadastrados no sistema pelo
	 * orgao da classe
	 * 
	 * @return List<String> nomesProfissionaisTipo
	 * @param OrgaoClasse
	 *            orgaoClasse
	 */
	public List<ProfissionalTipo> consultarListaProfissionaisTipoPorOrgao(
			OrgaoClasse orgaoClasse);

}
