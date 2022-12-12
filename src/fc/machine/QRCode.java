package fc.machine;

import java.util.ArrayList;

import fc.ComponentFC;

public class QRCode implements Rentable  {

	private Movie movie;
	private String qrCode;
	
	public QRCode() {
		
	}
	
	public Movie getMovie() {
		return movie;
	}
	
	public void setMovie(Movie movie) {
		this.movie=movie;
	}
	
	public String getQRCode() {
		return qrCode;
	}
	@Override
	/*
	 * EVENT_TYPE :
	 * 	RENT_QRCODE_EVENT_TYPE
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		switch(EVENT_TYPE) {
		case "RENT_QRCODE_EVENT_TYPE":
			int qr = (int)(Math.random()*200);
			qrCode = ""+qr;
			break;
		default:
			break;
	}
	
	}

	@Override
	public Movie getMovieModel() {
		return movie.getMovieModel();
	}


}
