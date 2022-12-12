package ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import ui.stateMachine.Page;
import ui.stateMachine.StateMachine;

/*
 * PUBLIC_EVENT:
 * 	RETURN_DAMAGED_EVENT_TYPE , data as : String[0] BluRay id
 *	RETURN_CORRECT_EVENT_TYPE , data as : String[0] BluRay id
 *
 * GUI_EVENT:
 * 	TO_WELCOME_PAGE
 */
public class ChoiceReturn extends Page{
	
	StateMachine stateMachine;
	JFrame jF;
	public ChoiceReturn(final JFrame jFrame, StateMachine stateM) {
		this.jF=jFrame;
		this.stateMachine=stateM;
		final JButton ReturnMovie;
		final JButton ReturnDamagedMovie;
		JPanel northPanel = new JPanel();
		JPanel centerPanel = new JPanel();
		JLabel label = new JLabel("Merci pour votre confiance.");
		Font font = new Font("TimesRoman", Font.BOLD, 18);
		label.setFont(font);
		
		
		this.setLayout(new BorderLayout());
		this.add(northPanel,BorderLayout.NORTH);
		this.add(centerPanel,BorderLayout.CENTER);
		northPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		northPanel.add(label);
		centerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		centerPanel.add(ReturnDamagedMovie = new JButton("Return Damaged Movie"));
		centerPanel.add(ReturnMovie = new JButton("Return Movie"));
		
		ReturnMovie.addActionListener(new ActionListener() {
       	 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(ReturnMovie)) {
					JOptionPane.showMessageDialog(jF, "Merci d'insérer votre BluRay.");
					stateMachine.notifyObserver("RETURN_CORRECT_EVENT_TYPE", new ArrayList<String>());
					stateMachine.changeState("TO_WELCOME_PAGE");
		        }
			}
  	});
		
		ReturnDamagedMovie.addActionListener(new ActionListener() {
	       	 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(ReturnDamagedMovie)) {
					JOptionPane.showMessageDialog(jF, "Merci d'insérer votre BluRay.");
					stateMachine.notifyObserver("RETURN_DAMAGED_EVENT_TYPE", new ArrayList<String>());
					stateMachine.changeState("TO_WELCOME_PAGE");
		        }
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
		case "TO_WELCOME_PAGE":
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
