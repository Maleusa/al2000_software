package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import ui.stateMachine.Page;
import ui.stateMachine.StateMachine;

/*
 *Public Event :
 *	SIGN_IN_EVENT_TYPE , data as :  String[0] wich is the subscriber number
 *GUI Event :
 *	TO_RETURN_PAGE
 *	TO_ID_PAGE
 *
 */
public class Welcome extends Page	 {
	
	JButton welcomeButton;
	JButton returnButton;
	JPanel panel_two;
	JLabel label;
	ArrayList<String> list = new ArrayList<String>();
	StateMachine stateMachine;
	JFrame jF;
	public Welcome(final JFrame jFrame, StateMachine stateM) {
		this.jF=jFrame;
		this.stateMachine=stateM;
		welcomeButton = new JButton("Welcome");
		label = new JLabel("AL2000 REVOLUTION");
		panel_two = new JPanel();
		returnButton = new JButton("Return Movie");
		//Initialisation des différents élements composants notre fenêtre
    	super.setLayout(new BorderLayout());
    	super.setBackground(Color.black);
    	label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
    	label.setFont(new Font("Verdana", Font.PLAIN, 42));
    	label.setForeground(Color.white);
    	
    	//welcome.setPreferredSize(new Dimension(jF.getHeight(),300));
    	panel_two.add(welcomeButton);
    	panel_two.add(returnButton);
    	
    	super.add(label);
    	super.add(panel_two,BorderLayout.SOUTH);
    	
    
    	
    	returnButton.addActionListener(new ActionListener() {
        	 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(returnButton)) {
					stateMachine.changeState("TO_RETURN_PAGE");
		        }
			}
   	});
   	
   	welcomeButton.addActionListener(new ActionListener() {
     	 //TODO justify : EdgeCase si on rentre � la fois des infos 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(welcomeButton)) {
					JTextField clientField = new JTextField(5);
                    JTextField bankField = new JTextField(5);

                    JPanel myPanel = new JPanel();
                    myPanel.add(new JLabel("IdAbonne :"));
                    myPanel.add(clientField);
                    myPanel.add(Box.createHorizontalStrut(15)); // a spacer
                    myPanel.add(new JLabel("CBClient:"));
                    myPanel.add(bankField);

                    int result = JOptionPane.showConfirmDialog(null, myPanel,
                        "Please Enter your ID ", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
	                    if(!clientField.getText().equals("")) {
	                    	ArrayList<String> data = new ArrayList<String>();
	                    	data.add(clientField.getText());
	                    	stateMachine.notifyObserver("SIGN_IN_EVENT_TYPE", data);
	                    	stateMachine.changeState("TO_MAIN_PAGE");
	                    }//On lance le message de logAbo
	                    else if(!bankField.getText().equals("")) {
	                    	stateMachine.setCbID(bankField.getText());
	                    	stateMachine.changeState("TO_ID_PAGE");; //On lance le message de To_IdPage
	                    }
                    }
					
                   
		        }
			}
   	});
	}
	
	
	public StateMachine getStateMachine() {
		return stateMachine;
	}



	@Override
	public void addStateMachine(StateMachine stateMachine) {
		this.stateMachine = stateMachine;
		
	}
	@Override
	public void changeState(String EVENT) {
		switch(EVENT) {
		case "TO_RETURN_PAGE":
			ChoiceReturn chPage = new ChoiceReturn(jF, stateMachine);
			stateMachine.setCurrentPage(chPage);
			jF.setContentPane(chPage);
			jF.repaint();
			jF.revalidate();
			break;
		case"TO_ID_PAGE":
			Identification idPage = new Identification(jF, stateMachine);
			stateMachine.setCurrentPage(idPage);
			jF.setContentPane(idPage);
			jF.repaint();
			jF.revalidate();
			break;
		case"TO_MAIN_PAGE":
			ResearchMovie mainPage = new ResearchMovie(jF, stateMachine);
			stateMachine.setCurrentPage(mainPage);
			jF.setContentPane(mainPage);
			jF.repaint();
			jF.revalidate();
		}
		
	}
}
