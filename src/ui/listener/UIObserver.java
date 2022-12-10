package ui.listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fc.ComponentFC;

public class UIObserver {

	Map<String,ArrayList<ComponentFC>> subscribed = new HashMap<String, ArrayList<ComponentFC>>();
	
	public void subscribed(String PAGE_EVENT_TYPE, ComponentFC component) {
		if(!subscribed.containsKey(PAGE_EVENT_TYPE))
			subscribed.put(PAGE_EVENT_TYPE, new ArrayList<ComponentFC>());
		if(!subscribed.get(PAGE_EVENT_TYPE).contains(component))
			subscribed.get(PAGE_EVENT_TYPE).add(component);
	}
	
	public void unsubscribed(String PAGE_EVENT_TYPE, ComponentFC component) {
		if(subscribed.containsKey(PAGE_EVENT_TYPE) && subscribed.get(PAGE_EVENT_TYPE).contains(component))
			subscribed.get(PAGE_EVENT_TYPE).remove(component);
	}
	
	public void notify(String PAGE_EVENT_TYPE, ArrayList<String> data) {
		for(ComponentFC component : subscribed.get(PAGE_EVENT_TYPE))
			component.update(PAGE_EVENT_TYPE, data);
	}

}
