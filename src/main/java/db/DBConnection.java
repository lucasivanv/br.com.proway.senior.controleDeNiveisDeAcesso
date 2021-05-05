package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe DBConnection
 * 
 * Classe responsavel pela conexao com o banco de dados (DB)
 * 
 * @author Sprint 4
 * @author Elton Oliveira, elton.oliveira@senior.com.br
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Thiago Barbieri, thiago.barbieri@senior.com.br
 * @author Vitor Goncalves, vitor.goncalves@senior.com.br
 * @author Vitor Gehrke, vitor.gehrke@senior.com.br
 */
public class DBConnection {

	static String url = "jdbc:postgresql://localhost:5432/grupo3";
	static String user = "grupo3";
	static String password = "grupo3";

	public static Connection con;

	static DBConnection dbConnection = null;

	private DBConnection() {
		this.connect();
	}

	/**
	 * Implementacao do singleton da classe.
	 * Retorna a instancia unica da conexao com o banco de dados
	 * @return DBConnection
	 */
	public static DBConnection getInstance() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	/**
	 * Metodo para conectar com o DB a partir dos atributos necessarios (url, user e
	 * password)
	 * 
	 * @return void
	 */
	public void connect() {
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Metodo que executa uma query
	 * 
	 * @param query String
	 * @throws SQLException
	 * @return void
	 */
	public void executeUpdate(String query) throws SQLException {
		try {
			con.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que executa uma query
	 * 
	 * @param query String
	 * @throws SQLException
	 * @return ResultSet
	 */
	public ResultSet executeQuery(String query) throws SQLException {

		try {
			return con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * Metodo que retorna a versao do DB
	 * 
	 * @return String
	 */
	public String dbVersion() {
		try {
			if (con == null) {
				connect();
			}
			ResultSet rs = executeQuery("SELECT VERSION()");
			if (rs.next()) {
				return rs.getString(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Metodo que limpa uma tabela no DB
	 * 
	 * @param nomeTabela String
	 * @return void
	 */
	public void limparDB(String nomeTabela) {
		String limparTabela = "delete from " + nomeTabela + ";";
		try {
			dbConnection.executeUpdate(limparTabela);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
