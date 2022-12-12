package dao;

import java.util.ArrayList;
import java.util.HashMap;

public class DaoEventManager {
    
    private HashMap<EventType, ArrayList<ListenerAbstract>> listeners;
    /**
    * Construct DaoEventManager and initialize the HashMap of Events / Listeners 
    */
    public DaoEventManager() {
    	listeners = new HashMap<EventType, ArrayList<ListenerAbstract>>();
        listeners.put(EventType.QRCODE_RENT_EVENT_DAO, new ArrayList<ListenerAbstract>());
        listeners.put(EventType.RENT_BLURAY_EVENT_DAO, new ArrayList<ListenerAbstract>());
        listeners.put(EventType.RETURN_CORRECT_EVENT_DAO, new ArrayList<ListenerAbstract>());
        listeners.put(EventType.RETURN_DAMAGED_EVENT_DAO, new ArrayList<ListenerAbstract>());
        listeners.put(EventType.UPDATE_SUBSCRIBER_EVENT_DAO, new ArrayList<ListenerAbstract>());
    }
    
    /**
     * add a Listener l to an Event e
     * @param e
     * @param l
     */
    void subscribe(EventType e, ListenerAbstract l) {
        listeners.get(e).add(l);
    }
    
    /**
     * remove a Listener l to an Event e
     * @param e
     * @param l
     */
    void unsubscribe(EventType e, ListenerAbstract l) {
        listeners.get(e).remove(l);
    }
    
    /**
     * notify all listener from specified event e
     * @param e
     * @param data
     */
    void notify(EventType e, ArrayList<String> data) {
        for (int i = 0; i < listeners.get(e).size(); i++) {
            listeners.get(e).get(i).update(data);
        }
    }
}