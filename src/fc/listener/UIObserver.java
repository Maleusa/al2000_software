package fc.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fc.ComponentFC;

public class UIObserver implements Observer {
	
	/*
	 * EVENT_TYPE :
	 * fc.Machine 
	 * 	RENT_BLURAY_EVENT_TYPE with data as : one String wich is the BluRayId
	 * 	RENT_QRCODE_EVENT_TYPE 
	 * 	RETURN_DAMAGED_EVENT_TYPE
	 * 	RETURN_CORRECT_EVENT_TYPE
	 * 	GUEST_IN_EVENT_TYPE (data = numCB)
	 * 	SIGN_IN_EVENT_TYPE (data = numAbo)
	 * 	SIGN_UP_EVENT_TYPE (data = all signUp info)
	 * 	SIGN_OUT_EVENT_TYPE
	 * 	RENT_PRINTQRCODE_EVENT_TYPE
	 * 	RENT_FREEBLURAY_EVENT_TYPE
	 * 	
	 * 	fc.search
	 * 	SEARCH_BY_TAG
	 * 	SEARCH_NO_TAG
	 * 
	 * 	fc.User
	 * 	UPDATE_SUBSCRIBER_EVENT_TYPE
	 * 	AUTHENTIFICATION_EVENT_TYPE
	 * 	RENT_BLURAY_EVENT_TYPE
	 * 	RETURN_DAMAGED_EVENT_TYPE
	 * 	RETURN_CORRECT_EVENT_TYPE
	 * 	QRCODE_PAIE_EVENT_TYPE
	 * 	REFOUND_TECHNICAL_EVENT_TYPE
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
