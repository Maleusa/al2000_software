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

import ui.stateMachine.Page;
import ui.stateMachine.StateMachine;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ResearchMovie extends Page{
	JPanel settingsPanel = new JPanel();
	JPanel settingsPanelRight = new JPanel();
	JPanel subPanel = new JPanel();
	JPanel moviePanel = new JPanel();
	JPanel settingsPanelLeft = new JPanel();
	JPanel subPanelCenter = new JPanel();
	JPanel[] tabJPanel = new JPanel[3];
	
	
	JPanel userPanelRadioButton = new JPanel();
	
	
	JLabel jtf;
	JLabel label_id = new JLabel("Welcome Thibaud");
	JTextArea researchZone = new JTextArea();
	JButton researchButton = new JButton("Recherche");
	JButton deleteButton = new JButton("X");
	JButton backButton = new JButton("<--");
	JButton userButton = new JButton("Mon compte");
	String[] eventList = {"Title","Actor", "Director", "Type", "Year of release","Description", "Tous"};
	JRadioButton[] tabJRadio = new JRadioButton[eventList.length];
	String[] s = {"Nom : ", "Prénom : ", "Adresse : ", "Genre bloqués : zddddddddddddddddddqbitebitebitebitebitebitebite ", "Solde : "};
	JTextArea[] jl = new JTextArea[s.length];
	Font font = new Font("TimesRoman", Font.BOLD, 18);
	//film_button test = new film_button(200,300,"https://image.tmdb.org/t/p/w500/sU0SPvZPJj9AORrCqoI8JnhJiIw.jpg");
	
	JFrame jF;
	StateMachine stateMachine;
	
	public ResearchMovie(JFrame jFrame, StateMachine stateM) {
		this.jF=jFrame;
		this.stateMachine=stateM;
		this.setLayout(new BorderLayout());
		label_id.setFont(font);
		
		
		//Partie NORD du Blayout
		this.add(settingsPanel, BorderLayout.NORTH);
		
		settingsPanel.setLayout(new BorderLayout());
		settingsPanel.add(deleteButton,BorderLayout.EAST);
		settingsPanel.add(settingsPanelLeft,BorderLayout.WEST);
		settingsPanelLeft.add(backButton);
		settingsPanelLeft.add(userButton);
		settingsPanel.setBackground(Color.BLUE);
		
		
		//Panel OUEST du Blayout
		subPanel.setLayout(new BorderLayout());
		this.add(subPanel, BorderLayout.WEST);
		subPanel.add(label_id,BorderLayout.NORTH);
		subPanel.add(subPanelCenter, BorderLayout.CENTER);
		subPanelCenter.setLayout(new GridLayout(s.length,1));
		for(int i = 0;i<s.length;i++) {
			subPanelCenter.add(jl[i] = new JTextArea(s[i]));
			jl[i].setEditable(false);
			jl[i].setLineWrap(true);
			jl[i].setWrapStyleWord(true);
			jl[i].setFont(font);
			
			
		}
		
		
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

	@Override
	public void addStateMachine(StateMachine stateMachine) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeState(String EVENT) {
		// TODO Auto-generated method stub
		
	}
}