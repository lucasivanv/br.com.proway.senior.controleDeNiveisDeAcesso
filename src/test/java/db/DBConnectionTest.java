package db;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

public class DBConnectionTest {

	@Test
	public void testGetInstance() {
		DBConnection dbConnection = DBConnection.getInstance();	
		System.out.println(dbConnection.dbVersion());
		assertNotEquals(dbConnection, null);	
	}

	@Test
	public void testExecuteQuery() {
		DBConnection dbConnection = DBConnection.getInstance();	
		try {
			dbConnection.executeQuery("CREATE TABLE TB_USUARIO ("
					+ "idusuario INTEGER NOT NULL PRIMARY KEY, "
					+ "nome varchar(255) NOT NULL, "
					+ "hashSenha VARCHAR(255) not null,"
					+ "email VARCHAR(255) not NULL"
					+ ");");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testDBVersion() {
		DBConnection dbConnection = DBConnection.getInstance();
		assertEquals("PostgreSQL 13.2, compiled by Visual C++ build 1914, 64-bit", dbConnection.dbVersion());
	}
}
