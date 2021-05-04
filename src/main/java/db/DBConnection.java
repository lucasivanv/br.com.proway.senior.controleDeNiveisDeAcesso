package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Classe DBConnection
 * 
 * Classe responsável pela conexão com o banco de dados (DB)
 * 
 * @author Sprint 4
 * @author Elton Oliveira, elton.oliveira@senior.com.br
 * @author Lucas Ivan, lucas.ivan@senior.com.br
 * @author Thiago Barbieri, thiago.barbieri@senior.com.br
 * @author Vitor Gonçalves, vitor.goncalves@senior.com.br
 * @author Vitor Gehrke, vitor.gehrke@senior.com.br
 */
public class DBConnection {

	static String url = "jdbc:postgresql://localhost:5432/Grupo3";
	static String user = "Grupo3";
	static String password = "admin";
	public static Connection con;

	static DBConnection dbConnection = null;

	private DBConnection() {
		this.connect();
	}

	public static DBConnection getInstance() {
		if (dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	/**
	 * Método para conectar com o DB a partir dos atributos necessários (url, user e
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
	 * Método que executa uma query
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
	 * Método que executa uma query
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
	 * Método que retorna a versao do DB
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
	 * Método que limpa uma tabela no DB
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
