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
		
		this.query = new StringBuilder("select title, realisateur, acteurs, descript, anneesortie, genre_un, genre_deux, genre_trois, genre_quatre, genre_cinq, genre_six, genre_sept, affiche, fp.ide, etat"
				+ " from LesFilms f, stockfilmsphysiques s, AL2000s a, filmphysique fp"
				+ "	where f.ide = fp.id_film AND s.nmr_stock=a.ide AND s.ide_film_physique=fp.ide AND a.ide=" +idAl2000);
				
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
				
				if (!(list2.get(list2.size()-1).equals(tag))) {
					this.query.append(" OR ");
					
				}
				else {
					this.query.append(")");
				}
			}

		}
	}
	
	public void insertLocationDemat(ArrayList<String> l) {
        this.query=new StringBuilder("INSERT INTO HistoriqueLocation"
                + "VALUES ("+ l.get(0)+ ", "+ l.get(1)+", "+ l.get(2)+", "+ l.get(3)+", NULL)");
    }
    
    public void insertLocationPhys(ArrayList<String> l) {
        this.query=new StringBuilder("INSERT INTO HistoriqueLocation"
                + "VALUES ("+ l.get(0)+ ", "+ l.get(1)+", "+ l.get(2)+", "+ l.get(3)+", NULL)");
    }
    
    public void updateClient(ArrayList<String> data) {
        // TODO Auto-generated method stub
        this.query=new StringBuilder("DELETE FROM TagPreferences t"
                + "WHERE t.ide=value;");
        
        for (int i = 0; i < data.get(i).length(); i++) {////NE MARCHE PAS c'est normal tkt
            this.query.append("INSERT INTO TagPreferences VALUES (value0, value1);");
        }
    }
	
	public String getQuery () {
		return this.query.toString();
	}
	
}
