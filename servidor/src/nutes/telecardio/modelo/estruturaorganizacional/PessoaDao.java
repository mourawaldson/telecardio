package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;

import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;
import nutes.telecardio.utils.Datas;

/**
 * Dao de Pessoa
 * 
 * @author hvb
 * 
 */
public class PessoaDao extends ObjetoNegocioDao implements IPessoaDao {

	@Override
	public boolean atualizar(Pessoa objetoNegocio) {
		int affected = 0;
		try {
			stm = con.getStatement();

			String query = "UPDATE " + CamposBanco.pessoaTabela + " SET "
					+ CamposBanco.pessoaNome + " = '"
					+ objetoNegocio.getNome().trim() + "', "
					+ CamposBanco.pessoaCpf + " = '" + objetoNegocio.getCpf()
					+ "'," + CamposBanco.pessoaDataNascimento + " = '"
					+ objetoNegocio.getDataHoraEua() + "',"
					+ CamposBanco.pessoaSexo + " = '" + objetoNegocio.getSexo()
					+ "'";
			if (objetoNegocio.getEndereco() != null)
				query += "," + CamposBanco.pessoaIdEndereco + " = "
						+ objetoNegocio.getEndereco().getChave();
			query += " WHERE " + CamposBanco.id + " = "
					+ objetoNegocio.getChavePessoa();

			affected = stm.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fecharConexao();
		}

		return affected > 0 ? true : false;
	}

	@Override
	public boolean excluir(Pessoa objetoNegocio) {
		int affected = 0;
		try {
			stm = con.getStatement();

			String query = "UPDATE " + CamposBanco.pessoaTabela + " SET "
					+ CamposBanco.status + " ='" + CamposBanco.statusInativo
					+ "'" + "  WHERE " + CamposBanco.id + " = "
					+ objetoNegocio.getChavePessoa();

			affected = stm.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fecharConexao();
		}

		return affected > 0 ? true : false;
	}

	/**
	 * Incluir uma Pessoa.
	 * 
	 * @param Pessoa
	 * @return int id do registro incluído.
	 */
	@Override
	public int incluir(Pessoa objetoNegocio) {
		int affected = 0;
		String nome = objetoNegocio.getNome();
		String dataNascimento = objetoNegocio.getDataHoraEua();
		String cpf = objetoNegocio.getCpf();
		String sexo = objetoNegocio.getSexo();
		int idEndereco = 0;

		if (objetoNegocio.getEndereco() != null)
			idEndereco = objetoNegocio.getEndereco().getChave();

		String sqlIncluir = null;
		try {
			stm = con.getStatement();

			sqlIncluir = "INSERT INTO " + CamposBanco.pessoaTabela + " (";

			sqlIncluir += CamposBanco.pessoaNome + ","
					+ CamposBanco.pessoaDataNascimento + ","
					+ CamposBanco.pessoaSexo + "," + CamposBanco.status;

			if (cpf != null)
				sqlIncluir += "," + CamposBanco.pessoaCpf;
			if (idEndereco != 0)
				sqlIncluir += "," + CamposBanco.pessoaIdEndereco;

			sqlIncluir += ") VALUES (";

			sqlIncluir += "'" + nome.trim() + "','" + dataNascimento + "','"
					+ sexo + "','" + CamposBanco.statusAtivo + "'";

			if (cpf != null)
				sqlIncluir += ",'" + cpf + "'";
			if (idEndereco != 0)
				sqlIncluir += "," + idEndereco;

			sqlIncluir += ");";

			affected = stm.executeUpdate(sqlIncluir);

			if (affected > 0) {
				String query = CamposBanco.selectIdentity
						+ CamposBanco.pessoaTabela;
				ResultSet rs = stm.executeQuery(query);
				while (rs.next())
					affected = rs.getInt(CamposBanco.id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			affected = 0;
		} finally {
			fecharConexao();
		}

		return affected;
	}

	/**
	 * Consulta uma {@link Pessoa} por um id.
	 * 
	 * @return {@link Pessoa}
	 */
	@Override
	public Pessoa consultarPorId(int id) {

		Pessoa pessoa = new Pessoa();
		pessoa.setEndereco(new Endereco());

		String sql = null;
		ResultSet rs = null;

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.pessoaNome + ","
					+ CamposBanco.pessoaCpf + ","
					+ CamposBanco.pessoaDataNascimento + ","
					+ CamposBanco.pessoaIdEndereco + ","
					+ CamposBanco.pessoaSexo;
			sql += " FROM " + CamposBanco.pessoaTabela;
			sql += " WHERE " + CamposBanco.id + " = " + id;

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				pessoa.setNome(rs.getString(CamposBanco.pessoaNome));
				pessoa.setCpf(rs.getString(CamposBanco.pessoaCpf));
				pessoa.setDataNascimento(Datas.formatarData(rs
						.getDate(CamposBanco.pessoaDataNascimento),
						Datas.dataBrasil));
				pessoa.getEndereco().setChave(
						rs.getInt(CamposBanco.pessoaIdEndereco));
				pessoa.setSexo(rs.getString(CamposBanco.pessoaSexo));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return pessoa;
	}

	/**
	 * Verifica se existe algum registro com o cpf informado. O idPessoa serve
	 * apenas para o alterar, pois o proprio registro não vai ser consultado na
	 * existência do cpf.
	 * 
	 * @return boolean
	 */
	@Override
	public boolean consultarExistenciaCpf(String cpf, int idPessoa) {

		String sql = null;
		ResultSet rs = null;
		int affected = 0;

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.id;
			sql += " FROM " + CamposBanco.pessoaTabela;
			sql += " WHERE " + CamposBanco.pessoaCpf + " = '" + cpf + "'";
			if (idPessoa != 0)
				sql += " AND " + CamposBanco.id + " <> " + idPessoa;

			rs = stm.executeQuery(sql);

			while (rs.next())
				affected = rs.getInt(CamposBanco.id);

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fecharConexao();
		}

		return affected > 0 ? true : false;
	}
}
