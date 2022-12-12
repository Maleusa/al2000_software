package fc.listener;

import java.util.ArrayList;

import fc.ComponentFC;

public interface Observer {

	public void subscribe(String PAGE_EVENT_TYPE, ComponentFC component);
	public void unsubscribe(String PAGE_EVENT_TYPE, ComponentFC component);
	public void notify(String PAGE_EVENT_TYPE, ArrayList<String> data);
}
