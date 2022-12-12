package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ui.stateMachine.Page;
import ui.stateMachine.StateMachine;
import fc.searchengine.*;
/*
 * UPDATE_SUBSCRIBER_EVENT_TYPE , data as :String[0]: Subscriber Name 
 *	                                        String[1]: Password 
 *	                                        String[2]: Adress
 *	                              			String[3]: Blocked Genre
 *	                              			String[4]: Initial Balance 
 */
public class UserSpace extends Page{
	String[] s = {"Nom : ", "Mot De Passe : ", "Adresse : ", "Genre bloqués : ", "Solde : "};
	modifyInfoUserSpace[] jl = new modifyInfoUserSpace[s.length];
	
	JPanel northPanel = new JPanel();
	JPanel northCenterPanel= new JPanel();
	JPanel centerPanel = new JPanel();
	JPanel centerPanelRight = new JPanel();
	JPanel centerPanelRightNorth = new JPanel();
	JPanel centerPanelRightCenter = new JPanel();
	JPanel centerPanelLeft = new JPanel();
	JPanel centerSouthTopPanel = new JPanel();
	JPanel centerSouthDownPanel = new JPanel();
	JPanel soldPanel = new JPanel();
	JButton deleteButton = new JButton("X");
	JButton backButton = new JButton("<--");
	JButton soldButton = new JButton("Valider");
	JLabel myProfile = new JLabel("Mon Profil");
	JLabel historique = new JLabel("Historique");
	Font font = new Font("TimesRoman", Font.BOLD, 18);
	JFrame jF;
	StateMachine stateMachine;
	
	public UserSpace(JFrame jFrame, StateMachine stateM) {
		
		this.jF=jFrame;
		this.stateMachine=stateM;
		
		myProfile.setFont(font);
		historique.setFont(font);
		
		
		
		
		
		northPanel.setLayout(new BorderLayout());
		northPanel.add(northCenterPanel,BorderLayout.CENTER);
		northCenterPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		northCenterPanel.add(myProfile);
		northPanel.add(deleteButton, BorderLayout.EAST);
		northPanel.add(backButton, BorderLayout.WEST);
		
		centerPanel.setLayout(new GridLayout(1,2));
		centerPanel.add(centerPanelLeft);
		centerPanel.add(centerPanelRight);
		
		centerPanelRight.setLayout(new BorderLayout());
		centerPanelRight.add(centerPanelRightNorth, BorderLayout.NORTH);
		centerPanelRight.add(centerPanelRightCenter, BorderLayout.CENTER);
		centerPanelRightNorth.setLayout(new FlowLayout(FlowLayout.CENTER));
		centerPanelRightNorth.add(historique);
		centerPanelRightCenter.setLayout(new StackLayout());
		
		
		
		centerPanelLeft.setLayout(new BorderLayout());
		centerPanelLeft.add(centerSouthTopPanel,BorderLayout.CENTER);
		centerSouthTopPanel.setLayout(new GridLayout(s.length,1));
		for(int i = 0;i<s.length;i++) {
			centerSouthTopPanel.add(jl[i] = new modifyInfoUserSpace(s[i]));
			jl[i].setFont(font);
		}
		centerPanelLeft.add(centerSouthDownPanel,BorderLayout.SOUTH);
		centerSouthDownPanel.setLayout(new GridLayout(2,1));
		centerSouthDownPanel.add(soldPanel);
		soldPanel.setLayout(new BorderLayout());
		centerSouthDownPanel.add(soldButton);
		
		
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		
		jl[0].setjTAmodif(stateMachine.getUserModel().getName());
		jl[1].setjTAmodif(stateMachine.getUserModel().getPassword());
		jl[2].setjTAmodif(stateMachine.getUserModel().getAdress());
		String genres = "";
		for(GenreTag genre : stateMachine.getUserModel().getRestrictedGenre()) genres+=genre.getTag()+";";
		jl[3].setjTAmodif(genres);
		jl[4].setjTAmodif(""+stateMachine.getUserModel().getSubscriberBalance());
		
		ArrayList<String> historic = stateMachine.getUserModel().getHistoric().getRepresentation();
		for(String line : historic) centerPanelRightCenter.add(new HistoricPanel(line));
		
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> data = new ArrayList<>();
				data.add(""+stateMachine.getUserModel().getId());
				stateMachine.notifyObserver("SIGN_OUT_EVENT_TYPE", data);
				stateMachine.changeState("TO_WELCOME_PAGE");
			}
		});
		
		backButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Page previous = stateMachine.returnToPrevious();
				jF.setContentPane(previous);
				jF.repaint();
				jF.revalidate();	
			}
		});
		
		soldButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> data = new ArrayList<>();
				for(modifyInfoUserSpace info : jl) data.add(info.getjTAmodif()); 
				stateMachine.notifyObserver("UPDATE_SUBSCRIBER_EVENT_TYPE", data);
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
		case"TO_USER_SPACE":
			jF.repaint();
			jF.revalidate();
			break;
		case"TO_WELCOME_PAGE":
			Welcome welPage = new Welcome(jF, stateMachine);
			stateMachine.setCurrentPage(welPage);
			stateMachine.reboot();
			jF.setContentPane(welPage);
			jF.repaint();
			jF.revalidate();
			break;
		}
		
	}
}