package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LocalDBconnection extends Thread{
	Connection base;
	String database="al2000";
	public LocalDBconnection() throws SQLException, ClassNotFoundException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		Class.forName("oracle.jdbc.driver.OracleDriver");
		base = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/"+database, "hr", "hr"); // connexion
		System.out.println("base connected");
	}
	
	public void connect() throws SQLException {
		String s1 = "alter session set container = al2000";
		String s2 = "connect kilian password";
		execquerry newquerry= new execquerry(base, s1);
		execquerry newquerry2= new execquerry(base, s2);
	}
	
	public void disconnect() throws SQLException {
		base.close();
	}
	
	public void run(){  
		try {
			this.connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		try {
			LocalDBconnection aaa= new LocalDBconnection();
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
