package fc;

import java.util.ArrayList;

/*
 * All our Component of the FC are ComponentFC : they are cloneable (those
 * which need a deep copy are gonna Override clone()) and are updatable.
 */
public interface ComponentFC extends Cloneable{

	public void update(String EVENT_TYPE, ArrayList<String> data);
}
