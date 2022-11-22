package fc.movie;

import fc.Al2000;
import fc.Constants;

public class FilmPhysique extends Film implements FilmLouable {
	DammageState state;
	
	public FilmPhysique(){
		this("");
	}
	
	public FilmPhysique(String titre) {
		state = DammageState.OPERATIONNAL;
		this.titre=titre;
		this.prix=Constants.PRIXPHYSIQUE;
	}
	@Override
	public void louer(Al2000 al2000) {
		// TODO Auto-generated method stub
		al2000.louerFilmPhysique();
	}

	@Override
	public void rendre(Al2000 al2000) {
		// TODO Auto-generated method stub
		
	}
	
	public boolean equals(FilmPhysique film) {
		if(super.equals(film)) return true;
		if(this.titre.equals(film.titre))return true;
		return false;
	}
	
	public DammageState getState() {
		return state;
	}
	
	public void setState(DammageState newState) {
		state = newState;
	}

}
