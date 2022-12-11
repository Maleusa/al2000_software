package fc.machine;

import java.lang.reflect.Array;
import java.util.ArrayList;
import fc.constants.*;
import fc.ComponentFC;

public class BluRayStock implements ComponentFC {

	private ArrayList<BluRay> rentableStock; //Max Size = 100
	private ArrayList<BluRay> rentedStock;
	private ArrayList<BluRay> unverifiedStock; //Max Size = 10
	
	public BluRayStock() {
		rentableStock = new ArrayList<BluRay>();
		unverifiedStock = new ArrayList<BluRay>();
	}
	
	public void addAll(ArrayList<BluRay> collection) {
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
	@Override
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		// TODO Auto-generated method stub
		
	}
}
