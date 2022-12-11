package fc.machine;

import java.util.ArrayList;

import fc.ComponentFC;
import fc.constants.MovieState;

/*
 * A BluRay is a Rentable object wich contains a movie, an id and a state (cf MovieState)
 */
public class BluRay implements ComponentFC, Rentable {

	private Movie movie;
	private int id;
	private MovieState state;
	
	public Movie getMovie() {
		return movie;
	}
	public void setMovie(Movie movie) {
		this.movie = movie;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public MovieState getState() {
		return state;
	}
	public void setState(MovieState state) {
		this.state = state;
	}

	public void update(String EVENT_TYPE, ArrayList<String> data) {
		// TODO Auto-generated method stub
		

	@Override
	public void louer(Machine al2000) {
		al2000.openBluRayExit();
	}

	
}
