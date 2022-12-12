package fc.user;

import java.util.ArrayList;

import fc.constants.Prices;

public class Technical extends User {

	protected Technical(int id) {
		super(id);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected int calculatePrice(int nbDay) {
		return 0;
	}
	
}
