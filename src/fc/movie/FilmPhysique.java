package fc.movie;

import fc.Al2000;

public class FilmPhysique extends Film implements FilmLouable {

	public FilmPhysique(String name, String actors, String directors, String genre, String classification,
			String year) {
		super(name, actors, directors, genre, classification, year);
		// TODO Auto-generated constructor stub
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

}
