package ui;

import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class HistoricPanel extends JPanel{

		public HistoricPanel(String historic) {
			
			Font font = new Font("TimesRoman", Font.BOLD, 18);
			JTextArea jTANomFilm;  
			this.add(jTANomFilm = new JTextArea(historic));
			jTANomFilm.setFont(font);
		}
}