package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
	
	static String url = "jdbc:postgresql://localhost:5432/grupo3";
	static String user = "grupo3";
	static String password = "grupo3";
	public static Connection con;
	
	static DBConnection dbConnection = null;
	
	private DBConnection () {
		this.connect();
	}
	
	public static DBConnection getInstance(){
		if(dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	public void connect() {
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public void executeUpdate(String query) throws SQLException {
		try {
			con.createStatement().executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		
		try {
			return con.createStatement().executeQuery(query);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String dbVersion() {
		try {
			if (con == null){
				connect();
			}
			ResultSet rs = executeQuery("SELECT VERSION()");
			if (rs.next()) {
				return rs.getString(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void limparDB(String nomeTabela) {
		String limparTabela = "delete from "+ nomeTabela +";";
		try {
			dbConnection.executeUpdate(limparTabela);
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
}
