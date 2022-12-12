package test;
import fc.uiInterface.UIObserver;
import fc.machine.DAORequestHandlerMachine;
import fc.machine.Machine;

import fc.uiInterface.UIObserver;

public class Main {

	public static void main(String[] args) {
		Machine al2000 = Machine.getInstance();
		UIObserver uiObserver = new UIObserver();
		/*
		 * Partie DAO
		 */
		DAORequestHandlerMachine daoRequestHandler = new DAORequestHandler();
		al2000.setDAORequestHandler(daoRequestHandler);
		al2000.getStockBluRayFromDAO();

		al2000.setUIObserver(uiObserver);
		al2000.subscribeAllListener();
		
		al2000.launch();
	}

}
