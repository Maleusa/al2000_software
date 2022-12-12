package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReservationMovie extends JPanel {
	
	
	JPanel jPanel_north = new JPanel();
	JPanel jPanel_north_east = new JPanel();
	JPanel jPanel_north_west = new JPanel();
	
	JPanel jPanel_center = new JPanel();
	JPanel jPanel_center_west = new JPanel();
	JPanel jPanel_center_east = new JPanel();
	
	JButton deleteButton;
	JButton backButton;
	JButton userButton;
	
	ArrayList<String> liste = new ArrayList<String>();
	
	public ReservationMovie(JFrame j, ArrayList<String> s) {
		liste.add("HARRY POTTER:");
		liste.add("fazkfla,lkef");
		liste.add("eklsfes fks");
		liste.add("d svlknskenlksefnksenf");
		
		
		this.setLayout(new BorderLayout());
		this.add(jPanel_north,BorderLayout.NORTH);
		
		jPanel_north.setLayout(new BorderLayout());
		jPanel_north.add(jPanel_north_east, BorderLayout.EAST);
		jPanel_north_east.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jPanel_north_east.add(deleteButton = new JButton("X"));
		
		
		jPanel_north.add(jPanel_north_west, BorderLayout.WEST);
		jPanel_north_west.setLayout(new FlowLayout(FlowLayout.LEFT));
		jPanel_north_west.add(backButton= new JButton("<--"));
		jPanel_north_west.add(userButton = new JButton("Mon compte"));
		
		
		
		this.add(jPanel_center,BorderLayout.CENTER);
		jPanel_center.setLayout(new GridLayout(1,2));
		jPanel_center.add(jPanel_center_west);
		
		jPanel_center_west.setBackground(Color.pink);
		jPanel_center_west.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 10;
		gbc.gridy = GridBagConstraints.RELATIVE;
		gbc.fill = GridBagConstraints.VERTICAL;
		gbc.ipady = gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(10, 10, 10, 10);
		
		
		for(int i = 0; i<liste.size();i++) {
			jPanel_center_west.add(new JLabel(liste.get(i)),gbc);
			
		}
		
		
		
		
		jPanel_center.add(jPanel_center_east);
		jPanel_center_east.setBackground(Color.BLACK);
		
			
			ImageIcon icon;
			BufferedImage image;
			BufferedImage imageResize;
			
			
				try {
					URL url = new URL("https://image.tmdb.org/t/p/w500/sU0SPvZPJj9AORrCqoI8JnhJiIw.jpg"); //"https://image.tmdb.org/t/p/w500/sU0SPvZPJj9AORrCqoI8JnhJiIw.jpg"
					image = ImageIO.read(url);
					icon = new ImageIcon(image);
					JLabel imageLabel = new JLabel(icon);
					jPanel_center_east.add(imageLabel);
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
}
