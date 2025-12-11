package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class BaseDAO {
	
	final String DB_URL ="jdbc:postgresql://localhost:5433/postgres";
	final String DB_USER = "postgres";
	final String DB_PASSWORD = "password";
	private Connection conn;
	
	public Connection getConnection() {
		if(conn == null) {
			try {
				Class.forName("org.postgresql.Driver"); // この行を追加
				conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			}catch(SQLException e) {
					e.printStackTrace();
					System.out.println("DB接続エラー: " + e.getMessage());
			}catch(ClassNotFoundException e) { // この例外処理も追加
				e.printStackTrace();
	            System.out.println("PostgreSQLドライバーが見つかりません: " + e.getMessage());
	        }
		}
		return conn;
			
	}
}
