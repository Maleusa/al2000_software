package fc.searchengine;

import java.lang.reflect.InvocationTargetException;

/**
 *Classe abstraite permettant de transformer les descripteurs en
 *tout genre en des objets comparable les uns avec les autres 
 *
 * @author yazid
 *
 */
public abstract class Tag implements Cloneable  {
	private String tag;
	private int prio;
	
	public Tag()  {
	 this.tag="";	
	}
	/**
	 * Constructeur de base d'un tag prenant un string en argument
	 * @param tag
	 */
	public Tag(String tag) {
		
		this.tag=tag;
	}
	public Tag(Tag tag) {
		this.tag=tag.tag;
		this.prio=tag.prio;
	}
	
	
	
	/**
	 * Constructeur secondaire d'un Tag si l'on veut modifier exceptionnelement la priorité d'un tag malgré
	 * sa classe réel
	 * @param tag
	 * @param prio
	 */
	public Tag(String tag,int prio) {
		this(tag);
		this.prio=prio;
	}
	public abstract String query();
	/**
	 * Redefinition de equals pour les Tag, les tag sont égaux ssi ils ont
	 * la même classe et le même string tag
	 */
	public boolean equals(Object o) {
		if((o instanceof Tag)==false)return false;
		if(this.getClass()==o.getClass() && this.getTag()==((Tag) o).getTag())
			return true;
		return false;	
	}
	/**
	 * Compares this with
	 * @param o
	 * @return <0 if this is prio over o >0 if o is prio over this ==0 if they are the same
	 */
	public int compareTo(Object o) {
		if(this.equals(o)) {
			return 0;
		}
		else if(getClass()==o.getClass()) {
			return this.getTag().compareToIgnoreCase(((Tag)o).getTag());
		}
		else return this.getPrio()-((Tag)o).getPrio();
		
		
	}
	/**
	 * refactor with ignore case //TODO
	 * @param t
	 * @return
	 */
	public boolean similareTo(Tag t) {
		if(this.toString().contains(t.toString())) return true;
		else if(t.toString().contains(this.toString())) return true;
		else return false;
	}
	
	public String toString() {
		return this.tag;
	}
	/**
	 * Setters et getters
	 * 
	 */
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public int getPrio() {
		return prio;
		
		
	}
	public abstract Tag clone();
	public void setPrio(int prio) {
		this.prio = prio;
	}
}
