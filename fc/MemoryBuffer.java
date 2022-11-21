package fc;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
		refresh();
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
	
	public boolean flush() {
		associationMap.clear();
		movieBuffer.clear();
		pageBuffer.clear();
		lastUpdate=new Date();
		return true;
	}
	
	public boolean logOut(Object o) {
		refresh();
		if(!updater.push(o)) return false;
		pageBuffer.clear();
		return true;
	}
	
	public boolean modification(Object o) {
		refresh();
		return updater.push(o);
		
	}
	
	public Page getPreviousPage(Page p) {
		refresh();
		for(Entry<Page, Integer> f:pageBuffer.entrySet()) {
			if (f.getKey().getClass()==p.getClass()) {
				f.setValue(constant.Values.USED);
				return f.getKey();
			}
		}
		
		return updater.pull(p);
		
	}
	/**
	 * //TODO Demander à Guillaume si ça marche
	 */
	public void refresh() {
		if(new Date().getTime()-lastUpdate.getTime()<constant.Values.REFRESH_TIMER)return;
		for (Entry<Page, Integer> f:pageBuffer.entrySet()) {
			if(f.getValue()==0)
				pageBuffer.remove(f.getKey());
			else f.setValue(f.getValue()-1);
		}
		for (Entry<Film, Integer> f:movieBuffer.entrySet()) {
			if(f.getValue()==0)
				movieBuffer.remove(f.getKey());
			else f.setValue(f.getValue()-1);
		}
		lastUpdate=new Date();
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
