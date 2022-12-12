package fc.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import fc.ComponentFC;
import fc.constants.MovieState;
import fc.machine.*;

public abstract class Historic{
	/*
	 * Act as if it was a two side map : dates(i)<->rentals(i)
	 */
	private class RentalsDate{
		private String startDate;
		private String endDate;
	}
	protected ArrayList<RentalsDate> dates;
	protected ArrayList<Rentable> rentals;
	
	protected Historic() {
		dates=new ArrayList<RentalsDate>();
		rentals=new ArrayList<Rentable>();
	}
	
	public ArrayList<String> getRepresentation(){
		ArrayList<String> representation = new ArrayList<>();
		for(int i=0; i<rentals.size(); i++) //Un peu ignoble car c'est le fc qui met les : et to et ça devrait être l'ui
			representation.add(rentals.get(i).getMovie().getTitle().toString()+" : "+dates.get(i).startDate+" to "+dates.get(i).endDate);
		return representation;
	}
	
	public boolean isRented(int idBluRay) {
		for(Rentable movie : rentals)
			if(movie instanceof BluRay) {
				BluRay bluray = (BluRay) (movie); //C'est dégueu il vaut mieux que je passe par un test avec date début et fin comme ça générique
				if(bluray.getId()==idBluRay && bluray.getState()==MovieState.RENTED) return true;
			}
		return false;
	}
	
	public void addRent(Rentable movie, String startDate, String endDate) {
		RentalsDate date = new RentalsDate();
		date.startDate=startDate;
		date.endDate=endDate;
		
		dates.add(date);
		rentals.add(movie);
	}
}
