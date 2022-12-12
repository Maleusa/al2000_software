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


public class Identification extends JPanel{
	
	JLabel label_sub = new JLabel("Connect by inserting your card or press the SUB button");
	JLabel label_rent = new JLabel("You can also rent your film without subscribing by clicking on 'Rent''");
	JButton button_subscribed = new JButton("Sub");
	JButton button_rent = new JButton("Louer");
	JPanel panel = new JPanel();
	JFrame jF;
	
	public Identification(JFrame j) {
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
				
				
					ResearchMovie frf = new ResearchMovie();
					jF.setContentPane(frf);
					jF.repaint();
					jF.revalidate();
		    

				
			}
    	});
		
		button_subscribed.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
					Inscription fcf = new Inscription(jF);
					jF.setContentPane(fcf);
					jF.repaint();
					jF.revalidate();				
			}
    	});
		
	}
}
