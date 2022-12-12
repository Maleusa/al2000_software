package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import fc.searchengine.ClickeableTag;
import ui.stateMachine.Page;
import ui.stateMachine.StateMachine;

import javax.swing.JButton;
import javax.swing.JFrame;

/*
 * PUBLIC_EVENT:
 * 	SIGN_OUT_EVENT_TYPE , data as : String[0] User id
 *  SEARCH_BY_TAG , data as String[n] = keyword n
 *	SEARCH_NO_TAG , data as String[n] = keyword n
 * GUI_EVENT:
 *	TO_USER_SPACE
 *	TO_CHOOSE_MOVIE
 *	TO_WELCOME_PAGE (for return and exit)
 */
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
	String[] s = {"Nom : ", "Adresse : ", "Genre bloqués : ", "Solde : "};
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
		
		researchButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					boolean atLeastOneSelected = false;
					for(JRadioButton radioButton : tabJRadio)
						if(radioButton.isSelected()) atLeastOneSelected=true;
					
					if(atLeastOneSelected) {
						ArrayList<ClickeableTag> tagSelection = stateMachine.getSearchModel().getTagSelection();
						for(int i=0; i<tabJRadio.length; i++) 
							if(tabJRadio[i].isSelected()) tagSelection.get(i).setClickedStatus(true);
						
						String[] request = researchZone.getText().split(" ");
						ArrayList<String> data = new ArrayList<>();
						for(String word : request) data.add(word);
						
						if(tabJRadio[tabJRadio.length-1].isSelected()) 
							stateMachine.notifyObserver("SEARCH_NO_TAG", data);
						else
							stateMachine.notifyObserver("SEARCH_BY_TAG", data);
						
						stateMachine.changeState("TO_CHOOSE_MOVIE");
					}
					else JOptionPane.showMessageDialog(jF, "Pas de tag sélectionné.");
				}
		});
		
		backButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<String> data = new ArrayList<>();
					data.add(""+stateMachine.getUserModel().getId());
					stateMachine.notifyObserver("SIGN_OUT_EVENT_TYPE", data);
					stateMachine.changeState("TO_WELCOME_PAGE");
				}
		});
		
		deleteButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ArrayList<String> data = new ArrayList<>();
					data.add(""+stateMachine.getUserModel().getId());
					stateMachine.notifyObserver("SIGN_OUT_EVENT_TYPE", data);
					stateMachine.changeState("TO_WELCOME_PAGE");
				}
		});
		
		userButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					stateMachine.changeState("TO_USER_SPACE");
				}
		});
		
		
		
	}

	@Override
	public void addStateMachine(StateMachine stateMachine) {
		this.stateMachine=stateMachine;
		
	}

	@Override
	public void changeState(String EVENT) {
		switch(EVENT) {
		case"TO_WELCOME_PAGE":
			Welcome welPage = new Welcome(jF, stateMachine);
			stateMachine.setCurrentPage(welPage);
			stateMachine.reboot();
			jF.setContentPane(welPage);
			jF.repaint();
			jF.revalidate();
			break;
		case "TO_USER_SPACE":
			UserSpace userPage = new UserSpace(jF, stateMachine);
			stateMachine.setCurrentPage(userPage);
			jF.setContentPane(userPage);
			jF.repaint();
			jF.revalidate();	
			break;
		case "TO_CHOOSE_MOVIE":
			ChooseMovie choosePage = new ChooseMovie(jF, stateMachine);
			stateMachine.setCurrentPage(choosePage);
			jF.setContentPane(choosePage);
			jF.repaint();
			jF.revalidate();	
			break;
		}
		
	}
}