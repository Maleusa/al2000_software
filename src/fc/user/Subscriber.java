package fc.user;

import java.util.ArrayList;

import fc.constants.Prices;
import fc.searchengine.GenreTag;

public class Subscriber extends User {

	
	public Subscriber(int id) {
		super(id);
	}
	
	public boolean checkIdentity(String password) {
		if(this.password==null) throw new NullPointerException();
		return this.password.equals(password);
	}
	
	@Override
	/*
	 * TODO : Ajouter le notify() vers listenerDAO lors des modifs
	 * 
	 * EVENT_TYPE :
	 * 	UPDATE_SUBSCRIBER_EVENT_TYPE
	 * 	AUTHENTIFICATION_EVENT_TYPE
	 * 
	 * 
	 * 	RENT_BLURAY_EVENT_TYPE
	 * 	RETURN_DAMAGED_EVENT_TYPE
	 * 	RETURN_CORRECT_EVENT_TYPE
	 * 	QRCODE_PAIE_EVENT_TYPE
	 * 
	 * 	REFOUND_TECHNICAL_EVENT_TYPE
	 * 	
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		switch(EVENT_TYPE) {
			case "AUTHENTIFICATION_EVENT_TYPE":
				//data.get(0) is the password
				if(checkIdentity(data.get(0))) identified=true;
				break;
			case "UPDATE_SUBSCRIBER_EVENT_TYPE":
				//data is : {"Nom", "Mot de passe", "Adresse", "Genre bloqué", "Solde"}
				this.name = data.get(0);
				this.password = data.get(1);
				this.adress = data.get(2);
				this.restrictedGenre = new ArrayList<>();
				String[] genres =  data.get(3).split(";");
				for(String genre : genres) this.restrictedGenre.add(new GenreTag(genre));
				this.subscriberBalance = Integer.parseInt(data.get(4));
				
				data.add(0, ""+this.cardNumber);
				//TODO notify DAO whith changes : UPDATE_SUBSCRIBER_EVENT_TYPE,data
				break;
			case "RENT_BLURAY_EVENT_TYPE":
				//data is : {idBluRay}
				data.add(""+this.id);
				data.add(""+this.cardNumber);
				//TODO notify DAO with idUser and idBluRay RENT_BLURAY_EVENT_TYPE,data
				break;
			case "RETURN_DAMAGED_EVENT_TYPE":
				//data is : {idBluRay}
				if(!historic.isRented(Integer.parseInt(data.get(0)))) throw new RuntimeException(); //Degueu trouver comment on signal que pas le bon movie
				
				//TODO notify DAO RETURN_DAMAGED_EVENT_DAO: data = {idBluRay, idUser, idAbonne}
				break;
			case "RETURN_CORRECT_EVENT_TYPE":
				if(!historic.isRented(Integer.parseInt(data.get(0)))) throw new RuntimeException();
				//CalculatePrice
				//TODO notify DAO RETURN_CORRECT_EVENT_DAO: data {idBluRay, idUser, idAbonne}
				break;
			case "QRCODE_PAIE_EVENT_TYPE":
				//Lui enlever son argent et notify DAO 
				int price = calculatePrice(1);
				if(this.subscriberBalance>price) subscriberBalance -= price;
				else debitBankCard(price);
				// QRCODE_RENT_EVENT_DAO: data = {idBluRay, idUser, idAbonne, Price}
				break;
			case "REFOUND_TECHNICAL_EVENT_TYPE":
				//PAS sûr qu'il là soit en réalité le refund c'est un trigger de bd et quand initialisation on aura la nouvelle valeur d'argent
				break;
			default:
				break;
		}
		
	}

	@Override
	protected int calculatePrice(int nbDay) {
		return nbDay*Prices.SUBSCRIBERPRICE;
	}

}
