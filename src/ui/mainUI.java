package ui;

import java.awt.HeadlessException;
import javax.swing.JFrame;

public class mainUI {

	private static JFrame jFrame; 
	
	public static void main(String[ ] args) throws HeadlessException {
    	
		
		//Création des différents éléments composant notre JFrame
    	jFrame = new JFrame("AL2000");
    	Welcome fw = new Welcome(jFrame);
    	
    	//Initialisation du JFrame
    	jFrame.setSize(1100, 900);
    	jFrame.setLocationRelativeTo(null);
    	jFrame.setVisible(true);
    	
    	jFrame.add(fw);
   }
}