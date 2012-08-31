package nutes.telecardio.modelo.estruturaorganizacional;

import java.sql.ResultSet;
import java.sql.SQLException;
import nutes.telecardio.modelo.ObjetoNegocioDao;
import nutes.telecardio.modelo.configuracao.CamposBanco;

/**
 * Dao de Endereço
 * 
 * @author hvb
 * 
 */
public class EnderecoDao extends ObjetoNegocioDao implements IEnderecoDao {

	@Override
	public boolean atualizar(Endereco objetoNegocio) {
		int affected = 0;
		try {
			stm = con.getStatement();

			String query = "UPDATE " + CamposBanco.enderecoTabela + " SET "
					+ CamposBanco.enderecoLogradouro + "= '"
					+ objetoNegocio.getLogradouro() + "'" + ","
					+ CamposBanco.enderecoNumero + " ='"
					+ objetoNegocio.getNumero() + "'" + ","
					+ CamposBanco.enderecoComplemento + " = '"
					+ objetoNegocio.getComplemento() + "'" + ","
					+ CamposBanco.enderecoCep + " = '" + objetoNegocio.getCep()
					+ "'" + "," + CamposBanco.enderecoIdEstado + " = "
					+ objetoNegocio.getUf().getChave() + ","
					+ CamposBanco.enderecoBairro + " = '"
					+ objetoNegocio.getBairro() + "'" + ","
					+ CamposBanco.enderecoMunicipio + " = '"
					+ objetoNegocio.getMunicipio() + "' " + "WHERE "
					+ CamposBanco.id + " = " + objetoNegocio.getChave();
			affected = stm.executeUpdate(query);

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		return affected > 0 ? true : false;
	}

	@Override
	public boolean excluir(Endereco objetoNegocio) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int incluir(Endereco objetoNegocio) {
		int affected = 0;
		try {
			stm = con.getStatement();

			String query = " INSERT INTO " + CamposBanco.enderecoTabela + " ("
					+ CamposBanco.enderecoLogradouro + ", "
					+ CamposBanco.enderecoNumero + ", "
					+ CamposBanco.enderecoComplemento + ", "
					+ CamposBanco.enderecoCep + ", "
					+ CamposBanco.enderecoIdEstado + ", "
					+ CamposBanco.enderecoBairro + ", "
					+ CamposBanco.enderecoMunicipio + ")" + "values('"
					+ objetoNegocio.getLogradouro().trim() + "','"
					+ objetoNegocio.getNumero().trim() + "','"
					+ objetoNegocio.getComplemento().trim() + "','"
					+ objetoNegocio.getCep() + "',"
					+ objetoNegocio.getUf().getChave() + ",'"
					+ objetoNegocio.getBairro().trim() + "','"
					+ objetoNegocio.getMunicipio().trim() + "')";

			affected = stm.executeUpdate(query);

			if (affected > 0) {
				query = CamposBanco.selectIdentity + CamposBanco.enderecoTabela;
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
	public Endereco ConsultarEnderecoPorUsuario(Usuario usuario) {
		Endereco endereco = null;
		try {
			stm = con.getStatement();
			String query = "SELECT e." + CamposBanco.id + ",e."
					+ CamposBanco.enderecoBairro + ",e."
					+ CamposBanco.enderecoCep + ", e."
					+ CamposBanco.enderecoComplemento + ", e."
					+ CamposBanco.enderecoLogradouro + ", " + "e."
					+ CamposBanco.enderecoMunicipio + ", e."
					+ CamposBanco.enderecoNumero + ", e."
					+ CamposBanco.enderecoIdEstado + "" + " FROM "
					+ CamposBanco.enderecoTabela + " e INNER JOIN "
					+ CamposBanco.pessoaTabela + " p on e." + CamposBanco.id
					+ "=p." + CamposBanco.pessoaIdEndereco + ""
					+ " INNER JOIN " + CamposBanco.usuarioTabela + " u on u."
					+ CamposBanco.usuarioIdPessoa + " = p." + CamposBanco.id
					+ " WHERE u." + CamposBanco.id + " = " + usuario.getChave();
			ResultSet rs = stm.executeQuery(query);
			while (rs.next()) {
				endereco = new Endereco();
				endereco.setBairro(rs.getString(CamposBanco.enderecoBairro));
				endereco.setCep(rs.getString(CamposBanco.enderecoCep));
				endereco.setChave(rs.getInt(CamposBanco.id));
				endereco.setComplemento(rs
						.getString(CamposBanco.enderecoComplemento));
				endereco.setLogradouro(rs
						.getString(CamposBanco.enderecoLogradouro));
				endereco.setMunicipio(rs
						.getString(CamposBanco.enderecoMunicipio));
				endereco.setNumero(rs.getString(CamposBanco.enderecoNumero));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			fecharConexao();
		}
		return endereco;
	}

	/**
	 * Retorna o {@link Endereco} de acordo com o Id fornecido.
	 * 
	 * @param id
	 * @return {@link Endereco}
	 */
	public Endereco ConsultarEnderecoPorId(int id) {
		String sql = null;
		ResultSet rs = null;

		Endereco endereco = new Endereco();
		endereco.setChave(id);
		endereco.setUf(new Uf());

		try {
			stm = con.getStatement();

			sql = "SELECT " + CamposBanco.id + ","
					+ CamposBanco.enderecoLogradouro + ","
					+ CamposBanco.enderecoNumero + ","
					+ CamposBanco.enderecoComplemento + ","
					+ CamposBanco.enderecoCep + ","
					+ CamposBanco.enderecoIdEstado + ","
					+ CamposBanco.enderecoBairro + ","
					+ CamposBanco.enderecoMunicipio;
			sql += " FROM " + CamposBanco.enderecoTabela;
			sql += " WHERE " + CamposBanco.id + " = " + id;

			rs = stm.executeQuery(sql);

			while (rs.next()) {
				endereco.setChave(rs.getInt(CamposBanco.id));
				endereco.setLogradouro(rs
						.getString(CamposBanco.enderecoLogradouro));
				endereco.setNumero(rs.getString(CamposBanco.enderecoNumero));
				endereco.setComplemento(rs
						.getString(CamposBanco.enderecoComplemento));
				endereco.setCep(rs.getString(CamposBanco.enderecoCep));
				endereco.getUf().setChave(
						rs.getInt(CamposBanco.enderecoIdEstado));
				endereco.setBairro(rs.getString(CamposBanco.enderecoBairro));
				endereco.setMunicipio(rs
						.getString(CamposBanco.enderecoMunicipio));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally {
			fecharConexao();
		}

		return endereco;
	}
}
