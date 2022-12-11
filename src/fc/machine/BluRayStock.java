package fc.machine;

import java.util.ArrayList;

import fc.constants.*;
import fc.ComponentFC;

/*
 * BluRayStock represent the stock of physical movies in Al2000
 * It contains three differents stock (unknown by the client) wich contains respectively :
 * blurays that are available, thoses that are rented and the unverified's ones
 */
public class BluRayStock implements ComponentFC {



	

	private ArrayList<BluRay> rentableStock; //Max Size = 100
	private ArrayList<BluRay> rentedStock;
	private ArrayList<BluRay> unverifiedStock; //Max Size = 10 (A warning is trigger on the DataBase if there is more than ten unverified movies)
	
	public BluRayStock() {
		rentableStock = new ArrayList<BluRay>();
		unverifiedStock = new ArrayList<BluRay>();
	}
	
	/*
	 * Add all the movies from collection to the stock
	 */
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
	
	public ArrayList<BluRay> getRentableMovies(){
		return rentableStock;
	}

	public void update(String EVENT_TYPE, ArrayList<String> data) {
		// TODO Auto-generated method stub
		
	}
	
	
	public ArrayList<BluRay> getRentableStock() {
		return rentableStock;
	}

	public void setRentableStock(ArrayList<BluRay> rentableStock) {
		this.rentableStock = rentableStock;
	}

	public ArrayList<BluRay> getUnverifiedStock() {
		return unverifiedStock;
	}
	
	public void setUnverifiedStock(ArrayList<BluRay> unverifiedStock) {
		this.unverifiedStock = unverifiedStock;
	}
}
