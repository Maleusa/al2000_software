package fc.machine;

import java.util.ArrayList;
import java.util.HashMap;

import dao.Stub3;
import fc.ComponentFC;

public class DAORequestHandlerMachine implements DAORequestHandler {

	@Override
	public ArrayList<BluRay> getStockBluRay(int idAl) {
		ArrayList<BluRay> stock = new ArrayList<BluRay>();
		Stub3 dao = new Stub3();
		ArrayList<HashMap<String,String>> tableMovie = dao.getStockBluRay(idAl);
		for(HashMap<String,String> movieInformations : tableMovie) {
			BluRay bluray = new BluRay();
		}
		return null;
	}

	
	
}
