package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fc.tag.Tag;

public class DaoMediatorConcret implements DaoMediator{

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void request() {
		// TODO Auto-generated method stub
		
	}
	
	public List<Map<String, Object>> getDigitalMovies(List<List<Tag>> list) throws Exception {
		
		LocalDBconnection database = LocalDBconnection.getInstance();
		QueryBuilder qb = new QueryBuilder();
		qb.getDigitalMovies(list);
		DaoExecQuery s = new DaoExecQuery(database.getBase(), qb.getQuery());
		List<Map<String, Object>> r = new ArrayList<>();
		r = s.sendquerry();
		return r;
		
	}
	
	public List<Map<String, Object>> getCardInfo(int id) throws Exception {
		
		LocalDBconnection database = LocalDBconnection.getInstance();
		QueryBuilder qb = new QueryBuilder();
		qb.getCardInfo(id);
		DaoExecQuery s = new DaoExecQuery(database.getBase(), qb.getQuery());
		List<Map<String, Object>> r = new ArrayList<>();
		r = s.sendquerry();
		return r;
		
	}
	
	public List<Map<String, Object>> getStockBluRay(int id) throws Exception {
		
		LocalDBconnection database = LocalDBconnection.getInstance();
		QueryBuilder qb = new QueryBuilder();
		qb.getStockBluRay(id);
		DaoExecQuery s = new DaoExecQuery(database.getBase(), qb.getQuery());
		List<Map<String, Object>> r = new ArrayList<>();
		r = s.sendquerry();
		return r;

	}
	
}
