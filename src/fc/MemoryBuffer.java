package fc;

import java.util.HashMap;

import fc.tag.Tag;

public class MemoryBuffer extends Thread{
	private HashMap<Object,Integer> Buffer;
	
	public MemoryBuffer() {
		Buffer=new HashMap<Object,Integer>();
	}

	public boolean isIn(Object o) {
		if (Buffer.containsValue(o)) return true;
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
		return Buffer;
	}

	public void setBuffer(HashMap<Object, Integer> buffer) {
		Buffer = buffer;
	}
}
