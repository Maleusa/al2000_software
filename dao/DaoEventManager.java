package dao;

import java.util.ArrayList;
import java.util.HashMap;

public class DaoEventManager {
    
    private HashMap<EventType, ArrayList<ListenerAbstract>> listeners;
    
    void subscribe(EventType e, ListenerAbstract l) {
        listeners.get(e).add(l);
        }
    
    void unsubscribe(EventType e, ListenerAbstract l) {
        listeners.get(e).remove(l);
    }
    
    void notify(EventType e, ArrayList<String> data) {
        for (int i = 0; i < listeners.get(e).size(); i++) {
            listeners.get(e).get(i).update(data);
        }
    }
}