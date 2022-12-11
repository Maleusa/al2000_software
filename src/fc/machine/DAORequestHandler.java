package fc.machine;

import java.util.ArrayList;
import java.util.HashMap;

import fc.user.User;
import fc.searchengine.*;

/*
 * Our Interface between the FC and the DAO part of the project
 * 
 */
public interface DAORequestHandler {

	/*
	 * Return the list of physical movies (BluRay) inside the Al2000 given 
	 * The list is return from the database by [DAOLinkName]
	 */
	public ArrayList<BluRay> getStockBluRay(int idAl);
	
	public User getUser(int userID); //Il faut étudier car en théorie c'est lui qui met direct l'abo dedans
									 //Mais l'ui à besoin de la dao avant pour proposer la selection
	
	public ArrayList<QRCode> getRequestedDigitalMovies(HashMap<String, ArrayList<Tag>> requests); //On doit vérifier le paramêtre d'envoie.
	
}
