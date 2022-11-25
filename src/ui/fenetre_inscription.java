package ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JFrame;


public class fenetre_inscription extends JPanel{
	String[] s = {"Nom","Prénom", "age", "adresse", "email"};
	JLabel[] tabJLabel = new JLabel[s.length];
	JTextArea[] tabTextArea = new JTextArea[s.length];
	JButton button_valider = new JButton("Valider");
	JButton button_retour = new JButton("Retour");
	JPanel panel = new JPanel();
	JPanel config = new JPanel();
	
	public fenetre_inscription(JFrame jF) {
		
		super.setLayout(new BorderLayout());
		super.add(config, BorderLayout.NORTH);
		config.add(button_retour);
		super.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout((s.length*2)+1,1));
		
		//ajout des JLabel et JTextArea sur "panel"
		//Elements de la liste dans l'ordre : "Nom","Prénom", "age", "adresse", "email"
		for(int i = 0;i<s.length;i++) {
			panel.add(tabJLabel[i] = new JLabel(s[i]));
			panel.add(tabTextArea[i]=new JTextArea());
			
		}
		panel.add(button_valider,BorderLayout.SOUTH);
		
		button_retour.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					fenetre_identification fcf = new fenetre_identification(jF);
					jF.setContentPane(fcf);
					jF.repaint();
					jF.revalidate();				
			}
    	});
	}
	
	
	
	
}
