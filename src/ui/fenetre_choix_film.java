package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class fenetre_choix_film extends JPanel{
	
	JButton button_supprimer;
	JButton button_compte;
	JButton button_retour;
	JLabel label_recherche;
	JLabel label_demat;
	JLabel label_mat;
	film_panel film_demat;
	film_panel film_mat;
	JPanel jPanel_north = new JPanel();
	JPanel jPanel_north_east = new JPanel();
	JPanel jPanel_north_west = new JPanel();
	JPanel jPanel_center = new JPanel();
	JPanel jPanel_center_top = new JPanel();
	JPanel jPanel_center_down = new JPanel();
	
	
	public fenetre_choix_film(JFrame j) {
		this.setLayout(new BorderLayout());
		this.add(jPanel_north,BorderLayout.NORTH);
		jPanel_north.setLayout(new BorderLayout());
		jPanel_north.add(jPanel_north_east, BorderLayout.EAST);
		jPanel_north_east.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jPanel_north_east.add(button_supprimer = new JButton("X"));
		
		
		jPanel_north.add(jPanel_north_west, BorderLayout.WEST);
		jPanel_north_west.setLayout(new FlowLayout(FlowLayout.LEFT));
		jPanel_north_west.add(button_retour= new JButton("<--"));
		jPanel_north_west.add(button_compte = new JButton("Mon compte"));
		jPanel_north_west.add(label_recherche = new JLabel("Votre recherche : "));
		
		this.add(jPanel_center);
		jPanel_center.setLayout(new GridLayout(2,1));
		jPanel_center.add(jPanel_center_top);
		jPanel_center.add(jPanel_center_down);
		
		
		
		jPanel_center_top.setBackground(Color.ORANGE);
		jPanel_center_down.setBackground(Color.ORANGE);											
		jPanel_center_top.setLayout(new BorderLayout());
		jPanel_center_down.setLayout(new BorderLayout());
		
		jPanel_center_top.add(label_demat = new JLabel("FILM dématérialisé"),BorderLayout.NORTH);
		jPanel_center_top.add(film_demat = new film_panel(),BorderLayout.CENTER);
		jPanel_center_down.add(label_mat = new JLabel("FILM matérialisé"),BorderLayout.NORTH);
		jPanel_center_down.add(film_demat = new film_panel(),BorderLayout.CENTER);
		
		
		
		
		
		

	}
}
