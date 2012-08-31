package nutes.telecardio.modelo.configuracao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe responsavel por realizar a conexão com o bando de dados, atuando como
 * camada de conexão
 * 
 * @author hvb
 * 
 */
public class ConectorSqlServer {

	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement;
	private static String url;
	private static String serverName;
	private static String portNumber;
	private static String databaseName;
	private static String userName;
	private static String password;

	private static boolean isConfigCarregada = false;

	// Atributo que define se a conexao do conextor esta em transacao, onde so
	// poderá ser
	// fechada pela trasação
	private boolean isConexaoEmTransacao = false;

	// Informs the driver to use server a side-cursor, which permits more than
	// one active statement on a connection.
	private final String selectMethod = "cursor";

	private static String driver = "com.microsoft.sqlserver.jdbc.SQLServerConnection";

	/**
	 * Monta a string de conexão.
	 * 
	 * @return String
	 */
	private String getConnectionUrl() {
		return getUrl() + getServerName() + ":" + getPortNumber()
				+ ";databaseName=" + getDatabaseName() + ";selectMethod="
				+ selectMethod + ";";
	}

	/**
	 * Faz a conexão com o banco de dados e retorna a mesma.
	 * 
	 * @return Connection
	 */
	public Connection getConnection() {
		try {

			Class.forName(driver);
			if (connection != null)
				return connection;
			else
				connection = java.sql.DriverManager.getConnection(this
						.getConnectionUrl(), getUserName(), getPassword());

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.exit(0);
		} catch (SQLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		return connection;
	}

	/**
	 * Fecha a conexão com o banco de dados se a mesma estiver aberta.
	 * 
	 * @return void
	 */
	public void fecharConexao() {
		try {
			if (connection != null)
				connection.close();
			connection = null;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Retorna o statment da conexão para poder realizar as consultas.
	 * 
	 * @return Statement
	 */
	public Statement getStatement() {
		try {
			statement = this.getConnection().createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return statement;
	}

	/**
	 * Retorna o preparedStatement da conexão para poder realizar as consultas.
	 * 
	 * @return PreparedStatement preparedStatement
	 */
	public PreparedStatement getPreparedStatement(String sql) {
		try {
			preparedStatement = this.getConnection().prepareStatement(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return preparedStatement;
	}

	public void setConexaoEmTransacao(boolean isConexaoEmTransacao) {
		this.isConexaoEmTransacao = isConexaoEmTransacao;
	}

	public boolean isConexaoEmTransacao() {
		return isConexaoEmTransacao;
	}

	public static void setPassword(String password) {
		ConectorSqlServer.password = password;
	}

	public static String getPassword() {
		return password;
	}

	public static void setUserName(String userName) {
		ConectorSqlServer.userName = userName;
	}

	public static String getUserName() {
		return userName;
	}

	public static void setDatabaseName(String databaseName) {
		ConectorSqlServer.databaseName = databaseName;
	}

	public static String getDatabaseName() {
		return databaseName;
	}

	public static void setPortNumber(String portNumber) {
		ConectorSqlServer.portNumber = portNumber;
	}

	public static String getPortNumber() {
		return portNumber;
	}

	public static void setServerName(String serverName) {
		ConectorSqlServer.serverName = serverName;
	}

	public static String getServerName() {
		return serverName;
	}

	public static void setUrl(String url) {
		ConectorSqlServer.url = url;
	}

	public static String getUrl() {
		return url;
	}

	public static void setConfigCarregada(boolean configCarregada) {
		isConfigCarregada = configCarregada;
	}

	public static boolean isConfigCarregada() {
		return isConfigCarregada;
	}

}