package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;

/**
 * Dao de funcionalidade
 * 
 * @author hvb
 * 
 */
public class FuncionalidadeDao extends ObjetoNegocioDao implements
		IFuncionalidadeDao {

	@Override
	public boolean atualizar(Funcionalidade objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean excluir(Funcionalidade objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int incluir(Funcionalidade objetoNegocio) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Funcionalidade> ConsultarFuncionalidadesPorPerfilOuUsuario(
			Usuario usuario, Perfil perfil) {
		List<Funcionalidade> funcionalidades = null;
		try {

			if (usuario != null && perfil == null) {
				String queryFuncionalidadesUsuario = "SELECT f."
						+ CamposBanco.id + ", f."
						+ CamposBanco.funcionalidadeNome + ", f."
						+ CamposBanco.funcionalidadeDescricao + " FROM "
						+ CamposBanco.funcionalidadeTabela + " f inner join "
						+ CamposBanco.funcionalidadeUsuarioTabela + " fu "
						+ "on f." + CamposBanco.id + " = fu."
						+ CamposBanco.funcionalidadeUsuarioIdFuncionalidade
						+ " where fu."
						+ CamposBanco.funcionalidadeUsuarioIdUsuario + " = "
						+ usuario.getChave();

				Statement stmFuncionalidadesUsuario = con.getStatement();
				ResultSet rsFuncionalidadesUsuario = stmFuncionalidadesUsuario
						.executeQuery(queryFuncionalidadesUsuario);

				funcionalidades = null;
				while (rsFuncionalidadesUsuario.next()) {
					Funcionalidade funcionalidade = null;
					funcionalidade = new Funcionalidade();
					funcionalidades = new ArrayList<Funcionalidade>();

					funcionalidade.setChave(rsFuncionalidadesUsuario
							.getInt(CamposBanco.id));
					funcionalidade.setDescricao(rsFuncionalidadesUsuario
							.getString(CamposBanco.funcionalidadeDescricao));
					funcionalidade.setNome(rsFuncionalidadesUsuario
							.getString(CamposBanco.funcionalidadeNome));

					funcionalidades.add(funcionalidade);
				}

			} else if (usuario == null && perfil != null) {

				String queryFuncionalidadesPerfil = "SELECT f."
						+ CamposBanco.id + ", f."
						+ CamposBanco.funcionalidadeNome + ", f."
						+ CamposBanco.funcionalidadeDescricao + " FROM "
						+ CamposBanco.funcionalidadeTabela + " f inner join "
						+ CamposBanco.funcionalidadePerfilTabela + " fp "
						+ "on f." + CamposBanco.id + " = fp."
						+ CamposBanco.funcionalidadePerfilIdFuncionalidade
						+ " where fp."
						+ CamposBanco.funcionalidadePerfilIdPerfil + "="
						+ perfil.getChave();

				Statement stmFuncionalidadesPerfil = con.getStatement();
				ResultSet rsFuncionalidadesPerfil = stmFuncionalidadesPerfil
						.executeQuery(queryFuncionalidadesPerfil);

				funcionalidades = null;
				while (rsFuncionalidadesPerfil.next()) {
					Funcionalidade funcionalidade = null;
					funcionalidade = new Funcionalidade();
					funcionalidades = new ArrayList<Funcionalidade>();

					funcionalidade.setChave(rsFuncionalidadesPerfil
							.getInt(CamposBanco.id));
					funcionalidade.setDescricao(rsFuncionalidadesPerfil
							.getString(CamposBanco.funcionalidadeDescricao));
					funcionalidade.setNome(rsFuncionalidadesPerfil
							.getString(CamposBanco.funcionalidadeNome));

					funcionalidades.add(funcionalidade);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

		return funcionalidades;

	}
}
