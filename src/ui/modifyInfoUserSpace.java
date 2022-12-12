package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class modifyInfoUserSpace extends JPanel{
	
	JLabel labelType;
	JTextArea jTAmodif;
	
	public modifyInfoUserSpace(String type) {
		jTAmodif = new JTextArea();
		labelType = new JLabel(type);
		this.setLayout(new BorderLayout());
		this.add(labelType,BorderLayout.NORTH);
		this.add(jTAmodif,BorderLayout.CENTER);
		
	}

	public String getjTAmodif() {
		return jTAmodif.getText();
	}

	public void setjTAmodif(String jTAmodif) {
		this.jTAmodif.setText(jTAmodif);
	}
	
	
}