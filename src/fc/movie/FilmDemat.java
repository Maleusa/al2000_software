package fc.movie;

import fc.Al2000;
import fc.Constants;

public class FilmDemat extends Film implements FilmLouable {
	
	public FilmDemat() {
		this("");
	}
	
	public FilmDemat(String titre) {
		this.titre=titre;
		this.prix=Constants.PRIXDEMAT;
	}
	
	@Override
	public void louer(Al2000 al2000) {
		// TODO Auto-generated method stub
		al2000.louerFilmDemat();
	}

	@Override
	public void rendre(Al2000 al2000) {}
	
	public boolean equals(FilmPhysique film) {
		if(super.equals(film)) return true;
		if(this.titre.equals(film.titre))return true;
		return false;
	}

}
