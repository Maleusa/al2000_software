package fc.user;

import java.util.ArrayList;

import fc.ComponentFC;
import fc.searchengine.*;
public abstract class User implements ComponentFC{

	protected int id;
	protected String password;
	protected Historic historic;
	protected int cardNumber;
	protected String name;
	protected String adress;
	protected ArrayList<GenreTag> restrictedGenre;
	protected boolean identified;
	protected int subscriberBalance;
	
	protected User(int id) {
		this.id=id;
		this.identified=false;
		this.restrictedGenre = new ArrayList<GenreTag>();
		this.subscriberBalance=0;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public void setHistoric(Historic historic) {
		this.historic = historic;
	}

	public void setCardNumber(int cardNumber) {
		this.cardNumber = cardNumber;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public void setRestrictedGenre(ArrayList<GenreTag> restrictedGenre) {
		this.restrictedGenre = restrictedGenre;
	}

	public int getId() {
		return id;
	}

	public String getPassword() {
		return password;
	}

	public Historic getHistoric() {
		return historic;
	}

	public String getName() {
		return name;
	}

	public String getAdress() {
		return adress;
	}

	public ArrayList<GenreTag> getRestrictedGenre() {
		return restrictedGenre;
	}

	public boolean isIdentified() {
		return identified;
	}

	public int getCardNumber() {
		return cardNumber;
	}
	
	protected abstract int calculatePrice(int nbDay);
	
	protected boolean debitBankCard(int price) {
		return true;
	}
}
