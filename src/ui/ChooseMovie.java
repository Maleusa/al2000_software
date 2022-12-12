package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/*
 * 
 * Window where user choose movie
 * 
 * 
 * 
 * 
 * 
 */

public class ChooseMovie extends JPanel{
	
	JButton button_delete;
	JButton button_user;
	JButton button_goBack;
	JLabel label_research;
	JLabel label_QRCODE;
	JLabel label_CD;
	MoviePanel movie_QRCODE;
	MoviePanel movie_CD;
	JPanel jPanel_north = new JPanel();
	JPanel jPanel_north_east = new JPanel();
	JPanel jPanel_north_west = new JPanel();
	JPanel jPanel_center = new JPanel();
	JPanel jPanel_center_top = new JPanel();
	JPanel jPanel_center_down = new JPanel();
	ArrayList<String> myList= new ArrayList<>();
	
	public ChooseMovie(JFrame jF) {		
		
		/*
		 * North Panel : exit button / GoBack button / MyAccountButton 
		 */
		
		this.setLayout(new BorderLayout());
		this.add(jPanel_north,BorderLayout.NORTH);
		jPanel_north.setLayout(new BorderLayout());
		jPanel_north.add(jPanel_north_east, BorderLayout.EAST);
		jPanel_north_east.setLayout(new FlowLayout(FlowLayout.RIGHT));
		jPanel_north_east.add(button_delete = new JButton("X"));
		jPanel_north.add(jPanel_north_west, BorderLayout.WEST);
		jPanel_north_west.setLayout(new FlowLayout(FlowLayout.LEFT));
		jPanel_north_west.add(button_goBack= new JButton("<--"));
		jPanel_north_west.add(button_user = new JButton("My account"));
		jPanel_north_west.add(label_research = new JLabel("Your research : "));
		
		
		
		this.add(jPanel_center);
		jPanel_center.setLayout(new GridLayout(2,1));
		jPanel_center.add(jPanel_center_top);
		jPanel_center.add(jPanel_center_down);
		jPanel_center_top.setBackground(Color.ORANGE);
		jPanel_center_down.setBackground(Color.ORANGE);											
		jPanel_center_top.setLayout(new BorderLayout());
		jPanel_center_down.setLayout(new BorderLayout());
		jPanel_center_top.add(label_QRCODE = new JLabel("QRCODE Movie"),BorderLayout.NORTH);
		jPanel_center_top.add(movie_QRCODE = new MoviePanel(myList,jF),BorderLayout.CENTER);
		jPanel_center_down.add(label_CD = new JLabel("CD Movie"),BorderLayout.NORTH);
		jPanel_center_down.add(movie_QRCODE = new MoviePanel(myList,jF),BorderLayout.CENTER);
		
		
		
		
		
		

	}
}
