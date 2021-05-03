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
	static Connection con;
	
	static DBConnection dbConnection = null;
	
	private DBConnection () {
		DBConnection.connect();
	}
	
	public static DBConnection getInstance(){
		if(dbConnection == null) {
			dbConnection = new DBConnection();
		}
		return dbConnection;
	}

	public static void connect() {
		try {
			con = DriverManager.getConnection(url, user, password);
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public static ResultSet executeQuery(String query) throws SQLException {
		Statement st = con.createStatement();
		ResultSet rs = st.executeQuery(query);
		return rs;
	}
	
	public static String dbVersion() {
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
	
}
