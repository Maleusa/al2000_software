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
	
	protected User userModel;
	protected SearchEngine searchModel;
	protected Stock searchResult;
	protected Stock bluRayStock;
	
	protected ArrayList<Page> previousState;
	protected Page currentPage;
	
	protected Observer observer;
	
	public static String USER_UPDATE="USER_UPDATE";
	public static String STOCK_UPDATE="STOCK_UPDATE";
	public static String SEARCH_UPDATE="SEARCH_UPDATE";
	public StateMachine() {
		currentPage=new DefaultPage();
		
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
