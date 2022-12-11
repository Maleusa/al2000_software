package dao;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fc.tag.*;

public class QueryBuilder {
	
	private StringBuilder query;
	
	public QueryBuilder() {
		
	}
	
	public void getStockBluRay(int idAl2000) {
		
		this.query = new StringBuilder("select title,real,acteur,decript,anneesortie,image,filmphysique.ide,etat,tag1,tag2,tag3,tag4,tag5,tag6,tag7,tag8,tag9,tag10\r\n"
				+ "\r\n"
				+ "from Lesfilms f, stockfilmphysique s, AL2000 a, filmphysique fp, genres g\r\n"
				+ "\r\n"
				+ "where f.ide=fp.id_film AND s.nmr_stock=a.id_stock_film_physique AND g.id_film=f.ide AND s.ide=fp.ide\r\n"
				+ "    AND a.ide=#####");
				
	}
	
	
	
	public void getCardInfo(int ClientId) {
		
		this.query = new StringBuilder("select * from Clients where IDE = '" + ClientId + "'");
		
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
	
}
