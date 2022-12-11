package fc.machine;

import java.util.ArrayList;

public class QRCodeStock implements Stock{

	private ArrayList<QRCode> movies;
	
	public QRCodeStock() {
		movies = new ArrayList<QRCode>();
	}

	/*
	 * EVENT_TYPE :
	 * 	RENT_QRCODE_EVENT_TYPE
	 */
	@Override
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		int id = Integer.parseInt(data.get(0));
		switch(EVENT_TYPE) {
			case "RENT_QRCODE_EVENT_TYPE":
				for(QRCode qrCode : movies)
					if(qrCode.getMovie().getId()==id) {
						qrCode.update(EVENT_TYPE, data);
						ArrayList<QRCode> movie = new ArrayList<QRCode>();
						movie.add(qrCode);
						movies=movie;
					}
				break;
			default:
				break;
	}
	
		
	}

	@Override
	public ArrayList<QRCode> getRentableMovies() {
		return movies;
	}
	
	public void setStock(ArrayList<QRCode> collection) {
		movies=collection;
	}
}
