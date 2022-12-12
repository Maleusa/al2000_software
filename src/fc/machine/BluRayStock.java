package fc.machine;

import java.util.ArrayList;

import fc.constants.*;
import fc.ComponentFC;

/*
 * BluRayStock represent the stock of physical movies in Al2000
 * It contains three differents stock (unknown by the client) wich contains respectively :
 * blurays that are available, thoses that are rented and the unverified's ones
 */

public class BluRayStock implements Stock {



	
	private ArrayList<BluRay> searchStock;
	private ArrayList<BluRay> rentableStock; //Max Size = 100
	private ArrayList<BluRay> rentedStock;
	private ArrayList<BluRay> unverifiedStock; //Max Size = 10 (A warning is trigger on the DataBase if there is more than ten unverified movies)
	
	public BluRayStock() {
		rentableStock = new ArrayList<BluRay>();
		unverifiedStock = new ArrayList<BluRay>();
		rentedStock = new ArrayList<BluRay>();
		searchStock = new ArrayList<BluRay>();
	}
	
	/*
	 * Add all the movies from collection to the stock
	 */

	public BluRay getRentableBluRay(int id) {
		for(BluRay b : rentedStock)
			if(b.getId()==id) return b;
		return null;
	}
	
	public ArrayList<BluRay> getSearchStock() {
		return searchStock;
	}

	public void setSearchStock(ArrayList<BluRay> searchStock) {
		this.searchStock = searchStock;
	}

	public ArrayList<BluRay> getRentableMovies(){
		return rentableStock;
	}

	/*
	 * 
	 * EVENT_TYPE :
	 * 	RENT_BLURAY_EVENT_TYPE
	 * 	RETURN_DAMAGED_EVENT_TYPE
	 * 	RETURN_CORRECT_EVENT_TYPE
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		int id = Integer.parseInt(data.get(0)); //Changera peut �tre si d'autre event
		switch(EVENT_TYPE) {
			case "RENT_BLURAY_EVENT_TYPE":
				for(BluRay bluray : rentableStock)
					if(bluray.getId()==id) {
						bluray.update(EVENT_TYPE, data);
						rentedStock.add(bluray);
						rentableStock.remove(bluray);
					}
				break;
			case "RETURN_DAMAGED_EVENT_TYPE":
				for(BluRay bluray : rentableStock)
					if(bluray.getId()==id) {
						bluray.update(EVENT_TYPE, data);
						unverifiedStock.add(bluray);
						rentedStock.remove(bluray);
					}
				break;
			case "RETURN_CORRECT_EVENT_TYPE":
				for(BluRay bluray : rentableStock)
					if(bluray.getId()==id) {
						bluray.update(EVENT_TYPE, data);
						rentableStock.add(bluray);
						rentedStock.remove(bluray);
					}
				break;
			default:
				break;
		}
		
	}
	


	public ArrayList<BluRay> getUnverifiedStock() {
		return unverifiedStock;
	}
	
	public void setUnverifiedStock(ArrayList<BluRay> unverifiedStock) {
		this.unverifiedStock = unverifiedStock;
	}

	public void addAll(ArrayList<BluRay> collection) {
		/*
		 * BluRays are dispatched between stocks acccording to there state
		 */
		for(BluRay bluray : collection) {
			switch(bluray.getState()) {
			case UNVERIFIED:
				unverifiedStock.add(bluray);
				break;
			case RENTED:
				rentedStock.add(bluray);
				break;
			case RENTABLE:
				rentableStock.add(bluray);
				break;
			default:
				break;
			}
		}
	}

	@Override
	public Stock getStockModel() {
		
		return this;
	}

	

}
