package nutes.telecardio.modelo.configuracao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocioDao;

/**
 * {@link Configuracao} DAO.
 * 
 * @author WaldsonMoura
 * 
 */
public class ConfiguracaoDao extends ObjetoNegocioDao implements
		IConfiguracaoDao {

	/**
	 * Inclui uma {@link Configuracao}.
	 * 
	 * @param {@link Configuracao}
	 * @return int id do registro incluído.
	 */
	@Override
	public int incluir(Configuracao configuracao) {
		int idRetorno = 0;
		String nome = configuracao.getNome().trim();
		String pastaExames = configuracao.getPastaExames().trim();
		int limiteHabilitacoesMedico = configuracao
				.getLimiteHabilitacoesMedico();
		int limiteHabilitacoesEnfermeiro = configuracao
				.getLimiteHabilitacoesEnfermeiro();
		int limiteExameLaudando = configuracao.getLimiteExameLaudando();
		int limiteExameAberto = configuracao.getLimiteExameAberto();
		String emailChecagemEmail = configuracao.getEmailChecagemEmail().trim();
		String senhaChecagemEmail = configuracao.getSenhaChecagemEmail();
		int portaChecagemEmail = configuracao.getPortaChecagemEmail();
		String hostChecagemEmail = configuracao.getHostChecagemEmail().trim();
		int intervaloChecagemEmail = configuracao.getIntervaloChecagemEmail();

		String sql = null;

		try {
			stm = con.getStatement();

			sql = "INSERT INTO " + CamposBanco.configTabela + " ("
					+ CamposBanco.configNome + ","
					+ CamposBanco.configPastaExames + ","
					+ CamposBanco.configLimiteHabilitacoesMedico + ","
					+ CamposBanco.configLimiteHabilitacoesEnfermeiro + ","
					+ CamposBanco.configLimiteExameLaudando + ","
					+ CamposBanco.configLimiteExameAberto + ","
					+ CamposBanco.configEmailChecagemEmail + ","
					+ CamposBanco.configSenhaChecagemEmail + ","
					+ CamposBanco.configPortaChecagemEmail + ","
					+ CamposBanco.configHostChecagemEmail + ","
					+ CamposBanco.configIntervaloChecagemEmail + ","
					+ CamposBanco.status;

			sql += ") VALUES (";

			sql += "'" + nome + "','" + pastaExames + "',"
					+ limiteHabilitacoesMedico + ","
					+ limiteHabilitacoesEnfermeiro + "," + limiteExameLaudando
					+ "," + limiteExameAberto + ",'" + emailChecagemEmail
					+ "','" + senhaChecagemEmail + "'," + portaChecagemEmail
					+ ",'" + hostChecagemEmail + "'," + intervaloChecagemEmail
					+ ",'" + CamposBanco.statusInativo + "'";

			sql += ");";
			idRetorno = stm.executeUpdate(sql);

			if (idRetorno > 0) {
				String query = CamposBanco.selectIdentity
						+ CamposBanco.configTabela;
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
	 * Atualiza as informações da {@link Configuracao}.
	 * 
	 * @param {@link Configuracao}
	 * @return boolean
	 */
	@Override
	public boolean atualizar(Configuracao configuracao) {
		int affected = 0;
		String nome = configuracao.getNome().trim();
		String pastaExames = configuracao.getPastaExames().trim();
		int limiteHabilitacoesMedico = configuracao
				.getLimiteHabilitacoesMedico();
		int limiteHabilitacoesEnfermeiro = configuracao
				.getLimiteHabilitacoesEnfermeiro();
		int limiteExameLaudando = configuracao.getLimiteExameLaudando();
		int limiteExameAberto = configuracao.getLimiteExameAberto();
		String emailChecagemEmail = configuracao.getEmailChecagemEmail().trim();
		String senhaChecagemEmail = configuracao.getSenhaChecagemEmail();
		int portaChecagemEmail = configuracao.getPortaChecagemEmail();
		String hostChecagemEmail = configuracao.getHostChecagemEmail().trim();
		int intervaloChecagemEmail = configuracao.getIntervaloChecagemEmail();

		String sql = null;

		try {
			stm = con.getStatement();

			sql = "UPDATE " + CamposBanco.configTabela + " SET "
					+ CamposBanco.configNome + " = '" + nome + "',"
					+ CamposBanco.configPastaExames + " = '" + pastaExames
					+ "'," + CamposBanco.configLimiteHabilitacoesMedico + " = "
					+ limiteHabilitacoesMedico + ","
					+ CamposBanco.configLimiteHabilitacoesEnfermeiro + " = "
					+ limiteHabilitacoesEnfermeiro + ","
					+ CamposBanco.configLimiteExameLaudando + " = "
					+ limiteExameLaudando + ","
					+ CamposBanco.configLimiteExameAberto + " = "
					+ limiteExameAberto + ","
					+ CamposBanco.configEmailChecagemEmail + " = '"
					+ emailChecagemEmail + "',"
					+ CamposBanco.configSenhaChecagemEmail + " = '"
					+ senhaChecagemEmail + "',"
					+ CamposBanco.configPortaChecagemEmail + " = "
					+ portaChecagemEmail + ","
					+ CamposBanco.configHostChecagemEmail + " = '"
					+ hostChecagemEmail + "',"
					+ CamposBanco.configIntervaloChecagemEmail + " = "
					+ intervaloChecagemEmail;

			sql += " WHERE " + CamposBanco.id + " = " + configuracao.getChave();
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
	 * Torna a {@link Configuracao} Inativa no sistema.
	 * 
	 * @param {@link Configuracao}
	 * @return boolean
	 */
	@Override
	public boolean ativar(Configuracao configuracao) {
		int affected = 0;
		int idConfiguracao = configuracao.getChave();

		String sqlAtivar = null, sqlDesativarTodos = null;

		try {
			stm = con.getStatement();

			sqlDesativarTodos = "UPDATE " + CamposBanco.configTabela + " SET "
					+ CamposBanco.status + " = '" + CamposBanco.statusInativo
					+ "'";

			stm.execute(sqlDesativarTodos);

			sqlAtivar = "UPDATE " + CamposBanco.configTabela + " SET "
					+ CamposBanco.status + " = '" + CamposBanco.statusAtivo
					+ "' WHERE " + CamposBanco.id + " = " + idConfiguracao;

			affected = stm.executeUpdate(sqlAtivar);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fecharConexao();
		}

		return (affected > 0) ? true : false;
	}

	/**
	 * Retorna uma List<{@link Configuracao}> com todas as configurações.
	 * 
	 * @return List<{@link Configuracao}>
	 */
	@Override
	public List<Configuracao> consultarTodas() {
		List<Configuracao> configuracoes = new ArrayList<Configuracao>();

		String sqlList = null;
		ResultSet rs = null;

		try {
			stm = con.getStatement();

			sqlList = "SELECT " + CamposBanco.id + "," + CamposBanco.configNome
					+ "," + CamposBanco.configPastaExames + ","
					+ CamposBanco.configLimiteHabilitacoesMedico + ","
					+ CamposBanco.configLimiteHabilitacoesEnfermeiro + ","
					+ CamposBanco.configLimiteExameLaudando + ","
					+ CamposBanco.configLimiteExameAberto + ","
					+ CamposBanco.configEmailChecagemEmail + ","
					+ CamposBanco.configSenhaChecagemEmail + ","
					+ CamposBanco.configPortaChecagemEmail + ","
					+ CamposBanco.configHostChecagemEmail + ","
					+ CamposBanco.configIntervaloChecagemEmail + ","
					+ CamposBanco.status;

			sqlList += " FROM " + CamposBanco.configTabela;

			rs = stm.executeQuery(sqlList);

			Configuracao configuracao;

			while (rs.next()) {
				configuracao = new Configuracao();

				int id = rs.getInt(CamposBanco.id);
				String nome = rs.getString(CamposBanco.configNome);
				String pastaExames = rs
						.getString(CamposBanco.configPastaExames);
				int limiteHabilitacoesMedico = rs
						.getInt(CamposBanco.configLimiteHabilitacoesMedico);
				int limiteHabilitacoesEnfermeiro = rs
						.getInt(CamposBanco.configLimiteHabilitacoesEnfermeiro);
				int limiteExameLaudando = rs
						.getInt(CamposBanco.configLimiteExameLaudando);
				int limiteExameAberto = rs
						.getInt(CamposBanco.configLimiteExameAberto);
				String emailChecagemEmail = rs
						.getString(CamposBanco.configEmailChecagemEmail);
				String senhaChecagemEmail = rs
						.getString(CamposBanco.configSenhaChecagemEmail);
				int portaChecagemEmail = rs
						.getInt(CamposBanco.configPortaChecagemEmail);
				String hostChecagemEmail = rs
						.getString(CamposBanco.configHostChecagemEmail);
				int intervaloChecagemEmail = rs
						.getInt(CamposBanco.configIntervaloChecagemEmail);
				String status = rs.getString(CamposBanco.status);

				configuracao.setChave(id);
				configuracao.setNome(nome);
				configuracao.setPastaExames(pastaExames);
				configuracao
						.setLimiteHabilitacoesMedico(limiteHabilitacoesMedico);
				configuracao
						.setLimiteHabilitacoesEnfermeiro(limiteHabilitacoesEnfermeiro);
				configuracao.setLimiteExameLaudando(limiteExameLaudando);
				configuracao.setLimiteExameAberto(limiteExameAberto);
				configuracao.setEmailChecagemEmail(emailChecagemEmail);
				configuracao.setSenhaChecagemEmail(senhaChecagemEmail);
				configuracao.setPortaChecagemEmail(portaChecagemEmail);
				configuracao.setHostChecagemEmail(hostChecagemEmail);
				configuracao.setIntervaloChecagemEmail(intervaloChecagemEmail);
				configuracao.setStatus(status.charAt(0));

				configuracoes.add(configuracao);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return configuracoes;
	}

	/**
	 * Retorna a {@link Configuracao} ativa.
	 * 
	 * @return {@link Configuracao}
	 */
	@Override
	public Configuracao consultarAtiva() {
		String sql = null;
		ResultSet rs = null;

		Configuracao configuracao = new Configuracao();

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.id + "," + CamposBanco.configNome
					+ "," + CamposBanco.configPastaExames + ","
					+ CamposBanco.configLimiteHabilitacoesMedico + ","
					+ CamposBanco.configLimiteHabilitacoesEnfermeiro + ","
					+ CamposBanco.configLimiteExameLaudando + ","
					+ CamposBanco.configLimiteExameAberto + ","
					+ CamposBanco.configEmailChecagemEmail + ","
					+ CamposBanco.configSenhaChecagemEmail + ","
					+ CamposBanco.configPortaChecagemEmail + ","
					+ CamposBanco.configHostChecagemEmail + ","
					+ CamposBanco.configIntervaloChecagemEmail;

			sql += " FROM " + CamposBanco.configTabela;

			sql += " WHERE " + CamposBanco.status + " = '"
					+ CamposBanco.statusAtivo + "'";

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				int id = rs.getInt(CamposBanco.id);
				String nome = rs.getString(CamposBanco.configNome);
				String pastaExames = rs
						.getString(CamposBanco.configPastaExames);
				int limiteHabilitacoesMedico = rs
						.getInt(CamposBanco.configLimiteHabilitacoesMedico);
				int limiteHabilitacoesEnfermeiro = rs
						.getInt(CamposBanco.configLimiteHabilitacoesEnfermeiro);
				int limiteExameLaudando = rs
						.getInt(CamposBanco.configLimiteExameLaudando);
				int limiteExameAberto = rs
						.getInt(CamposBanco.configLimiteExameAberto);
				String emailChecagemEmail = rs
						.getString(CamposBanco.configEmailChecagemEmail);
				String senhaChecagemEmail = rs
						.getString(CamposBanco.configSenhaChecagemEmail);
				int portaChecagemEmail = rs
						.getInt(CamposBanco.configPortaChecagemEmail);
				String hostChecagemEmail = rs
						.getString(CamposBanco.configHostChecagemEmail);
				int intervaloChecagemEmail = rs
						.getInt(CamposBanco.configIntervaloChecagemEmail);

				configuracao.setChave(id);
				configuracao.setNome(nome);
				configuracao.setPastaExames(pastaExames);
				configuracao
						.setLimiteHabilitacoesMedico(limiteHabilitacoesMedico);
				configuracao
						.setLimiteHabilitacoesEnfermeiro(limiteHabilitacoesEnfermeiro);
				configuracao.setLimiteExameLaudando(limiteExameLaudando);
				configuracao.setLimiteExameAberto(limiteExameAberto);
				configuracao.setEmailChecagemEmail(emailChecagemEmail);
				configuracao.setSenhaChecagemEmail(senhaChecagemEmail);
				configuracao.setPortaChecagemEmail(portaChecagemEmail);
				configuracao.setHostChecagemEmail(hostChecagemEmail);
				configuracao.setIntervaloChecagemEmail(intervaloChecagemEmail);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return configuracao;
	}

	/**
	 * Retorna a {@link Configuracao} de acordo com o Id que foi passado.
	 * 
	 * @param int
	 * @return {@link Configuracao}
	 */
	public Configuracao consultarPorId(int id) {
		String sql = null;
		ResultSet rs = null;

		Configuracao configuracao = new Configuracao();

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.configNome + ","
					+ CamposBanco.configPastaExames + ","
					+ CamposBanco.configLimiteHabilitacoesMedico + ","
					+ CamposBanco.configLimiteHabilitacoesEnfermeiro + ","
					+ CamposBanco.configLimiteExameLaudando + ","
					+ CamposBanco.configLimiteExameAberto + ","
					+ CamposBanco.configEmailChecagemEmail + ","
					+ CamposBanco.configSenhaChecagemEmail + ","
					+ CamposBanco.configPortaChecagemEmail + ","
					+ CamposBanco.configHostChecagemEmail + ","
					+ CamposBanco.configIntervaloChecagemEmail;

			sql += " FROM " + CamposBanco.configTabela;

			sql += " WHERE " + CamposBanco.id + " = " + id;

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				String nome = rs.getString(CamposBanco.configNome);
				String pastaExames = rs
						.getString(CamposBanco.configPastaExames);
				int limiteHabilitacoesMedico = rs
						.getInt(CamposBanco.configLimiteHabilitacoesMedico);
				int limiteHabilitacoesEnfermeiro = rs
						.getInt(CamposBanco.configLimiteHabilitacoesEnfermeiro);
				int limiteExameLaudando = rs
						.getInt(CamposBanco.configLimiteExameLaudando);
				int limiteExameAberto = rs
						.getInt(CamposBanco.configLimiteExameAberto);
				String emailChecagemEmail = rs
						.getString(CamposBanco.configEmailChecagemEmail);
				String senhaChecagemEmail = rs
						.getString(CamposBanco.configSenhaChecagemEmail);
				int portaChecagemEmail = rs
						.getInt(CamposBanco.configPortaChecagemEmail);
				String hostChecagemEmail = rs
						.getString(CamposBanco.configHostChecagemEmail);
				int intervaloChecagemEmail = rs
						.getInt(CamposBanco.configIntervaloChecagemEmail);

				configuracao.setNome(nome);
				configuracao.setPastaExames(pastaExames);
				configuracao
						.setLimiteHabilitacoesMedico(limiteHabilitacoesMedico);
				configuracao
						.setLimiteHabilitacoesEnfermeiro(limiteHabilitacoesEnfermeiro);
				configuracao.setLimiteExameLaudando(limiteExameLaudando);
				configuracao.setLimiteExameAberto(limiteExameAberto);
				configuracao.setEmailChecagemEmail(emailChecagemEmail);
				configuracao.setSenhaChecagemEmail(senhaChecagemEmail);
				configuracao.setPortaChecagemEmail(portaChecagemEmail);
				configuracao.setHostChecagemEmail(hostChecagemEmail);
				configuracao.setIntervaloChecagemEmail(intervaloChecagemEmail);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return configuracao;
	}

	@Override
	public boolean excluir(Configuracao objetoNegocio) {
		return false;
	}

}
