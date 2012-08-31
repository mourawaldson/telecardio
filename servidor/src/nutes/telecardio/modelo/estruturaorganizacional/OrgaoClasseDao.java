package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;

/**
 * Dao de OrgaoClasse
 * 
 * @author hvb
 * 
 */
public class OrgaoClasseDao extends ObjetoNegocioDao implements IOrgaoClasseDao {

	@Override
	public boolean atualizar(OrgaoClasse objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(OrgaoClasse objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int incluir(OrgaoClasse objetoNegocio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<OrgaoClasse> consultarListaOrgaosClasse() {
		List<OrgaoClasse> orgaosClasses = null;

		try {
			stm = con.getStatement();
			String query = "Select " + CamposBanco.id + ", "
					+ CamposBanco.orgaoClasseSigla + ", "
					+ CamposBanco.orgaoClasseNome + " from "
					+ CamposBanco.orgaoClasseTabela;

			ResultSet rs;
			rs = stm.executeQuery(query);

			orgaosClasses = new ArrayList<OrgaoClasse>();
			while (rs.next()) {
				OrgaoClasse orgaoClasse = null;
				orgaoClasse = new OrgaoClasse();
				orgaoClasse.setChave(rs.getInt(CamposBanco.id));
				orgaoClasse.setNome(rs.getString(CamposBanco.orgaoClasseNome));
				orgaoClasse
						.setSigla(rs.getString(CamposBanco.orgaoClasseSigla));

				orgaosClasses.add(orgaoClasse);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

		return orgaosClasses;

	}

}
