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


public class Inscription extends JPanel{
	String[] s = {"Nom","Prénom", "age", "adresse", "email"};
	JLabel[] tabJLabel = new JLabel[s.length];
	JTextArea[] tabTextArea = new JTextArea[s.length];
	JButton button_validate = new JButton("Valider");
	JButton button_back = new JButton("Retour");
	JPanel panel = new JPanel();
	JPanel config = new JPanel();
	
	public Inscription(JFrame jF) {
		
		super.setLayout(new BorderLayout());
		super.add(config, BorderLayout.NORTH);
		config.add(button_back);
		super.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout((s.length*2)+1,1));
		
		//ajout des JLabel et JTextArea sur "panel"
		//Elements de la liste dans l'ordre : "Nom","Prénom", "age", "adresse", "email"
		for(int i = 0;i<s.length;i++) {
			panel.add(tabJLabel[i] = new JLabel(s[i]));
			panel.add(tabTextArea[i]=new JTextArea());
			
		}
		panel.add(button_validate,BorderLayout.SOUTH);
		
		button_back.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					Identification fcf = new Identification(jF);
					jF.setContentPane(fcf);
					jF.repaint();
					jF.revalidate();				
			}
    	});
	}
	
	
	
	
}
