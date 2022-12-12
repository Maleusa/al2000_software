package ui.stateMachine;

import java.util.ArrayList;


import fc.machine.Stock;
import fc.searchengine.SearchEngine;
import fc.user.User;
import fc.uiInterface.Observer;
import fc.uiInterface.UIObserver;
import ui.SearchRepresentation;
import ui.StockRepresentation;
import ui.UiRepresentation;
import ui.UserRepresentation;

/*
 * TO DO : MAchine � �tat et M�mento
 */
public class StateMachine {
	
	protected String cbID; //Only use during the identification phase
	protected User userModel;
	protected SearchEngine searchModel;
	protected Stock searchResult;
	protected Stock bluRayStock;
	
	protected ArrayList<Page> previousState;
	protected Page currentPage;
	
	protected Observer observer;
	/*
	 *FC_EVENT_TYPE :
	 * 	data is an ArrayList<String>
     *  fc.Machine 
	 *	RENT_BLURAY_EVENT_TYPE , data as : String[0] wich is the BluRayId
	 *	RENT_QRCODE_EVENT_TYPE , data as :
	 *	RETURN_DAMAGED_EVENT_TYPE , data as : String[0] BluRay id
	 *	RETURN_CORRECT_EVENT_TYPE , data as : String[0] BluRay id
	 *	GUEST_IN_EVENT_TYPE , data as : String[0] wich is the credit card
	 *	number
	 *	SIGN_IN_EVENT_TYPE , data as :  String[0] wich is the subscriber
	 *	number
	 *	SIGN_UP_EVENT_TYPE , data as :String[0]: bankCardNumber
	 *								  String[1]: Subscriber Name 
	 *	                              String[2]: Password 
	 *								  String[3]: Adress
	 *	                              String[4]: Blocked Genre
	 *	                              String[5]: Initial Balance  
	 *	SIGN_OUT_EVENT_TYPE , data as : String[0] User id
	 *	RENT_PRINTQRCODE_EVENT_TYPE , data as String[0] rented movie id
	 *	RENT_FREEBLURAY_EVENT_TYPE , data as String[0] rented movie id
	 *	
	 *	fc.search
	 *	SEARCH_BY_TAG , data as String[n] = keyword n
	 *	SEARCH_NO_TAG , data as String[n] = keyword n
	 *	
	 *
	 *	fc.User
	 *	UPDATE_SUBSCRIBER_EVENT_TYPE , data as :String[0]: Subscriber Name 
	 *	                                        String[1]: Password 
	 *	                                        String[2]: Adress
	 *	                              			String[3]: Blocked Genre
	 *	                              			String[4]: Initial Balance 
	 *	AUTHENTIFICATION_EVENT_TYPE , data as : String [0] User password
	 *	RENT_BLURAY_EVENT_TYPE , data as : String[0] BluRay id
	 *	RETURN_DAMAGED_EVENT_TYPE , data as : String[0] BluRay id
	 *	RETURN_CORRECT_EVENT_TYPE , data as : String[0] BluRay id
	 *	QRCODE_PAIE_EVENT_TYPE , data as : String[0] BluRay id
	 *	                                   String[1] User id
	 *	REFOUND_TECHNICAL_EVENT_TYPE , data as : String[0] UserId to refound
	 *
	 *GUI_EVENT_TYPE :
	 *	TO_MAIN_PAGE
	 *	TO_ID_PAGE
	 *	TO_PREVIOUS_PAGE : remove len-1 puis devient le new len-1
	 *	TO_SIGNUP_PAGE
	 *	TO_WELCOME_PAGE
	 *	TO_USER_SPACE
	 *	TO_CHOOSE_MOVIE
	 */
	
	public StateMachine() {
		previousState = new ArrayList<>();
	}
	
	public User getUserModel() {
		return userModel;
	}

	public SearchEngine getSearchModel() {
		return searchModel;
	}

	public Stock getSearchResult() {
		return searchResult;
	}

	public Stock getBluRayStock() {
		return bluRayStock;
	}

	public ArrayList<Page> getPreviousState() {
		return previousState;
	}

	public Page getCurrentPage() {
		return currentPage;
	}

	public Observer getObserver() {
		return observer;
	}

	public void reboot() {
		previousState = new ArrayList<>();
	}
	
	public void changeState(String EVENT) {
		previousState.add(currentPage);
		currentPage.changeState(EVENT);
	}
	
	public void setUIObserver(Observer observer) {
		this.observer=observer;
	}
	
	public void notifyObserver(String EVENT,ArrayList<String> data) {
			observer.notify(EVENT, data);
	}
	
	public void repaint() {
		currentPage.repaint();
	}
	
	public void setCurrentPage(Page page) {
		this.currentPage=page;
	}



	public String getCbID() {
		return cbID;
	}

	public Page returnToPrevious() {
		return previousState.remove(previousState.size()-1);
	}

	public void setCbID(String cbID) {
		this.cbID = cbID;
	}
	
	
}
