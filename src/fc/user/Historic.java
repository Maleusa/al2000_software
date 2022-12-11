package fc.user;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import fc.ComponentFC;
import fc.machine.*;

public abstract class Historic{
	protected HashMap<String, Rentable> historic;
	
	protected Historic() {
		historic = new HashMap<String, Rentable>();
	}
	
	public Rentable getFilm(String date) {
		return historic.get(date);
	}
	
	public void ajouterLocation(Rentable film) {
		String date = new SimpleDateFormat("dd-mm-yyyy").format(new Date());
		historic.put(date, film);
	}
}
