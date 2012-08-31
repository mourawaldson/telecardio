package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;
import nutes.telecardio.utils.Datas;

/**
 * Dao de Usuario
 * 
 * @author hvb
 * 
 */
public class UsuarioDao extends ObjetoNegocioDao implements IUsuarioDao {

	@Override
	public boolean atualizar(Usuario objetoNegocio) {
		int affected = 0;
		try {
			stm = con.getStatement();

			String query = "Update " + CamposBanco.usuarioTabela + " set "
					+ CamposBanco.usuarioLogin + "='"
					+ objetoNegocio.getLogin() + "', "
					+ CamposBanco.usuarioIdPerfil + " = '"
					+ objetoNegocio.getPerfil().getChave() + "', "
					+ CamposBanco.usuarioEmail + " = '"
					+ objetoNegocio.getEmail() + "' where " + CamposBanco.id
					+ "= " + objetoNegocio.getChave();

			affected = stm.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
			affected = 0;
		} finally {
			fecharConexao();
		}

		return affected > 0 ? true : false;

	}

	@Override
	public boolean excluir(Usuario objetoNegocio) {
		int affected = 0;
		try {
			stm = con.getStatement();
			String query = "Update " + CamposBanco.pessoaTabela + " set "
					+ CamposBanco.status + "= '" + CamposBanco.statusInativo
					+ "' where " + CamposBanco.id + " ="
					+ objetoNegocio.getChavePessoa();

			affected = stm.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
			affected = 0;
		} finally {
			fecharConexao();
		}

		return affected > 0 ? true : false;
	}

	@Override
	public int incluir(Usuario objetoNegocio) {
		int affected = 0;
		try {
			stm = con.getStatement();

			String query = "Insert into " + CamposBanco.usuarioTabela + " ("
					+ CamposBanco.usuarioLogin + ", "
					+ CamposBanco.usuarioSenha + ", "
					+ CamposBanco.usuarioEmail + ","
					+ CamposBanco.usuarioIdPerfil + ", "
					+ CamposBanco.usuarioIdPessoa + ") values('"
					+ objetoNegocio.getLogin() + "', '"
					+ objetoNegocio.getSenha() + "', '"
					+ objetoNegocio.getEmail() + "', "
					+ objetoNegocio.getPerfil().getChave() + ","
					+ objetoNegocio.getChavePessoa() + ")";

			affected = stm.executeUpdate(query);

			if (affected > 0) {
				query = CamposBanco.selectIdentity + CamposBanco.usuarioTabela;
				ResultSet rs = stm.executeQuery(query);
				while (rs.next()) {
					affected = rs.getInt(CamposBanco.id);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
			affected = 0;
		} finally {
			fecharConexao();
		}

		return affected;
	}

	@Override
	public Usuario consultarUsuarioPorLoginSenha(Usuario usuario) {
		Usuario user = null;

		try {
			stm = con.getStatement();

			String query = "SELECT u." + CamposBanco.id + " as usu_id, u."
					+ CamposBanco.usuarioLogin + " as usu_login, u."
					+ CamposBanco.usuarioSenha + " as usu_senha, u."
					+ CamposBanco.usuarioEmail + " as usu_email,p."
					+ CamposBanco.id + " as pessoa_id, p."
					+ CamposBanco.pessoaNome + " as pessoa_nome, p."
					+ CamposBanco.pessoaCpf + " as pessoa_cpf, p."
					+ CamposBanco.pessoaSexo + " as pessoa_sexo, p."
					+ CamposBanco.pessoaDataNascimento
					+ " as pessoa_nascimento " + " FROM "
					+ CamposBanco.usuarioTabela + " u inner join "
					+ CamposBanco.pessoaTabela + " p on p." + CamposBanco.id
					+ " = u." + CamposBanco.usuarioIdPessoa + " WHERE p."
					+ CamposBanco.status + " = '" + CamposBanco.statusAtivo
					+ "' " + " AND u." + CamposBanco.usuarioLogin + "= '"
					+ usuario.getLogin() + "' AND u."
					+ CamposBanco.usuarioSenha + " = '" + usuario.getSenha()
					+ "'";

			ResultSet rs;

			rs = stm.executeQuery(query);
			while (rs.next()) {
				user = null;
				user = new Usuario();

				user.setCpf(rs.getString("pessoa_cpf"));
				usuario.setDataNascimento(Datas.formatarData(rs
						.getDate("pessoa_nascimento"), Datas.dataBrasil));
				user.setChavePessoa(rs.getInt("pessoa_id"));
				user.setLogin(rs.getString("usu_login"));
				user.setNome(rs.getString("pessoa_nome"));
				user.setSenha(rs.getString("usu_senha"));
				user.setChave(rs.getInt("usu_id"));
				user.setEmail(rs.getString("usu_email"));
				user.setSexo(rs.getString("pessoa_sexo"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

		return user;
	}

	@Override
	public Usuario consultarUsuarioPorChave(int chaveUsuario) {
		Usuario usuario = null;

		try {
			stm = con.getStatement();
			String query = "SELECT u." + CamposBanco.id + " as usu_id, u."
					+ CamposBanco.usuarioLogin + " as usu_login, u."
					+ CamposBanco.usuarioSenha + " as usu_senha, " + "u."
					+ CamposBanco.usuarioEmail + " as usu_email,p."
					+ CamposBanco.id + " as pessoa_id, p."
					+ CamposBanco.pessoaNome + " as pessoa_nome, " + "p."
					+ CamposBanco.pessoaCpf + " as pessoa_cpf, p."
					+ CamposBanco.pessoaSexo + " as pessoa_sexo, " + "p."
					+ CamposBanco.pessoaDataNascimento
					+ " as pessoa_nascimento " + "FROM "
					+ CamposBanco.usuarioTabela + " u inner join "
					+ CamposBanco.pessoaTabela + " p on p." + CamposBanco.id
					+ " = u." + CamposBanco.usuarioIdPessoa + " " + " where p."
					+ CamposBanco.status + " = '" + CamposBanco.statusAtivo
					+ "' AND u." + CamposBanco.id + "= " + chaveUsuario;

			ResultSet rs;

			rs = stm.executeQuery(query);
			while (rs.next()) {
				usuario = null;
				usuario = new Usuario();

				usuario.setCpf(rs.getString("pessoa_cpf"));
				usuario.setDataNascimento(Datas.formatarData(rs
						.getDate("pessoa_nascimento"), Datas.dataBrasil));
				usuario.setChavePessoa(rs.getInt("pessoa_id"));
				usuario.setLogin(rs.getString("usu_login"));
				usuario.setNome(rs.getString("pessoa_nome"));
				usuario.setSenha(rs.getString("usu_senha"));
				usuario.setChave(rs.getInt("usu_id"));
				usuario.setEmail(rs.getString("usu_email"));
				usuario.setSexo(rs.getString("pessoa_sexo"));
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			fecharConexao();
		}

		return usuario;

	}

	@Override
	public List<Usuario> consultarUsuarios(String campo, String valor) {
		List<Usuario> usuarios = null;

		Usuario usuario = null;

		try {
			stm = con.getStatement();
			String query = "SELECT u." + CamposBanco.id + " as usu_id, u."
					+ CamposBanco.usuarioLogin + " as usu_login, u."
					+ CamposBanco.usuarioSenha + " as usu_senha, " + "u."
					+ CamposBanco.usuarioEmail + " as usu_email,p."
					+ CamposBanco.id + " as pessoa_id, p."
					+ CamposBanco.pessoaNome + " as pessoa_nome, " + "p."
					+ CamposBanco.pessoaCpf + " as pessoa_cpf, p."
					+ CamposBanco.pessoaSexo + " as pessoa_sexo, " + "p."
					+ CamposBanco.pessoaDataNascimento
					+ " as pessoa_nascimento " + "FROM "
					+ CamposBanco.usuarioTabela + " u inner join "
					+ CamposBanco.pessoaTabela + " p on p." + CamposBanco.id
					+ " = u." + CamposBanco.usuarioIdPessoa + " where p."
					+ CamposBanco.status + " = '" + CamposBanco.statusAtivo
					+ "' AND " + CamposBanco.usuarioLogin + " <> 'adm'";

			if (campo != null && valor != null) {
				query += "and " + campo + " like '%" + valor + "%'";
				query += " ORDER BY " + campo + " ";
			} else {
				query += " ORDER BY p." + CamposBanco.pessoaNome;
			}

			ResultSet rs;

			rs = stm.executeQuery(query);
			usuarios = new ArrayList<Usuario>();
			while (rs.next()) {
				usuario = null;
				usuario = new Usuario();

				usuario.setCpf(rs.getString("pessoa_cpf"));
				usuario.setChavePessoa(rs.getInt("pessoa_id"));
				usuario.setDataNascimento(Datas.formatarData(rs
						.getDate("pessoa_nascimento"), Datas.dataBrasil));
				usuario.setLogin(rs.getString("usu_login"));
				usuario.setNome(rs.getString("pessoa_nome"));
				usuario.setSenha(rs.getString("usu_senha"));
				usuario.setChave(rs.getInt("usu_id"));
				usuario.setEmail(rs.getString("usu_email"));
				usuario.setSexo(rs.getString("pessoa_sexo"));

				usuarios.add(usuario);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}

		return usuarios;

	}

}
