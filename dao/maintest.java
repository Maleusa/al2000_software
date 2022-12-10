package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class maintest {

	public static void main(String[] args) {
		System.out.println("azeazeaze");
		daoConnection dao = new daoConnection();
		dao.connectDB();
		execquerry q = new execquerry(dao.base, "SELECT * FROM lestests2");
		List<Map<String, Object>> r = new ArrayList<>();
		try {
			r = q.sendquerry();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < r.size(); i++) {
			System.out.println(i);
			System.out.println(r.get(i));
		}
		
		dao.disconnectDB();
	}
}
