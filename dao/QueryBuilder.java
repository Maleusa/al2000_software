package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fc.tag.*;

public class QueryBuilder {
	
	private StringBuilder query;
	
	public QueryBuilder() {	
	}
	
	/** Builds the query to get the list of every physical movies in an Al2000, based on its id. We select all useful infos,
	 *  namely : title, director, actors, description, release year, genres, poster, its id as a physical film, and its state.
	 * 
	 * @param idAl2000
	 */
	public void getStockBluRay(int idAl2000) {
		
		this.query = new StringBuilder("select title, realisateur, acteurs, descript, anneesortie, genre_un, genre_deux, genre_trois, genre_quatre, genre_cinq, genre_six, genre_sept, affiche, fp.ide, etat"
				+ " from LesFilms f, stockfilmsphysiques s, AL2000s a, filmphysique fp"
				+ "	where f.ide = fp.id_film AND s.nmr_stock=a.ide AND s.ide_film_physique=fp.ide AND a.ide=" +idAl2000);
				
	}
	
	/** Builds the query to get every info on a client based on their id.
	 * 
	 * @param ClientId
	 */
	public void getCardInfo(int ClientId) {
		
		this.query = new StringBuilder("select * from Clients where IDE = '" + ClientId + "'");
		
	}
	
	/** Builds the query to get every info on every subscription of a client based on their id.
	 * 
	 * @param ClientId
	 */
	public void getAbonnesFromClient(int ClientId) {
		
		this.query = new StringBuilder("select * from Abonne where id_client = '" + ClientId + "'");
		
	}
	
	/** Builds the query for a film research. Take a list of list of Tags that's built depending on
	 *  checked JRadioButtons in the tag research and the String researched. For each String and Tag searched,
	 *  we check for the existence of a combination of <Tag><String>, and we select lines where we have a matching combo.
	 *  
	 *  For example, if the user enters "Batman George Clooney" in the search bar, and check the tags actor and title,
	 *  the paramaters will be :
	 *  <<NameTag Batman, ActorTag Batman> <NameTag George, ActorTag George> <NameTag Clooney, ActorTag Clooney>>
	 *  and the query will be :
	 *  "select * from LesFilms where
	 *			(TITLE LIKE '%Batman%' OR ACTEURS LIKE '%Batman%')
 	 *		AND (TITLE LIKE '%George%' OR ACTEURS LIKE '%George%')
 	 *		AND (TITLE LIKE '%Clooney%' OR ACTEURS LIKE '%Clooney%')"
 	 *	We don't treat the case where a user misspel any of the Strings, we consider he knows Clooney is spelled this way
	 * 
	 * @param List of list of Tags
	 * @return void
	 */
	public void getDigitalMovies(List<List<Tag>> list) {

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
				
				if (!(list2.get(list2.size()-1).equals(tag))) {
					this.query.append(" OR ");
					
				}
				else {
					this.query.append(")");
				}
			}

		}
	}
	/**
     * Build a query that insert a QRCode Location in database 
     * @param l Arraylist of necessary info : l = {idBluRay, idUser, idAbonne}
     */
	public void insertLocationDemat(ArrayList<String> l) {// data = {idBluRay, idUser, idAbonne} la DAO doit créer une date à la reception et met les tables à jours
		this.query=new StringBuilder("INSERT INTO HistoriqueLocationDemat"
				+ "VALUES ((select max(ide)+1 from HistoriqueLocationDemat),"+ l.get(2)+ ", "+ l.get(0)+", (SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM dual;) , (SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM dual;), NULL)");
	}
	
	/**
    * Build a query that insert a Bluray Loaction in database
    * @param l Arraylist of necessary info : l = {idBluRay, idUser, idAbonne, Price}
    */
	public void insertLocationPhys(ArrayList<String> l) {//, data = {idBluRay, idUser, idAbonne, Price} DAO gère date
		this.query=new StringBuilder("INSERT INTO HistoriqueLocationPhysique(ide, id_film_physique ,id_abonne, ddloc, dfloc, price)"
				+ "VALUES ((select max(ide)+1 from HistoriqueLocationDemat),"+ l.get(2)+ ", " + l.get(0)+", (SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM dual;) , NULL, "+ l.get(3) +")");
	}
	/**
    * Build a query that update all info from Abonne account
    * @param data, Arraylist of necessary info : data = {idAbonne,"Nom", "Mot de passe", "Adresse", "Genre bloqué", "Solde"}
    */
	public void updateClient(ArrayList<String> data) {// data = {idAbonne,"Nom", "Mot de passe", "Adresse", "Genre bloqué", "Solde"} met à jour les tables
		// TODO Auto-generated method stub
		this.query=new StringBuilder("DELETE FROM TagBloque t"
				+ "WHERE t.ide=value;\n");
		
		String tag = data.get(4);
		String[] tagsplit = tag.split(" ");
		for (int i = 0; i < tagsplit.length; i++) {
			
			this.query.append("INSERT INTO TagBloque VALUES ("+ data.get(0) +","+ tagsplit[i] +");\n");
		}
		
		this.query.append("\n");
		this.query.append("UPDATE CLIENTS"
				+"SET name = "+data.get(1)
				+"adress ="+data.get(3)
				+"WHERE ide = (SELECT id_client FROM Abonne	WHERE ide = " +data.get(0)+ ");");
		this.query.append("UPDATE Abonne"
				+"SET hashed_password = " + data.get(2)
				+"montant_carte_abonnement =" + data.get(5)
				+"WHERE ide = "+data.get(0));
	}
	
	/**
    * Build a query that update a specified Location and set his state to CORRECT
    * @param data, Arraylist of necessary info : data = {idBluRay, idUser, idAbonne, Price}
    */
	public void returnLocationCorrect(ArrayList<String> data) {// data = {idBluRay, idUser, idAbonne, Price} DAO gère date
		this.query=new StringBuilder("UPDATE HistoriqueLocationPhysique"
				+ "SET dfloc = (SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM dual;) "
				+ "WHERE  id_abonne = " + data.get(2) + " AND " + "id_film_physique = " +  data.get(0) + " AND " + "dfloc = NULL;"
				+ "UPDATE FilmPhysique"
				+ "SET etat = UNVERIFIED"
				+ "WHERE id_film = " + data.get(0)
				);
		//UPDATE table_name
		//SET column1 = value1, column2 = value2, ...
		//WHERE condition;
	}
	
	/**
     * Build a query that update a Location and set his state to UNVERIFIED
     * @param data, Arraylist of necessary info : data = {idBluRay, idUser, idAbonne, Price}
     */
	public void returnLocationDamaged(ArrayList<String> data) {// data = {idBluRay, idUser, idAbonne, Price} DAO gère date
		this.query=new StringBuilder("UPDATE HistoriqueLocationPhysique"
				+ "SET dfloc = (SELECT TO_CHAR(SYSDATE, 'DD-MM-YYYY') FROM dual;) "
				+ "WHERE  id_abonne = " + data.get(2) + " AND " + "id_film_physique = " +  data.get(0) + " AND " + "dfloc = NULL;"
				+ "UPDATE FilmPhysique"
				+ "SET etat = UNVERIFIED"
				+ "WHERE id_film = " + data.get(0)
				);
	}
	/**
     * Build a querry that update a state of a phisical movie
     * @param data, Arraylist of necessary info : data = { idAbonne, IdBluray, state }
     */
	public void updateStateFilmPhys(ArrayList<String> data) {
		this.query=new StringBuilder("UPDATE FilmPhysique"
				+ "SET etat =" + data.get(2)
				+ "WHERE ide ="+ data.get(1));
	}
	
	public String getQuery () {
		return this.query.toString();
	}
	
}
