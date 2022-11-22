package ui;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;


public class fenetre_identification extends JPanel{
	
	JLabel label_sub = new JLabel("Connectez vous en insérant votre carte ou appuyez sur le bouton SUB");
	JLabel label_louer = new JLabel("Tu peux aussi louer ton film sans t'abonner en cliquand sur 'Louer'");
	JButton button_subscribed = new JButton("Sub");
	JButton button_louer = new JButton("Louer");
	JPanel panel = new JPanel();
	JFrame jF;
	
	public fenetre_identification(JFrame j) {
		super.setLayout(new BorderLayout());
		super.setBackground(Color.BLACK);
		
		panel.setBackground(Color.BLACK);
		panel.setLayout(new GridLayout(2,1));
		label_sub.setFont(new Font("Verdana", Font.PLAIN, 28));
		label_louer.setFont(new Font("Verdana", Font.PLAIN, 28));
		label_louer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		label_sub.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		label_sub.setForeground(Color.white);
		label_louer.setForeground(Color.WHITE);
		
		super.add(button_louer,BorderLayout.SOUTH);
		super.add(panel, BorderLayout.NORTH);
		super.add(label_louer, BorderLayout.CENTER);
		
		panel.add(label_sub);
		panel.add(button_subscribed);
		
		jF=j;
		
		button_louer.addActionListener(new ActionListener(){
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				
					fenetre_choix_film fcf = new fenetre_choix_film();
					jF.setContentPane(fcf);
					jF.repaint();
					jF.revalidate();
		    

				
			}
    	});
		
		button_subscribed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					fenetre_inscription fcf = new fenetre_inscription(jF);
					jF.setContentPane(fcf);
					jF.repaint();
					jF.revalidate();				
			}
    	});
		
	}
}
