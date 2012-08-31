package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;

/**
 * Dao de Perfil
 * 
 * @author hvb
 * 
 */
public class PerfilDao extends ObjetoNegocioDao implements IPerfilDao {

	@Override
	public boolean atualizar(Perfil objetoNegocio) {

		return false;
	}

	@Override
	public boolean excluir(Perfil objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int incluir(Perfil objetoNegocio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Perfil> consultarListaPerfil() {

		List<Perfil> perfis = null;

		try {
			stm = con.getStatement();
			String query = "Select " + CamposBanco.id + ", "
					+ CamposBanco.perfilNome + " from "
					+ CamposBanco.perfilTabela + "";

			ResultSet rs;
			rs = stm.executeQuery(query);

			perfis = new ArrayList<Perfil>();
			while (rs.next()) {
				Perfil perfil = null;
				perfil = new Perfil();

				perfil.setChave(rs.getInt(CamposBanco.id));
				perfil.setNome(rs.getString(CamposBanco.perfilNome));

				String queryPerfil = "SELECT f." + CamposBanco.id + ", f."
						+ CamposBanco.funcionalidadeNome + ", f."
						+ CamposBanco.funcionalidadeDescricao + " " + "FROM "
						+ CamposBanco.funcionalidadeTabela + " f inner join "
						+ CamposBanco.funcionalidadePerfilTabela + " fp "
						+ "on f." + CamposBanco.id + " = fp."
						+ CamposBanco.funcionalidadePerfilIdFuncionalidade
						+ " where fp."
						+ CamposBanco.funcionalidadePerfilIdPerfil + " = "
						+ perfil.getChave();

				Statement stmPerfil = con.getStatement();
				ResultSet rsFuncionalidade = stmPerfil
						.executeQuery(queryPerfil);

				List<Funcionalidade> listaFuncionalidade = null;
				while (rsFuncionalidade.next()) {
					Funcionalidade funcionalidade = null;
					funcionalidade = new Funcionalidade();
					listaFuncionalidade = new ArrayList<Funcionalidade>();

					funcionalidade.setChave(rsFuncionalidade
							.getInt(CamposBanco.id));
					funcionalidade.setDescricao(rsFuncionalidade
							.getString(CamposBanco.funcionalidadeDescricao));
					funcionalidade.setNome(rsFuncionalidade
							.getString(CamposBanco.funcionalidadeNome));

					listaFuncionalidade.add(funcionalidade);
				}
				perfil.setFuncionalidades(listaFuncionalidade);

				perfis.add(perfil);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

		return perfis;
	}

	@Override
	public Perfil ConsultarPerfilPorUsuario(Usuario usuario) {
		Perfil perfil = null;
		try {
			stm = con.getStatement();
			String query = "SELECT p." + CamposBanco.id + ", p."
					+ CamposBanco.perfilNome + " " + "FROM "
					+ CamposBanco.perfilTabela + " p inner join "
					+ CamposBanco.usuarioTabela + " u on u."
					+ CamposBanco.usuarioIdPerfil + " = p." + CamposBanco.id
					+ " " + "WHERE u." + CamposBanco.id + " = "
					+ usuario.getChave();

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				perfil = new Perfil();
				perfil.setChave(rs.getInt(CamposBanco.id));
				perfil.setNome(rs.getString(CamposBanco.perfilNome));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

		return perfil;
	}
}
