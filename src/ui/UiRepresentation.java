package ui;

import java.util.HashMap;
/**
 * Fields must respect the format FIELD
 * @author Cherit Y.
 *
 */
public interface UiRepresentation {
	
	
	
	public  void addField(String field, String value);
	
	public void updateField(String field,String values);
	
	public String getValue(String field);
	
}
