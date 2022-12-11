package fc.machine;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Stub3;
import fc.ComponentFC;
import fc.MediatorFC;
import fc.user.User;
import ui.listener.UIObserver;

/*
 * Represent our Al2000, in concrete, this code should be implement in each Al2000 machine.
 * Machine is a Singleton (there is only one instance of Machine at each time on a Al2000).
 * Contain a BluRayStock, the current user, a DAORequestHandler and a UIObserver [à compléter si besoin]
 */
public class Machine implements ComponentFC {
	
	private int idMachine;
	private static Machine instance; //SINGLETON : pas sûr car si on veut faire plusieurs machine : risque de sauter
	private BluRayStock stock;
	private User currentUser;
	private DAORequestHandler daoRequestHandler;
	private UIObserver uiObserver; 
	
	private Machine() {
		idMachine = 1; //ScanF 
		stock = new BluRayStock();
	}
	
	
	public BluRayStock getStock() {
		return stock;
	}

	public void setStock(BluRayStock stock) {
		this.stock = stock;
	}

	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public DAORequestHandler getDaoRequestHandler() {
		return daoRequestHandler;
	}

	public void setDaoRequestHandler(DAORequestHandler daoRequestHandler) {
		this.daoRequestHandler = daoRequestHandler;
	}

  public static Machine getInstance() {
		if(instance==null)  instance= new Machine();
		return instance;
	}
	
	public void setUIObserver(UIObserver uiObserver) {
		this.uiObserver=uiObserver;
	}
	
	public void subscribeAllListener() {
		/*
		 * TO DO : subscribe all the component to the observer
		 * peut être un peu trop de résponsabilité
		 */
		uiObserver.subscribed("CONNEXION_EVENT_SUB_TYPE", this);

	}
	
	public void openBluRayExit() {
		System.out.println("Les pinces s'ouvres, vous récupérer.");
	}
	
	public void openQRCodeExit() {
		System.out.println("Le QR code sort de la machine.");
	}
	
	public void setDAORequestHandler(DAORequestHandler daoRequestHandler) {
		this.daoRequestHandler=daoRequestHandler;
	}

	public void getStockBluRayFromDAO() {
		if(daoRequestHandler!=null)
			stock.addAll(daoRequestHandler.getStockBluRay(idMachine));
	}
	@Override
	/*
	 * Machine n'est subscribe qu'au ï¿½vï¿½nements de type CONNEXION_EVENT_TYPE qui contient
	 * comme DATA : Id_abo 
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		switch(EVENT_TYPE) {
		case "CONNEXION_SUB_EVENT_TYPE":
			currentUser = daoRequestHandler.getUser(Integer.parseInt(data.get(0))); //BOF BOF
			break;
		default:
			break;
		}
	}
	
	public void launch() {
		/*
		 * Open the first Window
		 * En théorie, Al ne s'éteint jamais, une fois launch le programme tourne jusqu'à la venue d'un technicien (la partie technicien 
		 * n'étant pas encore implémenter, on verra plus tard), pour l'instant, il appuie sur le boutton on/off à l'arrière : On ferme javaSwing
		 */
	}

}
