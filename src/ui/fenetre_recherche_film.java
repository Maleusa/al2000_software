package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;


import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;

public class fenetre_recherche_film extends JPanel{
	JPanel panel_parametre = new JPanel();
	JPanel panel_parametre_droit = new JPanel();
	JPanel panel_compte = new JPanel();
	JPanel panel_film = new JPanel();
	String[] info_compte = {"Prix","bob"};
	JLabel[] tabJLabel = new JLabel[info_compte.length];
	JPanel[] tabJPanel = new JPanel[3];
	
	
	JPanel panel_compte_radioButton = new JPanel();
	
	
	JLabel jtf;
	JLabel label_id = new JLabel("Bienvenue Thibaud");
	JTextArea zone_recherche = new JTextArea();
	JButton button_recherche = new JButton("Recherche");
	JButton button_delete = new JButton("X");
	JButton button_retour = new JButton("Retour");
	JButton button_compte = new JButton("Mon compte");
	String[] liste_event = {"Film","Realisateur", "Action", "Horreur", "Amour","-18","Enfant","test","jeSuisGentil","Bof","manque d'idée"};
	JRadioButton[] tabJRadio = new JRadioButton[liste_event.length];
	//film_button test = new film_button(200,300,"https://image.tmdb.org/t/p/w500/sU0SPvZPJj9AORrCqoI8JnhJiIw.jpg");
	
	public fenetre_recherche_film() {
		
		this.setLayout(new BorderLayout());
		label_id.setFont(new Font("Verdana", Font.PLAIN, 28));
		label_id.setForeground(Color.WHITE);
		
		
		//Partie NORD du Blayout
		this.add(panel_parametre, BorderLayout.NORTH);
		
		
		
		panel_parametre.setLayout(new BorderLayout());
		panel_parametre.add(button_delete,BorderLayout.EAST);
		panel_parametre.add(button_retour,BorderLayout.WEST);
		panel_parametre.setBackground(Color.BLUE);
		
		//Panel OUEST du Blayout
		panel_compte.setLayout(new BorderLayout());
		this.add(panel_compte, BorderLayout.WEST);
		panel_compte.add(label_id,BorderLayout.NORTH);
		panel_compte.add(button_compte,BorderLayout.SOUTH);
		panel_compte.setBackground(Color.red);
		
		
		//Panel CENTER du Blayout
		this.add(panel_film, BorderLayout.CENTER);
		panel_film.setLayout(new BorderLayout());
		panel_film.setBackground(Color.GREEN);
		panel_film.setLayout(new GridLayout(3,1));
		for(int i = 0;i<3;i++) {
			panel_film.add(tabJPanel[i] = new JPanel());
			tabJPanel[i].setLayout(new BorderLayout());
		}
		
		
		panel_compte_radioButton.setLayout(new GridLayout(liste_event.length/2,liste_event.length/2));
		for(int i = 0; i<liste_event.length; i++) {
			panel_compte_radioButton.add(tabJRadio[i]= new JRadioButton(liste_event[i]));
		}
		zone_recherche.setPreferredSize(new Dimension(100,20));
		
		tabJPanel[0].add(zone_recherche,BorderLayout.CENTER);
		tabJPanel[0].add(jtf = new JLabel("ENTRER VOTRE RECHERCHE",JLabel.CENTER),BorderLayout.NORTH);
		tabJPanel[1].add(panel_compte_radioButton);
		tabJPanel[2].add(button_recherche);
		
		
		
		//panel_film.add(test, BorderLayout.CENTER);
		
		
		
	}
}
