package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;
import nutes.telecardio.utils.Datas;

/**
 * Exame DAO.
 * 
 * @author WaldsonMoura
 * 
 */
public class ExameDao extends ObjetoNegocioDao implements IExameDao {

	/**
	 * Inclui um novo {@link Exame} (solicitação), deixando seu Estado 0 -
	 * Aberto.
	 * 
	 * @param {@link Exame}
	 * @return int rows affected
	 */
	@Override
	public int incluir(Exame exame) {
		int idRetorno = 0;
		int idPaciente = exame.getPaciente().getChave();
		int idProfissional = 0;
		if (exame.getProfissional() != null)
			idProfissional = exame.getProfissional().getChave();
		String emailRequisitante = exame.getEmailRequisitante();
		int medicoDesignadoCrm = exame.getMedicoDesignadoCrm();
		String parecerJustificativa = exame.getParecerJustificativa();
		String caminhoArquivos = exame.getCaminhoArquivos();
		String dataSolicitacao = exame.getDataSolicitacao();
		String dataConclusao = exame.getDataConclusao();
		float pesoPaciente = exame.getPesoPaciente();
		float alturaPaciente = exame.getAlturaPaciente();
		int cid = exame.getCid();
		String observacoes = exame.getObservacoes();

		String sql = null;

		try {
			stm = con.getStatement();
			sql = "INSERT INTO " + CamposBanco.exameTabela + " ("
					+ CamposBanco.exameIdPaciente + ","
					+ CamposBanco.exameEstado + ","
					+ CamposBanco.exameCaminhoArquivos + ","
					+ CamposBanco.exameDataSolicitacao + ","
					+ CamposBanco.status;

			if (emailRequisitante != null)
				sql += "," + CamposBanco.exameEmailRequisitante;
			if (idProfissional != 0)
				sql += "," + CamposBanco.exameIdProfissional;
			if (medicoDesignadoCrm != 0)
				sql += "," + CamposBanco.exameMedicoDesignadoCrm;
			if (parecerJustificativa != null)
				sql += "," + CamposBanco.exameParecerJustificativa;
			if (dataConclusao != null)
				sql += "," + CamposBanco.exameDataConclusao;
			if (pesoPaciente != 0)
				sql += "," + CamposBanco.examePesoPaciente;
			if (alturaPaciente != 0)
				sql += "," + CamposBanco.exameAlturaPaciente;
			if (cid != 0)
				sql += "," + CamposBanco.exameCid;
			if (observacoes != null)
				sql += "," + CamposBanco.exameObservacoes;

			sql += ") VALUES (" + idPaciente + ","
					+ Exame.Estados.ABERTO.getId() + ",'" + caminhoArquivos
					+ "','" + dataSolicitacao + "','" + CamposBanco.statusAtivo
					+ "'";

			if (emailRequisitante != null)
				sql += ",'" + emailRequisitante + "'";
			if (idProfissional != 0)
				sql += "," + idProfissional;
			if (medicoDesignadoCrm != 0)
				sql += "," + medicoDesignadoCrm;
			if (parecerJustificativa != null)
				sql += ",'" + parecerJustificativa + "'";
			if (dataConclusao != null)
				sql += ",'" + dataConclusao + "'";
			if (pesoPaciente != 0)
				sql += "," + pesoPaciente;
			if (alturaPaciente != 0)
				sql += "," + alturaPaciente;
			if (cid != 0)
				sql += "," + cid;
			if (observacoes != null)
				sql += ",'" + observacoes + "'";

			sql += ");";

			idRetorno = stm.executeUpdate(sql);

			if (idRetorno > 0) {
				String query = CamposBanco.selectIdentity
						+ CamposBanco.exameTabela;
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
	 * Atualiza as informações do {@link Exame}.
	 * 
	 * @param {@link Exame}
	 * @return boolean
	 */
	@Override
	public boolean atualizar(Exame exame) {
		int affected = 0;
		int idPaciente = exame.getPaciente().getChave();
		int idProfissional = 0;
		if (exame.getProfissional() != null)
			idProfissional = exame.getProfissional().getChave();
		String emailRequisitante = exame.getEmailRequisitante();
		int estado = exame.getEstado();
		int medicoDesignadoCrm = exame.getMedicoDesignadoCrm();
		String parecerJustificativa = exame.getParecerJustificativa();
		String caminhoArquivos = exame.getCaminhoArquivos();
		String dataSolicitacao = exame.getDataSolicitacao();
		String dataConclusao = exame.getDataConclusao();
		float pesoPaciente = exame.getPesoPaciente();
		float alturaPaciente = exame.getAlturaPaciente();
		int cid = exame.getCid();
		String observacoes = exame.getObservacoes();

		String sql = null;

		try {
			stm = con.getStatement();
			sql = "UPDATE " + CamposBanco.exameTabela + " SET "
					+ CamposBanco.exameIdPaciente + " = " + idPaciente + ","
					+ CamposBanco.exameEstado + " = '" + estado + "',"
					+ CamposBanco.exameCaminhoArquivos + " = '"
					+ caminhoArquivos + "'," + CamposBanco.exameDataSolicitacao
					+ " = '" + dataSolicitacao + "'";

			if (emailRequisitante != null)
				sql += "," + CamposBanco.exameEmailRequisitante + " = '"
						+ emailRequisitante + "'";
			if (idProfissional != 0)
				sql += "," + CamposBanco.exameIdProfissional + " = "
						+ idProfissional;
			if (medicoDesignadoCrm != 0)
				sql += "," + CamposBanco.exameMedicoDesignadoCrm + " = "
						+ medicoDesignadoCrm;
			if (parecerJustificativa != null)
				sql += "," + CamposBanco.exameParecerJustificativa + " = '"
						+ parecerJustificativa + "'";
			if (dataConclusao != null)
				sql += "," + CamposBanco.exameDataConclusao + " = '"
						+ dataConclusao + "'";
			if (pesoPaciente != 0)
				sql += "," + CamposBanco.examePesoPaciente + " = "
						+ pesoPaciente;
			if (alturaPaciente != 0)
				sql += "," + CamposBanco.exameAlturaPaciente + " = "
						+ alturaPaciente;
			if (cid != 0)
				sql += "," + CamposBanco.exameCid + " = " + cid;
			if (observacoes != null)
				sql += "," + CamposBanco.exameObservacoes + " = " + observacoes;

			sql += " WHERE " + CamposBanco.id + " = " + exame.getChave();
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
	 * Torna o {@link Exame} Inativo no sistema.
	 * 
	 * @param {@link Exame}
	 * @return boolean
	 */
	@Override
	public boolean excluir(Exame exame) {
		int affected = 0;
		int idExame = exame.getChave();

		String sql = null;

		try {
			stm = con.getStatement();

			sql = "UPDATE " + CamposBanco.exameTabela + " SET "
					+ CamposBanco.status + " = '" + CamposBanco.statusInativo
					+ "' WHERE " + CamposBanco.id + " = " + idExame;

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
	 * Consulta o estado de um {@link Exame} de acordo com o Id que foi passado.
	 * 
	 * @return int
	 */
	@Override
	public int consultarEstado(int id) {
		String sql = null;
		ResultSet rs = null;
		int estado = -1;

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.exameEstado;
			sql += " FROM " + CamposBanco.exameTabela;
			sql += " WHERE " + CamposBanco.id + " = " + id + " AND "
					+ CamposBanco.status + " = '" + CamposBanco.statusAtivo
					+ "'";

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				estado = rs.getInt(CamposBanco.exameEstado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		} finally {
			fecharConexao();
		}

		return estado;
	}

	/**
	 * Laudar a soliciticação.
	 * 
	 * @param {@link Exame}
	 * @return boolean true caso tenha laudado, false caso não tenha laudado.
	 */
	public boolean laudar(Exame exame) {
		int affected = 0;

		String sql = null;

		int medicoDesignadoCrm = exame.getMedicoDesignadoCrm();
		String crm = (medicoDesignadoCrm > 0) ? Integer
				.toString(medicoDesignadoCrm) : "NULL";

		try {
			stm = con.getStatement();

			sql = "UPDATE " + CamposBanco.exameTabela + " SET "
					+ CamposBanco.exameEstado + " = " + exame.getEstado()
					+ ", " + CamposBanco.exameParecerJustificativa + " = '"
					+ exame.getParecerJustificativa().trim() + "', "
					+ CamposBanco.exameDataConclusao + " = GETDATE(), "
					+ CamposBanco.exameIdProfissional + " = "
					+ exame.getProfissional().getChave() + ", "
					+ CamposBanco.exameMedicoDesignadoCrm + " = " + crm;
			sql += " WHERE " + CamposBanco.id + " = " + exame.getChave();

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
	 * Definir a solicitação como Laudado.
	 * 
	 * @param id
	 * @return boolean true caso tenha laudado, false caso não tenha laudado.
	 */
	public boolean definirLaudando(int id) {
		int affected = 0;

		String sql = null;

		try {
			stm = con.getStatement();

			sql = "UPDATE " + CamposBanco.exameTabela + " SET "
					+ CamposBanco.exameEstado + " = "
					+ Exame.Estados.LAUDANDO.getId() + " WHERE "
					+ CamposBanco.id + " = " + id;

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
	 * Cancelar laudo (deixar solicitação no estado anterior).
	 * 
	 * @param {@link Exame}
	 * @return boolean true caso tenha cancelado, false caso não tenha
	 *         cancelado.
	 */
	public boolean cancelar(Exame exame) {
		int affected = 0;

		String sql = null;

		try {
			stm = con.getStatement();

			sql = "UPDATE " + CamposBanco.exameTabela + " SET "
					+ CamposBanco.exameEstado + " = " + exame.getEstado()
					+ " WHERE " + CamposBanco.id + " = " + exame.getChave();

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
	 * Consulta um {@link Exame} por um id.
	 * 
	 * @return {@link Exame}
	 */
	@Override
	public Exame consultarPorId(int id) {
		Exame exame = new Exame();
		exame.getPaciente().setEndereco(new Endereco());
		exame.setChave(id);

		String sql = null;
		ResultSet rs = null;

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaNome + ","
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaCpf + ","
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaDataNascimento + ","
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaIdEndereco + ","
					+ CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.pacienteIdTipoSanguineo + ","
					+ CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.pacienteNomeMae + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameAlturaPaciente + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.examePesoPaciente + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameCaminhoArquivos + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameObservacoes + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameMedicoDesignadoCrm + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameParecerJustificativa + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameEstado;
			sql += " FROM " + CamposBanco.pacienteTabela;
			sql += " INNER JOIN " + CamposBanco.exameTabela + " ON "
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameIdPaciente + " = "
					+ CamposBanco.pacienteTabelaComSeparador + CamposBanco.id;
			sql += " INNER JOIN " + CamposBanco.pessoaTabela + " ON "
					+ CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.pacienteIdPessoa + " = "
					+ CamposBanco.pessoaTabelaComSeparador + CamposBanco.id;
			sql += " WHERE " + CamposBanco.exameTabelaComSeparador
					+ CamposBanco.id + " = " + id;

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				exame.getPaciente().setEndereco(new Endereco());
				String nome = rs.getString(CamposBanco.pessoaNome);
				String cpf = rs.getString(CamposBanco.pessoaCpf);
				Date dataNascimentoDt = rs
						.getTimestamp(CamposBanco.pessoaDataNascimento);
				String dataNascimentoStr = Datas.formatarData(dataNascimentoDt,
						Datas.dataBrasil);
				int idEndereco = rs.getInt(CamposBanco.pessoaIdEndereco);
				int idTipoSanguineo = rs
						.getInt(CamposBanco.pacienteIdTipoSanguineo);
				String nomeMae = rs.getString(CamposBanco.pacienteNomeMae);
				float alturaPaciente = rs
						.getFloat(CamposBanco.exameAlturaPaciente);
				float pesoPaciente = rs.getFloat(CamposBanco.examePesoPaciente);
				String caminhoArquivos = rs
						.getString(CamposBanco.exameCaminhoArquivos);
				String observacoes = rs.getString(CamposBanco.exameObservacoes);
				int medicoDesignadoCrm = rs
						.getInt(CamposBanco.exameMedicoDesignadoCrm);
				String parecerJustificativa = rs
						.getString(CamposBanco.exameParecerJustificativa);
				int estado = rs.getInt(CamposBanco.exameEstado);

				exame.getPaciente().setNome(nome);
				exame.getPaciente().setCpf(cpf);
				exame.getPaciente().setDataNascimento(dataNascimentoStr);
				exame.getPaciente().getEndereco().setChave(idEndereco);
				exame.getPaciente().getTipoSanguineo()
						.setChave(idTipoSanguineo);
				exame.getPaciente().setNomeMae(nomeMae);
				exame.setAlturaPaciente(alturaPaciente);
				exame.setPesoPaciente(pesoPaciente);
				exame.setCaminhoArquivos(caminhoArquivos);
				exame.setObservacoes(observacoes);
				exame.setMedicoDesignadoCrm(medicoDesignadoCrm);
				exame.setParecerJustificativa(parecerJustificativa);
				exame.setEstado(estado);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return exame;
	}

	/**
	 * Retorna uma List<Exame> com todos os exames abertos.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * @param int crm
	 * 
	 * @return List<Exame>
	 */
	@Override
	public List<Exame> consultarTodosAbertos(String campo, String valor, int crm) {
		List<Exame> exames = new ArrayList<Exame>();

		String sql = null;
		ResultSet rs = null;

		String crmFiltro = (crm > 0) ? "= " + Integer.toString(crm)
				: "IS NOT NULL";

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.exameTabelaComSeparador
					+ CamposBanco.id + ","
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaNome + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameDataSolicitacao + ","
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaIdEndereco + "," + "DATEDIFF(hh,"
					+ CamposBanco.exameDataSolicitacao + ",GETDATE()) AS "
					+ CamposBanco.exameHorasAlias + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameEstado + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameMedicoDesignadoCrm;
			sql += " FROM " + CamposBanco.pacienteTabela;
			sql += " INNER JOIN " + CamposBanco.exameTabela + " ON "
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameIdPaciente + " = "
					+ CamposBanco.pacienteTabelaComSeparador + CamposBanco.id;
			sql += " INNER JOIN " + CamposBanco.pessoaTabela + " ON "
					+ CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.pacienteIdPessoa + " = "
					+ CamposBanco.pessoaTabelaComSeparador + CamposBanco.id;

			sql += " WHERE (" + CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameEstado + " = "
					+ Exame.Estados.ABERTO.getId() + " OR ("
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameEstado + " = "
					+ Exame.Estados.NAO_LAUDADO.getId() + " AND "
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameMedicoDesignadoCrm + " " + crmFiltro
					+ ")) AND " + CamposBanco.exameTabelaComSeparador
					+ CamposBanco.status + " = '" + CamposBanco.statusAtivo
					+ "'";

			if (campo != null && valor != null) {
				if (campo.equalsIgnoreCase(CamposBanco.pessoaNome))
					campo = CamposBanco.pessoaTabelaComSeparador
							+ CamposBanco.pessoaNome;
				else if (campo.equalsIgnoreCase(CamposBanco.pessoaCpf))
					campo = CamposBanco.pessoaTabelaComSeparador
							+ CamposBanco.pessoaCpf;

				sql += " AND " + campo + " LIKE '%" + valor + "%'";
			}

			sql += " ORDER BY " + CamposBanco.exameDataSolicitacao + " ASC";

			rs = stm.executeQuery(sql);

			Exame exame = null;

			while (rs.next()) {
				exame = new Exame();
				exame.getPaciente().setEndereco(new Endereco());
				int id = rs.getInt(CamposBanco.id);
				String nome = rs.getString(CamposBanco.pessoaNome);
				Date dataSolicitacaoDt = rs
						.getTimestamp(CamposBanco.exameDataSolicitacao);
				String dataSolicitacaoStr = Datas.formatarData(
						dataSolicitacaoDt, Datas.dataHoraBrasil);
				int idEndereco = rs.getInt(CamposBanco.pessoaIdEndereco);
				int horasAberto = rs.getInt(CamposBanco.exameHorasAlias);
				int estado = rs.getInt(CamposBanco.exameEstado);
				int medicoDesignadoCrm = rs
						.getInt(CamposBanco.exameMedicoDesignadoCrm);

				exame.setChave(id);
				exame.getPaciente().setNome(nome);
				exame.setDataSolicitacao(dataSolicitacaoStr);
				exame.getPaciente().getEndereco().setChave(idEndereco);
				exame.setHorasAberto(horasAberto);
				exame.setEstado(estado);
				exame.setMedicoDesignadoCrm(medicoDesignadoCrm);

				exames.add(exame);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return exames;
	}

	/**
	 * Retorna uma List<{@link Exame}> com todos os exames laudados ou não
	 * laudados, contanto que os não laudados não tenham encaminhamento, ou
	 * seja, o crm do médico designado seja null.
	 * 
	 * @param String
	 *            campo
	 * @param String
	 *            valor
	 * 
	 * @return List<Exame>
	 */
	@Override
	public List<Exame> consultarTodosConcluidos(String campo, String valor) {
		List<Exame> exames = new ArrayList<Exame>();

		String sql = null;
		ResultSet rs = null;

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.exameTabelaComSeparador
					+ CamposBanco.id + ","
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaNome + ","
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaDataNascimento + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameDataConclusao + ","
					+ CamposBanco.pessoaTabelaComSeparador
					+ CamposBanco.pessoaIdEndereco + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameEstado + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameCaminhoArquivos + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameParecerJustificativa + ","
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameIdProfissional;
			sql += " FROM " + CamposBanco.pacienteTabela;
			sql += " INNER JOIN " + CamposBanco.exameTabela + " ON "
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameIdPaciente + " = "
					+ CamposBanco.pacienteTabelaComSeparador + CamposBanco.id;
			sql += " INNER JOIN " + CamposBanco.pessoaTabela + " ON "
					+ CamposBanco.pacienteTabelaComSeparador
					+ CamposBanco.pacienteIdPessoa + " = "
					+ CamposBanco.pessoaTabelaComSeparador + CamposBanco.id;
			sql += " WHERE ((" + CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameEstado + " = "
					+ Exame.Estados.LAUDADO.getId() + " OR "
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameEstado + " = "
					+ Exame.Estados.NAO_LAUDADO.getId() + ") AND "
					+ CamposBanco.exameTabelaComSeparador
					+ CamposBanco.exameMedicoDesignadoCrm + " IS NULL) AND "
					+ CamposBanco.exameTabelaComSeparador + CamposBanco.status
					+ " = '" + CamposBanco.statusAtivo + "'";

			if (campo != null && valor != null) {
				if (campo.equalsIgnoreCase(CamposBanco.pessoaNome))
					campo = CamposBanco.pessoaTabelaComSeparador
							+ CamposBanco.pessoaNome;
				else if (campo.equalsIgnoreCase(CamposBanco.pessoaCpf))
					campo = CamposBanco.pessoaTabelaComSeparador
							+ CamposBanco.pessoaCpf;

				sql += " AND " + campo + " LIKE '%" + valor + "%'";
				sql += " ORDER BY " + campo;
			} else
				sql += " ORDER BY " + CamposBanco.exameDataConclusao + " ASC";

			rs = stm.executeQuery(sql);

			Exame exame = null;

			while (rs.next()) {
				exame = new Exame();
				exame.getPaciente().setEndereco(new Endereco());
				exame.setProfissional(new Profissional());
				int id = rs.getInt(CamposBanco.id);
				String nome = rs.getString(CamposBanco.pessoaNome);
				int idEndereco = rs.getInt(CamposBanco.pessoaIdEndereco);
				Date dataConclusaoDt = rs
						.getTimestamp(CamposBanco.exameDataConclusao);
				String dataConclusaoStr = Datas.formatarData(dataConclusaoDt,
						Datas.dataHoraBrasil);
				int estado = rs.getInt(CamposBanco.exameEstado);
				String caminhoArquivos = rs
						.getString(CamposBanco.exameCaminhoArquivos);
				Date dataNascimentoDt = rs
						.getTimestamp(CamposBanco.pessoaDataNascimento);
				String dataNascimentoStr = Datas.formatarData(dataNascimentoDt,
						Datas.dataEua);
				String parecerJustificativa = rs
						.getString(CamposBanco.exameParecerJustificativa);
				int idProfissional = rs.getInt(CamposBanco.exameIdProfissional);

				exame.setChave(id);
				exame.getPaciente().setNome(nome);
				exame.getPaciente().getEndereco().setChave(idEndereco);
				exame.setDataConclusao(dataConclusaoStr);
				exame.setEstado(estado);
				exame.setCaminhoArquivos(caminhoArquivos);
				exame.getPaciente().setDataNascimento(dataNascimentoStr);
				exame.setParecerJustificativa(parecerJustificativa);
				exame.getProfissional().setChave(idProfissional);

				exames.add(exame);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return exames;
	}
}
