package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fc.tag.*;

public class QueryBuilder {
	
	private StringBuilder query;
	
	public QueryBuilder() {
		
	}
	
	
	
	public void getFilm(List<List<Tag>> list) {
		
		this.query = new StringBuilder("select * from LesFilms where\n");
		
		for (List<Tag> list2 : list) {
			
			if (!(list.get(0).equals(list2))) {
				this.query.append("\n AND ");
			}
			
			for (Tag tag : list2) {
				
				if(list2.get(0).equals(tag)){
					this.query.append("(");
				}
				
				this.query.append(tag.query());
				
				if (!(list2.get(list.size()-2).equals(tag))) {
					this.query.append(" OR ");
					
				}
				else {
					this.query.append(")");
				}
			}

		}
	}
	
	public String getQuery () {
		return this.query.toString();
	}
	
	public void execQuerry() {
		
	}
	
	public static void main(String[] args) {
		
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
		String s = qb.getQuery();
		System.out.println(s);
		
	}
	
}
