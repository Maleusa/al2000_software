package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import fc.tag.ActorTag;
import fc.tag.NameTag;
import fc.tag.Tag;

public class TestPasLaOuIlFaut {
	
	public static void main(String[] args) throws Exception {
		LocalDBconnection aaa = new LocalDBconnection();
		aaa.connect();
		
		NameTag t1 = new NameTag("Mission");
		
		ActorTag t2 = new ActorTag("Mission");
		
		NameTag t3 = new NameTag("Tom");
		
		ActorTag t4 = new ActorTag("Tom");
		
		NameTag t5 = new NameTag("Cruise");
		
		ActorTag t6 = new ActorTag("Cruise");
		
		List<Tag> list1 = new ArrayList<>();
		list1.add(t1);
		list1.add(t2);
		
		List<Tag> list2 = new ArrayList<>();
		list2.add(t3);
		list2.add(t4);
		
		List<Tag> list3 = new ArrayList<>();
		list3.add(t5);
		list3.add(t6);
		
		List<List<Tag>> vraieList = new ArrayList<>();
		vraieList.add(list1);
		vraieList.add(list2);
		vraieList.add(list3);
		
		QueryBuilder qb = new QueryBuilder();
		qb.getFilm(vraieList);
		DaoExecQuery s = new DaoExecQuery(aaa.base, qb.getQuery());
		List<Map<String, Object>> r = new ArrayList<>(); 
		r=s.sendquerry();
		for (int i = 0; i < r.size(); i++) {
			System.out.println(r.get(i));
		}
		aaa.disconnect();
	}
}