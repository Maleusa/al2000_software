package fc.machine;

import java.util.ArrayList;
import java.util.Scanner;

import dao.Stub3;
import fc.ComponentFC;
import fc.MediatorFC;
import fc.user.User;

public class Machine implements ComponentFC {
	
	private int idMachine;
	private static Machine instance;
	private BluRayStock stock;
	private User currentUser;
	private DAORequestHandler daoRequestHandler;
	
	private Machine() {
		idMachine = 1; //SCANF 
		stock = new BluRayStock();
		Stub3 dao = new Stub3();
		dao.getStockBluRay(idMachine);
	}
	
	public void ouvrirPince() {
		System.out.println("Les pinces s'ouvres, vous récupérer.");
	}
	
	public void ouvrirQrCode() {
		System.out.println("Le QR code sort de la machine.");
	}
	
	public Machine getInstance() {
		if(instance==null)  this.instance= new Machine();
		return this.instance;
	}
	
	public void setDAORequestHandler(DAORequestHandler daoRequestHandler) {
		this.daoRequestHandler=daoRequestHandler;
	}

	@Override
	/*
	 * Machine n'est subscribe qu'au évènements de type CONNEXION_EVENT_TYPE qui contient
	 * comme DATA : Id_abo 
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		
		
		dao.cardInfo(0);
	}

}
