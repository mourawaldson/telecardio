package nutes.telecardio.modelo.operacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;
import nutes.telecardio.modelo.estruturaorganizacional.Endereco;
import nutes.telecardio.modelo.estruturaorganizacional.Pessoa;
import nutes.telecardio.utils.Datas;

/**
 * Paciente DAO.
 * 
 * @author WaldsonMoura
 * 
 */
public class PacienteDao extends ObjetoNegocioDao implements IPacienteDao {

	/**
	 * Inclui um {@link Paciente}.
	 * 
	 * @param {@link Paciente}
	 * @return int id do registro incluído.
	 */
	@Override
	public int incluir(Paciente paciente) {
		int idRetorno = 0;
		int idPessoa = paciente.getChavePessoa();
		int idConvenio = paciente.getIdConvenio();
		String nomeMae = paciente.getNomeMae();
		int idTipoSanguineo = paciente.getTipoSanguineo().getChave();

		String sql = null;

		try {
			stm = con.getStatement();

			sql = "INSERT INTO " + CamposBanco.pacienteTabela + " (";

			sql += CamposBanco.pacienteIdPessoa;

			if (idConvenio != 0)
				sql += "," + CamposBanco.pacienteIdConvenio;
			if (nomeMae != null)
				sql += "," + CamposBanco.pacienteNomeMae;

			sql += "," + CamposBanco.pacienteIdTipoSanguineo;

			sql += ") VALUES (";

			sql += idPessoa;

			if (idConvenio != 0)
				sql += "," + idConvenio;
			if (nomeMae != null)
				sql += ",'" + nomeMae.trim() + "'";

			sql += "," + idTipoSanguineo;

			sql += ");";

			idRetorno = stm.executeUpdate(sql);

			if (idRetorno > 0) {
				String query = CamposBanco.selectIdentity
						+ CamposBanco.pacienteTabela;
				ResultSet rs = stm.executeQuery(query);
				while (rs.next())
					idRetorno = rs.getInt(CamposBanco.id);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			idRetorno = 0;
		} finally {
			fecharConexao();
		}

		return idRetorno;
	}

	/**
	 * Atualiza as informações do {@link Paciente}.
	 * 
	 * @param {@link Paciente}
	 * @return boolean
	 */
	@Override
	public boolean atualizar(Paciente paciente) {
		int affected = 0;
		int idConvenio = paciente.getIdConvenio();
		String dataUltimoContato = paciente.getDataUltimoContato();
		String nomeMae = paciente.getNomeMae().trim();
		int idTipoSanguineo = paciente.getTipoSanguineo().getChave();

		String sql = null;

		try {
			stm = con.getStatement();

			sql = "UPDATE " + CamposBanco.pacienteTabela + " SET ";

			sql += CamposBanco.pacienteIdTipoSanguineo + " = "
					+ idTipoSanguineo;

			if (idConvenio != 0)
				sql += "," + CamposBanco.pacienteIdConvenio + " = "
						+ idConvenio;
			if (nomeMae != null)
				sql += "," + CamposBanco.pacienteNomeMae + " = '" + nomeMae
						+ "'";
			if (dataUltimoContato != null)
				sql += "," + CamposBanco.pacienteDataUltimoContato + " = '"
						+ dataUltimoContato + "'";

			sql += " WHERE " + CamposBanco.id + " = " + paciente.getChave();

			affected = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fecharConexao();
		}

		return (affected > 0) ? true : false;
	}

	/**
	 * Torna a {@link Pessoa} Inativa no sistema.
	 * 
	 * @param {@link Paciente}
	 * @return boolean
	 */
	@Override
	public boolean excluir(Paciente paciente) {
		int affected = 0;
		int idPaciente = paciente.getChave();

		String sql = null;

		try {
			stm = con.getStatement();

			sql = "UPDATE " + CamposBanco.pessoaTabela + " SET "
					+ CamposBanco.status + " = '" + CamposBanco.statusInativo
					+ "' WHERE " + CamposBanco.id + " = (SELECT "
					+ CamposBanco.pacienteIdPessoa + " FROM "
					+ CamposBanco.pacienteTabela + " WHERE " + CamposBanco.id
					+ " = " + idPaciente + ")";

			affected = stm.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fecharConexao();
		}

		return (affected > 0) ? true : false;
	}

	/**
	 * Retorna uma List<{@link Paciente}> com todos os pacientes.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @return List<{@link Paciente}>
	 */
	@Override
	public List<Paciente> consultarTodos(String campo, String valor) {
		List<Paciente> pacientes = new ArrayList<Paciente>();

		String sql = null;
		ResultSet rs = null;

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.id + ","
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaNome + ","
					+ CamposBanco.pessoaDataNascimento + ","
					+ CamposBanco.tipoSanguineoTabelaComSeparador
					+ CamposBanco.tipoSanguineoNome + " AS "
					+ CamposBanco.tipoSanguineoAlias;
			sql += " FROM " + CamposBanco.pacienteTabela;
			sql += " INNER JOIN " + CamposBanco.pessoaTabela + " ON "
					+ CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.pacienteIdPessoa + " = "
					+ CamposBanco.pessoaTabelaComSeparador + CamposBanco.id;
			sql += " INNER JOIN " + CamposBanco.tipoSanguineoTabela + " ON "
					+ CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.pacienteIdTipoSanguineo + " = "
					+ CamposBanco.tipoSanguineoTabelaComSeparador
					+ CamposBanco.id;
			sql += " WHERE " + CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.status + " = '" + CamposBanco.statusAtivo
					+ "'";

			if (campo != null && valor != null) {
				if (campo.equalsIgnoreCase(CamposBanco.pessoaNome))
					campo = CamposBanco.pessoaTabelaComSeparador
							+ CamposBanco.pessoaNome;
				sql += " AND " + campo + " LIKE '%" + valor + "%'";
				sql += " ORDER BY " + campo;
			} else
				sql += " ORDER BY " + CamposBanco.pessoaTabelaComSeparador
						+ CamposBanco.pessoaNome;

			rs = stm.executeQuery(sql);

			Paciente paciente = null;

			while (rs.next()) {
				paciente = new Paciente();

				paciente.setChave(rs.getInt(CamposBanco.id));
				paciente.getTipoSanguineo().setNome(
						rs.getString(CamposBanco.tipoSanguineoAlias));
				paciente.setNome(rs.getString(CamposBanco.pessoaNome));
				paciente.setDataNascimento(Datas.formatarData(rs
						.getDate(CamposBanco.pessoaDataNascimento),
						Datas.dataBrasil));

				pacientes.add(paciente);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return pacientes;
	}

	/**
	 * Consulta pelo nome e pela data de nascimento se o {@link Paciente} já
	 * está cadastrado.
	 * 
	 * @param {@link Paciente}
	 * @return int id do {@link Paciente} caso ele exista e 0 se não existir.
	 */
	public int consultarExistencia(Paciente paciente) {
		int idRetorno = 0;
		String nome = paciente.getNome();
		String dataNascimento = paciente.getDataHoraEua();

		String sql = null;
		ResultSet rs = null;

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.id;
			sql += " FROM " + CamposBanco.pacienteTabela;
			sql += " INNER JOIN " + CamposBanco.pessoaTabela;
			sql += " ON " + CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.pacienteIdPessoa + " = "
					+ CamposBanco.pessoaTabelaComSeparador + CamposBanco.id;
			sql += " WHERE (" + CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaNome + " = '" + nome + "' AND "
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaDataNascimento
					+ " = CONVERT(DATETIME, '" + dataNascimento + "', 102))";

			rs = stm.executeQuery(sql);

			while (rs.next())
				idRetorno = rs.getInt(CamposBanco.id);

		} catch (SQLException e) {
			e.printStackTrace();
			return idRetorno = 0;
		} finally {
			fecharConexao();
		}

		return idRetorno;
	}

	/**
	 * Retorna o {@link Paciente} de acordo com o Id que foi passado.
	 * 
	 * @param int
	 * @return {@link Paciente}
	 */
	public Paciente consultarPorId(int id) {
		String sql = null;
		ResultSet rs = null;

		Paciente paciente = new Paciente();
		paciente.setChave(id);
		paciente.setEndereco(new Endereco());

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.pessoaNome + ","
					+ CamposBanco.pessoaCpf + ","
					+ CamposBanco.pessoaDataNascimento + ","
					+ CamposBanco.pessoaIdEndereco + ","
					+ CamposBanco.pessoaSexo + ","
					+ CamposBanco.pacienteIdConvenio + ","
					+ CamposBanco.pacienteIdPessoa + ","
					+ CamposBanco.pacienteIdTipoSanguineo + ","
					+ CamposBanco.pacienteDataUltimoContato + ","
					+ CamposBanco.pacienteNomeMae;
			sql += " FROM " + CamposBanco.pacienteTabela;
			sql += " INNER JOIN " + CamposBanco.pessoaTabela;
			sql += " ON " + CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.pacienteIdPessoa + " = "
					+ CamposBanco.pessoaTabelaComSeparador + CamposBanco.id;
			sql += " WHERE " + CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.id + " = " + id;

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				paciente.setNome(rs.getString(CamposBanco.pessoaNome));
				paciente.setCpf(rs.getString(CamposBanco.pessoaCpf));
				paciente.setDataNascimento(Datas.formatarData(rs
						.getDate(CamposBanco.pessoaDataNascimento),
						Datas.dataBrasil));
				paciente.getEndereco().setChave(
						rs.getInt(CamposBanco.pessoaIdEndereco));
				paciente.setSexo(rs.getString(CamposBanco.pessoaSexo));
				paciente
						.setChavePessoa(rs.getInt(CamposBanco.pacienteIdPessoa));
				paciente.setIdConvenio(rs
						.getInt(CamposBanco.pacienteIdConvenio));
				paciente.getTipoSanguineo().setChave(
						rs.getInt(CamposBanco.pacienteIdTipoSanguineo));
				paciente.setDataUltimoContato(Datas.formatarData(rs
						.getDate(CamposBanco.pacienteDataUltimoContato),
						Datas.dataBrasil));
				paciente.setNomeMae(rs.getString(CamposBanco.pacienteNomeMae));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return paciente;
	}
}
