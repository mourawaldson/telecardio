package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;

/**
 * Dao da classe ProfissionaisTipo
 * 
 * @author hvb
 * 
 */
public class ProfissionaisTipoDao extends ObjetoNegocioDao implements
		IProfissionaisTipoDao {

	@Override
	public boolean atualizar(ProfissionalTipo objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(ProfissionalTipo objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int incluir(ProfissionalTipo objetoNegocio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<ProfissionalTipo> consultarListaProfissionaisTipoPorOrgao(
			OrgaoClasse objetoNegocio) {

		List<ProfissionalTipo> profissionaisTipos = null;

		try {
			stm = con.getStatement();
			String query = "select " + CamposBanco.id + ", "
					+ CamposBanco.profissionalTipoIdOrgaoClasse + ","
					+ CamposBanco.profissionalTipoNome + " from "
					+ CamposBanco.profissionalTipoTabela + " where "
					+ CamposBanco.profissionalTipoIdOrgaoClasse + " = '"
					+ objetoNegocio.getChave() + "'";

			ResultSet rs;
			rs = stm.executeQuery(query);

			profissionaisTipos = new ArrayList<ProfissionalTipo>();
			while (rs.next()) {
				ProfissionalTipo profissionaisTipo = null;
				profissionaisTipo = new ProfissionalTipo();

				OrgaoClasse orgaoClasse = null;
				orgaoClasse = new OrgaoClasse();

				orgaoClasse.setChave(rs
						.getInt(CamposBanco.profissionalTipoIdOrgaoClasse));

				profissionaisTipo.setChave(rs.getInt(CamposBanco.id));
				profissionaisTipo.setOrgaoClasse(orgaoClasse);
				profissionaisTipo.setNome(rs
						.getString(CamposBanco.profissionalTipoNome));

				profissionaisTipos.add(profissionaisTipo);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			con.fecharConexao();
		}

		return profissionaisTipos;
	}

}
