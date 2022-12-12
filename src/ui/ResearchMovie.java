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

public class ResearchMovie extends JPanel{
	JPanel settingsPanel = new JPanel();
	JPanel settingsPanelRight = new JPanel();
	JPanel subPanel = new JPanel();
	JPanel moviePanel = new JPanel();
	String[] userInformation = {"Prix","bob"};
	JLabel[] tabJLabel = new JLabel[userInformation.length];
	JPanel[] tabJPanel = new JPanel[3];
	
	
	JPanel userPanelRadioButton = new JPanel();
	
	
	JLabel jtf;
	JLabel label_id = new JLabel("Welcome Thibaud");
	JTextArea researchZone = new JTextArea();
	JButton researchButton = new JButton("Recherche");
	JButton deleteButton = new JButton("X");
	JButton backButton = new JButton("Retour");
	JButton userButton = new JButton("Mon compte");
	String[] eventList = {"Film","Realisateur", "Action", "Horreur", "Amour","-18","Enfant","test","jeSuisGentil","Bof","manque d'idée"};
	JRadioButton[] tabJRadio = new JRadioButton[eventList.length];
	//film_button test = new film_button(200,300,"https://image.tmdb.org/t/p/w500/sU0SPvZPJj9AORrCqoI8JnhJiIw.jpg");
	
	public ResearchMovie() {
		
		this.setLayout(new BorderLayout());
		label_id.setFont(new Font("Verdana", Font.PLAIN, 28));
		label_id.setForeground(Color.WHITE);
		
		
		//Partie NORD du Blayout
		this.add(settingsPanel, BorderLayout.NORTH);
		
		
		
		settingsPanel.setLayout(new BorderLayout());
		settingsPanel.add(deleteButton,BorderLayout.EAST);
		settingsPanel.add(backButton,BorderLayout.WEST);
		settingsPanel.setBackground(Color.BLUE);
		
		//Panel OUEST du Blayout
		subPanel.setLayout(new BorderLayout());
		this.add(subPanel, BorderLayout.WEST);
		subPanel.add(label_id,BorderLayout.NORTH);
		subPanel.add(userButton,BorderLayout.SOUTH);
		subPanel.setBackground(Color.red);
		
		
		//Panel CENTER du Blayout
		this.add(moviePanel, BorderLayout.CENTER);
		moviePanel.setLayout(new BorderLayout());
		moviePanel.setBackground(Color.GREEN);
		moviePanel.setLayout(new GridLayout(3,1));
		for(int i = 0;i<3;i++) {
			moviePanel.add(tabJPanel[i] = new JPanel());
			tabJPanel[i].setLayout(new BorderLayout());
		}
		
		
		userPanelRadioButton.setLayout(new GridLayout(eventList.length/2,eventList.length/2));
		for(int i = 0; i<eventList.length; i++) {
			userPanelRadioButton.add(tabJRadio[i]= new JRadioButton(eventList[i]));
		}
		researchZone.setPreferredSize(new Dimension(100,20));
		
		tabJPanel[0].add(researchZone,BorderLayout.CENTER);
		tabJPanel[0].add(jtf = new JLabel("ENTRER VOTRE RECHERCHE",JLabel.CENTER),BorderLayout.NORTH);
		tabJPanel[1].add(userPanelRadioButton);
		tabJPanel[2].add(researchButton);
		
		
		
		//panel_film.add(test, BorderLayout.CENTER);
		
		
		
	}
}
