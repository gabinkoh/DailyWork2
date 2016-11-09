package dailywork2;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBTemplate {

	private Connection con;

	public DBTemplate() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//2 db접속
			String url = "jdbc:mysql://localhost:3306/library";
			String id = "GABjQuery";
			String pw = "GABjQuery";
			con = DriverManager.getConnection(url, id, pw);
			con.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void commit() {
		try {
			con.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void rollback() {
		try {
			con.rollback();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Connection getCon() {
		return con;
	}
	public void setCon(Connection con) {
		this.con = con;
	}
	
	
}
