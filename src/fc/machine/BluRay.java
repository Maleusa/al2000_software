package fc.machine;

import java.util.ArrayList;

import fc.ComponentFC;
import fc.constants.MovieState;
import ui.listener.UIObserver;

/*
 * A BluRay is a Rentable object wich contains a movie, an id and a state (cf MovieState)
 */
public class BluRay implements Rentable {

	private Movie movie;
	private int id;
	private MovieState state;
	
	public BluRay() {
	}
	
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

	/*
	 * EVENT_TYPE :
	 * 	RENT_BLURAY_EVENT_TYPE
	 * 	RETURN_DAMAGED_EVENT_TYPE
	 * 	RETURN_CORRECT_EVENT_TYPE
	 */
	public void update(String EVENT_TYPE, ArrayList<String> data) {
		
		switch(EVENT_TYPE) {
			case "RENT_BLURAY_EVENT_TYPE":
				this.state=MovieState.RENTED;
				break;
			case "RETURN_DAMAGED_EVENT_TYPE":
				this.state=MovieState.UNVERIFIED;
				break;
			case "RETURN_CORRECT_EVENT_TYPE":
				this.state=MovieState.RENTABLE;
				break;
			default:
				break;
		}
	}

}
