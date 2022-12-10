package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class fenetre_reservation_film extends JPanel {
	
	
	JPanel jPanel_north = new JPanel();
	JPanel jPanel_north_east = new JPanel();
	JPanel jPanel_north_west = new JPanel();
	
	JPanel jPanel_center = new JPanel();
	JPanel jPanel_center_east = new JPanel();
	JPanel jPanel_center_west = new JPanel();
	
	JButton button_supprimer;
	JButton button_retour;
	JButton button_compte;
	
	
	public fenetre_reservation_film(JFrame j) {
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
		
		
		
		this.add(jPanel_center,BorderLayout.CENTER);
		jPanel_center.setLayout(new GridLayout(1,2));
		jPanel_center.add(jPanel_center_east);
		jPanel_center_east.setBackground(Color.pink);
		jPanel_center_east.setLayout(new VerticalLayout());
		jPanel_center.add(jPanel_center_west);
		jPanel_center_west.setBackground(Color.BLUE);
	}
}
