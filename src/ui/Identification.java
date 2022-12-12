package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.stateMachine.Page;
import ui.stateMachine.StateMachine;

import javax.swing.JFrame;

/*
 * Public_EVENT:
 *	GUEST_IN_EVENT_TYPE , data as : String[0] wich is the credit card
 * GUI_EVENT:
 * 	TO_SIGNUP_PAGE
 * 	TO_MAIN_PAGE
 */
public class Identification extends Page{
	
	JLabel label_sub = new JLabel("Connect by inserting your card or press the SUB button");
	JLabel label_rent = new JLabel("You can also rent your film without subscribing by clicking on 'Rent''");
	JButton button_subscribed = new JButton("Sub");
	JButton button_rent = new JButton("Louer");
	JPanel panel = new JPanel();
	JFrame jF;
	StateMachine stateMachine;
	
	public Identification(JFrame j, StateMachine stateM) {
		this.stateMachine=stateM;
		super.setLayout(new BorderLayout());
		super.setBackground(Color.BLACK);
		
		panel.setBackground(Color.BLACK);
		panel.setLayout(new GridLayout(2,1));
		label_sub.setFont(new Font("Verdana", Font.PLAIN, 28));
		label_rent.setFont(new Font("Verdana", Font.PLAIN, 28));
		label_rent.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		label_sub.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		label_sub.setForeground(Color.white);
		label_rent.setForeground(Color.WHITE);
		
		super.add(button_rent,BorderLayout.SOUTH);
		super.add(panel, BorderLayout.NORTH);
		super.add(label_rent, BorderLayout.CENTER);
		
		panel.add(label_sub);
		panel.add(button_subscribed);
		
		jF=j;
		
		button_rent.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				ArrayList<String> data = new ArrayList<String>();
            	data.add(stateMachine.getCbID());
            	stateMachine.notifyObserver("GUEST_IN_EVENT_TYPE", data);
            	stateMachine.changeState("TO_MAIN_PAGE");

				
			}
    	});
		
		button_subscribed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
					stateMachine.changeState("TO_SIGNUP_PAGE");			
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
		case"TO_SIGNUP_PAGE":
			Inscription insPage = new Inscription(jF, stateMachine);
			stateMachine.setCurrentPage(insPage);
			jF.setContentPane(insPage);
			jF.repaint();
			jF.revalidate();	
			break;
		case"TO_MAIN_PAGE":
			ResearchMovie mainPage = new ResearchMovie(jF, stateMachine);
			stateMachine.setCurrentPage(mainPage);
			jF.setContentPane(mainPage);
			jF.repaint();
			jF.revalidate();
			break;
		default:
			break;
		}
		
	}
}
