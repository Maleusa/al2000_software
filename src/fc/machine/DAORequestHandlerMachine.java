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
		 * ATTENTION � la r�daction dans la hashmap : avec/sans majuscule, anglais, etc 
		 */
		movie.setId(Integer.parseInt(movieInformations.get("id")));
		movie.setTitle(movieInformations.get("title"));
		movie.setDirectors(movieInformations.get("director"));
		movie.setActors(movieInformations.get("actors"));
		movie.setDescription(movieInformations.get("description"));
		movie.setYear(movieInformations.get("date"));
		return movie;
	}

	/*
	 * Pour l'instant : cet objet sera en �coute de l'ui et lui donnera les informations n�cessaires
	 */
	@Override
	public User getUser(int userID) {
		//Return subscriber
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserFromCB(int numCB) {
		//return guest 
		// TODO Auto-generated method stub
		return null;
	}

	
	
	
}
