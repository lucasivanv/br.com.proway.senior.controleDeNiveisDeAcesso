package db;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Ignore;
import org.junit.Test;

public class DBConnectionTest {

	@Test
	@Ignore
	public void testGetInstance() {
		DBConnection dbConnection = DBConnection.getInstance();	
		System.out.println(dbConnection.dbVersion());
		assertNotEquals(dbConnection, null);	
	}

	@Test
	public void testExecuteQuery() {
		DBConnection dbConnection = DBConnection.getInstance();	
		try {
			dbConnection.executeQuery("");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	@Ignore
	public void testDBVersion() {
		DBConnection dbConnection = DBConnection.getInstance();
		assertEquals("PostgreSQL 13.2, compiled by Visual C++ build 1914, 64-bit", dbConnection.dbVersion());
	}
}
