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
	String[] s = {"Nom","Prénom", "age", "adresse"};
	JLabel nom,prenom,age,adresse;
	JTextArea jT_nom,jT_prenom,jT_age,jT_adresse;
	JButton button = new JButton("Valider");
	JButton button_retour = new JButton("Retour");
	JPanel panel = new JPanel();
	JPanel config = new JPanel();
	
	public fenetre_inscription(JFrame jF) {
		
		super.setLayout(new BorderLayout());
		super.add(config, BorderLayout.NORTH);
		config.add(button_retour);
		super.add(panel, BorderLayout.CENTER);
		panel.setLayout(new GridLayout((s.length*2)+1,1));
		panel.add(nom = new JLabel("nom"));
		panel.add(jT_nom = new JTextArea());
		panel.add(prenom = new JLabel("prénom"));
		panel.add(jT_prenom = new JTextArea());
		panel.add(age = new JLabel("age"));
		panel.add(jT_age = new JTextArea());
		panel.add(adresse = new JLabel("adresse"));
		panel.add(jT_adresse = new JTextArea());
		panel.add(button);
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
