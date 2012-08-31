package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;
import nutes.telecardio.modelo.configuracao.ConectorSqlServer;

/**
 * Dao de profissional
 * 
 * @author hvb
 * 
 */
public class ProfissionalDao extends ObjetoNegocioDao implements
		IProfissionalDao {

	@Override
	public boolean atualizar(Profissional objetoNegocio) {
		int affected = 0;
		try {
			stm = con.getStatement();

			String query = "UPDATE " + CamposBanco.profissionalTabela + " SET "
					+ CamposBanco.profissionalIdProfissionaoTipo + "="
					+ objetoNegocio.getProfissionalTipo().getChave()
					+ " WHERE " + CamposBanco.id + " = "
					+ objetoNegocio.getChave();

			affected = stm.executeUpdate(query);
			if (affected > 0) {

				for (ProfissionalOrgao profissionalOrgao : objetoNegocio
						.getProfissionaisOrgaos()) {
					query = "UPDATE " + CamposBanco.profissionalOrgaoTabela
							+ " SET "
							+ CamposBanco.profissionalOrgaoHabilitacao + " = '"
							+ profissionalOrgao.getHabilitacao() + "',"
							+ CamposBanco.profissionalOrgaoIdOrgaoClasse + "="
							+ profissionalOrgao.getOrgaoClasse().getChave()
							+ ", " + CamposBanco.profissionalOrgaoIdEstado
							+ "=" + profissionalOrgao.getUf().getChave()
							+ " WHERE " + CamposBanco.id + " ="
							+ profissionalOrgao.getChave();
					affected += stm.executeUpdate(query);
				}
			}

		} catch (SQLException e) {
			affected = 0;
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		return affected > 0 ? true : false;
	}

	@Override
	public boolean excluir(Profissional objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int incluir(Profissional objetoNegocio) {
		int affected = 0;
		try {
			stm = con.getStatement();

			String query = "INSERT INTO profissionais (id_usuario, id_profissional_tipo)"
					+ "values("
					+ objetoNegocio.getUsuario().getChave()
					+ ", "
					+ objetoNegocio.getProfissionalTipo().getChave() + ");";

			affected = stm.executeUpdate(query);

			if (affected > 0) {
				int i;
				affected = 0;
				for (i = 0; i < objetoNegocio.getProfissionaisOrgaos().size(); i++) {
					query = "INSERT INTO "
							+ CamposBanco.profissionalOrgaoTabela
							+ " ("
							+ CamposBanco.profissionalOrgaoIdProfissional
							+ ","
							+ CamposBanco.profissionalOrgaoIdOrgaoClasse
							+ " ,"
							+ CamposBanco.profissionalOrgaoHabilitacao
							+ ","
							+ CamposBanco.profissionalOrgaoIdEstado
							+ ")"
							+ "values(("
							+ CamposBanco.selectIdentity
							+ " "
							+ CamposBanco.profissionalTabela
							+ "), "
							+ objetoNegocio.getProfissionaisOrgaos().get(i)
									.getOrgaoClasse().getChave()
							+ ", '"
							+ objetoNegocio.getProfissionaisOrgaos().get(i)
									.getHabilitacao()
							+ "', "
							+ objetoNegocio.getProfissionaisOrgaos().get(i)
									.getUf().getChave() + ")";
					affected += stm.executeUpdate(query);
				}
				if (affected == i) {
					return affected;
				}

			} else {
				return affected;
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
	public Profissional ConsultarProfissionalPorUsuario(Usuario usuario) {
		Profissional profissional = null;
		try {
			con = new ConectorSqlServer();
			stm = con.getStatement();
			if (usuario != null) {
				String queryProfissional = "select p." + CamposBanco.id
						+ " as profissionais_id,pt." + CamposBanco.id
						+ " as profissionais_tipo_id, " + "pt."
						+ CamposBanco.profissionalTipoNome
						+ " as profissionais_tipo_nome, oc."
						+ CamposBanco.orgaoClasseNome
						+ " as orgao_classe_nome, " + "oc." + CamposBanco.id
						+ " as orgao_classe_id,  oc."
						+ CamposBanco.orgaoClasseSigla
						+ " as orgao_classe_sigla " + "from "
						+ CamposBanco.profissionalTabela + " p inner join "
						+ CamposBanco.profissionalTipoTabela + " pt " + "on p."
						+ CamposBanco.profissionalIdProfissionaoTipo + " = pt."
						+ CamposBanco.id + " inner join orgaos_classes oc "
						+ "on pt." + CamposBanco.profissionalTipoIdOrgaoClasse
						+ " = oc." + CamposBanco.id + " " + "where p."
						+ CamposBanco.profissionalIdUsuario + " = "
						+ usuario.getChave();

				ResultSet rsProfissional = stm.executeQuery(queryProfissional);

				while (rsProfissional.next()) {
					profissional = null;
					profissional = new Profissional();

					ProfissionalTipo profissionalTipo = null;
					profissionalTipo = new ProfissionalTipo();
					OrgaoClasse orgaoClasse = null;
					orgaoClasse = new OrgaoClasse();

					orgaoClasse.setChave(rsProfissional
							.getInt("orgao_classe_id"));
					orgaoClasse.setNome(rsProfissional
							.getString("orgao_classe_nome"));
					orgaoClasse.setSigla(rsProfissional
							.getString("orgao_classe_sigla"));

					profissionalTipo.setChave(rsProfissional
							.getInt("profissionais_tipo_id"));
					profissionalTipo.setNome(rsProfissional
							.getString("profissionais_tipo_nome"));

					profissionalTipo.setOrgaoClasse(orgaoClasse);

					profissional.setChave(rsProfissional
							.getInt("profissionais_id"));
					profissional.setUsuario(usuario);
					profissional.setProfissionalTipo(profissionalTipo);

					String queryProfissionaisOrgaos = "Select po."
							+ CamposBanco.id + " as po_id, po."
							+ CamposBanco.profissionalOrgaoHabilitacao
							+ " as po_habilidatacao, " + "po."
							+ CamposBanco.profissionalOrgaoIdEstado
							+ " as po_estado, oc."
							+ CamposBanco.orgaoClasseNome + " as oc_nome, oc."
							+ CamposBanco.id + " as oc_id, oc."
							+ CamposBanco.orgaoClasseSigla + " as oc_sigla, "
							+ "es." + CamposBanco.id + " as es_id, es."
							+ CamposBanco.ufNome + " as es_nome, es."
							+ CamposBanco.ufSigla + " as es_uf " + "from "
							+ CamposBanco.profissionalOrgaoTabela
							+ " po inner join " + CamposBanco.orgaoClasseTabela
							+ " oc on po."
							+ CamposBanco.profissionalOrgaoIdOrgaoClasse
							+ " =oc.id " + "inner join " + CamposBanco.ufTabela
							+ " es on po."
							+ CamposBanco.profissionalOrgaoIdEstado + " = es."
							+ CamposBanco.id + " " + "where po."
							+ CamposBanco.profissionalOrgaoIdProfissional
							+ " =" + profissional.getChave();

					Statement stmProfissionaisOrgaos = con.getStatement();
					ResultSet rsProfissionaisOrgaos = stmProfissionaisOrgaos
							.executeQuery(queryProfissionaisOrgaos);

					List<ProfissionalOrgao> profissionaisOrgaos = new ArrayList<ProfissionalOrgao>();
					while (rsProfissionaisOrgaos.next()) {
						ProfissionalOrgao profissionalOrgao = null;
						profissionalOrgao = new ProfissionalOrgao();
						OrgaoClasse orgaoProfissionalOrgao = null;
						orgaoProfissionalOrgao = new OrgaoClasse();
						Uf ufProfissionalOrgao = null;
						ufProfissionalOrgao = new Uf();

						orgaoProfissionalOrgao.setChave(rsProfissionaisOrgaos
								.getInt("oc_id"));
						orgaoProfissionalOrgao.setNome(rsProfissionaisOrgaos
								.getString("oc_nome"));
						orgaoProfissionalOrgao.setSigla(rsProfissionaisOrgaos
								.getString("oc_sigla"));

						ufProfissionalOrgao.setChave(rsProfissionaisOrgaos
								.getInt("es_id"));
						ufProfissionalOrgao.setNome(rsProfissionaisOrgaos
								.getString("es_nome"));
						ufProfissionalOrgao.setSigla(rsProfissionaisOrgaos
								.getString("es_uf"));

						profissionalOrgao
								.setOrgaoClasse(orgaoProfissionalOrgao);

						profissionalOrgao.setUf(ufProfissionalOrgao);
						profissionalOrgao.setChave(rsProfissionaisOrgaos
								.getInt("po_id"));
						profissionalOrgao.setHabilitacao(rsProfissionaisOrgaos
								.getInt("po_habilidatacao"));
						profissionalOrgao.setProfissional(profissional);

						profissionaisOrgaos.add(profissionalOrgao);
					}
					profissional.setProfissionaisOrgaos(profissionaisOrgaos);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		return profissional;
	}

	@Override
	public boolean excluirProfissionalPermanentePorUsuario(Usuario usuario) {
		int affected = 0;
		try {
			stm = con.getStatement();
			String query = "SELECT " + CamposBanco.id + " FROM "
					+ CamposBanco.profissionalTabela + " WHERE "
					+ CamposBanco.profissionalIdUsuario + " = "
					+ usuario.getChave();

			ResultSet rs = stm.executeQuery(query);

			while (rs.next()) {
				affected = rs.getInt(CamposBanco.id);
			}

			if (affected > 0) {
				query = "DELETE FROM " + CamposBanco.profissionalOrgaoTabela
						+ " WHERE id_profissional = " + affected;

				affected = stm.executeUpdate(query);

				if (affected > 0) {
					query = "DELETE FROM " + CamposBanco.profissionalTabela
							+ " WHERE " + CamposBanco.profissionalIdUsuario
							+ " = " + usuario.getChave();
					affected = stm.executeUpdate(query);
				} else {
					return false;
				}

			} else {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			affected = 0;
		} finally {
			fecharConexao();
		}
		return affected > 0 ? true : false;
	}

	/**
	 * Consulta todos os profissionais do tipo médico, menos o passado por
	 * parâmetro!
	 * 
	 * @param id
	 * @return List<{@link Profissional}>
	 */
	@Override
	public List<Profissional> consultarMedicosMenosParametro(int id) {
		Profissional profissional = null;
		List<Profissional> profissionais = null;
		try {
			con = new ConectorSqlServer();
			stm = con.getStatement();
			if (id != 0) {
				String queryProfissional = "select p." + CamposBanco.id
						+ " as profissionais_id,pt." + CamposBanco.id
						+ " as profissionais_tipo_id, pt."
						+ CamposBanco.profissionalTipoNome
						+ " as profissionais_tipo_nome,p."
						+ CamposBanco.profissionalIdUsuario + ", oc."
						+ CamposBanco.orgaoClasseNome
						+ " as orgao_classe_nome, oc." + CamposBanco.id
						+ " as orgao_classe_id,  oc."
						+ CamposBanco.orgaoClasseSigla
						+ " as orgao_classe_sigla FROM "
						+ CamposBanco.profissionalTabela + " p inner join "
						+ CamposBanco.profissionalTipoTabela + " pt on p."
						+ CamposBanco.profissionalIdProfissionaoTipo + " = pt."
						+ CamposBanco.id + " inner join orgaos_classes oc "
						+ "on pt." + CamposBanco.profissionalTipoIdOrgaoClasse
						+ " = oc." + CamposBanco.id + " where p."
						+ CamposBanco.profissionalIdUsuario + " <> " + id;

				ResultSet rsProfissional = stm.executeQuery(queryProfissional);

				profissionais = new ArrayList<Profissional>();

				while (rsProfissional.next()) {
					profissional = null;
					profissional = new Profissional();

					ProfissionalTipo profissionalTipo = null;
					profissionalTipo = new ProfissionalTipo();
					OrgaoClasse orgaoClasse = null;
					orgaoClasse = new OrgaoClasse();

					orgaoClasse.setChave(rsProfissional
							.getInt("orgao_classe_id"));
					orgaoClasse.setNome(rsProfissional
							.getString("orgao_classe_nome"));
					orgaoClasse.setSigla(rsProfissional
							.getString("orgao_classe_sigla"));

					profissionalTipo.setChave(rsProfissional
							.getInt("profissionais_tipo_id"));
					profissionalTipo.setNome(rsProfissional
							.getString("profissionais_tipo_nome"));

					profissionalTipo.setOrgaoClasse(orgaoClasse);

					profissional.setChave(rsProfissional
							.getInt("profissionais_id"));
					Usuario usuario = new Usuario();
					usuario.setChave(rsProfissional.getInt("id_usuario"));
					profissional.setUsuario(usuario);
					profissional.setProfissionalTipo(profissionalTipo);

					String queryProfissionaisOrgaos = "Select po."
							+ CamposBanco.id + " as po_id, po."
							+ CamposBanco.profissionalOrgaoHabilitacao
							+ " as po_habilidatacao, " + "po."
							+ CamposBanco.profissionalOrgaoIdEstado
							+ " as po_estado, oc."
							+ CamposBanco.orgaoClasseNome + " as oc_nome, oc."
							+ CamposBanco.id + " as oc_id, oc."
							+ CamposBanco.orgaoClasseSigla + " as oc_sigla, "
							+ "es." + CamposBanco.id + " as es_id, es."
							+ CamposBanco.ufNome + " as es_nome, es."
							+ CamposBanco.ufSigla + " as es_uf " + "from "
							+ CamposBanco.profissionalOrgaoTabela
							+ " po inner join " + CamposBanco.orgaoClasseTabela
							+ " oc on po."
							+ CamposBanco.profissionalOrgaoIdOrgaoClasse
							+ " =oc.id " + "inner join " + CamposBanco.ufTabela
							+ " es on po."
							+ CamposBanco.profissionalOrgaoIdEstado + " = es."
							+ CamposBanco.id + " " + "where po."
							+ CamposBanco.profissionalOrgaoIdProfissional
							+ " =" + profissional.getChave();

					Statement stmProfissionaisOrgaos = con.getStatement();
					ResultSet rsProfissionaisOrgaos = stmProfissionaisOrgaos
							.executeQuery(queryProfissionaisOrgaos);

					List<ProfissionalOrgao> profissionaisOrgaos = new ArrayList<ProfissionalOrgao>();
					while (rsProfissionaisOrgaos.next()) {
						ProfissionalOrgao profissionalOrgao = null;
						profissionalOrgao = new ProfissionalOrgao();
						OrgaoClasse orgaoProfissionalOrgao = null;
						orgaoProfissionalOrgao = new OrgaoClasse();
						Uf ufProfissionalOrgao = null;
						ufProfissionalOrgao = new Uf();

						orgaoProfissionalOrgao.setChave(rsProfissionaisOrgaos
								.getInt("oc_id"));
						orgaoProfissionalOrgao.setNome(rsProfissionaisOrgaos
								.getString("oc_nome"));
						orgaoProfissionalOrgao.setSigla(rsProfissionaisOrgaos
								.getString("oc_sigla"));

						ufProfissionalOrgao.setChave(rsProfissionaisOrgaos
								.getInt("es_id"));
						ufProfissionalOrgao.setNome(rsProfissionaisOrgaos
								.getString("es_nome"));
						ufProfissionalOrgao.setSigla(rsProfissionaisOrgaos
								.getString("es_uf"));

						profissionalOrgao
								.setOrgaoClasse(orgaoProfissionalOrgao);

						profissionalOrgao.setUf(ufProfissionalOrgao);
						profissionalOrgao.setChave(rsProfissionaisOrgaos
								.getInt("po_id"));
						profissionalOrgao.setHabilitacao(rsProfissionaisOrgaos
								.getInt("po_habilidatacao"));
						profissionalOrgao.setProfissional(profissional);

						profissionaisOrgaos.add(profissionalOrgao);
					}
					profissional.setProfissionaisOrgaos(profissionaisOrgaos);
					profissionais.add(profissional);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		return profissionais;

	}

	@Override
	public Profissional ConsultarProfissionalPorChave(int chaveProfissional) {
		Profissional profissional = null;
		try {
			con = new ConectorSqlServer();
			stm = con.getStatement();
			if (chaveProfissional > 0) {
				String queryProfissional = "select p." + CamposBanco.id
						+ " as profissionais_id, p."
						+ CamposBanco.profissionalIdUsuario
						+ " as profissional_id_usuario, pt." + CamposBanco.id
						+ " as profissionais_tipo_id, pt."
						+ CamposBanco.profissionalTipoNome
						+ " as profissionais_tipo_nome, oc."
						+ CamposBanco.orgaoClasseNome
						+ " as orgao_classe_nome, oc." + CamposBanco.id
						+ " as orgao_classe_id,  oc."
						+ CamposBanco.orgaoClasseSigla
						+ " as orgao_classe_sigla FROM "
						+ CamposBanco.profissionalTabela + " p inner join "
						+ CamposBanco.profissionalTipoTabela + " pt on p."
						+ CamposBanco.profissionalIdProfissionaoTipo + " = pt."
						+ CamposBanco.id + " inner join orgaos_classes oc "
						+ "on pt." + CamposBanco.profissionalTipoIdOrgaoClasse
						+ " = oc." + CamposBanco.id + " WHERE p."
						+ CamposBanco.id + " = " + chaveProfissional;

				ResultSet rsProfissional = stm.executeQuery(queryProfissional);

				while (rsProfissional.next()) {
					profissional = null;
					profissional = new Profissional();

					ProfissionalTipo profissionalTipo = null;
					profissionalTipo = new ProfissionalTipo();
					OrgaoClasse orgaoClasse = null;
					orgaoClasse = new OrgaoClasse();

					orgaoClasse.setChave(rsProfissional
							.getInt("orgao_classe_id"));
					orgaoClasse.setNome(rsProfissional
							.getString("orgao_classe_nome"));
					orgaoClasse.setSigla(rsProfissional
							.getString("orgao_classe_sigla"));

					profissionalTipo.setChave(rsProfissional
							.getInt("profissionais_tipo_id"));
					profissionalTipo.setNome(rsProfissional
							.getString("profissionais_tipo_nome"));

					profissionalTipo.setOrgaoClasse(orgaoClasse);

					profissional.setChave(rsProfissional
							.getInt("profissionais_id"));
					Usuario usuario = new Usuario();
					usuario.setChave(rsProfissional
							.getInt("profissional_id_usuario"));
					profissional.setUsuario(usuario);
					profissional.setProfissionalTipo(profissionalTipo);

					String queryProfissionaisOrgaos = "Select po."
							+ CamposBanco.id + " as po_id, po."
							+ CamposBanco.profissionalOrgaoHabilitacao
							+ " as po_habilidatacao, " + "po."
							+ CamposBanco.profissionalOrgaoIdEstado
							+ " as po_estado, oc."
							+ CamposBanco.orgaoClasseNome + " as oc_nome, oc."
							+ CamposBanco.id + " as oc_id, oc."
							+ CamposBanco.orgaoClasseSigla + " as oc_sigla, "
							+ "es." + CamposBanco.id + " as es_id, es."
							+ CamposBanco.ufNome + " as es_nome, es."
							+ CamposBanco.ufSigla + " as es_uf " + "from "
							+ CamposBanco.profissionalOrgaoTabela
							+ " po inner join " + CamposBanco.orgaoClasseTabela
							+ " oc on po."
							+ CamposBanco.profissionalOrgaoIdOrgaoClasse
							+ " =oc.id " + "inner join " + CamposBanco.ufTabela
							+ " es on po."
							+ CamposBanco.profissionalOrgaoIdEstado + " = es."
							+ CamposBanco.id + " " + "where po."
							+ CamposBanco.profissionalOrgaoIdProfissional
							+ " =" + profissional.getChave();

					Statement stmProfissionaisOrgaos = con.getStatement();
					ResultSet rsProfissionaisOrgaos = stmProfissionaisOrgaos
							.executeQuery(queryProfissionaisOrgaos);

					List<ProfissionalOrgao> profissionaisOrgaos = new ArrayList<ProfissionalOrgao>();
					while (rsProfissionaisOrgaos.next()) {
						ProfissionalOrgao profissionalOrgao = null;
						profissionalOrgao = new ProfissionalOrgao();
						OrgaoClasse orgaoProfissionalOrgao = null;
						orgaoProfissionalOrgao = new OrgaoClasse();
						Uf ufProfissionalOrgao = null;
						ufProfissionalOrgao = new Uf();

						orgaoProfissionalOrgao.setChave(rsProfissionaisOrgaos
								.getInt("oc_id"));
						orgaoProfissionalOrgao.setNome(rsProfissionaisOrgaos
								.getString("oc_nome"));
						orgaoProfissionalOrgao.setSigla(rsProfissionaisOrgaos
								.getString("oc_sigla"));

						ufProfissionalOrgao.setChave(rsProfissionaisOrgaos
								.getInt("es_id"));
						ufProfissionalOrgao.setNome(rsProfissionaisOrgaos
								.getString("es_nome"));
						ufProfissionalOrgao.setSigla(rsProfissionaisOrgaos
								.getString("es_uf"));

						profissionalOrgao
								.setOrgaoClasse(orgaoProfissionalOrgao);

						profissionalOrgao.setUf(ufProfissionalOrgao);
						profissionalOrgao.setChave(rsProfissionaisOrgaos
								.getInt("po_id"));
						profissionalOrgao.setHabilitacao(rsProfissionaisOrgaos
								.getInt("po_habilidatacao"));
						profissionalOrgao.setProfissional(profissional);

						profissionaisOrgaos.add(profissionalOrgao);
					}
					profissional.setProfissionaisOrgaos(profissionaisOrgaos);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		return profissional;
	}

	@Override
	public boolean verificarExistenciaHabilitacao(Profissional profissional,
			boolean isAlteracao) {

		try {
			con = new ConectorSqlServer();
			stm = con.getStatement();
			String query = "SELECT "
					+ CamposBanco.id
					+ " FROM  "
					+ CamposBanco.profissionalOrgaoTabela
					+ " WHERE "
					+ CamposBanco.profissionalOrgaoHabilitacao
					+ " = "
					+ profissional.getProfissionaisOrgaos().get(0)
							.getHabilitacao()
					+ " AND "
					+ CamposBanco.profissionalOrgaoIdEstado
					+ "= "
					+ profissional.getProfissionaisOrgaos().get(0).getUf()
							.getChave();
			if (isAlteracao)
				query += " AND "
						+ CamposBanco.id
						+ " <> "
						+ profissional.getProfissionaisOrgaos().get(0)
								.getChave();

			ResultSet reader = stm.executeQuery(query);
			while (reader.next()) {
				return true;

			}
			return false;

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			fecharConexao();
		}

	}
}
