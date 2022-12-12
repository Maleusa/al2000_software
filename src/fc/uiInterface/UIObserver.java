package fc.uiInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fc.ComponentFC;

public class UIObserver implements Observer {
	
	/*
	 * EVENT_TYPE :
	 * data is an ArrayList<String>
     *  fc.Machine 
	 *	RENT_BLURAY_EVENT_TYPE , data as : String[0] wich is the BluRayId
	 *	RENT_QRCODE_EVENT_TYPE , data as :
	 *	RETURN_DAMAGED_EVENT_TYPE , data as : String[0] BluRay id
	 *	RETURN_CORRECT_EVENT_TYPE , data as : String[0] BluRay id
	 *	GUEST_IN_EVENT_TYPE , data as : String[0] wich is the credit card
	 *	number
	 *	SIGN_IN_EVENT_TYPE , data as :  String[0] wich is the subscriber
	 *	number
	 *	SIGN_UP_EVENT_TYPE , data as :String[0]: Subscriber Name 
	 *	                              String[1]: Password 
	 *	                              String[2]: Blocked Genre
	 *	                              String[3]: Initial Balance  
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
	 *	                                        String[2]: Blocked Genre
	 *	                                        String[3]: Current Balance 
	 *	AUTHENTIFICATION_EVENT_TYPE , data as : String [0] User password
	 *	RENT_BLURAY_EVENT_TYPE , data as : String[0] BluRay id
	 *	RETURN_DAMAGED_EVENT_TYPE , data as : String[0] BluRay id
	 *	RETURN_CORRECT_EVENT_TYPE , data as : String[0] BluRay id
	 *	QRCODE_PAIE_EVENT_TYPE , data as : String[0] BluRay id
	 *	                                   String[1] User id
	 *	REFOUND_TECHNICAL_EVENT_TYPE , data as : String[0] UserId to refound
	 */

	public static String RENT_BLURAY_EVENT_TYPE = "RENT_BLURAY_EVENT_TYPE";
	
	public Map<String,ArrayList<ComponentFC>> subscribed;
	
	public UIObserver() {
		subscribed = new HashMap<String, ArrayList<ComponentFC>>();
	}
	
	public void subscribe(String PAGE_EVENT_TYPE, ComponentFC component) {
		if(!subscribed.containsKey(PAGE_EVENT_TYPE))
			subscribed.put(PAGE_EVENT_TYPE, new ArrayList<ComponentFC>());
		if(!subscribed.get(PAGE_EVENT_TYPE).contains(component))
			subscribed.get(PAGE_EVENT_TYPE).add(component);
	}
	
	public void unsubscribe(String PAGE_EVENT_TYPE, ComponentFC component) {
		if(subscribed.containsKey(PAGE_EVENT_TYPE) && subscribed.get(PAGE_EVENT_TYPE).contains(component))
			subscribed.get(PAGE_EVENT_TYPE).remove(component);
	}
	
	public void notify(String PAGE_EVENT_TYPE, ArrayList<String> data) {
		for(ComponentFC component : subscribed.get(PAGE_EVENT_TYPE))
			component.update(PAGE_EVENT_TYPE, data);
	}

	

}
