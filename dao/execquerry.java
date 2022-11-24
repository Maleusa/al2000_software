package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class execquerry {
	Connection base;
	String querry;
	
	public execquerry(Connection conn, String querry) {
		base=conn;
		this.querry=querry;
	}
	
	public ResultSet sendquerry() throws SQLException {
		Statement requete = base.createStatement();
		ResultSet resultat = requete.executeQuery(querry);
		return resultat;
	}
}
