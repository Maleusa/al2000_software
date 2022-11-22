package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class fenetre_welcome extends JPanel	 {
	
	JButton welcome;
	JPanel panel_two;
	JLabel label;

	public fenetre_welcome(JFrame jF) {
		welcome = new JButton("Welcome");
		label = new JLabel("AL2000 REVOLUTION");
		panel_two = new JPanel();
		
		//Initialisation des différents élements composants notre fenêtre
    	super.setLayout(new BorderLayout());
    	super.setBackground(Color.black);
    	label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER); 
    	label.setFont(new Font("Verdana", Font.PLAIN, 42));
    	label.setForeground(Color.white);
    	
    	//welcome.setPreferredSize(new Dimension(jF.getHeight(),300));
    	panel_two.add(welcome);
    	
    	super.add(label);
    	super.add(panel_two,BorderLayout.SOUTH);
    	
    	
    	welcome.addActionListener(new ActionListener() {
      	  
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(welcome)) {
					fenetre_identification fcf = new fenetre_identification(jF);
					jF.setContentPane(fcf);
					jF.repaint();
					jF.revalidate();
		        }

				
			}
    	});
	}
}
