package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.platform.engine.TestTag;

import fc.tag.*;

public class JustTesting {

	public static void testTriggerUnverif() throws SQLException {
		LocalDBconnection aaa = new LocalDBconnection();
		PreparedStatement init;
		int r = 0;
		try {
			for(int i = 1; i< 12; i++) {
				r++;
				init = aaa.getBase().prepareStatement("UPDATE FILMPHYSIQUE SET ETAT = 'RENTABLE' WHERE ide = ?");
				init.setInt(1, i);
				init.executeUpdate();
				aaa.getBase().commit();
				init.clearParameters();		
			}
		}
		catch(SQLException sqle) {
			System.out.println(r);
			System.err.println(sqle.getErrorCode());
		}
		aaa.disconnect();
	}
	
	public static void testTriggerOod() throws SQLException{
		LocalDBconnection aaa = new LocalDBconnection();
		PreparedStatement init;
		init = aaa.getBase().prepareStatement("INSERT INTO HISTORIQUECREDIT VALUES (2,1,1,TO_DATE('10-12-2022','DD-MM-YYYY'))");
		init.executeUpdate();
		aaa.getBase().commit();
		init.clearParameters();
		init = aaa.getBase().prepareStatement("INSERT INTO HISTORIQUELOCATIONPHYSIQUE VALUES (2,1,1,TO_DATE('10-12-2022','DD-MM-YYYY'),TO_DATE('11-12-2022','DD-MM-YYYY'), 4)");
		init.executeUpdate();
		aaa.getBase().commit();
		init.clearParameters();
		aaa.disconnect();
	}
	
	public static void main(String[] args) throws Exception {
		DaoAppSubscriber dao = new DaoAppSubscriber();
		ArrayList<String> al = new ArrayList<String>();
		al.add("9");
		al.add("1");
		al.add("1");
		dao.ld.update(al);
	}
}