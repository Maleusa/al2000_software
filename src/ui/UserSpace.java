package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.stateMachine.Page;
import ui.stateMachine.StateMachine;

public class UserSpace extends Page{
	
	StateMachine stateMachine;
	JFrame jF;
	public UserSpace(JFrame jFrame, StateMachine stateM) {
		this.jF=jFrame;
		this.stateMachine=stateM;
		String[] s = {"Nom", "Prénom", "Adresse", "Telephone", "Mail"};
		JLabel[] jl = new JLabel[s.length];
		
		JPanel northPanel = new JPanel();
		JPanel northCenterPanel= new JPanel();
		JPanel centerPanel = new JPanel();
		JPanel centerPanelRight = new JPanel();
		JPanel centerPanelRightNorth = new JPanel();
		JPanel centerPanelRightCenter = new JPanel();
		JPanel centerPanelLeft = new JPanel();
		JButton deleteButton = new JButton("X");
		JButton backButton = new JButton("<--");
		JLabel myProfile = new JLabel("My Profile");
		JLabel historique = new JLabel("Historique");
		Font font = new Font("TimesRoman", Font.BOLD, 18);
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
		
		centerPanelLeft.setLayout(new GridLayout(s.length,1));
		for(int i = 0;i<s.length;i++) {
			centerPanelLeft.add(jl[i] = new JLabel(s[i]));
			jl[i].setFont(font);
		}
		
		
		this.setLayout(new BorderLayout());
		this.add(northPanel, BorderLayout.NORTH);
		this.add(centerPanel, BorderLayout.CENTER);
		
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
