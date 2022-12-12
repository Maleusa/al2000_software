package ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import ui.stateMachine.Page;
import ui.stateMachine.StateMachine;

import javax.swing.JFrame;

/*PUBLIC_EVENT:
 * SIGN_UP_EVENT_TYPE , data as :String[0]: Subscriber Name 
 *	                              String[1]: Password 
 *	                              String[2]: Adress
 *	                              String[3]: Blocked Genre
 *	                              String[4]: Initial Balance 
 *GUI_EVENT_TYPE :
 *	TO_PREVIOUS_PAGE
 *	TO_WELCOME_PAGE
 */
public class Inscription extends Page{
	String[] s = {"Nom","Mot de passe", "Adresse", "Genre bloqué", "Solde"};
	JLabel[] tabJLabel = new JLabel[s.length];
	JTextArea[] tabTextArea = new JTextArea[s.length];
	JButton button_validate = new JButton("Valider");
	JButton button_back = new JButton("Retour");
	JPanel panel = new JPanel();
	JPanel config = new JPanel();
	StateMachine stateMachine;
	JFrame jF;
	
	public Inscription(final JFrame jFrame, StateMachine stateM) {
		this.stateMachine=stateM;
		this.jF=jFrame;
		super.setLayout(new BorderLayout());
		super.add(config, BorderLayout.NORTH);
		config.add(button_back);
		super.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout((s.length*2)+1,1));
		
		//ajout des JLabel et JTextArea sur "panel"
		//Elements de la liste dans l'ordre : "Nom","PrÃ©nom", "age", "adresse", "email"
		for(int i = 0;i<s.length;i++) {
			panel.add(tabJLabel[i] = new JLabel(s[i]));
			panel.add(tabTextArea[i]=new JTextArea());
			
		}
		panel.add(button_validate,BorderLayout.SOUTH);
		
		button_validate.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> data = new ArrayList<String>();
            	data.add(stateMachine.getCbID());
            	for(JTextArea info : tabTextArea) data.add(info.getText());
            	stateMachine.notifyObserver("SIGN_UP_EVENT_TYPE", data);
            	stateMachine.changeState("TO_WELCOME_PAGE");
			}
		});
		
		button_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Page previous = stateMachine.returnToPrevious();
				jF.setContentPane(previous);
				jF.repaint();
				jF.revalidate();				
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
		}
		
	}
	
	
	
	
}
