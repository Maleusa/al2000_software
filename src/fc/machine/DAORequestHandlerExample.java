package fc.machine;

import java.util.ArrayList;
import java.util.HashMap;

import fc.searchengine.Tag;
import fc.user.User;
import fc.constants.MovieState;
public class DAORequestHandlerExample implements DAORequestHandler{
	
	public DAORequestHandlerExample() {
		
	}
	
	@Override
	public ArrayList<BluRay> getStockBluRay(int idAl) {
		BluRay thebatman = new BluRay();
		BluRay badboys = new BluRay();
		BluRay therock = new BluRay();
		
		ArrayList<Movie> movies = movieCreator();
		
		ArrayList<BluRay> blurays = new ArrayList<BluRay>();
		blurays.add(thebatman);
		blurays.add(badboys);
		blurays.add(therock);
		
		for(int i=0; i<3; i++) {
			blurays.get(i).setId(i);
			blurays.get(i).setMovie(movies.get(i));
			blurays.get(i).setState(MovieState.RENTABLE);
		}
		
		return blurays;
	}

	@Override
	public User getUser(int userID) {
		
		return null;
	}

	@Override
	public ArrayList<QRCode> getRequestedDigitalMovies(HashMap<String, ArrayList<Tag>> requests) {
		ArrayList<QRCode> qrCodes = new ArrayList<QRCode>();
		ArrayList<Movie> movies = movieCreator();
		
		return null;
	}


	
	private ArrayList<Movie> movieCreator(){
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Movie moviethebatman = new Movie();
		Movie moviebadboys = new Movie();
		Movie movietherock = new Movie();
		
		moviethebatman.setTitle("The batman");
		moviethebatman.setDirectors("Matt Reeves");
		moviethebatman.setActors("Robert Pattinson;Zoë Kravitz;Paul Dano");
		moviethebatman.setDescription("The batman, il bien et dure trois heures.");
		
		moviebadboys.setTitle("Bad boys");
		moviebadboys.setDirectors("Micheal Bay");
		moviebadboys.setActors("Will Smith;Martin Lawrence;Joe Pantaliano");
		moviebadboys.setDescription("Bad boys, bad boys what you gonna do when ...");
		
		movietherock.setTitle("The Rock");
		movietherock.setDirectors("Micheal Bay");
		movietherock.setActors("Sean Connery;Nicolas Cage;Ed Harris");
		movietherock.setDescription("The Rock, le meilleur film de Micheal Bay, une suite de James Bond ??");
		
		/*
		 * AJOUTER URL
		 */
		movies.add(moviethebatman);
		movies.add(moviebadboys);
		movies.add(movietherock);
		
		
		return movies;
	}

	@Override
	public User getUserFromCB(int numCB) {
		// TODO Auto-generated method stub
		return null;
	}

}
