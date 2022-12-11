package fc.user;

import java.util.ArrayList;

public class Subscriber extends User {

	
	public Subscriber(int id) {
		super(id);
	}
	@Override
	/*
	 * TODO : Ajouter le notify() vers listenerDAO lors des modifs
	 * 
	 * EVENT_TYPE :
	 * 	UPDATE_SUBSCRIBER_EVENT_TYPE
	 * 	AUTHENTIFICATION_EVENT_TYPE
	 * 
	 * 
	 * 	RENT_BLURAY_EVENT_TYPE
	 * 	RETURN_DAMAGED_EVENT_TYPE
	 * 	RETURN_CORRECT_EVENT_TYPE
	 * 	QRCODE_PAIE_EVENT_TYPE
	 * 
	 * 	REFOUND_TECHNICAL_EVENT_TYPE
	 * 	
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		// TODO Auto-generated method stub
		
	}

}
