package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class execquerry {
	Connection base;
	String querry;

	public execquerry(Connection conn, String querry) {
		base=conn;
		this.querry=querry;
	}

	public List<Map<String, Object>> sendquerry() throws SQLException {
		Statement requete = base.createStatement();
		ResultSet resultat = requete.executeQuery(querry);
		List<Map<String, Object>> r = new ArrayList<>();
		ResultSetMetaData rsmd = resultat.getMetaData();
		while(resultat.next()) {
			for (int i = 1; i <= rsmd.getColumnCount(); i++) {
				Map<String, Object> curr = new HashMap<>();
				String cType = rsmd.getColumnTypeName(i);
				System.out.println("cType : "+cType);
				switch (cType) {
				case "DATE":
					curr.put(rsmd.getColumnName(i), resultat.getDate(i));
					break;
				case "VARCHAR":
					curr.put(rsmd.getColumnName(i), resultat.getString(i));
					break;
				case "INT":
					curr.put(rsmd.getColumnName(i), resultat.getInt(i));
					break;
				case "FLOAT":
					curr.put(rsmd.getColumnName(i), resultat.getDouble(i));
					break;
				default:
					break;
				}
				r.add(curr);
			}
		}
		
		return r;
	}
}
