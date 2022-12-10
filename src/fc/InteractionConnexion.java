package fc;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import dao.*;

/*
 * Utile lors de la connexion : L'interaction de la première page
 */
public class InteractionConnexion extends Interaction implements ActionListener{
	
	private Stub3 dao;
	private PageFC pageFC;
	
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Lors du clique de la première page : le client a rentré sa carte bleu ou sa carte Abo
		 */
		
		//Ouvre un popup avec l'ID : cb ou abo
		
		int idTemporaire = 0000;
		List<Map<String, Object>> listAbonnement = dao.cardInfo(idTemporaire);
		
		
	}
	
	
	
}
