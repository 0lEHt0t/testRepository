package servlet;

import java.sql.Connection;
import java.sql.DriverManager;

public class Common {
    public static Connection getConnection() throws Exception {
        String driver = "oracle.jdbc.driver.OracleDriver";
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String dbId = "user0416";
        String dbPw = "pass1234";
        
		Connection conn = null;
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, dbId, dbPw);
		} catch(Exception e) {
			e.printStackTrace();
		}

        return conn;
    }
}