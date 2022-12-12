package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fc.tag.Tag;

public class DaoMediatorConcret implements DaoMediator{

	/** Given a List<List<Tag>>, connects to the database, lets a QueryBuilder create the right SQL query
	 *  and asks the database which film it has in its LesFilms table that matches the required tags, 
	 *  return each of them with their informations under the form : <Title : xxxx, Realisateur : yyy, ... >
	 *  see QueryBuilder.getDigitalMovies(List<List<Tag>>) for more informations about the return.
	 * 
	 *  @return List<Map<String, Object>> Films
	 */
	public List<Map<String, Object>> getDigitalMovies(List<List<Tag>> list) throws Exception {
		
		DaoConnection database = DaoConnection.getInstance();
		QueryBuilder qb = new QueryBuilder();
		qb.getDigitalMovies(list);
		DaoExecQuery s = new DaoExecQuery(database.getBase(), qb.getQuery());
		List<Map<String, Object>> r = new ArrayList<>();
		r = s.sendquerry();
		return r;
		
	}
	/** Given a CardId, connects to the database, lets a QueryBuilder create the right SQL query
	 *  and asks the database all about the client that has this id
	 *  under the form : <ClientId : xxxx, EmpreinteBancaire : yyy, ... >
	 *  see QueryBuilder.getCardInfo(int id) for more informations about the return.
	 *  The list should be of size 1 because each client is unique
	 * 
	 *  @return List<Map<String, Object>> Client 
	 */
	public List<Map<String, Object>> getCardInfo(int id) throws Exception {
		
		DaoConnection database = DaoConnection.getInstance();
		QueryBuilder qb = new QueryBuilder();
		qb.getCardInfo(id);
		DaoExecQuery s = new DaoExecQuery(database.getBase(), qb.getQuery());
		List<Map<String, Object>> r = new ArrayList<>();
		r = s.sendquerry();
		return r;
		
	}
	
	/** Given a CardId, connects to the database, lets a QueryBuilder create the right SQL query
	 *  and asks the database all subscription related to the client that has this id
	 *  under the form : <AbonnementId : xxxx, Hashed_card_id : yyy, ... >
	 *  see QueryBuilder.getAbonnesFromClient(int id) for more informations about the return.
	 * 
	 *  @return List<Map<String, Object>> Abonnement
	 */
	public List<Map<String, Object>> getAbonnesFromClient(int id) throws Exception {
		
		DaoConnection database = DaoConnection.getInstance();
		QueryBuilder qb = new QueryBuilder();
		qb.getAbonnesFromClient(id);
		DaoExecQuery s = new DaoExecQuery(database.getBase(), qb.getQuery());
		List<Map<String, Object>> r = new ArrayList<>();
		r = s.sendquerry();
		return r;
		
	}

	/** Given an id of an Al2000, connects to the database, lets a QueryBuilder create the right SQL query
	 *  and asks the database which film it has in its StockFilmPhysique table, return each of them with their informations
	 *  under the form : <Title : xxxx, Realisateur : yyy, ... >
	 *  see QueryBuilder.getStockBluray(List<List<Tag>>) for more informations about the return.
	 * 
	 *  @return List<Map<String, Object>> StockFilmPhysique
	 */
	public List<Map<String, Object>> getStockBluRay(int id) throws Exception {
		
		DaoConnection database = DaoConnection.getInstance();
		QueryBuilder qb = new QueryBuilder();
		qb.getStockBluRay(id);
		DaoExecQuery s = new DaoExecQuery(database.getBase(), qb.getQuery());
		List<Map<String, Object>> r = new ArrayList<>();
		r = s.sendquerry();
		return r;

	}
	
}
