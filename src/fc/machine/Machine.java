package fc.machine;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;

import dao.Stub3;
import fc.ComponentFC;
import fc.searchengine.SearchEngine;
import fc.uiInterface.Observer;
import fc.uiInterface.UIObserver;
import fc.user.User;
import ui.ChooseMovie;
import ui.Welcome;
import ui.stateMachine.StateMachine;

/*
 * Represent our Al2000, in concrete, this code should be implement in each Al2000 machine.
 * Machine is a Singleton (there is only one instance of Machine at each time on a Al2000).
 * Contain a BluRayStock, the current user, a DAORequestHandler and a UIObserver [à compléter si besoin]
 */
public class Machine implements ComponentFC {
	
	private int idMachine;
	private static Machine instance; //SINGLETON : pas sûr car si on veut faire plusieurs machine : risque de sauter
	private BluRayStock stock;
	private QRCodeStock searchResultStock;
	private User currentUser;
	private DAORequestHandler daoRequestHandler;
	private Observer uiObserver; 
	private SearchEngine searchEngine;
	private StateMachine stateMachine;
	//private EventListenerDAO




	private Machine() {
		idMachine = 1; //ScanF 
		stock = new BluRayStock();
		searchResultStock = new QRCodeStock();
	}
	
	


	public StateMachine getStateMachine() {
		return stateMachine;
	}

	public void setStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
	}
	
	public SearchEngine getSearchEngine() {
		return searchEngine;
	}



	public void setSearchEngine(SearchEngine searchEngine) {
		this.searchEngine = searchEngine;
	}



	public QRCodeStock getSearchResultStock() {
		return searchResultStock;
	}


	public void setSearchResultStock(QRCodeStock searchResultStock) {
		this.searchResultStock = searchResultStock;
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
		uiObserver.subscribe("GUEST_IN_EVENT_TYPE", this);
		uiObserver.subscribe("SIGN_IN_EVENT_TYPE", this);
		uiObserver.subscribe("SIGN_UP_EVENT_TYPE", this);
		uiObserver.subscribe("SIGN_OUT_EVENT_TYPE", this);
		uiObserver.subscribe("RENT_PRINTQRCODE_EVENT_TYPE", this);
		uiObserver.subscribe("RENT_FREEBLURAY_EVENT_TYPE", this);
		
		uiObserver.subscribe("RENT_QRCODE_EVENT_TYPE", searchResultStock);
		uiObserver.subscribe("SEARCH_BY_TAG", searchEngine);
		uiObserver.subscribe("SEARCH_NO_TAG", searchEngine);
	}
	
	public void openBluRayExit(BluRay bluray) {
		System.out.println("Les pinces s'ouvres, vous récupérer : "+bluray.getMovie().getTitle());
	}
	
	public void openQRCodeExit(QRCode qrCode) {
		System.out.println("Le QR code sort de la machine : "+qrCode.getQRCode());
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
	 * 
	 * EVENT_TYPE :
	 * 	GUEST_IN_EVENT_TYPE (data = numCB)
	 * 	SIGN_IN_EVENT_TYPE (data = numAbo)
	 * 	SIGN_UP_EVENT_TYPE (data = all signUp info)
	 * 	SIGN_OUT_EVENT_TYPE
	 * 	RENT_PRINTQRCODE_EVENT_TYPE
	 * 	RENT_FREEBLURAY_EVENT_TYPE
	 * 	
	 * 
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		switch(EVENT_TYPE) {
			case "GUEST_IN_EVENT_TYPE":
				currentUser = daoRequestHandler.getUserFromCB(Integer.parseInt(data.get(0)));
				break;
			case "SIGN_IN_EVENT_TYPE":
				currentUser = daoRequestHandler.getUser(Integer.parseInt(data.get(0))); //BOF BOF
				//TODO uiObserver.subscribe(EVENT_TYPE, currentUser)
				break;
			case "SIGN_UP_EVENT_TYPE":
				//daoEventManager.notify(SIGNUP_EVENT, data)
				update("SIGN_OUT_EVENT_TYPE", data);
				break;
			case "SIGN_OUT_EVENT_TYPE":
				System.out.println("Here is your card"+currentUser.getCardNumber()+".");
				//Pour chaque abo du user on l'unsubscribe
				currentUser = null;
				uiObserver.unsubscribe("RENT_QRCODE_EVENT_TYPE", searchResultStock);
				searchResultStock = null;
				break;
			case "RENT_PRINTQRCODE_EVENT_TYPE":
				openQRCodeExit(searchResultStock.getRentableMovies().get(0));
				update("SIGN_OUT_EVENT_TYPE", data);
				break;
			case "RENT_FREEBLURAY_EVENT_TYPE":
				openBluRayExit(stock.getRentableBluRay(Integer.parseInt(data.get(0))));
				update("SIGN_OUT_EVENT_TYPE", data);
				break;
			default:
				break;
		}
	}
	
	public void launch() {
		stock = new BluRayStock();
		searchResultStock = new QRCodeStock();
		stock.addAll(daoRequestHandler.getStockBluRay(0));
		JFrame jFrame;
		jFrame = new JFrame("AL2000");
    	Welcome fw = new Welcome(jFrame,stateMachine);
    	stateMachine.setCurrentPage(fw);
    	//Initialisation du JFrame
    	jFrame.setSize(900, 700);
    	jFrame.setLocationRelativeTo(null);
    	jFrame.setVisible(true);
    	
    	jFrame.add(fw);
	}

}
