package nutes.telecardio.modelo.operacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;

/**
 * {@link TipoSanguineo} DAO.
 * 
 * @author WaldsonMoura
 * 
 */
public class TipoSanguineoDao extends ObjetoNegocioDao implements
		ITipoSanguineoDao {

	/**
	 * Retorna uma List<{@link TipoSanguineo}> com todos os tipos sanguíneos.
	 * 
	 * @return List<{@link TipoSanguineo}>
	 */
	@Override
	public List<TipoSanguineo> consultarTodos() {
		List<TipoSanguineo> tiposSanguineos = new ArrayList<TipoSanguineo>();

		String sql = null;
		ResultSet rs = null;

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.id + ","
					+ CamposBanco.tipoSanguineoNome;

			sql += " FROM " + CamposBanco.tipoSanguineoTabela;

			sql += " WHERE " + CamposBanco.id + " <> " + TipoSanguineo.sTS;

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				TipoSanguineo tipoSanguineo = new TipoSanguineo();

				int id = rs.getInt(CamposBanco.id);
				String nome = rs.getString(CamposBanco.tipoSanguineoNome);

				tipoSanguineo.setChave(id);
				tipoSanguineo.setNome(nome);

				tiposSanguineos.add(tipoSanguineo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return tiposSanguineos;
	}

	// REVISAR - Método ainda não usado!
	@Override
	public TipoSanguineo consultarPorId(int id) {
		String sql = null;
		ResultSet rs = null;

		TipoSanguineo tipoSanguineo = new TipoSanguineo();
		tipoSanguineo.setChave(id);

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.tipoSanguineoNome;

			sql += " FROM " + CamposBanco.tipoSanguineoTabela;

			sql += " WHERE " + CamposBanco.id + " = " + id;

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				String nome = rs.getString(CamposBanco.tipoSanguineoNome);

				tipoSanguineo.setNome(nome);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return tipoSanguineo;
	}

	@Override
	public boolean atualizar(TipoSanguineo objetoNegocio) {
		return false;
	}

	@Override
	public boolean excluir(TipoSanguineo objetoNegocio) {
		return false;
	}

	@Override
	public int incluir(TipoSanguineo objetoNegocio) {
		return 0;
	}

}
