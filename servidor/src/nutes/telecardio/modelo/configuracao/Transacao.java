package nutes.telecardio.modelo.configuracao;

import java.sql.SQLException;
import nutes.telecardio.modelo.ObjetoNegocioDao;

/**
 * Classe que ira fazer o controle de conexao com banco, onde ira fornecer a
 * conexa para todos os daos que ira participar da transação com o banco,
 * podendo no final da transação realizar o comit ou o rollback
 * 
 * @author hvb
 */
public class Transacao {

	private ConectorSqlServer conector;

	/**
	 * Instância o objeto Transação junto com a conexão com o banco de dados
	 * seta o auto comite para false e informa que a conexão é da transação
	 */
	public Transacao() {
		try {
			conector = new ConectorSqlServer();
			conector.getConnection().setAutoCommit(false);
			conector.setConexaoEmTransacao(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Adiciona o Dao na Transação relacionando seu conecotr com o da transação
	 * onde todos vão enchergar o mesmo conector
	 * 
	 * @param Dao
	 */
	public void addDaoTransacao(ObjetoNegocioDao Dao) {
		Dao.setConectorSql(this.conector);
	}

	/**
	 * Confirma a transação e fecha a conexão
	 */
	public void commitTransacao() {
		try {
			conector.getConnection().commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		conector.fecharConexao();
	}

	/**
	 * Não confirma a transação no banco retornando ao estado anterior a
	 * transação, e depois fecha a conexão
	 */
	public void rollbackTransacao() {
		try {
			conector.getConnection().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		conector.fecharConexao();
	}

	public void fecharConexao() {
		conector.fecharConexao();
	}

}
