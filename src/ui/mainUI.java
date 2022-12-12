package ui;

import java.awt.HeadlessException;
import java.util.ArrayList;

import javax.swing.JFrame;

public class mainUI {

	private static JFrame jFrame; 
	
	public static void main(String[ ] args) throws HeadlessException {
    	
		
		//Création des différents éléments composant notre JFrame
    	jFrame = new JFrame("AL2000");
    	ChooseMovie fw = new ChooseMovie(jFrame,null);
    	
    	//Initialisation du JFrame
    	jFrame.setSize(900, 700);
    	jFrame.setLocationRelativeTo(null);
    	jFrame.setVisible(true);
    	
    	jFrame.add(fw);
   }
}
