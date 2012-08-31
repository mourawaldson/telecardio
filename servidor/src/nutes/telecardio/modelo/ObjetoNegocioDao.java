package nutes.telecardio.modelo;

import java.sql.Statement;

import nutes.telecardio.modelo.configuracao.ConectorSqlServer;

/**
 * Entidade básica comum a todos os Daos do sistema
 * 
 * @author hvb
 */
public abstract class ObjetoNegocioDao {

	/**
	 * Atributo que ira se conectar com o banco de dados
	 */
	protected ConectorSqlServer con;

	/**
	 * Atributo de statement que ira interagir com a conexão do banco de dados
	 * passando as querys solicitadas
	 */
	protected Statement stm;

	public ObjetoNegocioDao() {
		con = new ConectorSqlServer();

	}

	/**
	 * Seta o ConectorSqlServer
	 * 
	 * @param ConectorSqlServer
	 *            conector
	 */
	public void setConectorSql(ConectorSqlServer conector) {
		con = conector;
	}

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public void fecharConexao() {
		// Caso a conexao não esteja em transação o Dao pode fechar a mesma,
		// caso contrario
		// so pode fechar conexão pela transação
		if (!con.isConexaoEmTransacao())
			con.fecharConexao();
	}

}
