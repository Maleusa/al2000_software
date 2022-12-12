package test;
import fc.listener.UIObserver;
import fc.machine.DAORequestHandlerMachine;
import fc.machine.Machine;
public class Main {

	public static void main(String[] args) {
		Machine al2000 = Machine.getInstance();
		UIObserver uiObserver = null; //= new ....
		/*
		 * Partie DAO
		 */
		DAORequestHandlerMachine daoRequestHandler = new DAORequestHandlerMachine();
		al2000.setDAORequestHandler(daoRequestHandler);
		al2000.getStockBluRayFromDAO();

		al2000.setUIObserver(uiObserver);
		al2000.subscribeAllListener();
		
		al2000.launch();
	}

}
