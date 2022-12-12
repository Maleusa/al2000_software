package fc.machine;

import java.util.ArrayList;

import fc.ComponentFC;
import fc.machine.Rentable;

public interface Stock extends ComponentFC {

	public ArrayList<? extends Rentable> getRentableMovies();
	public Stock getStockModel();
}
