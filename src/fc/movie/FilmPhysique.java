package fc.movie;

import fc.Al2000;

public class FilmPhysique extends Film implements FilmLouable {
	DammageState state;
	
	public FilmPhysique(){
		state = DammageState.OPERATIONNAL;
	}
	
	public FilmPhysique(String titre) {
		state = DammageState.OPERATIONNAL;
		this.titre=titre;
	}
	@Override
	public int louer(Al2000 al2000) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int rendre(Al2000 al2000) {
		// TODO Auto-generated method stub
		return 0;
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
