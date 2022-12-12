package fc.machine;

import java.util.ArrayList;
import java.util.HashMap;

import fc.searchengine.GenreTag;
import fc.searchengine.Tag;
import fc.user.Guest;
import fc.user.Subscriber;
import fc.user.User;
import fc.user.UserHistoric;
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
		User thibaud=new Subscriber(userID);
		thibaud.setAdress("12 rue des lapins");
		thibaud.setHistoric(new UserHistoric());
		thibaud.setName("Thibaud");
		thibaud.setPassword("1234");
		thibaud.setRestrictedGenre(new ArrayList<GenreTag>());
		return thibaud;
	}

	@Override
	public ArrayList<QRCode> getRequestedDigitalMovies(HashMap<String, ArrayList<Tag>> requests) {
		QRCode thebatman = new QRCode();
		QRCode badboys = new QRCode();
		QRCode therock = new QRCode();
		
		
		ArrayList<QRCode> qrCodes = new ArrayList<QRCode>();
		ArrayList<Movie> movies = movieCreator();
		qrCodes.add(thebatman);
		qrCodes.add(badboys);
		qrCodes.add(therock);
		
		for(int i=0; i<3; i++) {
			
			qrCodes.get(i).setMovie(movies.get(i));
			
		}
		return qrCodes;
	}


	
	private ArrayList<Movie> movieCreator(){
		ArrayList<Movie> movies = new ArrayList<Movie>();
		Movie moviethebatman = new Movie();
		Movie moviebadboys = new Movie();
		Movie movietherock = new Movie();
		Movie moviegattaca =new Movie();
		
		moviethebatman.setTitle("The batman");
		moviethebatman.setDirectors("Matt Reeves");
		moviethebatman.setActors("Robert Pattinson;Zo� Kravitz;Paul Dano");
		moviethebatman.setDescription("The batman, il bien et dure trois heures.");
		moviethebatman.setUrl("https://m.media-amazon.com/images/I/81DGyn3r62L.jpg");
		moviebadboys.setTitle("Bad boys");
		moviebadboys.setDirectors("Micheal Bay");
		moviebadboys.setActors("Will Smith;Martin Lawrence;Joe Pantaliano");
		moviebadboys.setDescription("Bad boys, bad boys what you gonna do when ...");
		moviebadboys.setUrl("https://www.ecranlarge.com/media/cache/1600x1200/uploads/image/001/121/l3daia6meglcxxdoun8mqgfms7w-846.jpg");
		movietherock.setTitle("The Rock");
		movietherock.setDirectors("Micheal Bay");
		movietherock.setActors("Sean Connery;Nicolas Cage;Ed Harris");
		movietherock.setDescription("The Rock, le meilleur film de Micheal Bay, une suite de James Bond ??");
		movietherock.setUrl("https://upload.wikimedia.org/wikipedia/en/8/82/The_Rock_%28movie%29.jpg");
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
		User thibaud=new Guest(numCB);
		thibaud.setAdress("");
		thibaud.setHistoric(new UserHistoric());
		thibaud.setName("Guest");
		thibaud.setPassword("");
		thibaud.setRestrictedGenre(new ArrayList<GenreTag>());
		return thibaud;
	}

}
