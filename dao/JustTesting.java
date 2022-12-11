package dao;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fc.tag.*;

public class JustTesting {

	public static void main(String[] args) throws Exception {
		LocalDBconnection aaa = new LocalDBconnection();
		aaa.connect();
		
		ActorTag t1 = new ActorTag("Tom");
		
		GenreTag t2 = new GenreTag("Tom");
		
		ActorTag t3 = new ActorTag("Action");
		
		GenreTag t4 = new GenreTag("Action");

		List<Tag> list1 = new ArrayList<>();
		list1.add(t1);
		list1.add(t2);
		
		List<Tag> list2 = new ArrayList<>();
		list2.add(t3);
		list2.add(t4);

		List<List<Tag>> vraieList = new ArrayList<>();
		vraieList.add(list1);
		vraieList.add(list2);

		QueryBuilder qb = new QueryBuilder();
		qb.getDigitalMovies(vraieList);
		DaoExecQuery s = new DaoExecQuery(aaa.getBase(), qb.getQuery());
		List<Map<String, Object>>  r = s.sendquerry();
		DaoMediatorConcret dmc = new DaoMediatorConcret();
		System.out.println(dmc.getStockBluRay(1));
		aaa.disconnect();
	}
}