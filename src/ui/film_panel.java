package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.*;

public class film_panel extends JPanel{
	
	private static final long serialVersionUID = 1L;

	public film_panel() {
		film_button fleche_droite;
		film_button fleche_gauche;
		film_button[] film = new film_button[5];
		JPanel panel = new JPanel();
		JPanel panel_avec_film = new JPanel();
		
		this.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());

		fleche_droite = new film_button(50,50,"https://www.onhaye.be/accordeon/fleche.jpg/@@images/image.jpeg");
		fleche_gauche = new film_button(50,50,"https://assets.stickpng.com/images/585e4695cb11b227491c3373.png");

		panel_avec_film.setLayout(new GridLayout(1,5));
		for(int i = 0; i<5; i++) {
			film[i] = new film_button(50,50,"https://image.tmdb.org/t/p/w500/sU0SPvZPJj9AORrCqoI8JnhJiIw.jpg");
			panel_avec_film.add(film[i]);
		}
		
		panel.add(fleche_droite,BorderLayout.EAST);
		panel.add(fleche_gauche,BorderLayout.WEST);
		panel.add(panel_avec_film,BorderLayout.CENTER);
		this.add(panel,BorderLayout.CENTER);

	}

}
