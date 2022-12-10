package fc;

import java.util.HashMap;

import fc.tag.Tag;
import fc.user.Abonnement;
import fc.user.CarteAbonnement;
import fc.user.User;

public class MemoryBuffer{ // extends Thread{
	private HashMap<Object,Integer> buffer;
	
	public MemoryBuffer() {
		buffer=new HashMap<Object,Integer>();
	}

	public boolean isIn(Object o) {
		if (buffer.containsValue(o)) return true;
		else return false;
		
	}
	
	public Object getObject(Tag tag) {
		Object e=new Object();
		return  e;
	}
	public Object getObject(String aboId) {
		Object e=new Object();
		return  e;
	}
	public Object getObject(int numCB) {
		Object e=new Object();
		return  e;
	}
	public HashMap<Object, Integer> getBuffer() {
		return buffer;
	}

	public void setBuffer(HashMap<Object, Integer> buffer) {
		this.buffer = buffer;
	}
	
	public void add(Object o, Integer id) {
		buffer.put(o, id);
	}
	
	public boolean deleteSubscriber(int id) {
		//Send request to dao to delete subscriber n°id
		//return true if dao return true to suppression
		
		//return dao.deleteSubscriber(int id)

		return true;
	}
	
	public boolean modificationOnSubscriber(Abonnement s) {
		//Send 
		return true;
	}
	
	public boolean logOut() {
		//dao.update
		return true;
	}
}
