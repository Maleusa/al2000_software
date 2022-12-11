package fc.user;

import fc.ComponentFC;

public abstract class User implements ComponentFC{

	protected int id;
	protected String password;
	protected Historic historic;
	protected int cardNumber;
	
	protected User(int id) {
		this.id=id;
	}
	
	public int getCardNumber() {
		return cardNumber;
	}
}
