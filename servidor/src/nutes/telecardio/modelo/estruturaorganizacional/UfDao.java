package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;

/**
 * Dao de Uf
 * 
 * @author hvb
 * 
 */
public class UfDao extends ObjetoNegocioDao implements IUfDao {

	@Override
	public boolean atualizar(Uf objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Uf objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int incluir(Uf objetoNegocio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Uf> consultarListaUfPorOrgaoClasse(OrgaoClasse objetoNegocio) {
		List<Uf> ufs = null;

		try {
			stm = con.getStatement();
			String query = "Select e." + CamposBanco.id + ", e."
					+ CamposBanco.ufNome + ", e." + CamposBanco.ufSigla
					+ " from " + CamposBanco.ufTabela + " e inner join "
					+ CamposBanco.orgaosClassesEstadosTabela + " oce "
					+ "on e." + CamposBanco.id + " = oce."
					+ CamposBanco.orgaosClassesEstadosIdEstado + " where oce."
					+ CamposBanco.orgaosClassesEstadosIdOrgaoClasseIdEstado
					+ " = " + objetoNegocio.getChave();

			ResultSet rs;
			rs = stm.executeQuery(query);

			ufs = new ArrayList<Uf>();
			while (rs.next()) {
				Uf uf = null;
				uf = new Uf();
				uf.setChave(rs.getInt(CamposBanco.id));
				uf.setNome(rs.getString(CamposBanco.ufNome));
				uf.setSigla(rs.getString(CamposBanco.ufSigla));

				ufs.add(uf);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

		return ufs;
	}

	@Override
	public List<Uf> consultarListaUf() {
		List<Uf> ufs = new ArrayList<Uf>();

		String sql = null;
		ResultSet rs = null;

		try {
			stm = con.getStatement();
			sql = "SELECT " + CamposBanco.id + "," + CamposBanco.ufNome + ","
					+ CamposBanco.ufSigla;
			sql += " FROM " + CamposBanco.ufTabela;

			rs = stm.executeQuery(sql);

			Uf uf = null;

			while (rs.next()) {
				uf = new Uf();

				uf.setChave(rs.getInt(CamposBanco.id));
				uf.setNome(rs.getString(CamposBanco.ufNome));
				uf.setSigla(rs.getString(CamposBanco.ufSigla));

				ufs.add(uf);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return ufs;
	}

	@Override
	public Uf ConsultarUfPorEndereco(Endereco endereco) {
		Uf uf = null;
		try {
			stm = con.getStatement();
			String query = "SELECT es." + CamposBanco.id + ", es."
					+ CamposBanco.ufNome + ", es." + CamposBanco.ufSigla
					+ " FROM " + CamposBanco.ufTabela + " es inner join "
					+ CamposBanco.enderecoTabela + " ed on es."
					+ CamposBanco.id + " = ed." + CamposBanco.enderecoIdEstado
					+ " " + "WHERE ed." + CamposBanco.id + " ="
					+ endereco.getChave();

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				uf = new Uf();

				uf.setChave(rs.getInt(CamposBanco.id));
				uf.setNome(rs.getString(CamposBanco.ufNome));
				uf.setSigla(rs.getString(CamposBanco.ufSigla));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

		return uf;
	}

	/**
	 * Consulta uma {@link Uf} por um id.
	 * 
	 * @return {@link Uf}
	 */
	@Override
	public Uf consultarUfPorId(int id) {
		String sql = null;
		ResultSet rs = null;

		Uf uf = new Uf();
		uf.setChave(id);

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.ufNome + "," + CamposBanco.ufSigla;
			sql += " FROM " + CamposBanco.ufTabela;
			sql += " WHERE " + CamposBanco.id + " = " + id;

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				uf.setNome(rs.getString(CamposBanco.ufNome));
				uf.setSigla(rs.getString(CamposBanco.ufSigla));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return uf;
	}
}
