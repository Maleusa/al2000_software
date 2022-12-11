package ui.stateMachine;

import java.util.ArrayList;

import ui.SearchRepresentation;
import ui.StockRepresentation;
import ui.UiRepresentation;
import ui.UserRepresentation;
import ui.listener.Observer;
import ui.listener.UIObserver;

/*
 * TO DO : MAchine � �tat et M�mento
 */
public class StateMachine {
	
	protected UiRepresentation user;
	protected UiRepresentation bluRayStock;
	protected UiRepresentation searchResult;
	
	protected ArrayList<Page> previousState;
	protected Page currentPage;
	
	protected Observer observer;
	
	public static String USER_UPDATE="USER_UPDATE";
	public static String STOCK_UPDATE="STOCK_UPDATE";
	public static String SEARCH_UPDATE="SEARCH_UPDATE";
	public StateMachine() {
		currentPage=new DefaultPage();
		user= new UserRepresentation();
		bluRayStock=new StockRepresentation();
		searchResult = new SearchRepresentation();
		currentPage.addStateMachine(this);
	}
	
	public void updateRepresentation(String EVENT, UiRepresentation representation) {
		switch(EVENT){
			case "USER_UPDATE":
				this.user=representation;
				repaint();
				break;
			case "STOCK_UPDATE":
				this.bluRayStock=representation;
				repaint();
				break;
			case "SEARCH_UPDATE":
				this.searchResult=representation;
				repaint();
				break;
			default:
				break;
		}
			 
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
	
	
}
