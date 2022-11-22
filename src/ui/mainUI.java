package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class mainUI {

	private static JFrame jFrame; 
	
	public static void main(String[ ] args) throws HeadlessException {
    	
		
		//Création des différents éléments composant notre JFrame
    	jFrame = new JFrame("AL2000");
    	fenetre_welcome fw = new fenetre_welcome(jFrame);
    	
    	//Initialisation du JFrame
    	jFrame.setSize(1100, 900);
    	jFrame.setLocationRelativeTo(null);
    	jFrame.setVisible(true);
    	
    	jFrame.add(fw);
   }
}
