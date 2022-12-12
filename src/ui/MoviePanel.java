package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MoviePanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	private int index = 0;
	private ArrayList<MovieButton> listButton= new ArrayList<>();
	private ArrayList<String> movies = new ArrayList<>();
    
	public MoviePanel(ArrayList<String> s, JFrame jF) {
		
		for(int i = 0;i<s.size(); i++) {
			movies.add(s.get(i));
		}
		MovieButton rightArrow;
		MovieButton leftArrow;
		//film_button[] film = new film_button[5];
		JPanel panel = new JPanel();
		JPanel panelWithMovie = new JPanel();
		
		   /*
    	   * CAS AVEC UNE LISTE VIDE A GERER
    	   */
		
		this.setLayout(new BorderLayout());
		panel.setLayout(new BorderLayout());

		rightArrow = new MovieButton(50,50,"https://www.onhaye.be/accordeon/fleche.jpg/@@images/image.jpeg");
		leftArrow = new MovieButton(50,50,"https://assets.stickpng.com/images/585e4695cb11b227491c3373.png");

		panelWithMovie.setLayout(new GridLayout(1,5));
		for(int i = 0;  i<5; i++) {
			MovieButton button = new MovieButton(50,50,s.get(index));
			listButton.add(button);
			//film[i]=button;
			index++;
			panelWithMovie.add(listButton.get(i));
		}
		System.out.println("Indice :"+index);
		
		panel.add(rightArrow,BorderLayout.EAST);
		panel.add(leftArrow,BorderLayout.WEST);
		panel.add(panelWithMovie,BorderLayout.CENTER);
		this.add(panel,BorderLayout.CENTER);
		
		rightArrow.addActionListener(new ActionListener() {
	      	  
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(rightArrow)) {
				//////////////////////	
					if(index+5<s.size()) {
						for(int i = 0;i<5; i++) {
							listButton.get(i).setUrl(movies.get(index));
							index++;
						}
						jF.repaint();
					} 
				
				//////////////////////////////////////////
					
					else {
						int i = 0;
						while(index != s.size()-1) {
							listButton.get(i).setUrl(movies.get(index));
							i++;
							index++;
						}
						index = 0;
						while(i < 5) {
							listButton.get(i).setUrl(movies.get(index));
							index++;
							i++;
						}
						jF.repaint();
					} 
				}
			}
    	});
		
		leftArrow.addActionListener(new ActionListener() {
	      	  
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(leftArrow)) {
				//////////////////////	
					
					if(index-10>=0) {
						System.out.println("JE suis ici");
						index = index-5;
						for(int i = 0;i<5; i++) {
							listButton.get(i).setUrl(movies.get(index));
							index--;
						}
						jF.repaint();
					} 
				
				//////////////////////////////////////////
					else if(index == 5) {
						
					}
					else {
						
					}
				}
			}
    	});
		
		
		
		
		}

}
