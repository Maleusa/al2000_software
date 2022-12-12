package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LocalDBconnection extends Thread{
	private static LocalDBconnection instance;
	static Connection base;
	String database="al2000";

	public LocalDBconnection() throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		base = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/"+database, "hr", "hr"); // connexion
		base.setAutoCommit(false);
		System.out.println("base connected");
	}
	public static LocalDBconnection getInstance() throws Exception {
		if (LocalDBconnection.instance == null) {
			LocalDBconnection.instance = new LocalDBconnection();
		}
		return LocalDBconnection.instance;
	}
	
	public void connect() throws SQLException {
		String s1 = "alter session set container = al2000";
		new DaoExecQuery(base, s1);
		System.out.println("ça passe");
	}
	
	public Connection getBase() {
		return this.base;
	}
	
	public void disconnect() throws SQLException {
		base.close();
		System.out.println("disconnected");
	}
	
	public void run(){  
		try {
			this.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
