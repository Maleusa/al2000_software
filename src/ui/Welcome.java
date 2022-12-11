package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Welcome extends JPanel	 {
	
	JButton welcomeButton;
	JButton returnButton;
	JPanel panel_two;
	JLabel label;
	ArrayList<String> list = new ArrayList<String>();
	public Welcome(JFrame jF) {
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
					//JOptionPane.showMessageDialog(jF, "Id cb : \nId Abo :");
					
					ChoiceReturn fcf = new ChoiceReturn(jF);
					jF.setContentPane(fcf);
					jF.repaint();
					jF.revalidate();
		        }
			}
   	});
   	
   	welcomeButton.addActionListener(new ActionListener() {
     	 
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (e.getSource().equals(welcomeButton)) {
					JOptionPane.showMessageDialog(jF, "Id cb : \nId Abo :");
					
					Identification fcf = new Identification(jF);
					jF.setContentPane(fcf);
					jF.repaint();
					jF.revalidate();
		        }
			}
   	});
	}
}
