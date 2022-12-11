package fc.machine;

import java.util.ArrayList;
import fc.ComponentFC;

public class BluRayStock implements ComponentFC {



	public void setUnverifiedStock(ArrayList<BluRay> unverifiedStock) {
		this.unverifiedStock = unverifiedStock;
	}

	private ArrayList<BluRay> rentableStock; //Max Size = 100
	private ArrayList<BluRay> unverifiedStock; //Max Size = 10
	
	public BluRayStock() {
		rentableStock = new ArrayList<BluRay>();
		unverifiedStock = new ArrayList<BluRay>();
	}
	
	public void addAll(ArrayList<BluRay> collection) {
		
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
	
	
}
