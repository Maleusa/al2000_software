package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fc.tag.ActorTag;
import fc.tag.NameTag;
import fc.tag.Tag;

public class TestPasLaOuIlFaut {
	
	public static void main(String[] args) throws Exception {
		LocalDBconnection aaa = new LocalDBconnection();
		aaa.connect();
		
		NameTag t1 = new NameTag("Batman");
		
		ActorTag t2 = new ActorTag("Batman");
		
		NameTag t3 = new NameTag("George");
		
		ActorTag t4 = new ActorTag("George");
		
		NameTag t5 = new NameTag("Clooney");
		
		ActorTag t6 = new ActorTag("Clooney");
		
		List<Tag> list1 = new ArrayList<>();
		list1.add(t1);
		list1.add(t2);
		
		List<Tag> list2 = new ArrayList<>();
		list2.add(t3);
		list2.add(t4);
		
		List<Tag> list3 = new ArrayList<>();
		list3.add(t5);
		list3.add(t6);
		
		List<List<Tag>> vraiList = new ArrayList<>();
		vraiList.add(list1);
		vraiList.add(list2);
		vraiList.add(list3);
		
		QueryBuilder qb = new QueryBuilder(vraiList);
		execquerry s = new execquerry(aaa.base, qb.getQuery());
		ResultSet r = s.sendquerry();
		while(r.next()) {
			System.out.println(r.getString("title"));
		}
	}
}
