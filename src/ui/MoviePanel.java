package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import fc.machine.Movie;

public class MoviePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int index = 0;
	private ArrayList<MovieButton> listButton= new ArrayList<>();
	private ArrayList<Movie> movies = new ArrayList<>();
    
	public MoviePanel(final ArrayList<Movie> s, final JFrame jF) {
		
		for(int i = 0;i<s.size(); i++) {
			movies.add(s.get(i));
		}
		final MovieButton rightArrow;
		//film_button[] film = new film_button[5];
		JPanel panel = new JPanel();
		JPanel panelWithMovie = new JPanel();
		
		   /*
    	   * CAS AVEC UNE LISTE VIDE A GERER
    	   */
		
		this.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());

		rightArrow = new MovieButton(50,50,"https://www.onhaye.be/accordeon/fleche.jpg/@@images/image.jpeg",null);
		
		panelWithMovie.setLayout(new GridLayout(1,5));
		if(movies.size()>=5) {
			for(int i = 0;  i<5; i++) {
				MovieButton button = new MovieButton(50,50,s.get(index).getUrl(),s.get(index));
				listButton.add(button);
				//film[i]=button;
				index++;
				panelWithMovie.add(listButton.get(i));
			}
		} else {
			for(int i = 0;i<movies.size(); i++) {
				MovieButton button = new MovieButton(50,50,s.get(index).getUrl(),s.get(index));
				listButton.add(button);
				//film[i]=button;
				index++;
				panelWithMovie.add(listButton.get(i));
			}
		}
		System.out.println("Indice :"+index);
		
		panel.add(rightArrow,BorderLayout.EAST);
		panel.add(panelWithMovie,BorderLayout.CENTER);
		this.add(panel,BorderLayout.CENTER);
		
		rightArrow.addActionListener(new ActionListener() {
	      	  
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(rightArrow)) {
				//////////////////////	
					if(movies.size()<5) {
						JOptionPane.showMessageDialog(jF, "Il n'y a pas d'autre film:");
					}
					else if(index+5<s.size()) {
						for(int i = 0;i<5; i++) {
							listButton.get(i).setUrl(movies.get(index).getUrl());
							index++;
						}
						jF.repaint();
					} 
				
				//////////////////////////////////////////
					
					else {
						int i = 0;
						while(index != s.size()-1) {
							listButton.get(i).setUrl(movies.get(index).getUrl());
							i++;
							index++;
						}
						index = 0;
						while(i < 5) {
							listButton.get(i).setUrl(movies.get(index).getUrl());
							index++;
							i++;
						}
						jF.repaint();
					} 
				}
			}
    	});
		
		}

}