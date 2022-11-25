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
import javax.swing.JButton;

public class fenetre_choix_film extends JPanel{
	JPanel panel_parametre = new JPanel();
	JPanel panel_compte = new JPanel();
	JPanel panel_film = new JPanel();
	JPanel panel_compte_radioButton = new JPanel();
	JLabel label_id = new JLabel("Bienvenue Thibaud");
	JTextArea zone_recherche = new JTextArea();
	JButton button_recherche = new JButton("Recherche");
	String[] liste_event = {"Nom","Prénom", "age", "adresse", "email","bob","zobi","test","jeSuisGentil","Bof","manque d'idée"};
	JRadioButton[] tabJRadio = new JRadioButton[liste_event.length];
	liste_film_pref test = new liste_film_pref();
	
	public fenetre_choix_film() {
		super.setLayout(new BorderLayout());
		panel_parametre.setLayout(new FlowLayout(FlowLayout.RIGHT));
		panel_compte.setLayout(new BorderLayout());
		panel_compte_radioButton.setLayout(new GridLayout(liste_event.length/2,liste_event.length/2));
		panel_film.setLayout(new BorderLayout());
		
		super.add(panel_compte, BorderLayout.WEST);
		super.add(panel_parametre, BorderLayout.NORTH);
		super.add(panel_film, BorderLayout.CENTER);
		panel_compte.add(label_id,BorderLayout.NORTH);
		panel_compte.add(panel_compte_radioButton,BorderLayout.SOUTH);
		panel_compte.setBackground(Color.red);
		for(int i = 0; i<liste_event.length; i++) {
			panel_compte_radioButton.add(tabJRadio[i]= new JRadioButton(liste_event[i]));
		}
		
		panel_parametre.add(button_recherche);
		panel_parametre.add(zone_recherche);
		panel_parametre.setBackground(Color.BLUE);
		
		label_id.setFont(new Font("Verdana", Font.PLAIN, 28));
		label_id.setForeground(Color.WHITE);
		zone_recherche.setPreferredSize(new Dimension(100,20));
		
		
		panel_film.setBackground(Color.GREEN);
		panel_film.add(test, BorderLayout.CENTER);
		
		
		
	}
}
