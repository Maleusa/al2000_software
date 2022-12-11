package fc.machine;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import dao.Stub3;
import fc.ComponentFC;
import fc.constants.MovieState;
import fc.searchengine.Tag;
import fc.user.User;

/*
 * An implementation of our DAORequestHandler. Take the [DAOLinkerName] in attribute
 * Is dependant from the return type from [DAOLinkerName]
 */
public class DAORequestHandlerMachine implements DAORequestHandler {
	
	private Stub3 dao;
	public DAORequestHandlerMachine() {
		dao = new Stub3();
	}

	@Override
	public ArrayList<BluRay> getStockBluRay(int idAl) {
		ArrayList<BluRay> stock = new ArrayList<BluRay>();
		
		ArrayList<HashMap<String,String>> tableMovie = dao.getStockBluRay(idAl);
		for(HashMap<String,String> movieInformations : tableMovie) {
			BluRay bluray = new BluRay();
			Movie movie = movieCreator(movieInformations);
			
			bluray.setId(Integer.parseInt(movieInformations.get("id")));
			bluray.setState(MovieState.valueOf(movieInformations.get("state"))); //ATTENTION LES STATES DOIVENT RESPECTER ABSOLUMENT LA CASE ET LE TEXTE DE L'ENUM
			bluray.setMovie(movie);
			
			stock.add(bluray);
		}
		return stock;
	}
	
	@Override
	public ArrayList<QRCode> getRequestedDigitalMovies(HashMap<String, ArrayList<Tag>> request) {
		ArrayList<QRCode> qrCodes = new ArrayList<QRCode>();
		
		ArrayList<HashMap<String,String>> tableMovie = dao.getDigitalMovies(request);
		for(HashMap<String,String> movieInformations : tableMovie) {
			QRCode qrCode = new QRCode();
			Movie movie = movieCreator(movieInformations);
			qrCode.setMovie(movie);
		}
		
		return qrCodes;
	}

	private Movie movieCreator(HashMap<String,String> movieInformations) {
		Movie movie = new Movie();
		/*
		 * ATTENTION à la rédaction dans la hashmap : avec/sans majuscule, anglais, etc 
		 */
		movie.setTitle(movieInformations.get("title"));
		movie.setDirector(movieInformations.get("director"));
		movie.setActors(movieInformations.get("actors"));
		movie.setDescription(movieInformations.get("description"));
		try {
			movie.setRelease(new SimpleDateFormat("yyyy-mm-dd").parse(movieInformations.get("date")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return movie;
	}

	/*
	 * Pour l'instant : cet objet sera en écoute de l'ui et lui donnera les informations nécessaires
	 */
	@Override
	public User getUser(int userID) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		// TODO Auto-generated method stub
	}

	
	
	
}
