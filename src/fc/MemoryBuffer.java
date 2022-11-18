package fc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

import dao.DatabaseUpdater;
import fc.movie.Film;
import fc.tag.Tag;
import ui.Page;

public class MemoryBuffer extends Thread{
	private HashMap<Object,Integer> Buffer;
	private HashMap<String,Integer> associationMap;
	private HashMap<Film,Integer> movieBuffer;
	private HashMap<Page,Integer> pageBuffer;
	private DatabaseUpdater updater;
	private Date lastUpdate;
	
	public MemoryBuffer() {
		Buffer=new HashMap<Object,Integer>();
		movieBuffer=new HashMap<Film,Integer>();
		pageBuffer=new HashMap<Page,Integer>();
		associationMap=new HashMap<String,Integer>();
		updater=new DatabaseUpdater();
		lastUpdate=new Date();
		
	}

	public boolean isIn(Object o) {
		if (Buffer.containsValue(o)) return true;
		else return false;
		
	}
	/**
	 * NEED REFACTORING
	 * @param tag
	 * @return arraylist de film contenant le tag
	 */
	public ArrayList<Film> getMovies(Tag tag){
		ArrayList<Film> temp = new ArrayList<Film>();
		int numberOfMoviesByTag;
		int bufferedMovie=0;
		if(associationMap.containsKey(tag.toString())) {
			numberOfMoviesByTag=associationMap.get(tag.toString());
			for(Entry<Film, Integer> f:movieBuffer.entrySet()) {
				if(f.getKey().containsTag(tag)) bufferedMovie++;
			}
			if(bufferedMovie==numberOfMoviesByTag) {
				for(Entry<Film, Integer> f:movieBuffer.entrySet()) {
					if(f.getKey().containsTag(tag)) {
						temp.add(f.getKey());
						f.setValue(constant.Values.USED);
					}
					
					}
				return temp;
				}
			else {
				for(Entry<Film, Integer> f:movieBuffer.entrySet()) {
					if(f.getKey().containsTag(tag)) {
						temp.add(f.getKey());
						
					}
					
				}
				for(Film f:temp) {
					movieBuffer.remove(f);
				}
				temp.clear();
				temp=updater.getMovies(tag);
				for(Film f:temp) {
					movieBuffer.put(f, constant.Values.USED);
				}
				return temp;
			}
		}
		else {
			temp.clear();
			temp=updater.getMovies(tag);
			for(Film f:temp) {
				movieBuffer.put(f, constant.Values.USED);
			}
			associationMap.put(tag.toString(), temp.size());
		}
			
		
		
		
		return temp;
		
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
