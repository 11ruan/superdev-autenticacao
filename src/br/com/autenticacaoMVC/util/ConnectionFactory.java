package br.com.autenticacaoMVC.util;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ConnectionFactory {
	public static final String PATH = "jdbc:postgresql://localhost:5432/db_superdev_autenticacao";
	public static final String USER = "postgres";
	public static final String PASSWORD = "admin";
	
	public static Connection getConnection() throws Exception{
		try {
			Class.forName("org.postgresql.Driver");
			return DriverManager.getConnection(PATH, USER, PASSWORD);
		} catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
	
	public static void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		close(conn, stmt, rs);
	}
	
	public static void closeConnection(Connection conn, Statement stmt) throws Exception {
		close(conn, stmt, null);
	}
	
	public static void closeConnection(Connection conn) throws Exception {
		close(conn, null, null);
	}
	
	private static void close(Connection conn, Statement stmt, ResultSet rs) throws Exception {
		try {
			if(rs != null) {
				rs.close();
			}
			if(stmt != null) {
				stmt.close();
			}
			if(conn != null) {
				conn.close();
			}
		}catch(Exception ex) {
			throw new Exception(ex.getMessage());
		}
	}
}
