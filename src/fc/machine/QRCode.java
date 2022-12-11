package fc.machine;

import java.util.ArrayList;

import fc.ComponentFC;

public class QRCode implements ComponentFC, Rentable  {

	private Movie movie;
	private String qrCode;
	
	public QRCode() {
		
	}
	
	public void setMovie(Movie movie) {
		this.movie=movie;
	}
	
	public String getQRCode() {
		return qrCode;
	}
	@Override

	public void update(String EVENT_TYPE, ArrayList<String> data) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void louer(Machine al2000) {
		al2000.openQRCodeExit();
	}


}
